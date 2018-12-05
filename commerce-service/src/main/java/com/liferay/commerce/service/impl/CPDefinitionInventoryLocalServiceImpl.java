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

package com.liferay.commerce.service.impl;

import com.liferay.commerce.model.CPDefinitionInventory;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.service.base.CPDefinitionInventoryLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.spring.extender.service.ServiceReference;

/**
 * @author Alessio Antonio Rendina
 * @author Alec Sloan
 */
public class CPDefinitionInventoryLocalServiceImpl
	extends CPDefinitionInventoryLocalServiceBaseImpl {

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public CPDefinitionInventory addCPDefinitionInventory(
			long cpDefinitionId, String cpDefinitionInventoryEngine,
			String lowStockActivity, boolean displayAvailability,
			boolean displayStockQuantity, int minStockQuantity,
			boolean backOrders, int minOrderQuantity, int maxOrderQuantity,
			String allowedOrderQuantities, int multipleOrderQuantity,
			ServiceContext serviceContext)
		throws PortalException {

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		return cpDefinitionInventoryLocalService.
			addCPDefinitionInventoryByCProductId(
				cpDefinition.getCProductId(), cpDefinitionInventoryEngine,
				lowStockActivity, displayAvailability, displayStockQuantity,
				minStockQuantity, backOrders, minOrderQuantity,
				maxOrderQuantity, allowedOrderQuantities, multipleOrderQuantity,
				serviceContext);
	}

	@Override
	public CPDefinitionInventory addCPDefinitionInventoryByCProductId(
			long cProductId, String cpDefinitionInventoryEngine,
			String lowStockActivity, boolean displayAvailability,
			boolean displayStockQuantity, int minStockQuantity,
			boolean backOrders, int minOrderQuantity, int maxOrderQuantity,
			String allowedOrderQuantities, int multipleOrderQuantity,
			ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(serviceContext.getUserId());
		long groupId = serviceContext.getScopeGroupId();

		long cpDefinitionInventoryId = counterLocalService.increment();

		CPDefinitionInventory cpDefinitionInventory =
			cpDefinitionInventoryPersistence.create(cpDefinitionInventoryId);

		cpDefinitionInventory.setUuid(serviceContext.getUuid());
		cpDefinitionInventory.setGroupId(groupId);
		cpDefinitionInventory.setCompanyId(user.getCompanyId());
		cpDefinitionInventory.setUserId(user.getUserId());
		cpDefinitionInventory.setUserName(user.getFullName());
		cpDefinitionInventory.setCProductId(cProductId);
		cpDefinitionInventory.setCPDefinitionInventoryEngine(
			cpDefinitionInventoryEngine);
		cpDefinitionInventory.setLowStockActivity(lowStockActivity);
		cpDefinitionInventory.setDisplayAvailability(displayAvailability);
		cpDefinitionInventory.setDisplayStockQuantity(displayStockQuantity);
		cpDefinitionInventory.setMinStockQuantity(minStockQuantity);
		cpDefinitionInventory.setBackOrders(backOrders);
		cpDefinitionInventory.setMinOrderQuantity(minOrderQuantity);
		cpDefinitionInventory.setMaxOrderQuantity(maxOrderQuantity);
		cpDefinitionInventory.setAllowedOrderQuantities(allowedOrderQuantities);
		cpDefinitionInventory.setMultipleOrderQuantity(multipleOrderQuantity);

		cpDefinitionInventoryPersistence.update(cpDefinitionInventory);

		return cpDefinitionInventory;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CPDefinitionInventory deleteCPDefinitionInventory(
		CPDefinitionInventory cpDefinitionInventory) {

		return cpDefinitionInventoryPersistence.remove(cpDefinitionInventory);
	}

	@Override
	public CPDefinitionInventory deleteCPDefinitionInventory(
			long cpDefinitionInventoryId)
		throws PortalException {

		CPDefinitionInventory cpDefinitionInventory =
			cpDefinitionInventoryPersistence.findByPrimaryKey(
				cpDefinitionInventoryId);

		return cpDefinitionInventoryLocalService.deleteCPDefinitionInventory(
			cpDefinitionInventory);
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public void deleteCPDefinitionInventoryByCPDefinitionId(
		long cpDefinitionId) {

		CPDefinition cpDefinition = _cpDefinitionLocalService.fetchCPDefinition(
			cpDefinitionId);

		if (cpDefinition != null) {
			cpDefinitionInventoryLocalService.
				deleteCPDefinitionInventoryByCProductId(
					cpDefinition.getCProductId());
		}
	}

	@Override
	public void deleteCPDefinitionInventoryByCProductId(long cProductId) {
		CPDefinitionInventory cpDefinitionInventory =
			cpDefinitionInventoryLocalService.
				fetchCPDefinitionInventoryByCProductId(cProductId);

		if (cpDefinitionInventory != null) {
			cpDefinitionInventoryLocalService.deleteCPDefinitionInventory(
				cpDefinitionInventory);
		}
	}

	/**
	 * @deprecated As of Judson (7.1.x)
	 */
	@Deprecated
	@Override
	public CPDefinitionInventory fetchCPDefinitionInventoryByCPDefinitionId(
			long cpDefinitionId)
		throws PortalException {

		CPDefinition cpDefinition = _cpDefinitionLocalService.getCPDefinition(
			cpDefinitionId);

		return cpDefinitionInventoryLocalService.
			fetchCPDefinitionInventoryByCProductId(
				cpDefinition.getCProductId());
	}

	@Override
	public CPDefinitionInventory fetchCPDefinitionInventoryByCProductId(
		long cProductId) {

		return cpDefinitionInventoryPersistence.fetchByCProductId(cProductId);
	}

	@Override
	public CPDefinitionInventory updateCPDefinitionInventory(
			long cpDefinitionInventoryId, String cpDefinitionInventoryEngine,
			String lowStockActivity, boolean displayAvailability,
			boolean displayStockQuantity, int minStockQuantity,
			boolean backOrders, int minOrderQuantity, int maxOrderQuantity,
			String allowedOrderQuantities, int multipleOrderQuantity,
			ServiceContext serviceContext)
		throws PortalException {

		CPDefinitionInventory cpDefinitionInventory =
			cpDefinitionInventoryPersistence.findByPrimaryKey(
				cpDefinitionInventoryId);

		cpDefinitionInventory.setCPDefinitionInventoryEngine(
			cpDefinitionInventoryEngine);
		cpDefinitionInventory.setLowStockActivity(lowStockActivity);
		cpDefinitionInventory.setDisplayAvailability(displayAvailability);
		cpDefinitionInventory.setDisplayStockQuantity(displayStockQuantity);
		cpDefinitionInventory.setMinStockQuantity(minStockQuantity);
		cpDefinitionInventory.setBackOrders(backOrders);
		cpDefinitionInventory.setMinOrderQuantity(minOrderQuantity);
		cpDefinitionInventory.setMaxOrderQuantity(maxOrderQuantity);
		cpDefinitionInventory.setAllowedOrderQuantities(allowedOrderQuantities);
		cpDefinitionInventory.setMultipleOrderQuantity(multipleOrderQuantity);

		cpDefinitionInventoryPersistence.update(cpDefinitionInventory);

		return cpDefinitionInventory;
	}

	@ServiceReference(type = CPDefinitionLocalService.class)
	private CPDefinitionLocalService _cpDefinitionLocalService;

}