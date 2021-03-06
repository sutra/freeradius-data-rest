package org.oxerr.freeradius.service.impl;

import static org.oxerr.freeradius.domain.RadCheck.DEFAULT_OP;

import java.util.Optional;

import org.oxerr.freeradius.domain.QRadCheck;
import org.oxerr.freeradius.domain.RadCheck;
import org.oxerr.freeradius.repository.RadCheckRepository;
import org.oxerr.freeradius.service.RadCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.ExpressionUtils;

@Service
public class RadCheckServiceImpl implements RadCheckService {

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
			ExpressionUtils.allOf(
				qRadCheck.userName.eq(userName),
				qRadCheck.attribute.eq(attribute)
			)
		);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<RadCheck> getRadChecks(String userName) {
		return radCheckRepository.findAll(qRadCheck.userName.eq(userName));
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
	@Transactional
	public void deleteRadChecks(String userName) {
		radCheckRepository.delete(getRadChecks(userName));
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
