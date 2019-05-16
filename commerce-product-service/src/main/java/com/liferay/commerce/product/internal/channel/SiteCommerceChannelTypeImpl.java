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

package com.liferay.commerce.product.internal.channel;

import com.liferay.commerce.product.channel.CommerceChannelType;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.UnicodeProperties;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = {
		"commerce.product.channel.type.key=" + SiteCommerceChannelTypeImpl.KEY,
		"commerce.product.channel.type.order:Integer=30"
	},
	service = CommerceChannelType.class
)
public class SiteCommerceChannelTypeImpl implements CommerceChannelType {

	public static final String KEY = "site";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, KEY);
	}

	@Override
	public UnicodeProperties getTypeSettingsProperties(
		HttpServletRequest httpServletRequest) {

		UnicodeProperties typeSettingsProperties = new UnicodeProperties(true);

		long[] groupIds = ParamUtil.getLongValues(
			httpServletRequest,
			"CommerceChannelSitesSearchContainerPrimaryKeys");

		typeSettingsProperties.put("groupIds", StringUtil.merge(groupIds));

		return typeSettingsProperties;
	}

	@Override
	public void update(
			CommerceChannel commerceChannel,
			HttpServletRequest httpServletRequest)
		throws PortalException {
	}

}