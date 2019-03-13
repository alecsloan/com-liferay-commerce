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

package com.liferay.commerce.warehouse.web.internal.portlet;

import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceWarehouseService;
import com.liferay.commerce.warehouse.web.internal.display.context.CommerceWarehousesDisplayContext;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
    immediate = true,
    property = {
        "com.liferay.portlet.display-category=commerce",
        "com.liferay.portlet.layout-cacheable=true",
        "com.liferay.portlet.preferences-owned-by-group=true",
        "com.liferay.portlet.preferences-unique-per-layout=false",
        "com.liferay.portlet.private-request-attributes=false",
        "com.liferay.portlet.private-session-attributes=false",
        "com.liferay.portlet.render-weight=50",
        "com.liferay.portlet.scopeable=true",
        "javax.portlet.display-name=Commerce Warehouse Search",
        "javax.portlet.expiration-cache=0",
        "javax.portlet.init-param.view-template=/search.jsp",
        "javax.portlet.name=com_liferay_commerce_warehouse_web_internal_portlet_CommerceWarehouseSearchPortlet",
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user",
        "javax.portlet.supports.mime-type=text/html"
    },
    service = {CommerceWarehouseSearchPortlet.class, Portlet.class}
)
public class CommerceWarehouseSearchPortlet extends MVCPortlet {

    @Override
    public void render(
        RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        HttpServletRequest httpServletRequest = _portal.getHttpServletRequest(
            renderRequest);

        CommerceWarehousesDisplayContext commerceWarehousesDisplayContext =
            new CommerceWarehousesDisplayContext(
                _commerceCountryService, _commerceWarehouseService,
                httpServletRequest, _portletResourcePermission);

        renderRequest.setAttribute(
            WebKeys.PORTLET_DISPLAY_CONTEXT,
            commerceWarehousesDisplayContext);

        super.render(renderRequest, renderResponse);
    }

    @Reference
    CommerceCountryService _commerceCountryService;

    @Reference
    CommerceWarehouseService _commerceWarehouseService;

    @Reference
    Portal _portal;

    @Reference(
        target = "(resource.name=" + CommerceConstants.RESOURCE_NAME + ")"
    )
    PortletResourcePermission _portletResourcePermission;

}