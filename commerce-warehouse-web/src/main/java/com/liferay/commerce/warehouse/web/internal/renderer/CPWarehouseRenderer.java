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

package com.liferay.commerce.warehouse.web.internal.renderer;

import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.content.render.CPContentRenderer;
import com.liferay.commerce.service.CommerceCountryService;
import com.liferay.commerce.service.CommerceWarehouseService;
import com.liferay.commerce.warehouse.web.internal.display.context.CommerceWarehousesDisplayContext;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
    immediate = true,
    property = {
        "commerce.product.content.renderer.key=" + CPWarehouseRenderer.KEY,
        "commerce.product.content.renderer.type=simple"
    },
    service = CPContentRenderer.class
)
public class CPWarehouseRenderer implements CPContentRenderer {

    public static final String KEY = "product-by-warehouse";

    @Override
    public String getKey() {
        return KEY;
    }

    @Override
    public String getLabel(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
            "content.Language", locale, getClass());

        return LanguageUtil.get(resourceBundle, KEY);
    }

    @Override
    public void render(
        CPCatalogEntry cpCatalogEntry,
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
        throws Exception {

        setCommerceWarehousesDisplayContext(httpServletRequest);

        _jspRenderer.renderJSP(
            _servletContext, httpServletRequest, httpServletResponse,
            "/warehouse/renderer/view.jsp");
    }

    protected void setCommerceWarehousesDisplayContext(
        HttpServletRequest httpServletRequest) {

        CommerceWarehousesDisplayContext commerceWarehousesDisplayContext =
            new CommerceWarehousesDisplayContext(
                _commerceCountryService, _commerceWarehouseService,
                httpServletRequest, _portletResourcePermission);

        httpServletRequest.setAttribute(
            WebKeys.PORTLET_DISPLAY_CONTEXT, commerceWarehousesDisplayContext);
    }

    @Reference
    CommerceCountryService _commerceCountryService;

    @Reference
    CommerceWarehouseService _commerceWarehouseService;

    @Reference(
        target = "(resource.name=" + CommerceConstants.RESOURCE_NAME + ")"
    )
    PortletResourcePermission _portletResourcePermission;

    @Reference
    private JSPRenderer _jspRenderer;

    @Reference(
        target = "(osgi.web.symbolicname=com.liferay.commerce.warehouse.web)"
    )
    private ServletContext _servletContext;

}