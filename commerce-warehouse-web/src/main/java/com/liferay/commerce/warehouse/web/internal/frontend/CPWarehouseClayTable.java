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

package com.liferay.commerce.warehouse.web.internal.frontend;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.context.CommerceContextFactory;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.util.CommercePriceFormatter;
import com.liferay.commerce.frontend.ClayTable;
import com.liferay.commerce.frontend.ClayTableAction;
import com.liferay.commerce.frontend.ClayTableActionProvider;
import com.liferay.commerce.frontend.ClayTableSchema;
import com.liferay.commerce.frontend.ClayTableSchemaBuilder;
import com.liferay.commerce.frontend.ClayTableSchemaBuilderFactory;
import com.liferay.commerce.frontend.ClayTableSchemaField;
import com.liferay.commerce.frontend.CommerceDataSetDataProvider;
import com.liferay.commerce.frontend.Filter;
import com.liferay.commerce.frontend.Pagination;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.service.CommercePriceEntryLocalService;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.commerce.product.catalog.CPSku;
import com.liferay.commerce.product.content.constants.CPContentWebKeys;
import com.liferay.commerce.product.content.util.CPContentHelper;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceWarehouseLocalService;
import com.liferay.commerce.warehouse.web.internal.model.CPWarehouse;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.HttpUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
    immediate = true,
    property = {
        "commerce.data.provider.key=" + CPWarehouseClayTable.NAME,
        "commerce.table.name=" + CPWarehouseClayTable.NAME
    },
    service = {
        ClayTable.class, ClayTableActionProvider.class,
        CommerceDataSetDataProvider.class
    }
)
public class CPWarehouseClayTable
    implements CommerceDataSetDataProvider<CPWarehouse>, ClayTable,
    ClayTableActionProvider {

    public static final String NAME = "cpWarehouses";

    @Override
    public List<ClayTableAction> clayTableActions(
        HttpServletRequest httpServletRequest, long groupId, Object model)
        throws PortalException {

        return new ArrayList<>();
    }

    @Override
    public int countItems(HttpServletRequest httpServletRequest, Filter filter)
        throws PortalException {

        ThemeDisplay themeDisplay =
            (ThemeDisplay)httpServletRequest.getAttribute(
                WebKeys.THEME_DISPLAY);

        CommerceCountry commerceCountry =
            _commerceCountryLocalService.getCommerceCountry(
                themeDisplay.getScopeGroupId(), "US");

        return _commerceWarehouseLocalService.getCommerceWarehousesCount(
            themeDisplay.getSiteGroupId(), true,
            commerceCountry.getCommerceCountryId());
    }

    @Override
    public ClayTableSchema getClayTableSchema() {
        ClayTableSchemaBuilder clayTableSchemaBuilder =
            _clayTableSchemaBuilderFactory.clayTableSchemaBuilder();

        ClayTableSchemaField vendorField = clayTableSchemaBuilder.addField(
            "name", "vendor");

        vendorField.setContentRenderer("contactDetail");

        clayTableSchemaBuilder.addField("hoursOfOperation", "hours of operation");

        ClayTableSchemaField priceField = clayTableSchemaBuilder.addField(
            "price", "price");

        priceField.setContentRenderer("price");

        ClayTableSchemaField directionsField = clayTableSchemaBuilder.addField(
            "directionsUrl", "directions");

        directionsField.setContentRenderer("directionsButton");

        clayTableSchemaBuilder.addProperties("price", "sortable");

        ClayTableSchemaField addToCartField = clayTableSchemaBuilder.addField(
            "addToCart", "");

        addToCartField.setContentRenderer("addToCartButton");

        return clayTableSchemaBuilder.build();
    }

    @Override
    public String getId() {
        return NAME;
    }

    @Override
    public List<CPWarehouse> getItems(
        HttpServletRequest httpServletRequest, Filter filter,
        Pagination pagination, Sort sort)
        throws PortalException {

        ThemeDisplay themeDisplay =
            (ThemeDisplay)httpServletRequest.getAttribute(
                WebKeys.THEME_DISPLAY);

        List<CPWarehouse> CPWarehouses = new ArrayList<>();

        CommerceContext commerceContext = _commerceContextFactory.create(
            themeDisplay.getScopeGroupId(),
            _portal.getUserId(httpServletRequest), 0, 0, StringPool.BLANK);

        httpServletRequest = PortalUtil.getOriginalServletRequest(httpServletRequest);

        double latitude = ParamUtil.get(httpServletRequest, "latitude", 32.9345787);
        double longitude = ParamUtil.get(httpServletRequest, "longitude", -117.1124716);

        if (latitude == Double.MAX_VALUE || longitude == Double.MAX_VALUE) {
            try {
                JSONObject jo = JSONFactoryUtil.createJSONObject(HttpUtil.URLtoString("http://ip-api.com/json/" + httpServletRequest.getRemoteAddr()));
                latitude = jo.getDouble("lat");
                longitude = jo.getDouble("lon");
            }
            catch (Exception e){

            }
        }

        List<CommerceWarehouse> commerceWarehouses =
            _commerceWarehouseLocalService.searchCommerceWarehouses(
                themeDisplay.getCompanyId(), themeDisplay.getScopeGroupId(),
                latitude, longitude, pagination.getStartPosition(), pagination.getEndPosition(), sort);

        CPContentHelper cpContentHelper =
            (CPContentHelper)httpServletRequest.getAttribute(
                CPContentWebKeys.CP_CONTENT_HELPER);

        CPCatalogEntry cpCatalogEntry =
            cpContentHelper.getCPCatalogEntry(httpServletRequest);

        for (CommerceWarehouse commerceWarehouse : commerceWarehouses) {

            ExpandoBridge expandoBridge = commerceWarehouse.getExpandoBridge();

            Map<String, Serializable> attributes = expandoBridge.getAttributes();

            long commercePriceListId = (Long)attributes.get("priceListId");

            String price = getPrice(cpCatalogEntry, commerceContext,
                commercePriceListId, "price", themeDisplay.getLocale());

            String promoPrice = getPrice(cpCatalogEntry, commerceContext,
                commercePriceListId, "promo", themeDisplay.getLocale());

            if (!Validator.isBlank(price)) {
                CPWarehouses.add(
                    new CPWarehouse(
                        commerceWarehouse, cpCatalogEntry, price, promoPrice,
                        new Double[]{latitude, longitude}, attributes));
            }
        }

        return ListUtil.sort(CPWarehouses, Comparator.naturalOrder());
    }

    @Override
    public boolean isShowActionsMenu() {
        return false;
    }

    protected String getPrice(
            CPCatalogEntry cpCatalogEntry, CommerceContext commerceContext,
            long commercePriceListId, String type, Locale locale)
        throws PortalException {

        CommerceCurrency commerceCurrency =
            commerceContext.getCommerceCurrency();

        List<CPSku> cpSkus = cpCatalogEntry.getCPSkus();

        CPInstance cpInstance =
            _cpInstanceLocalService.fetchCPInstance(cpSkus.get(0).getCPInstanceId());

        CommercePriceEntry commercePriceEntry =
            _commercePriceEntryLocalService.fetchCommercePriceEntry(
                commercePriceListId, cpInstance.getCPInstanceUuid());

        if (commercePriceEntry == null) {
            return StringPool.BLANK;
        }

        CommerceMoney commerceMoney = commercePriceEntry.getPriceMoney(
            commerceCurrency.getCommerceCurrencyId());

        if (type.equals("promo")) {
            commerceMoney = commercePriceEntry.getPromoPriceMoney(
                commerceCurrency.getCommerceCurrencyId());
        }

        return _commercePriceFormatter.format(
            commerceCurrency, commerceMoney.getPrice(), locale);
    }

    @Reference
    private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

    @Reference
    private CommerceContextFactory _commerceContextFactory;

    @Reference
    private CommerceCountryLocalService _commerceCountryLocalService;

    @Reference
    private CommercePriceEntryLocalService _commercePriceEntryLocalService;

    @Reference
    private CommercePriceFormatter _commercePriceFormatter;


    @Reference
    private CommerceWarehouseLocalService _commerceWarehouseLocalService;

    @Reference
    private CPInstanceLocalService _cpInstanceLocalService;

    @Reference
    private Portal _portal;

}