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

package com.liferay.commerce.inventory.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.model.AuditedModel;
import com.liferay.portal.kernel.model.BaseModel;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ShardedModel;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.Date;

/**
 * The base model interface for the CommerceInventoryWarehouseItem service. Represents a row in the &quot;CIWarehouseItem&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseItemModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseItemImpl}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseItem
 * @see com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseItemImpl
 * @see com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseItemModelImpl
 * @generated
 */
@ProviderType
public interface CommerceInventoryWarehouseItemModel extends AuditedModel,
	BaseModel<CommerceInventoryWarehouseItem>, ShardedModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a commerce inventory warehouse item model instance should use the {@link CommerceInventoryWarehouseItem} interface instead.
	 */

	/**
	 * Returns the primary key of this commerce inventory warehouse item.
	 *
	 * @return the primary key of this commerce inventory warehouse item
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this commerce inventory warehouse item.
	 *
	 * @param primaryKey the primary key of this commerce inventory warehouse item
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the commerce inventory warehouse item ID of this commerce inventory warehouse item.
	 *
	 * @return the commerce inventory warehouse item ID of this commerce inventory warehouse item
	 */
	public long getCommerceInventoryWarehouseItemId();

	/**
	 * Sets the commerce inventory warehouse item ID of this commerce inventory warehouse item.
	 *
	 * @param commerceInventoryWarehouseItemId the commerce inventory warehouse item ID of this commerce inventory warehouse item
	 */
	public void setCommerceInventoryWarehouseItemId(
		long commerceInventoryWarehouseItemId);

	/**
	 * Returns the company ID of this commerce inventory warehouse item.
	 *
	 * @return the company ID of this commerce inventory warehouse item
	 */
	@Override
	public long getCompanyId();

	/**
	 * Sets the company ID of this commerce inventory warehouse item.
	 *
	 * @param companyId the company ID of this commerce inventory warehouse item
	 */
	@Override
	public void setCompanyId(long companyId);

	/**
	 * Returns the user ID of this commerce inventory warehouse item.
	 *
	 * @return the user ID of this commerce inventory warehouse item
	 */
	@Override
	public long getUserId();

	/**
	 * Sets the user ID of this commerce inventory warehouse item.
	 *
	 * @param userId the user ID of this commerce inventory warehouse item
	 */
	@Override
	public void setUserId(long userId);

	/**
	 * Returns the user uuid of this commerce inventory warehouse item.
	 *
	 * @return the user uuid of this commerce inventory warehouse item
	 */
	@Override
	public String getUserUuid();

	/**
	 * Sets the user uuid of this commerce inventory warehouse item.
	 *
	 * @param userUuid the user uuid of this commerce inventory warehouse item
	 */
	@Override
	public void setUserUuid(String userUuid);

	/**
	 * Returns the user name of this commerce inventory warehouse item.
	 *
	 * @return the user name of this commerce inventory warehouse item
	 */
	@AutoEscape
	@Override
	public String getUserName();

	/**
	 * Sets the user name of this commerce inventory warehouse item.
	 *
	 * @param userName the user name of this commerce inventory warehouse item
	 */
	@Override
	public void setUserName(String userName);

	/**
	 * Returns the create date of this commerce inventory warehouse item.
	 *
	 * @return the create date of this commerce inventory warehouse item
	 */
	@Override
	public Date getCreateDate();

	/**
	 * Sets the create date of this commerce inventory warehouse item.
	 *
	 * @param createDate the create date of this commerce inventory warehouse item
	 */
	@Override
	public void setCreateDate(Date createDate);

	/**
	 * Returns the modified date of this commerce inventory warehouse item.
	 *
	 * @return the modified date of this commerce inventory warehouse item
	 */
	@Override
	public Date getModifiedDate();

	/**
	 * Sets the modified date of this commerce inventory warehouse item.
	 *
	 * @param modifiedDate the modified date of this commerce inventory warehouse item
	 */
	@Override
	public void setModifiedDate(Date modifiedDate);

	/**
	 * Returns the commerce inventory warehouse ID of this commerce inventory warehouse item.
	 *
	 * @return the commerce inventory warehouse ID of this commerce inventory warehouse item
	 */
	public long getCommerceInventoryWarehouseId();

	/**
	 * Sets the commerce inventory warehouse ID of this commerce inventory warehouse item.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID of this commerce inventory warehouse item
	 */
	public void setCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId);

	/**
	 * Returns the sku of this commerce inventory warehouse item.
	 *
	 * @return the sku of this commerce inventory warehouse item
	 */
	@AutoEscape
	public String getSku();

	/**
	 * Sets the sku of this commerce inventory warehouse item.
	 *
	 * @param sku the sku of this commerce inventory warehouse item
	 */
	public void setSku(String sku);

	/**
	 * Returns the quantity of this commerce inventory warehouse item.
	 *
	 * @return the quantity of this commerce inventory warehouse item
	 */
	public int getQuantity();

	/**
	 * Sets the quantity of this commerce inventory warehouse item.
	 *
	 * @param quantity the quantity of this commerce inventory warehouse item
	 */
	public void setQuantity(int quantity);

	/**
	 * Returns the reserved quantity of this commerce inventory warehouse item.
	 *
	 * @return the reserved quantity of this commerce inventory warehouse item
	 */
	public int getReservedQuantity();

	/**
	 * Sets the reserved quantity of this commerce inventory warehouse item.
	 *
	 * @param reservedQuantity the reserved quantity of this commerce inventory warehouse item
	 */
	public void setReservedQuantity(int reservedQuantity);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(
		CommerceInventoryWarehouseItem commerceInventoryWarehouseItem);

	@Override
	public int hashCode();

	@Override
	public CacheModel<CommerceInventoryWarehouseItem> toCacheModel();

	@Override
	public CommerceInventoryWarehouseItem toEscapedModel();

	@Override
	public CommerceInventoryWarehouseItem toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}