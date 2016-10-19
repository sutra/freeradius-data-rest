package org.oxerr.freeradius.service;

import javax.annotation.Nonnull;

import org.oxerr.freeradius.domain.RadCheck;

public interface CheckService {

	Long getMinLongAttribute(@Nonnull String userName, @Nonnull String attribute);

	RadCheck saveUser(@Nonnull String userName, @Nonnull String password);

	void deleteUser(@Nonnull String userName);

}
