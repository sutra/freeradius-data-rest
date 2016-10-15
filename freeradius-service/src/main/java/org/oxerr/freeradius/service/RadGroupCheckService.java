package org.oxerr.freeradius.service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.oxerr.freeradius.domain.RadGroupCheck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RadGroupCheckService {

	Page<RadGroupCheck> getRadGroupChecks(@Nonnull String groupName, @Nullable Pageable pageable);

	RadGroupCheck getRadGroupCheck(@Nonnull String groupName, @Nonnull String attribute);

}
