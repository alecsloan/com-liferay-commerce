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

package com.liferay.commerce.warehouse.web.internal;

import com.liferay.commerce.exception.CommerceGeocoderException;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceGeocoder;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.petra.string.CharPool;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.URLCodec;
import com.liferay.portal.kernel.util.Validator;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alec Sloan
 */
@Component(
	immediate = true, service = {CommerceGeocoder.class, GoogleLocationFinder.class}
)
public class GoogleLocationFinder implements CommerceGeocoder {

	public double[] getCoordinates(String searchInput)
		throws CommerceGeocoderException {

		try {
			 return _getCoordinates(_getPlaceId(searchInput));
		}
		catch (CommerceGeocoderException cge) {
			throw cge;
		}
		catch (Exception e) {
			throw new CommerceGeocoderException(e);
		}
	}

	@Override
	public double[] getCoordinates(
			String street, String city, String zip,
			CommerceRegion commerceRegion, CommerceCountry commerceCountry)
		throws CommerceGeocoderException {

		return null;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_apiKey = "<YOUR-API-KEY-HERE>";
	}

	@Deactivate
	protected void deactivate() {
		_apiKey = null;
	}

	private void _addParameter(StringBundler sb, String name, String value) {
		if (Validator.isNull(value)) {
			return;
		}

		sb.append(name);
		sb.append(CharPool.EQUAL);
		sb.append(URLCodec.encodeURL(value));
		sb.append(CharPool.AMPERSAND);
	}

	private double[] _getCoordinates(String placeId)
		throws Exception {

		Http.Options options = new Http.Options();

		String url = _getDetailsUrl(placeId);

		options.setLocation(url);

		String json = _http.URLtoString(options);

		Http.Response response = options.getResponse();

		int responseCode = response.getResponseCode();

		if (responseCode != HttpServletResponse.SC_OK) {
			throw new CommerceGeocoderException(
				"Google Place returned an error code (" +
					responseCode + StringPool.CLOSE_PARENTHESIS);
		}

		String xMsBmWsInfo = response.getHeader("X-MS-BM-WS-INFO");

		if (Validator.isNotNull(xMsBmWsInfo) && xMsBmWsInfo.equals("1")) {
			throw new CommerceGeocoderException(
				"Google Place is temporarily unavailable");
		}

		JSONObject jsonObject = _jsonFactory.createJSONObject(json);

		JSONObject resultObject = jsonObject.getJSONObject(
			"result");

		JSONObject geometryObject = resultObject.getJSONObject("geometry");

		JSONObject location = geometryObject.getJSONObject("location");

		double latitude = location.getDouble("lat", Double.MAX_VALUE);
		double longitude = location.getDouble("lng", Double.MAX_VALUE);

		return new double[] {latitude, longitude};
	}

	private String _getDetailsUrl(String placeId) {
		StringBundler sb = new StringBundler(28);

		sb.append("https://maps.googleapis.com/maps/api/place/details/json?");

		_addParameter(sb, "placeid", placeId);
		_addParameter(sb, "key", _apiKey);

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	private String _getFindUrl(String searchInput) {

		StringBundler sb = new StringBundler(28);

		sb.append("https://maps.googleapis.com/maps/api/place/findplacefromtext/json?");

		_addParameter(sb, "input", searchInput);
		_addParameter(sb, "inputtype", "textquery");
		_addParameter(sb, "key", _apiKey);

		sb.setIndex(sb.index() - 1);

		return sb.toString();
	}

	private String _getPlaceId(String searchInput)
		throws Exception {

		if (Validator.isNull(_apiKey)) {
			throw new CommerceGeocoderException(
				"No API key was given.");
		}

		Http.Options options = new Http.Options();

		String url = _getFindUrl(searchInput);

		options.setLocation(url);

		String json = _http.URLtoString(options);

		Http.Response response = options.getResponse();

		int responseCode = response.getResponseCode();

		if (responseCode != HttpServletResponse.SC_OK) {
			throw new CommerceGeocoderException(
				"Google returned an error code (" +
					responseCode + StringPool.CLOSE_PARENTHESIS);
		}

		String xMsBmWsInfo = response.getHeader("X-MS-BM-WS-INFO");

		if (Validator.isNotNull(xMsBmWsInfo) && xMsBmWsInfo.equals("1")) {
			throw new CommerceGeocoderException(
				"Google is temporarily unavailable");
		}

		JSONObject jsonObject = _jsonFactory.createJSONObject(json);

		JSONArray candidatesJSONArray = jsonObject.getJSONArray(
			"candidates");

		JSONObject resourceSetJSONObject = candidatesJSONArray.getJSONObject(
			0);

		return resourceSetJSONObject.getString("place_id");
	}

	private volatile String _apiKey;

	@Reference
	private Http _http;

	@Reference
	private JSONFactory _jsonFactory;

}