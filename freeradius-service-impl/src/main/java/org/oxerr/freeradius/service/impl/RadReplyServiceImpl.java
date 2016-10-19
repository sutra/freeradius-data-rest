package org.oxerr.freeradius.service.impl;

import org.oxerr.freeradius.domain.QRadReply;
import org.oxerr.freeradius.domain.RadReply;
import org.oxerr.freeradius.repository.RadReplyRepository;
import org.oxerr.freeradius.service.RadReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RadReplyServiceImpl implements RadReplyService {

	private final QRadReply qRadReply = QRadReply.radReply;
	private final RadReplyRepository radReplyRepository;

	@Autowired
	public RadReplyServiceImpl(RadReplyRepository radReplyRepository) {
		this.radReplyRepository = radReplyRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Iterable<RadReply> getRadReplies(String userName) {
		return radReplyRepository.findAll(qRadReply.userName.eq(userName));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void deleteRadReplies(String userName) {
		radReplyRepository.delete(getRadReplies(userName));
	}

}
