package org.oxerr.freeradius.service.impl;

import java.util.Optional;

import org.oxerr.freeradius.domain.QRadCheck;
import org.oxerr.freeradius.domain.RadCheck;
import org.oxerr.freeradius.repository.RadCheckRepository;
import org.oxerr.freeradius.service.RadCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.types.expr.BooleanExpression;

@Service
public class RadCheckServiceImpl implements RadCheckService {

	private static final String DEFAULT_OP = "==";
	private static final String ATTRIBUTE_PASSWORD = "Password";

	private final QRadCheck qRadCheck = QRadCheck.radCheck;
	private final RadCheckRepository radCheckRepository;

	@Autowired
	public RadCheckServiceImpl(RadCheckRepository radCheckRepository) {
		this.radCheckRepository = radCheckRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RadCheck getRadCheck(String userName, String attribute) {
		return radCheckRepository.findOne(
			BooleanExpression.allOf(
				qRadCheck.userName.eq(userName),
				qRadCheck.attribute.eq(attribute)
			)
		);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RadCheck saveRadCheck(RadCheck radCheck) {
		return radCheckRepository.save(radCheck);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RadCheck saveAttribute(String userName, String attribute, String op,
			String value) {
		RadCheck radCheck = getRadCheck(userName, attribute);

		if (value == null) {
			if (radCheck != null) {
				radCheckRepository.delete(radCheck);
				radCheck = null;
			}
		} else {
			if (radCheck == null) {
				radCheck = new RadCheck(userName, attribute,
					Optional.ofNullable(op).orElse(DEFAULT_OP), value);
			} else {
				if (op != null) {
					radCheck.setOp(op);
				}
				radCheck.setValue(value);
			}

			radCheck = radCheckRepository.save(radCheck);
		}

		return radCheck;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RadCheck savePassword(String userName, String password) {
		if (password == null) {
			throw new IllegalArgumentException("Password is null.");
		}
		return saveAttribute(userName, ATTRIBUTE_PASSWORD, null, password);
	}

}
