/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.media.impl;

import com.liferay.commerce.media.CommerceMediaDefaultImage;
import com.liferay.commerce.media.constants.CommerceMediaConstants;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactory;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = "commerce.media.default.image.key=" + CatalogCommerceMediaDefaultImage.KEY,
	service = CommerceMediaDefaultImage.class
)
public class CatalogCommerceMediaDefaultImage
	implements CommerceMediaDefaultImage {

	public static final String KEY = "catalog";

	@Override
	public long getConfiguration(long groupId) throws PortalException {
		Settings settings = _settingsFactory.getSettings(
			new GroupServiceSettingsLocator(
				groupId, CommerceMediaConstants.SERVICE_NAME));

		return Long.valueOf(settings.getValue("defaultFileEntryId", "0"));
	}

	@Override
	public void updateConfiguration(String fileEntryId, long groupId)
		throws Exception {

		Settings settings = _settingsFactory.getSettings(
			new GroupServiceSettingsLocator(
				groupId, CommerceMediaConstants.SERVICE_NAME));

		ModifiableSettings modifiableSettings =
			settings.getModifiableSettings();

		modifiableSettings.setValue("defaultFileEntryId", fileEntryId);

		modifiableSettings.store();
	}

	@Reference
	private SettingsFactory _settingsFactory;

}