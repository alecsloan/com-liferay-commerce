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

package com.liferay.commerce.product.model.impl;

import com.liferay.commerce.product.model.CPOptionValue;
import com.liferay.commerce.product.model.CPOptionValueModel;
import com.liferay.commerce.product.model.CPOptionValueSoap;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.expando.kernel.util.ExpandoBridgeFactoryUtil;
import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.LocaleException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSON;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.impl.BaseModelImpl;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * The base model implementation for the CPOptionValue service. Represents a row in the &quot;CPOptionValue&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface </code>CPOptionValueModel</code> exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CPOptionValueImpl}.
 * </p>
 *
 * @author Marco Leo
 * @see CPOptionValueImpl
 * @generated
 */
@JSON(strict = true)
public class CPOptionValueModelImpl
	extends BaseModelImpl<CPOptionValue> implements CPOptionValueModel {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a cp option value model instance should use the <code>CPOptionValue</code> interface instead.
	 */
	public static final String TABLE_NAME = "CPOptionValue";

	public static final Object[][] TABLE_COLUMNS = {
		{"uuid_", Types.VARCHAR}, {"externalReferenceCode", Types.VARCHAR},
		{"CPOptionValueId", Types.BIGINT}, {"companyId", Types.BIGINT},
		{"userId", Types.BIGINT}, {"userName", Types.VARCHAR},
		{"createDate", Types.TIMESTAMP}, {"modifiedDate", Types.TIMESTAMP},
		{"CPOptionId", Types.BIGINT}, {"name", Types.VARCHAR},
		{"priority", Types.DOUBLE}, {"key_", Types.VARCHAR},
		{"lastPublishDate", Types.TIMESTAMP}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
		new HashMap<String, Integer>();

	static {
		TABLE_COLUMNS_MAP.put("uuid_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("externalReferenceCode", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("CPOptionValueId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("companyId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("userName", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("createDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("modifiedDate", Types.TIMESTAMP);
		TABLE_COLUMNS_MAP.put("CPOptionId", Types.BIGINT);
		TABLE_COLUMNS_MAP.put("name", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("priority", Types.DOUBLE);
		TABLE_COLUMNS_MAP.put("key_", Types.VARCHAR);
		TABLE_COLUMNS_MAP.put("lastPublishDate", Types.TIMESTAMP);
	}

	public static final String TABLE_SQL_CREATE =
		"create table CPOptionValue (uuid_ VARCHAR(75) null,externalReferenceCode VARCHAR(75) null,CPOptionValueId LONG not null primary key,companyId LONG,userId LONG,userName VARCHAR(75) null,createDate DATE null,modifiedDate DATE null,CPOptionId LONG,name STRING null,priority DOUBLE,key_ VARCHAR(75) null,lastPublishDate DATE null)";

	public static final String TABLE_SQL_DROP = "drop table CPOptionValue";

	public static final String ORDER_BY_JPQL =
		" ORDER BY cpOptionValue.priority ASC, cpOptionValue.name ASC";

	public static final String ORDER_BY_SQL =
		" ORDER BY CPOptionValue.priority ASC, CPOptionValue.name ASC";

	public static final String DATA_SOURCE = "liferayDataSource";

	public static final String SESSION_FACTORY = "liferaySessionFactory";

	public static final String TX_MANAGER = "liferayTransactionManager";

	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.product.service.util.ServiceProps.get(
			"value.object.entity.cache.enabled.com.liferay.commerce.product.model.CPOptionValue"),
		true);

	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.product.service.util.ServiceProps.get(
			"value.object.finder.cache.enabled.com.liferay.commerce.product.model.CPOptionValue"),
		true);

	public static final boolean COLUMN_BITMASK_ENABLED = GetterUtil.getBoolean(
		com.liferay.commerce.product.service.util.ServiceProps.get(
			"value.object.column.bitmask.enabled.com.liferay.commerce.product.model.CPOptionValue"),
		true);

	public static final long CPOPTIONID_COLUMN_BITMASK = 1L;

	public static final long COMPANYID_COLUMN_BITMASK = 2L;

	public static final long EXTERNALREFERENCECODE_COLUMN_BITMASK = 4L;

	public static final long KEY_COLUMN_BITMASK = 8L;

	public static final long UUID_COLUMN_BITMASK = 16L;

	public static final long PRIORITY_COLUMN_BITMASK = 32L;

	public static final long NAME_COLUMN_BITMASK = 64L;

	/**
	 * Converts the soap model instance into a normal model instance.
	 *
	 * @param soapModel the soap model instance to convert
	 * @return the normal model instance
	 */
	public static CPOptionValue toModel(CPOptionValueSoap soapModel) {
		if (soapModel == null) {
			return null;
		}

		CPOptionValue model = new CPOptionValueImpl();

		model.setUuid(soapModel.getUuid());
		model.setExternalReferenceCode(soapModel.getExternalReferenceCode());
		model.setCPOptionValueId(soapModel.getCPOptionValueId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setUserId(soapModel.getUserId());
		model.setUserName(soapModel.getUserName());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCPOptionId(soapModel.getCPOptionId());
		model.setName(soapModel.getName());
		model.setPriority(soapModel.getPriority());
		model.setKey(soapModel.getKey());
		model.setLastPublishDate(soapModel.getLastPublishDate());

		return model;
	}

	/**
	 * Converts the soap model instances into normal model instances.
	 *
	 * @param soapModels the soap model instances to convert
	 * @return the normal model instances
	 */
	public static List<CPOptionValue> toModels(CPOptionValueSoap[] soapModels) {
		if (soapModels == null) {
			return null;
		}

		List<CPOptionValue> models = new ArrayList<CPOptionValue>(
			soapModels.length);

		for (CPOptionValueSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(
		com.liferay.commerce.product.service.util.ServiceProps.get(
			"lock.expiration.time.com.liferay.commerce.product.model.CPOptionValue"));

	public CPOptionValueModelImpl() {
	}

	@Override
	public long getPrimaryKey() {
		return _CPOptionValueId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setCPOptionValueId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _CPOptionValueId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Class<?> getModelClass() {
		return CPOptionValue.class;
	}

	@Override
	public String getModelClassName() {
		return CPOptionValue.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		Map<String, Function<CPOptionValue, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		for (Map.Entry<String, Function<CPOptionValue, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPOptionValue, Object> attributeGetterFunction =
				entry.getValue();

			attributes.put(
				attributeName,
				attributeGetterFunction.apply((CPOptionValue)this));
		}

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Map<String, BiConsumer<CPOptionValue, Object>>
			attributeSetterBiConsumers = getAttributeSetterBiConsumers();

		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String attributeName = entry.getKey();

			BiConsumer<CPOptionValue, Object> attributeSetterBiConsumer =
				attributeSetterBiConsumers.get(attributeName);

			if (attributeSetterBiConsumer != null) {
				attributeSetterBiConsumer.accept(
					(CPOptionValue)this, entry.getValue());
			}
		}
	}

	public Map<String, Function<CPOptionValue, Object>>
		getAttributeGetterFunctions() {

		return _attributeGetterFunctions;
	}

	public Map<String, BiConsumer<CPOptionValue, Object>>
		getAttributeSetterBiConsumers() {

		return _attributeSetterBiConsumers;
	}

	private static Function<InvocationHandler, CPOptionValue>
		_getProxyProviderFunction() {

		Class<?> proxyClass = ProxyUtil.getProxyClass(
			CPOptionValue.class.getClassLoader(), CPOptionValue.class,
			ModelWrapper.class);

		try {
			Constructor<CPOptionValue> constructor =
				(Constructor<CPOptionValue>)proxyClass.getConstructor(
					InvocationHandler.class);

			return invocationHandler -> {
				try {
					return constructor.newInstance(invocationHandler);
				}
				catch (ReflectiveOperationException roe) {
					throw new InternalError(roe);
				}
			};
		}
		catch (NoSuchMethodException nsme) {
			throw new InternalError(nsme);
		}
	}

	private static final Map<String, Function<CPOptionValue, Object>>
		_attributeGetterFunctions;
	private static final Map<String, BiConsumer<CPOptionValue, Object>>
		_attributeSetterBiConsumers;

	static {
		Map<String, Function<CPOptionValue, Object>> attributeGetterFunctions =
			new LinkedHashMap<String, Function<CPOptionValue, Object>>();
		Map<String, BiConsumer<CPOptionValue, ?>> attributeSetterBiConsumers =
			new LinkedHashMap<String, BiConsumer<CPOptionValue, ?>>();

		attributeGetterFunctions.put(
			"uuid",
			new Function<CPOptionValue, Object>() {

				@Override
				public Object apply(CPOptionValue cpOptionValue) {
					return cpOptionValue.getUuid();
				}

			});
		attributeSetterBiConsumers.put(
			"uuid",
			new BiConsumer<CPOptionValue, Object>() {

				@Override
				public void accept(CPOptionValue cpOptionValue, Object uuid) {
					cpOptionValue.setUuid((String)uuid);
				}

			});
		attributeGetterFunctions.put(
			"externalReferenceCode",
			new Function<CPOptionValue, Object>() {

				@Override
				public Object apply(CPOptionValue cpOptionValue) {
					return cpOptionValue.getExternalReferenceCode();
				}

			});
		attributeSetterBiConsumers.put(
			"externalReferenceCode",
			new BiConsumer<CPOptionValue, Object>() {

				@Override
				public void accept(
					CPOptionValue cpOptionValue, Object externalReferenceCode) {

					cpOptionValue.setExternalReferenceCode(
						(String)externalReferenceCode);
				}

			});
		attributeGetterFunctions.put(
			"CPOptionValueId",
			new Function<CPOptionValue, Object>() {

				@Override
				public Object apply(CPOptionValue cpOptionValue) {
					return cpOptionValue.getCPOptionValueId();
				}

			});
		attributeSetterBiConsumers.put(
			"CPOptionValueId",
			new BiConsumer<CPOptionValue, Object>() {

				@Override
				public void accept(
					CPOptionValue cpOptionValue, Object CPOptionValueId) {

					cpOptionValue.setCPOptionValueId((Long)CPOptionValueId);
				}

			});
		attributeGetterFunctions.put(
			"companyId",
			new Function<CPOptionValue, Object>() {

				@Override
				public Object apply(CPOptionValue cpOptionValue) {
					return cpOptionValue.getCompanyId();
				}

			});
		attributeSetterBiConsumers.put(
			"companyId",
			new BiConsumer<CPOptionValue, Object>() {

				@Override
				public void accept(
					CPOptionValue cpOptionValue, Object companyId) {

					cpOptionValue.setCompanyId((Long)companyId);
				}

			});
		attributeGetterFunctions.put(
			"userId",
			new Function<CPOptionValue, Object>() {

				@Override
				public Object apply(CPOptionValue cpOptionValue) {
					return cpOptionValue.getUserId();
				}

			});
		attributeSetterBiConsumers.put(
			"userId",
			new BiConsumer<CPOptionValue, Object>() {

				@Override
				public void accept(CPOptionValue cpOptionValue, Object userId) {
					cpOptionValue.setUserId((Long)userId);
				}

			});
		attributeGetterFunctions.put(
			"userName",
			new Function<CPOptionValue, Object>() {

				@Override
				public Object apply(CPOptionValue cpOptionValue) {
					return cpOptionValue.getUserName();
				}

			});
		attributeSetterBiConsumers.put(
			"userName",
			new BiConsumer<CPOptionValue, Object>() {

				@Override
				public void accept(
					CPOptionValue cpOptionValue, Object userName) {

					cpOptionValue.setUserName((String)userName);
				}

			});
		attributeGetterFunctions.put(
			"createDate",
			new Function<CPOptionValue, Object>() {

				@Override
				public Object apply(CPOptionValue cpOptionValue) {
					return cpOptionValue.getCreateDate();
				}

			});
		attributeSetterBiConsumers.put(
			"createDate",
			new BiConsumer<CPOptionValue, Object>() {

				@Override
				public void accept(
					CPOptionValue cpOptionValue, Object createDate) {

					cpOptionValue.setCreateDate((Date)createDate);
				}

			});
		attributeGetterFunctions.put(
			"modifiedDate",
			new Function<CPOptionValue, Object>() {

				@Override
				public Object apply(CPOptionValue cpOptionValue) {
					return cpOptionValue.getModifiedDate();
				}

			});
		attributeSetterBiConsumers.put(
			"modifiedDate",
			new BiConsumer<CPOptionValue, Object>() {

				@Override
				public void accept(
					CPOptionValue cpOptionValue, Object modifiedDate) {

					cpOptionValue.setModifiedDate((Date)modifiedDate);
				}

			});
		attributeGetterFunctions.put(
			"CPOptionId",
			new Function<CPOptionValue, Object>() {

				@Override
				public Object apply(CPOptionValue cpOptionValue) {
					return cpOptionValue.getCPOptionId();
				}

			});
		attributeSetterBiConsumers.put(
			"CPOptionId",
			new BiConsumer<CPOptionValue, Object>() {

				@Override
				public void accept(
					CPOptionValue cpOptionValue, Object CPOptionId) {

					cpOptionValue.setCPOptionId((Long)CPOptionId);
				}

			});
		attributeGetterFunctions.put(
			"name",
			new Function<CPOptionValue, Object>() {

				@Override
				public Object apply(CPOptionValue cpOptionValue) {
					return cpOptionValue.getName();
				}

			});
		attributeSetterBiConsumers.put(
			"name",
			new BiConsumer<CPOptionValue, Object>() {

				@Override
				public void accept(CPOptionValue cpOptionValue, Object name) {
					cpOptionValue.setName((String)name);
				}

			});
		attributeGetterFunctions.put(
			"priority",
			new Function<CPOptionValue, Object>() {

				@Override
				public Object apply(CPOptionValue cpOptionValue) {
					return cpOptionValue.getPriority();
				}

			});
		attributeSetterBiConsumers.put(
			"priority",
			new BiConsumer<CPOptionValue, Object>() {

				@Override
				public void accept(
					CPOptionValue cpOptionValue, Object priority) {

					cpOptionValue.setPriority((Double)priority);
				}

			});
		attributeGetterFunctions.put(
			"key",
			new Function<CPOptionValue, Object>() {

				@Override
				public Object apply(CPOptionValue cpOptionValue) {
					return cpOptionValue.getKey();
				}

			});
		attributeSetterBiConsumers.put(
			"key",
			new BiConsumer<CPOptionValue, Object>() {

				@Override
				public void accept(CPOptionValue cpOptionValue, Object key) {
					cpOptionValue.setKey((String)key);
				}

			});
		attributeGetterFunctions.put(
			"lastPublishDate",
			new Function<CPOptionValue, Object>() {

				@Override
				public Object apply(CPOptionValue cpOptionValue) {
					return cpOptionValue.getLastPublishDate();
				}

			});
		attributeSetterBiConsumers.put(
			"lastPublishDate",
			new BiConsumer<CPOptionValue, Object>() {

				@Override
				public void accept(
					CPOptionValue cpOptionValue, Object lastPublishDate) {

					cpOptionValue.setLastPublishDate((Date)lastPublishDate);
				}

			});

		_attributeGetterFunctions = Collections.unmodifiableMap(
			attributeGetterFunctions);
		_attributeSetterBiConsumers = Collections.unmodifiableMap(
			(Map)attributeSetterBiConsumers);
	}

	@JSON
	@Override
	public String getUuid() {
		if (_uuid == null) {
			return "";
		}
		else {
			return _uuid;
		}
	}

	@Override
	public void setUuid(String uuid) {
		_columnBitmask |= UUID_COLUMN_BITMASK;

		if (_originalUuid == null) {
			_originalUuid = _uuid;
		}

		_uuid = uuid;
	}

	public String getOriginalUuid() {
		return GetterUtil.getString(_originalUuid);
	}

	@JSON
	@Override
	public String getExternalReferenceCode() {
		if (_externalReferenceCode == null) {
			return "";
		}
		else {
			return _externalReferenceCode;
		}
	}

	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		_columnBitmask |= EXTERNALREFERENCECODE_COLUMN_BITMASK;

		if (_originalExternalReferenceCode == null) {
			_originalExternalReferenceCode = _externalReferenceCode;
		}

		_externalReferenceCode = externalReferenceCode;
	}

	public String getOriginalExternalReferenceCode() {
		return GetterUtil.getString(_originalExternalReferenceCode);
	}

	@JSON
	@Override
	public long getCPOptionValueId() {
		return _CPOptionValueId;
	}

	@Override
	public void setCPOptionValueId(long CPOptionValueId) {
		_CPOptionValueId = CPOptionValueId;
	}

	@JSON
	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_columnBitmask |= COMPANYID_COLUMN_BITMASK;

		if (!_setOriginalCompanyId) {
			_setOriginalCompanyId = true;

			_originalCompanyId = _companyId;
		}

		_companyId = companyId;
	}

	public long getOriginalCompanyId() {
		return _originalCompanyId;
	}

	@JSON
	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return "";
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@JSON
	@Override
	public String getUserName() {
		if (_userName == null) {
			return "";
		}
		else {
			return _userName;
		}
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;
	}

	@JSON
	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	@JSON
	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public boolean hasSetModifiedDate() {
		return _setModifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_setModifiedDate = true;

		_modifiedDate = modifiedDate;
	}

	@JSON
	@Override
	public long getCPOptionId() {
		return _CPOptionId;
	}

	@Override
	public void setCPOptionId(long CPOptionId) {
		_columnBitmask |= CPOPTIONID_COLUMN_BITMASK;

		if (!_setOriginalCPOptionId) {
			_setOriginalCPOptionId = true;

			_originalCPOptionId = _CPOptionId;
		}

		_CPOptionId = CPOptionId;
	}

	public long getOriginalCPOptionId() {
		return _originalCPOptionId;
	}

	@JSON
	@Override
	public String getName() {
		if (_name == null) {
			return "";
		}
		else {
			return _name;
		}
	}

	@Override
	public String getName(Locale locale) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId);
	}

	@Override
	public String getName(Locale locale, boolean useDefault) {
		String languageId = LocaleUtil.toLanguageId(locale);

		return getName(languageId, useDefault);
	}

	@Override
	public String getName(String languageId) {
		return LocalizationUtil.getLocalization(getName(), languageId);
	}

	@Override
	public String getName(String languageId, boolean useDefault) {
		return LocalizationUtil.getLocalization(
			getName(), languageId, useDefault);
	}

	@Override
	public String getNameCurrentLanguageId() {
		return _nameCurrentLanguageId;
	}

	@JSON
	@Override
	public String getNameCurrentValue() {
		Locale locale = getLocale(_nameCurrentLanguageId);

		return getName(locale);
	}

	@Override
	public Map<Locale, String> getNameMap() {
		return LocalizationUtil.getLocalizationMap(getName());
	}

	@Override
	public void setName(String name) {
		_columnBitmask = -1L;

		_name = name;
	}

	@Override
	public void setName(String name, Locale locale) {
		setName(name, locale, LocaleUtil.getDefault());
	}

	@Override
	public void setName(String name, Locale locale, Locale defaultLocale) {
		String languageId = LocaleUtil.toLanguageId(locale);
		String defaultLanguageId = LocaleUtil.toLanguageId(defaultLocale);

		if (Validator.isNotNull(name)) {
			setName(
				LocalizationUtil.updateLocalization(
					getName(), "Name", name, languageId, defaultLanguageId));
		}
		else {
			setName(
				LocalizationUtil.removeLocalization(
					getName(), "Name", languageId));
		}
	}

	@Override
	public void setNameCurrentLanguageId(String languageId) {
		_nameCurrentLanguageId = languageId;
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap) {
		setNameMap(nameMap, LocaleUtil.getDefault());
	}

	@Override
	public void setNameMap(Map<Locale, String> nameMap, Locale defaultLocale) {
		if (nameMap == null) {
			return;
		}

		setName(
			LocalizationUtil.updateLocalization(
				nameMap, getName(), "Name",
				LocaleUtil.toLanguageId(defaultLocale)));
	}

	@JSON
	@Override
	public double getPriority() {
		return _priority;
	}

	@Override
	public void setPriority(double priority) {
		_columnBitmask = -1L;

		_priority = priority;
	}

	@JSON
	@Override
	public String getKey() {
		if (_key == null) {
			return "";
		}
		else {
			return _key;
		}
	}

	@Override
	public void setKey(String key) {
		_columnBitmask |= KEY_COLUMN_BITMASK;

		if (_originalKey == null) {
			_originalKey = _key;
		}

		_key = key;
	}

	public String getOriginalKey() {
		return GetterUtil.getString(_originalKey);
	}

	@JSON
	@Override
	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	@Override
	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return new StagedModelType(
			PortalUtil.getClassNameId(CPOptionValue.class.getName()));
	}

	public long getColumnBitmask() {
		return _columnBitmask;
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return ExpandoBridgeFactoryUtil.getExpandoBridge(
			getCompanyId(), CPOptionValue.class.getName(), getPrimaryKey());
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		ExpandoBridge expandoBridge = getExpandoBridge();

		expandoBridge.setAttributes(serviceContext);
	}

	@Override
	public String[] getAvailableLanguageIds() {
		Set<String> availableLanguageIds = new TreeSet<String>();

		Map<Locale, String> nameMap = getNameMap();

		for (Map.Entry<Locale, String> entry : nameMap.entrySet()) {
			Locale locale = entry.getKey();
			String value = entry.getValue();

			if (Validator.isNotNull(value)) {
				availableLanguageIds.add(LocaleUtil.toLanguageId(locale));
			}
		}

		return availableLanguageIds.toArray(
			new String[availableLanguageIds.size()]);
	}

	@Override
	public String getDefaultLanguageId() {
		String xml = getName();

		if (xml == null) {
			return "";
		}

		Locale defaultLocale = LocaleUtil.getDefault();

		return LocalizationUtil.getDefaultLanguageId(xml, defaultLocale);
	}

	@Override
	public void prepareLocalizedFieldsForImport() throws LocaleException {
		Locale defaultLocale = LocaleUtil.fromLanguageId(
			getDefaultLanguageId());

		Locale[] availableLocales = LocaleUtil.fromLanguageIds(
			getAvailableLanguageIds());

		Locale defaultImportLocale = LocalizationUtil.getDefaultImportLocale(
			CPOptionValue.class.getName(), getPrimaryKey(), defaultLocale,
			availableLocales);

		prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	@Override
	@SuppressWarnings("unused")
	public void prepareLocalizedFieldsForImport(Locale defaultImportLocale)
		throws LocaleException {

		Locale defaultLocale = LocaleUtil.getDefault();

		String modelDefaultLanguageId = getDefaultLanguageId();

		String name = getName(defaultLocale);

		if (Validator.isNull(name)) {
			setName(getName(modelDefaultLanguageId), defaultLocale);
		}
		else {
			setName(getName(defaultLocale), defaultLocale, defaultLocale);
		}
	}

	@Override
	public CPOptionValue toEscapedModel() {
		if (_escapedModel == null) {
			Function<InvocationHandler, CPOptionValue>
				escapedModelProxyProviderFunction =
					EscapedModelProxyProviderFunctionHolder.
						_escapedModelProxyProviderFunction;

			_escapedModel = escapedModelProxyProviderFunction.apply(
				new AutoEscapeBeanHandler(this));
		}

		return _escapedModel;
	}

	@Override
	public Object clone() {
		CPOptionValueImpl cpOptionValueImpl = new CPOptionValueImpl();

		cpOptionValueImpl.setUuid(getUuid());
		cpOptionValueImpl.setExternalReferenceCode(getExternalReferenceCode());
		cpOptionValueImpl.setCPOptionValueId(getCPOptionValueId());
		cpOptionValueImpl.setCompanyId(getCompanyId());
		cpOptionValueImpl.setUserId(getUserId());
		cpOptionValueImpl.setUserName(getUserName());
		cpOptionValueImpl.setCreateDate(getCreateDate());
		cpOptionValueImpl.setModifiedDate(getModifiedDate());
		cpOptionValueImpl.setCPOptionId(getCPOptionId());
		cpOptionValueImpl.setName(getName());
		cpOptionValueImpl.setPriority(getPriority());
		cpOptionValueImpl.setKey(getKey());
		cpOptionValueImpl.setLastPublishDate(getLastPublishDate());

		cpOptionValueImpl.resetOriginalValues();

		return cpOptionValueImpl;
	}

	@Override
	public int compareTo(CPOptionValue cpOptionValue) {
		int value = 0;

		if (getPriority() < cpOptionValue.getPriority()) {
			value = -1;
		}
		else if (getPriority() > cpOptionValue.getPriority()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		value = getName().compareTo(cpOptionValue.getName());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof CPOptionValue)) {
			return false;
		}

		CPOptionValue cpOptionValue = (CPOptionValue)obj;

		long primaryKey = cpOptionValue.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return ENTITY_CACHE_ENABLED;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return FINDER_CACHE_ENABLED;
	}

	@Override
	public void resetOriginalValues() {
		CPOptionValueModelImpl cpOptionValueModelImpl = this;

		cpOptionValueModelImpl._originalUuid = cpOptionValueModelImpl._uuid;

		cpOptionValueModelImpl._originalExternalReferenceCode =
			cpOptionValueModelImpl._externalReferenceCode;

		cpOptionValueModelImpl._originalCompanyId =
			cpOptionValueModelImpl._companyId;

		cpOptionValueModelImpl._setOriginalCompanyId = false;

		cpOptionValueModelImpl._setModifiedDate = false;

		cpOptionValueModelImpl._originalCPOptionId =
			cpOptionValueModelImpl._CPOptionId;

		cpOptionValueModelImpl._setOriginalCPOptionId = false;

		cpOptionValueModelImpl._originalKey = cpOptionValueModelImpl._key;

		cpOptionValueModelImpl._columnBitmask = 0;
	}

	@Override
	public CacheModel<CPOptionValue> toCacheModel() {
		CPOptionValueCacheModel cpOptionValueCacheModel =
			new CPOptionValueCacheModel();

		cpOptionValueCacheModel.uuid = getUuid();

		String uuid = cpOptionValueCacheModel.uuid;

		if ((uuid != null) && (uuid.length() == 0)) {
			cpOptionValueCacheModel.uuid = null;
		}

		cpOptionValueCacheModel.externalReferenceCode =
			getExternalReferenceCode();

		String externalReferenceCode =
			cpOptionValueCacheModel.externalReferenceCode;

		if ((externalReferenceCode != null) &&
			(externalReferenceCode.length() == 0)) {

			cpOptionValueCacheModel.externalReferenceCode = null;
		}

		cpOptionValueCacheModel.CPOptionValueId = getCPOptionValueId();

		cpOptionValueCacheModel.companyId = getCompanyId();

		cpOptionValueCacheModel.userId = getUserId();

		cpOptionValueCacheModel.userName = getUserName();

		String userName = cpOptionValueCacheModel.userName;

		if ((userName != null) && (userName.length() == 0)) {
			cpOptionValueCacheModel.userName = null;
		}

		Date createDate = getCreateDate();

		if (createDate != null) {
			cpOptionValueCacheModel.createDate = createDate.getTime();
		}
		else {
			cpOptionValueCacheModel.createDate = Long.MIN_VALUE;
		}

		Date modifiedDate = getModifiedDate();

		if (modifiedDate != null) {
			cpOptionValueCacheModel.modifiedDate = modifiedDate.getTime();
		}
		else {
			cpOptionValueCacheModel.modifiedDate = Long.MIN_VALUE;
		}

		cpOptionValueCacheModel.CPOptionId = getCPOptionId();

		cpOptionValueCacheModel.name = getName();

		String name = cpOptionValueCacheModel.name;

		if ((name != null) && (name.length() == 0)) {
			cpOptionValueCacheModel.name = null;
		}

		cpOptionValueCacheModel.priority = getPriority();

		cpOptionValueCacheModel.key = getKey();

		String key = cpOptionValueCacheModel.key;

		if ((key != null) && (key.length() == 0)) {
			cpOptionValueCacheModel.key = null;
		}

		Date lastPublishDate = getLastPublishDate();

		if (lastPublishDate != null) {
			cpOptionValueCacheModel.lastPublishDate = lastPublishDate.getTime();
		}
		else {
			cpOptionValueCacheModel.lastPublishDate = Long.MIN_VALUE;
		}

		return cpOptionValueCacheModel;
	}

	@Override
	public String toString() {
		Map<String, Function<CPOptionValue, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			4 * attributeGetterFunctions.size() + 2);

		sb.append("{");

		for (Map.Entry<String, Function<CPOptionValue, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPOptionValue, Object> attributeGetterFunction =
				entry.getValue();

			sb.append(attributeName);
			sb.append("=");
			sb.append(attributeGetterFunction.apply((CPOptionValue)this));
			sb.append(", ");
		}

		if (sb.index() > 1) {
			sb.setIndex(sb.index() - 1);
		}

		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		Map<String, Function<CPOptionValue, Object>> attributeGetterFunctions =
			getAttributeGetterFunctions();

		StringBundler sb = new StringBundler(
			5 * attributeGetterFunctions.size() + 4);

		sb.append("<model><model-name>");
		sb.append(getModelClassName());
		sb.append("</model-name>");

		for (Map.Entry<String, Function<CPOptionValue, Object>> entry :
				attributeGetterFunctions.entrySet()) {

			String attributeName = entry.getKey();
			Function<CPOptionValue, Object> attributeGetterFunction =
				entry.getValue();

			sb.append("<column><column-name>");
			sb.append(attributeName);
			sb.append("</column-name><column-value><![CDATA[");
			sb.append(attributeGetterFunction.apply((CPOptionValue)this));
			sb.append("]]></column-value></column>");
		}

		sb.append("</model>");

		return sb.toString();
	}

	private static class EscapedModelProxyProviderFunctionHolder {

		private static final Function<InvocationHandler, CPOptionValue>
			_escapedModelProxyProviderFunction = _getProxyProviderFunction();

	}

	private String _uuid;
	private String _originalUuid;
	private String _externalReferenceCode;
	private String _originalExternalReferenceCode;
	private long _CPOptionValueId;
	private long _companyId;
	private long _originalCompanyId;
	private boolean _setOriginalCompanyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private boolean _setModifiedDate;
	private long _CPOptionId;
	private long _originalCPOptionId;
	private boolean _setOriginalCPOptionId;
	private String _name;
	private String _nameCurrentLanguageId;
	private double _priority;
	private String _key;
	private String _originalKey;
	private Date _lastPublishDate;
	private long _columnBitmask;
	private CPOptionValue _escapedModel;

}