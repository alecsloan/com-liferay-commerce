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

package com.liferay.commerce.service.http;

import aQute.bnd.annotation.ProviderType;

import com.liferay.commerce.service.CommerceOrderServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * {@link CommerceOrderServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * {@link HttpPrincipal} parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Alessio Antonio Rendina
 * @see CommerceOrderServiceSoap
 * @see HttpPrincipal
 * @see CommerceOrderServiceUtil
 * @generated
 */
@ProviderType
public class CommerceOrderServiceHttp {
	public static com.liferay.commerce.model.CommerceOrder addCommerceOrder(
		HttpPrincipal httpPrincipal, long groupId, long userId,
		long commerceAccountId, long commerceCurrencyId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"addCommerceOrder", _addCommerceOrderParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					userId, commerceAccountId, commerceCurrencyId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getUserCommerceOrders(
		HttpPrincipal httpPrincipal, long groupId, String keywords, int start,
		int end) throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getUserCommerceOrders",
					_getUserCommerceOrdersParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					keywords, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.commerce.model.CommerceOrder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getUserCommerceOrdersCount(HttpPrincipal httpPrincipal,
		long groupId, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getUserCommerceOrdersCount",
					_getUserCommerceOrdersCountParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					keywords);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getUserCommerceOrders(
		HttpPrincipal httpPrincipal, long groupId, int orderStatus,
		String keywords, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getUserCommerceOrders",
					_getUserCommerceOrdersParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					orderStatus, keywords, start, end);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.commerce.model.CommerceOrder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getUserCommerceOrdersCount(HttpPrincipal httpPrincipal,
		long groupId, int orderStatus, String keywords)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getUserCommerceOrdersCount",
					_getUserCommerceOrdersCountParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					orderStatus, keywords);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder addCommerceOrder(
		HttpPrincipal httpPrincipal, long groupId, long commerceAccountId,
		long commerceCurrencyId, long shippingAddressId,
		String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"addCommerceOrder", _addCommerceOrderParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					commerceAccountId, commerceCurrencyId, shippingAddressId,
					purchaseOrderNumber);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder addCommerceOrder(
		HttpPrincipal httpPrincipal, long groupId, long commerceAccountId,
		long shippingAddressId, String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"addCommerceOrder", _addCommerceOrderParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					commerceAccountId, shippingAddressId, purchaseOrderNumber);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder approveCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"approveCommerceOrder", _approveCommerceOrderParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder checkoutCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"checkoutCommerceOrder",
					_checkoutCommerceOrderParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, commerceContext, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void deleteCommerceOrder(HttpPrincipal httpPrincipal,
		long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"deleteCommerceOrder", _deleteCommerceOrderParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder executeWorkflowTransition(
		HttpPrincipal httpPrincipal, long commerceOrderId, long workflowTaskId,
		String transitionName, String comment)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"executeWorkflowTransition",
					_executeWorkflowTransitionParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, workflowTaskId, transitionName, comment);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder fetchByExternalReferenceCode(
		HttpPrincipal httpPrincipal, long companyId,
		String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"fetchByExternalReferenceCode",
					_fetchByExternalReferenceCodeParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					companyId, externalReferenceCode);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"fetchCommerceOrder", _fetchCommerceOrderParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
		HttpPrincipal httpPrincipal, long groupId, int orderStatus)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"fetchCommerceOrder", _fetchCommerceOrderParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					orderStatus);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder fetchCommerceOrder(
		HttpPrincipal httpPrincipal, String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"fetchCommerceOrder", _fetchCommerceOrderParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(methodKey, uuid,
					groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int[] getAvailableOrderStatuses(HttpPrincipal httpPrincipal,
		long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getAvailableOrderStatuses",
					_getAvailableOrderStatusesParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (int[])returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder getCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrder", _getCommerceOrderParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder getCommerceOrderByUuidAndGroupId(
		HttpPrincipal httpPrincipal, String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrderByUuidAndGroupId",
					_getCommerceOrderByUuidAndGroupIdParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(methodKey, uuid,
					groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrders(
		HttpPrincipal httpPrincipal, long groupId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceOrder> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrders", _getCommerceOrdersParameterTypes18);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.commerce.model.CommerceOrder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrders(
		HttpPrincipal httpPrincipal, long groupId, int[] orderStatuses)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrders", _getCommerceOrdersParameterTypes19);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					orderStatuses);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.commerce.model.CommerceOrder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.commerce.model.CommerceOrder> getCommerceOrders(
		HttpPrincipal httpPrincipal, long groupId, long commerceAccountId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<com.liferay.commerce.model.CommerceOrder> orderByComparator)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrders", _getCommerceOrdersParameterTypes20);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					commerceAccountId, start, end, orderByComparator);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (java.util.List<com.liferay.commerce.model.CommerceOrder>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceOrdersCount(HttpPrincipal httpPrincipal,
		long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrdersCount",
					_getCommerceOrdersCountParameterTypes21);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static int getCommerceOrdersCount(HttpPrincipal httpPrincipal,
		long groupId, long commerceAccountId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"getCommerceOrdersCount",
					_getCommerceOrdersCountParameterTypes22);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId,
					commerceAccountId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void mergeGuestCommerceOrder(HttpPrincipal httpPrincipal,
		long guestCommerceOrderId, long userCommerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"mergeGuestCommerceOrder",
					_mergeGuestCommerceOrderParameterTypes23);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					guestCommerceOrderId, userCommerceOrderId, commerceContext,
					serviceContext);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder reorderCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"reorderCommerceOrder",
					_reorderCommerceOrderParameterTypes24);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, commerceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder submitCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"submitCommerceOrder", _submitCommerceOrderParameterTypes25);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updateBillingAddress(
		HttpPrincipal httpPrincipal, long commerceOrderId, String name,
		String description, String street1, String street2, String street3,
		String city, String zip, long commerceRegionId, long commerceCountryId,
		String phoneNumber,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updateBillingAddress",
					_updateBillingAddressParameterTypes26);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, name, description, street1, street2,
					street3, city, zip, commerceRegionId, commerceCountryId,
					phoneNumber, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId,
		long billingAddressId, long shippingAddressId,
		String commercePaymentMethodKey, long commerceShippingMethodId,
		String shippingOptionName, String purchaseOrderNumber,
		java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
		java.math.BigDecimal total, String advanceStatus,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updateCommerceOrder", _updateCommerceOrderParameterTypes27);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, billingAddressId, shippingAddressId,
					commercePaymentMethodKey, commerceShippingMethodId,
					shippingOptionName, purchaseOrderNumber, subtotal,
					shippingAmount, total, advanceStatus, commerceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updateCommerceOrder(
		HttpPrincipal httpPrincipal, long commerceOrderId,
		long billingAddressId, long shippingAddressId,
		String commercePaymentMethodKey, long commerceShippingMethodId,
		String shippingOptionName, String purchaseOrderNumber,
		java.math.BigDecimal subtotal, java.math.BigDecimal shippingAmount,
		java.math.BigDecimal total, String advanceStatus,
		String externalReferenceCode,
		com.liferay.commerce.context.CommerceContext commerceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updateCommerceOrder", _updateCommerceOrderParameterTypes28);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, billingAddressId, shippingAddressId,
					commercePaymentMethodKey, commerceShippingMethodId,
					shippingOptionName, purchaseOrderNumber, subtotal,
					shippingAmount, total, advanceStatus,
					externalReferenceCode, commerceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updateOrderStatus(
		HttpPrincipal httpPrincipal, long commerceOrderId, int orderStatus)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updateOrderStatus", _updateOrderStatusParameterTypes29);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, orderStatus);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updatePaymentStatus(
		HttpPrincipal httpPrincipal, long commerceOrderId, int paymentStatus)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updatePaymentStatus", _updatePaymentStatusParameterTypes30);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, paymentStatus);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updatePaymentStatusAndTransactionId(
		HttpPrincipal httpPrincipal, long commerceOrderId, int paymentStatus,
		String transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updatePaymentStatusAndTransactionId",
					_updatePaymentStatusAndTransactionIdParameterTypes31);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, paymentStatus, transactionId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updatePurchaseOrderNumber(
		HttpPrincipal httpPrincipal, long commerceOrderId,
		String purchaseOrderNumber)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updatePurchaseOrderNumber",
					_updatePurchaseOrderNumberParameterTypes32);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, purchaseOrderNumber);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updateShippingAddress(
		HttpPrincipal httpPrincipal, long commerceOrderId, String name,
		String description, String street1, String street2, String street3,
		String city, String zip, long commerceRegionId, long commerceCountryId,
		String phoneNumber,
		com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updateShippingAddress",
					_updateShippingAddressParameterTypes33);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, name, description, street1, street2,
					street3, city, zip, commerceRegionId, commerceCountryId,
					phoneNumber, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updateTransactionId(
		HttpPrincipal httpPrincipal, long commerceOrderId, String transactionId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updateTransactionId", _updateTransactionIdParameterTypes34);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, transactionId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.commerce.model.CommerceOrder updateUser(
		HttpPrincipal httpPrincipal, long commerceOrderId, long userId)
		throws com.liferay.portal.kernel.exception.PortalException {
		try {
			MethodKey methodKey = new MethodKey(CommerceOrderServiceUtil.class,
					"updateUser", _updateUserParameterTypes35);

			MethodHandler methodHandler = new MethodHandler(methodKey,
					commerceOrderId, userId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof com.liferay.portal.kernel.exception.PortalException) {
					throw (com.liferay.portal.kernel.exception.PortalException)e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(e);
			}

			return (com.liferay.commerce.model.CommerceOrder)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CommerceOrderServiceHttp.class);
	private static final Class<?>[] _addCommerceOrderParameterTypes0 = new Class[] {
			long.class, long.class, long.class, long.class
		};
	private static final Class<?>[] _getUserCommerceOrdersParameterTypes1 = new Class[] {
			long.class, String.class, int.class, int.class
		};
	private static final Class<?>[] _getUserCommerceOrdersCountParameterTypes2 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _getUserCommerceOrdersParameterTypes3 = new Class[] {
			long.class, int.class, String.class, int.class, int.class
		};
	private static final Class<?>[] _getUserCommerceOrdersCountParameterTypes4 = new Class[] {
			long.class, int.class, String.class
		};
	private static final Class<?>[] _addCommerceOrderParameterTypes5 = new Class[] {
			long.class, long.class, long.class, long.class, String.class
		};
	private static final Class<?>[] _addCommerceOrderParameterTypes6 = new Class[] {
			long.class, long.class, long.class, String.class
		};
	private static final Class<?>[] _approveCommerceOrderParameterTypes7 = new Class[] {
			long.class
		};
	private static final Class<?>[] _checkoutCommerceOrderParameterTypes8 = new Class[] {
			long.class, com.liferay.commerce.context.CommerceContext.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteCommerceOrderParameterTypes9 = new Class[] {
			long.class
		};
	private static final Class<?>[] _executeWorkflowTransitionParameterTypes10 = new Class[] {
			long.class, long.class, String.class, String.class
		};
	private static final Class<?>[] _fetchByExternalReferenceCodeParameterTypes11 =
		new Class[] { long.class, String.class };
	private static final Class<?>[] _fetchCommerceOrderParameterTypes12 = new Class[] {
			long.class
		};
	private static final Class<?>[] _fetchCommerceOrderParameterTypes13 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _fetchCommerceOrderParameterTypes14 = new Class[] {
			String.class, long.class
		};
	private static final Class<?>[] _getAvailableOrderStatusesParameterTypes15 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceOrderParameterTypes16 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceOrderByUuidAndGroupIdParameterTypes17 =
		new Class[] { String.class, long.class };
	private static final Class<?>[] _getCommerceOrdersParameterTypes18 = new Class[] {
			long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getCommerceOrdersParameterTypes19 = new Class[] {
			long.class, int[].class
		};
	private static final Class<?>[] _getCommerceOrdersParameterTypes20 = new Class[] {
			long.class, long.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class
		};
	private static final Class<?>[] _getCommerceOrdersCountParameterTypes21 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getCommerceOrdersCountParameterTypes22 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _mergeGuestCommerceOrderParameterTypes23 = new Class[] {
			long.class, long.class,
			com.liferay.commerce.context.CommerceContext.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _reorderCommerceOrderParameterTypes24 = new Class[] {
			long.class, com.liferay.commerce.context.CommerceContext.class
		};
	private static final Class<?>[] _submitCommerceOrderParameterTypes25 = new Class[] {
			long.class
		};
	private static final Class<?>[] _updateBillingAddressParameterTypes26 = new Class[] {
			long.class, String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, long.class, long.class,
			String.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateCommerceOrderParameterTypes27 = new Class[] {
			long.class, long.class, long.class, String.class, long.class,
			String.class, String.class, java.math.BigDecimal.class,
			java.math.BigDecimal.class, java.math.BigDecimal.class, String.class,
			com.liferay.commerce.context.CommerceContext.class
		};
	private static final Class<?>[] _updateCommerceOrderParameterTypes28 = new Class[] {
			long.class, long.class, long.class, String.class, long.class,
			String.class, String.class, java.math.BigDecimal.class,
			java.math.BigDecimal.class, java.math.BigDecimal.class, String.class,
			String.class, com.liferay.commerce.context.CommerceContext.class
		};
	private static final Class<?>[] _updateOrderStatusParameterTypes29 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _updatePaymentStatusParameterTypes30 = new Class[] {
			long.class, int.class
		};
	private static final Class<?>[] _updatePaymentStatusAndTransactionIdParameterTypes31 =
		new Class[] { long.class, int.class, String.class };
	private static final Class<?>[] _updatePurchaseOrderNumberParameterTypes32 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _updateShippingAddressParameterTypes33 = new Class[] {
			long.class, String.class, String.class, String.class, String.class,
			String.class, String.class, String.class, long.class, long.class,
			String.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateTransactionIdParameterTypes34 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _updateUserParameterTypes35 = new Class[] {
			long.class, long.class
		};
}