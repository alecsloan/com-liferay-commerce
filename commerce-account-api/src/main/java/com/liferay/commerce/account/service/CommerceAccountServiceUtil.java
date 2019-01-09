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

package com.liferay.commerce.account.service;

import aQute.bnd.annotation.ProviderType;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the remote service utility for CommerceAccount. This utility wraps
 * {@link com.liferay.commerce.account.service.impl.CommerceAccountServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on a remote server. Methods of this service are expected to have security
 * checks based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Marco Leo
 * @see CommerceAccountService
 * @see com.liferay.commerce.account.service.base.CommerceAccountServiceBaseImpl
 * @see com.liferay.commerce.account.service.impl.CommerceAccountServiceImpl
 * @generated
 */
@ProviderType
public class CommerceAccountServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.commerce.account.service.impl.CommerceAccountServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */
	public static com.liferay.commerce.account.model.CommerceAccount addBusinessCommerceAccount(
		String name, long parentCommerceAccountId, String email, String taxId,
		boolean active, String externalReferenceCode, long[] userIds,
		String[] emailAddresses,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addBusinessCommerceAccount(name, parentCommerceAccountId,
			email, taxId, active, externalReferenceCode, userIds,
			emailAddresses, serviceContext);
	}

	public static com.liferay.commerce.account.model.CommerceAccount addCommerceAccount(
		String name, long parentCommerceAccountId, String email, String taxId,
		int type, boolean active, String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .addCommerceAccount(name, parentCommerceAccountId, email,
			taxId, type, active, externalReferenceCode, serviceContext);
	}

	public static void deleteCommerceAccount(long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		getService().deleteCommerceAccount(commerceAccountId);
	}

	public static com.liferay.commerce.account.model.CommerceAccount fetchCommerceAccount(
		long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().fetchCommerceAccount(commerceAccountId);
	}

	public static com.liferay.commerce.account.model.CommerceAccount getCommerceAccount(
		long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getCommerceAccount(commerceAccountId);
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.commerce.account.model.CommerceAccount getPersonalCommerceAccount(
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersonalCommerceAccount(serviceContext);
	}

	public static java.util.List<com.liferay.commerce.account.model.CommerceAccount> getUserCommerceAccounts(
		com.liferay.commerce.account.util.CommerceSiteType commerceSiteType,
		int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getUserCommerceAccounts(commerceSiteType, start, end);
	}

	public static java.util.List<com.liferay.commerce.account.model.CommerceAccount> getUserCommerceAccounts(
		Long parentCommerceAccountId,
		com.liferay.commerce.account.util.CommerceSiteType commerceSiteType,
		String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getUserCommerceAccounts(parentCommerceAccountId,
			commerceSiteType, keywords, start, end);
	}

	public static int getUserCommerceAccountsCount(
		Long parentCommerceAccountId,
		com.liferay.commerce.account.util.CommerceSiteType commerceSiteType,
		String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .getUserCommerceAccountsCount(parentCommerceAccountId,
			commerceSiteType, keywords);
	}

	public static com.liferay.portal.kernel.search.BaseModelSearchResult<com.liferay.commerce.account.model.CommerceAccount> searchCommerceAccounts(
		long parentCommerceAccountId, String keywords, Boolean active,
		int start, int end, com.liferay.portal.kernel.search.Sort sort)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .searchCommerceAccounts(parentCommerceAccountId, keywords,
			active, start, end, sort);
	}

	public static com.liferay.commerce.account.model.CommerceAccount updateCommerceAccount(
		long commerceAccountId, String name, String email, String taxId,
		boolean active,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .updateCommerceAccount(commerceAccountId, name, email,
			taxId, active, serviceContext);
	}

	public static com.liferay.commerce.account.model.CommerceAccount upsertCommerceAccount(
		String name, long parentCommerceAccountId, boolean logo,
		byte[] logoBytes, String email, String taxId, int type, boolean active,
		String externalReferenceCode,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService()
				   .upsertCommerceAccount(name, parentCommerceAccountId, logo,
			logoBytes, email, taxId, type, active, externalReferenceCode,
			serviceContext);
	}

	public static CommerceAccountService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker<CommerceAccountService, CommerceAccountService> _serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(CommerceAccountService.class);

		ServiceTracker<CommerceAccountService, CommerceAccountService> serviceTracker =
			new ServiceTracker<CommerceAccountService, CommerceAccountService>(bundle.getBundleContext(),
				CommerceAccountService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}
}