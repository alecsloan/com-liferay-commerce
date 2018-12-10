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

package com.liferay.commerce.internal.order;

import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.order.CommerceOrderValidator;
import com.liferay.commerce.order.CommerceOrderValidatorResult;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CProduct;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.portal.kernel.exception.PortalException;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true,
	property = {
		"commerce.order.validator.key=" + VersionCommerceOrderValidatorImpl.KEY,
		"commerce.order.validator.priority:Integer=30"
	},
	service = CommerceOrderValidator.class
)
public class VersionCommerceOrderValidatorImpl
	implements CommerceOrderValidator {

	public static final String KEY = "version";

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public CommerceOrderValidatorResult validate(
			CommerceOrderItem commerceOrderItem)
		throws PortalException {

		CPInstance cpInstance = commerceOrderItem.getCPInstance();

		CProduct cProduct = commerceOrderItem.getCProduct();

		if (cpInstance.getCPDefinitionId() !=
				cProduct.getPublishedDefinitionId()) {

			boolean instanceUpdated = _updateInstance(
				commerceOrderItem, cProduct);

			if (instanceUpdated) {
				return new CommerceOrderValidatorResult(
					commerceOrderItem.getCommerceOrderItemId(), false,
					"this-product-will-be-automatically-updated-to-a-newer-version");
			}

			return new CommerceOrderValidatorResult(
				commerceOrderItem.getCommerceOrderItemId(), false,
				"there-is-a-newer-version-of-this-product-available");
		}

		return new CommerceOrderValidatorResult(true);
	}

	@Override
	public CommerceOrderValidatorResult validate(
			CPInstance cpInstance, int quantity)
		throws PortalException {

		return new CommerceOrderValidatorResult(true);
	}

	protected void setCommerceOrderItemLocalService(
		CommerceOrderItemLocalService commerceOrderItemLocalService) {

		_commerceOrderItemLocalService = commerceOrderItemLocalService;
	}

	protected void setCPInstanceLocalService(
		CPInstanceLocalService cpInstanceLocalService) {

		_cpInstanceLocalService = cpInstanceLocalService;
	}

	private boolean _updateInstance(
			CommerceOrderItem commerceOrderItem, CProduct cProduct)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cProduct.getPublishedDefinitionId(), commerceOrderItem.getSku());

		if (cpInstance != null) {
			commerceOrderItem.setCPInstanceId(cpInstance.getCPInstanceId());

			_commerceOrderItemLocalService.updateCommerceOrderItem(
				commerceOrderItem);

			return true;
		}

		return false;
	}

	@Reference
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

}