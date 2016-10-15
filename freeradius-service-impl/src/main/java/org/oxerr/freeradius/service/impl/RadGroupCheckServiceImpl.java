package org.oxerr.freeradius.service.impl;

import org.oxerr.freeradius.domain.QRadGroupCheck;
import org.oxerr.freeradius.domain.RadGroupCheck;
import org.oxerr.freeradius.repository.RadGroupCheckRepository;
import org.oxerr.freeradius.service.RadGroupCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysema.query.types.expr.BooleanExpression;

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
	public RadGroupCheck getRadGroupCheck(String groupName, String attribute) {
		return radGroupCheckRepository.findOne(
			BooleanExpression.allOf(
				qRadGroupCheck.groupName.eq(groupName),
				qRadGroupCheck.attribute.eq(attribute)
			)
		);
	}

}
