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

package com.liferay.commerce.media;

import com.liferay.commerce.constants.CommerceMediaConstants;
import com.liferay.commerce.product.model.CPAttachmentFileEntry;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPAttachmentFileEntryLocalService;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CProductLocalService;
import com.liferay.document.library.kernel.service.DLAppLocalService;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.servlet.ServletResponseUtil;
import com.liferay.portal.kernel.util.File;
import com.liferay.portal.kernel.util.Html;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.URLCodec;

import java.io.IOException;

import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(service = CommerceMediaResolver.class)
public class DLCommerceMediaResolver implements CommerceMediaResolver {

	@Override
	public String getDownloadUrl(
			long cpAttachmentFileEntryId, long cpDefinitionId)
		throws PortalException {

		return getUrl(cpAttachmentFileEntryId, cpDefinitionId, true, false);
	}

	@Override
	public byte[] getMediaBytes(HttpServletRequest httpServletRequest)
		throws IOException, PortalException {

		FileEntry fileEntry = getFileEntry(httpServletRequest);

		return _file.getBytes(fileEntry.getContentStream());
	}

	@Override
	public String getThumbnailUrl(
			long cpAttachmentFileEntryId, long cpDefinitionId)
		throws PortalException {

		return getUrl(cpAttachmentFileEntryId, cpDefinitionId, false, true);
	}

	@Override
	public String getUrl(long cpAttachmentFileEntryId, long cpDefinitionId)
		throws PortalException {

		return getUrl(cpAttachmentFileEntryId, cpDefinitionId, false, false);
	}

	@Override
	public String getUrl(
			long cpAttachmentFileEntryId, long cpDefinitionId, boolean download,
			boolean thumbnail)
		throws PortalException {

		CPDefinition cpDefinition = _cpDefinitionLocalService.fetchCPDefinition(
			cpDefinitionId);

		CPAttachmentFileEntry cpAttachmentFileEntry =
			_cpAttachmentFileEntryLocalService.getCPAttachmentFileEntry(
				cpAttachmentFileEntryId);

		if (cpAttachmentFileEntry == null) {
			return StringPool.BLANK;
		}

		StringBundler sb = new StringBundler(13);

		sb.append(_portal.getPathModule());
		sb.append(StringPool.SLASH);
		sb.append(CommerceMediaConstants.SERVLET_PATH);
		sb.append("/products/");
		sb.append(cpDefinition.getCProductId());
		sb.append(StringPool.SLASH);

		Map<Locale, String> productFriendlyURLMap =
			_cpDefinitionLocalService.getUrlTitleMap(cpDefinitionId);

		Locale siteDefaultLocale = _portal.getSiteDefaultLocale(
			cpAttachmentFileEntry.getGroupId());

		sb.append(productFriendlyURLMap.get(siteDefaultLocale));

		sb.append(StringPool.SLASH);
		sb.append(cpAttachmentFileEntry.getFileEntryId());
		sb.append(StringPool.SLASH);
		sb.append(
			URLCodec.encodeURL(
				_html.unescape(
					cpAttachmentFileEntry.getTitle(siteDefaultLocale)), true));

		sb.append("?download=");
		sb.append(download);

		return _html.escape(sb.toString());
	}

	@Override
	public void sendMediaBytes(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException, PortalException {

		sendMediaBytes(httpServletRequest, httpServletResponse, null);
	}

	@Override
	public void sendMediaBytes(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, String contentDisposition)
		throws IOException, PortalException {

		String path = _http.fixPath(httpServletRequest.getPathInfo());

		String[] pathArray = StringUtil.split(path, CharPool.SLASH);

		if (pathArray.length >= 2) {
			try {
				CProduct cProduct = _cProductLocalService.fetchCProduct(
					Long.valueOf(pathArray[1]));

				cpDefinitionModelResourcePermission.contains(
					PermissionThreadLocal.getPermissionChecker(),
					cProduct.getPublishedCPDefinitionId(), ActionKeys.VIEW);

				FileEntry fileEntry = getFileEntry(httpServletRequest);

				ServletResponseUtil.sendFile(
					httpServletRequest, httpServletResponse,
					fileEntry.getFileName(), getMediaBytes(httpServletRequest),
					null, contentDisposition);
			}
			catch (Exception e) {
				e.printStackTrace();
				httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
			}
		}
	}

	protected FileEntry getFileEntry(HttpServletRequest httpServletRequest)
		throws PortalException {

		String path = _http.fixPath(httpServletRequest.getPathInfo());

		String[] pathArray = StringUtil.split(path, CharPool.SLASH);

		if (pathArray.length >= 4) {
			return _dlAppLocalService.getFileEntry(Long.valueOf(pathArray[3]));
		}

		return null;
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.product.model.CPDefinition)"
	)
	protected ModelResourcePermission<CPDefinition>
		cpDefinitionModelResourcePermission;

	@Reference
	private CPAttachmentFileEntryLocalService
		_cpAttachmentFileEntryLocalService;

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private CProductLocalService _cProductLocalService;

	@Reference
	private DLAppLocalService _dlAppLocalService;

	@Reference
	private File _file;

	@Reference
	private Html _html;

	@Reference
	private Http _http;

	@Reference
	private Portal _portal;

}