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

package com.liferay.commerce.internal.upgrade;

import com.liferay.commerce.internal.upgrade.v1_1_0.CommerceOrderItemUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v1_1_0.CommerceOrderNoteUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v1_1_0.CommerceOrderUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v1_2_0.CPDAvailabilityEstimateUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v1_2_0.CPDefinitionInventoryUpgradeProcess;
import com.liferay.commerce.internal.upgrade.v1_2_0.CommerceWarehouseItemUpgradeProcess;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Rodrigo Guedes de Souza
 * @author Alec Sloan
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class CommerceServiceUpgradeStepRegistrator
	implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		if (_log.isInfoEnabled()) {
			_log.info("COMMERCE UPGRADE STEP REGISTRATOR STARTED");
		}

		registry.register(
			_SCHEMA_VERSION_1_0_0, _SCHEMA_VERSION_1_1_0,
			new CommerceOrderUpgradeProcess(),
			new CommerceOrderItemUpgradeProcess(),
			new CommerceOrderNoteUpgradeProcess());

		registry.register(
			_SCHEMA_VERSION_1_1_0, _SCHEMA_VERSION_1_2_0,
			new CommerceWarehouseItemUpgradeProcess(
				_cpDefinitionLocalService, _cpInstanceLocalService),
			new com.liferay.commerce.internal.upgrade.v1_2_0.
				CommerceOrderItemUpgradeProcess(
					_cpDefinitionLocalService, _cpInstanceLocalService),
			new CPDAvailabilityEstimateUpgradeProcess(),
			new CPDefinitionInventoryUpgradeProcess());

		if (_log.isInfoEnabled()) {
			_log.info("COMMERCE UPGRADE STEP REGISTRATOR FINISHED");
		}
	}

	private static final String _SCHEMA_VERSION_1_0_0 = "1.0.0";

	private static final String _SCHEMA_VERSION_1_1_0 = "1.1.0";

	private static final String _SCHEMA_VERSION_1_2_0 = "1.2.0";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceServiceUpgradeStepRegistrator.class);

	@Reference
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

}