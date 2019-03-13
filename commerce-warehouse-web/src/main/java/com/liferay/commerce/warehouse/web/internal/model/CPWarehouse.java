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

package com.liferay.commerce.warehouse.web.internal.model;

import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.product.catalog.CPCatalogEntry;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringBundler;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Alec Sloan
 */
public class CPWarehouse implements Comparable<CPWarehouse> {

    public CPWarehouse(
            CommerceWarehouse commerceWarehouse, CPCatalogEntry cpCatalogEntry,
            String price, String discountPrice, Double[] coordinates,
            Map<String, Serializable> attributes)
        throws  PortalException {

        _address = setAddress(commerceWarehouse, attributes);
        _directionsUrl = setDirectionsUrl(commerceWarehouse, coordinates);
        _hoursOfOperation = setHoursOfOperation(attributes);
        _name = commerceWarehouse.getName();
        _phoneNumber = setPhoneNumber(attributes);
        _price = price;
        _discountPrice = discountPrice;
        _warehouseId = commerceWarehouse.getCommerceWarehouseId();
        _instanceId = cpCatalogEntry.getCPSkus().get(0).getCPInstanceId();
    }

    @Override
    public int compareTo(CPWarehouse cpWarehouse) {
        return Double.compare(Double.valueOf(getDiscountPrice().substring(1)), Double.valueOf(cpWarehouse.getDiscountPrice().substring(1)));
    }

    public String getAddress() {
        return _address;
    }

    public String getDirectionsUrl() {
        return _directionsUrl;
    }

    public String getDiscountPrice() {
        return _discountPrice;
    }

    public String getHoursOfOperation() {
        return _hoursOfOperation;
    }

    public long getInstanceId() {
        return _instanceId;
    }

    public String getName() {
        return _name;
    }

    public String getPhoneNumber() {
        return _phoneNumber;
    }

    public String getPrice() {
        return _price;
    }

    public long getWarehouseId() {
        return _warehouseId;
    }

    protected String setAddress(
            CommerceWarehouse commerceWarehouse,
            Map<String, Serializable> attributes)
        throws PortalException {
        StringBundler sb = new StringBundler(7);

        sb.append(commerceWarehouse.getStreet1());
        sb.append(StringPool.NEW_LINE);
        sb.append(commerceWarehouse.getCity());
        sb.append(StringPool.COMMA_AND_SPACE);


        CommerceRegion commerceRegion =
            commerceWarehouse.getCommerceRegion();

        if (commerceRegion != null) {
            sb.append(commerceRegion.getCode());
        }

        sb.append(StringPool.SPACE);
        sb.append(commerceWarehouse.getZip());

        return sb.toString();
    }

    protected String setDirectionsUrl(
        CommerceWarehouse commerceWarehouse, Double[] coordinates) {

        return StringBundler.concat(
            "https://www.google.com/maps/dir/",
            String.valueOf(coordinates[0]), StringPool.COMMA,
            String.valueOf(coordinates[1]), StringPool.FORWARD_SLASH,
            String.valueOf(commerceWarehouse.getLatitude()), StringPool.COMMA,
            String.valueOf(commerceWarehouse.getLongitude()));
    }

    protected String setHoursOfOperation(Map<String, Serializable> attributes) {
        return String.valueOf(attributes.get("hoursOfOperation"));
    }

    protected String setPhoneNumber(Map<String, Serializable> attributes) {
        return String.valueOf(attributes.get("phoneNumber"));
    }

    private final String _address;
    private final String _directionsUrl;
    private final String _discountPrice;
    private final String _hoursOfOperation;
    private final long _instanceId;
    private final String _name;
    private final String _phoneNumber;
    private final String _price;
    private final long _warehouseId;

}