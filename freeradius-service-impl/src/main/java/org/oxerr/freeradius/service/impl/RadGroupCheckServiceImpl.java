package org.oxerr.freeradius.service.impl;

import static org.oxerr.freeradius.domain.RadGroupCheck.DEFAULT_OP;

import java.util.Optional;

import org.oxerr.freeradius.domain.QRadGroupCheck;
import org.oxerr.freeradius.domain.RadGroupCheck;
import org.oxerr.freeradius.repository.RadGroupCheckRepository;
import org.oxerr.freeradius.service.RadGroupCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.ExpressionUtils;

@Service
public class RadGroupCheckServiceImpl implements RadGroupCheckService {

	private final QRadGroupCheck qRadGroupCheck = QRadGroupCheck.radGroupCheck;
	private final RadGroupCheckRepository radGroupCheckRepository;

	@Autowired
	public RadGroupCheckServiceImpl(RadGroupCheckRepository radGroupCheckRepository) {
		this.radGroupCheckRepository = radGroupCheckRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<RadGroupCheck> getRadGroupChecks(
		String groupName,
		Pageable pageable
	) {
		return radGroupCheckRepository
			.findAll(qRadGroupCheck.groupName.eq(groupName), pageable);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RadGroupCheck getRadGroupCheck(String groupName, String attribute) {
		return radGroupCheckRepository.findOne(
			ExpressionUtils.allOf(
				qRadGroupCheck.groupName.eq(groupName),
				qRadGroupCheck.attribute.eq(attribute)
			)
		);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RadGroupCheck saveRadGroupCheck(RadGroupCheck radGroupCheck) {
		return radGroupCheckRepository.save(radGroupCheck);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public RadGroupCheck saveAttribute(String groupName, String attribute,
			String op, String value) {
		RadGroupCheck radGroupCheck = getRadGroupCheck(groupName, attribute);

		if (value == null) {
			if (radGroupCheck != null) {
				radGroupCheckRepository.delete(radGroupCheck);
				radGroupCheck = null;
			}
		} else {
			if (radGroupCheck == null) {
				radGroupCheck = new RadGroupCheck(groupName, attribute,
					Optional.ofNullable(op).orElse(DEFAULT_OP),
					value);
			} else {
				if (op != null) {
					radGroupCheck.setOp(op);
				}
				radGroupCheck.setValue(value);
			}

			radGroupCheck = radGroupCheckRepository.save(radGroupCheck);
		}

		return radGroupCheck;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteAttribute(String groupName, String attribute) {
		RadGroupCheck radGroupCheck = getRadGroupCheck(groupName, attribute);
		if (radGroupCheck != null) {
			radGroupCheckRepository.delete(radGroupCheck);
		}
	}

}
