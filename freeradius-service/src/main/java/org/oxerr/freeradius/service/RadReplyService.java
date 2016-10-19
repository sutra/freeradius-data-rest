package org.oxerr.freeradius.service;

import javax.annotation.Nonnull;

import org.oxerr.freeradius.domain.RadReply;

public interface RadReplyService {

	Iterable<RadReply> getRadReplies(@Nonnull String userName);

	void deleteRadReplies(@Nonnull String userName);

}
