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

package com.liferay.commerce.warehouse.web.internal.portlet.action;

import com.liferay.commerce.exception.CommerceGeocoderException;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPFriendlyURLEntry;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil;
import com.liferay.commerce.product.service.CPFriendlyURLEntryLocalServiceUtil;
import com.liferay.commerce.product.service.CPInstanceLocalServiceUtil;
import com.liferay.commerce.warehouse.web.internal.GoogleLocationFinder;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.net.URLEncoder;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=com_liferay_commerce_warehouse_web_internal_portlet_CommerceWarehouseSearchPortlet",
		"mvc.command.name=getLocation"
	},
	service = MVCActionCommand.class
)
public class GetLocationMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		try {
			String drugName = ParamUtil.getString(actionRequest,"productName");

			BaseModelSearchResult<CPInstance> cpInstanceSearchResult =
				CPInstanceLocalServiceUtil.searchCPInstances(
					themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(), drugName, WorkflowConstants.STATUS_APPROVED, 0, 1, null);

			CPInstance cpInstance = cpInstanceSearchResult.getBaseModels().get(0);

			CPDefinition cpDefinition = CPDefinitionLocalServiceUtil.getCPDefinition(cpInstance.getCPDefinitionId());

			String latitude = String.valueOf(32.9345787);
			String longitude = String.valueOf(-117.1124716);

			String location =
				ParamUtil.getString(actionRequest, "location");

			if (!Validator.isBlank(location)) {
				double[] coordinates = _googleLocationFinder.getCoordinates(
					location);

				latitude = String.valueOf(coordinates[0]);
				longitude = String.valueOf(coordinates[1]);
			}

			long cProductClassNameId =
				ClassNameLocalServiceUtil.getClassNameId(CProduct.class);

			CPFriendlyURLEntry cpFriendlyURLEntry =
				CPFriendlyURLEntryLocalServiceUtil.fetchCPFriendlyURLEntry(
					themeDisplay.getScopeGroupId(), cProductClassNameId,
					cpDefinition.getCProductId(), themeDisplay.getLanguageId(),
					true);

			LayoutSet layoutSet = themeDisplay.getLayoutSet();

			String groupFriendlyUrl = _portal.getGroupFriendlyURL(
				layoutSet, themeDisplay);

			String redirect =
				groupFriendlyUrl + CPConstants.SEPARATOR_PRODUCT_URL +
					cpFriendlyURLEntry.getUrlTitle();

			redirect = StringBundler.concat(
				redirect, "?drugname=",
				URLEncoder.encode(drugName, "UTF-8"), "&location=",
				URLEncoder.encode(location, "UTF-8"), "&latitude=",
				latitude, "&longitude=", longitude);

			hideDefaultSuccessMessage(actionRequest);

			sendRedirect(actionRequest, actionResponse, redirect);

		}
		catch (Exception e) {
			if (e instanceof PrincipalException
					|| e instanceof CommerceGeocoderException) {

				hideDefaultErrorMessage(actionRequest);
				hideDefaultSuccessMessage(actionRequest);

				SessionErrors.add(actionRequest, e.getClass());

				String redirect = ParamUtil.getString(
					actionRequest, "redirect");

				sendRedirect(actionRequest, actionResponse, redirect);
			}
			else {
				throw e;
			}
		}
	}

	@Reference
	private GoogleLocationFinder _googleLocationFinder;

	@Reference
	private Portal _portal;

}