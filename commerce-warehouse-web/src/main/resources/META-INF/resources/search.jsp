<%@ page import="com.liferay.commerce.exception.CommerceGeocoderException" %>

<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
	request = PortalUtil.getOriginalServletRequest(request);

	String drugName = ParamUtil.getString(request, "drugname");
	String location = ParamUtil.getString(request, "location");
%>

<portlet:actionURL name="getLocation" var="getLocationURL" />

<aui:form action="<%= getLocationURL %>" cssClass="container-fluid-1280" method="post" name="fm">
	<liferay-ui:error exception="<%= CommerceGeocoderException.class %>" message="Location not found, please enter a different location." />

	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

	<style>
		#_com_liferay_commerce_warehouse_web_internal_portlet_CommerceWarehouseSearchPortlet_fm {
			margin: 0 !important;
			padding: 0 !important;
		}

		.form-group.input-text-wrapper:nth-child(1) {
			flex: 0 0 25%;
			max-width: 25%;
			padding: 0 12px;
		}

		.form-group.input-text-wrapper:nth-child(2) {
			flex: 0 0 41.66667%;
			max-width: 41.66667%;
			padding: 0 12px;
		}

		.force-align {
			margin-top: 32px !important;
		}
	</style>

	<div class="row">
		<div class="input-group m-0 p-0" style="border-bottom: 1px solid #e5e5e5">
			<aui:input cssClass="" label="Drug Name" name="productName" type="Text" value="<%= drugName %>">
				<aui:validator name="required" />
			</aui:input>

			<aui:input label="Location" name="location" type="text" value="<%= location %>" />

			<aui:button cssClass="force-align mb-auto mr-auto mt-auto" type="submit" value="Search" />
		</div>
</aui:form>