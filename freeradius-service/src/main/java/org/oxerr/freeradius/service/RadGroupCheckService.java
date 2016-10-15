package org.oxerr.freeradius.service;

import javax.annotation.Nonnull;

import org.oxerr.freeradius.domain.RadGroupCheck;

public interface RadGroupCheckService {

	RadGroupCheck getRadGroupCheck(@Nonnull String groupName, @Nonnull String attribute);

}
