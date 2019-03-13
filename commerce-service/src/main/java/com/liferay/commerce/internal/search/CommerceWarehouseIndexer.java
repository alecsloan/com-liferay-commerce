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

package com.liferay.commerce.internal.search;

import com.liferay.commerce.model.CommerceWarehouse;
import com.liferay.commerce.service.CommerceWarehouseLocalService;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanClauseOccur;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.search.filter.GeoDistanceFilter;
import com.liferay.portal.kernel.search.geolocation.DistanceUnit;
import com.liferay.portal.kernel.search.geolocation.GeoDistance;
import com.liferay.portal.kernel.search.geolocation.GeoLocationPoint;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = Indexer.class)
public class CommerceWarehouseIndexer extends BaseIndexer<CommerceWarehouse> {

	public static final String CLASS_NAME = CommerceWarehouse.class.getName();

	public static final String FIELD_ACTIVE = "active";

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessContextBooleanFilter(
			BooleanFilter contextBooleanFilter, SearchContext searchContext)
		throws Exception {

		Boolean active = (Boolean)searchContext.getAttribute(FIELD_ACTIVE);

		if (active != null) {
			contextBooleanFilter.addTerm(
				FIELD_ACTIVE, String.valueOf(active), BooleanClauseOccur.MUST);
		}

		double latitude =
			GetterUtil.getDouble(searchContext.getAttribute("latitude"), Double.MAX_VALUE);

		double longitude =
			GetterUtil.getDouble(searchContext.getAttribute("longitude"), Double.MAX_VALUE);

		if (latitude != Double.MAX_VALUE && longitude != Double.MAX_VALUE) {
			GeoLocationPoint geoLocationPoint = new GeoLocationPoint(latitude, longitude);

			GeoDistanceFilter geoDistanceFilter = new GeoDistanceFilter(
				Field.GEO_LOCATION, geoLocationPoint,
				new GeoDistance(5, DistanceUnit.MILES));

			contextBooleanFilter.add(geoDistanceFilter);
		}

	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		addSearchTerm(searchQuery, searchContext, Field.ENTRY_CLASS_PK, false);
		addSearchTerm(searchQuery, searchContext, Field.NAME, false);
	}

	@Override
	protected void doDelete(CommerceWarehouse CommerceWarehouse) throws Exception {
		deleteDocument(
			CommerceWarehouse.getCompanyId(),
			CommerceWarehouse.getCommerceWarehouseId());
	}

	@Override
	protected Document doGetDocument(CommerceWarehouse CommerceWarehouse)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("Indexing commerce account " + CommerceWarehouse);
		}

		Document document = getBaseModelDocument(CLASS_NAME, CommerceWarehouse);

		document.addText(Field.NAME, CommerceWarehouse.getName());
		document.addKeyword(FIELD_ACTIVE, CommerceWarehouse.isActive());
		document.addGeoLocation(CommerceWarehouse.getLatitude(), CommerceWarehouse.getLongitude());

		if (_log.isDebugEnabled()) {
			_log.debug("Document " + CommerceWarehouse + " indexed successfully");
		}

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Summary summary = createSummary(
			document, Field.ENTRY_CLASS_PK, Field.NAME);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(CommerceWarehouse CommerceWarehouse) throws Exception {
		Document document = getDocument(CommerceWarehouse);

		_indexWriterHelper.updateDocument(
			getSearchEngineId(), CommerceWarehouse.getCompanyId(), document,
			isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		CommerceWarehouse CommerceWarehouse =
			_CommerceWarehouseLocalService.getCommerceWarehouse(classPK);

		doReindex(CommerceWarehouse);
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexCommerceWarehouses(companyId);
	}

	protected void reindexCommerceWarehouses(long companyId)
		throws PortalException {

		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			_CommerceWarehouseLocalService.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			(CommerceWarehouse CommerceWarehouse) -> {
				try {
					Document document = getDocument(CommerceWarehouse);

					indexableActionableDynamicQuery.addDocuments(document);
				}
				catch (PortalException pe) {
					if (_log.isWarnEnabled()) {
						_log.warn(
							"Unable to index commerce account " +
								CommerceWarehouse.getCommerceWarehouseId(),
							pe);
					}
				}
			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceWarehouseIndexer.class);

	@Reference
	private CommerceWarehouseLocalService _CommerceWarehouseLocalService;

	@Reference
	private IndexWriterHelper _indexWriterHelper;

}