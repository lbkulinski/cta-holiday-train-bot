/*
 * OpenAPI definition
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: v0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package app.cta4j.client.cta4j.api;

import app.cta4j.client.cta4j.invoker.*;
import app.cta4j.client.cta4j.model.Bus;
import app.cta4j.client.cta4j.model.Route;
import app.cta4j.client.cta4j.model.Stop;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RouteApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public RouteApi() {
        this(Configuration.getDefaultApiClient());
    }

    public RouteApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for getArrivals1
     * @param routeId The unique ID of the route. (required)
     * @param stopId The unique ID of the stop. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 404 </td><td> Route or stop not found. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of upcoming bus arrivals. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getArrivals1Call(String routeId, Integer stopId, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/routes/{routeId}/stops/{stopId}/arrivals"
            .replace("{" + "routeId" + "}", localVarApiClient.escapeString(routeId.toString()))
            .replace("{" + "stopId" + "}", localVarApiClient.escapeString(stopId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getArrivals1ValidateBeforeCall(String routeId, Integer stopId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'routeId' is set
        if (routeId == null) {
            throw new ApiException("Missing the required parameter 'routeId' when calling getArrivals1(Async)");
        }

        // verify the required parameter 'stopId' is set
        if (stopId == null) {
            throw new ApiException("Missing the required parameter 'stopId' when calling getArrivals1(Async)");
        }

        return getArrivals1Call(routeId, stopId, _callback);

    }

    /**
     * Retrieve upcoming bus arrivals for a stop on a route.
     * Retrieves a list of upcoming bus arrivals for a specific stop on a route, identified by the route ID and stop ID.
     * @param routeId The unique ID of the route. (required)
     * @param stopId The unique ID of the stop. (required)
     * @return List&lt;Bus&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 404 </td><td> Route or stop not found. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of upcoming bus arrivals. </td><td>  -  </td></tr>
     </table>
     */
    public List<Bus> getArrivals1(String routeId, Integer stopId) throws ApiException {
        ApiResponse<List<Bus>> localVarResp = getArrivals1WithHttpInfo(routeId, stopId);
        return localVarResp.getData();
    }

    /**
     * Retrieve upcoming bus arrivals for a stop on a route.
     * Retrieves a list of upcoming bus arrivals for a specific stop on a route, identified by the route ID and stop ID.
     * @param routeId The unique ID of the route. (required)
     * @param stopId The unique ID of the stop. (required)
     * @return ApiResponse&lt;List&lt;Bus&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 404 </td><td> Route or stop not found. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of upcoming bus arrivals. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Bus>> getArrivals1WithHttpInfo(String routeId, Integer stopId) throws ApiException {
        okhttp3.Call localVarCall = getArrivals1ValidateBeforeCall(routeId, stopId, null);
        Type localVarReturnType = new TypeToken<List<Bus>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve upcoming bus arrivals for a stop on a route. (asynchronously)
     * Retrieves a list of upcoming bus arrivals for a specific stop on a route, identified by the route ID and stop ID.
     * @param routeId The unique ID of the route. (required)
     * @param stopId The unique ID of the stop. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 404 </td><td> Route or stop not found. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of upcoming bus arrivals. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getArrivals1Async(String routeId, Integer stopId, final ApiCallback<List<Bus>> _callback) throws ApiException {

        okhttp3.Call localVarCall = getArrivals1ValidateBeforeCall(routeId, stopId, _callback);
        Type localVarReturnType = new TypeToken<List<Bus>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getDirections
     * @param routeId The unique ID of the route. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 404 </td><td> Route not found. </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of directions for the route. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDirectionsCall(String routeId, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/routes/{routeId}/directions"
            .replace("{" + "routeId" + "}", localVarApiClient.escapeString(routeId.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getDirectionsValidateBeforeCall(String routeId, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'routeId' is set
        if (routeId == null) {
            throw new ApiException("Missing the required parameter 'routeId' when calling getDirections(Async)");
        }

        return getDirectionsCall(routeId, _callback);

    }

    /**
     *  Retrieve directions for a route.
     * Retrieves a list of directions (e.g., Northbound or Southbound) for a specific route, identified by the route ID.
     * @param routeId The unique ID of the route. (required)
     * @return List&lt;String&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 404 </td><td> Route not found. </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of directions for the route. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
     </table>
     */
    public List<String> getDirections(String routeId) throws ApiException {
        ApiResponse<List<String>> localVarResp = getDirectionsWithHttpInfo(routeId);
        return localVarResp.getData();
    }

    /**
     *  Retrieve directions for a route.
     * Retrieves a list of directions (e.g., Northbound or Southbound) for a specific route, identified by the route ID.
     * @param routeId The unique ID of the route. (required)
     * @return ApiResponse&lt;List&lt;String&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 404 </td><td> Route not found. </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of directions for the route. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<String>> getDirectionsWithHttpInfo(String routeId) throws ApiException {
        okhttp3.Call localVarCall = getDirectionsValidateBeforeCall(routeId, null);
        Type localVarReturnType = new TypeToken<List<String>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     *  Retrieve directions for a route. (asynchronously)
     * Retrieves a list of directions (e.g., Northbound or Southbound) for a specific route, identified by the route ID.
     * @param routeId The unique ID of the route. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 404 </td><td> Route not found. </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of directions for the route. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getDirectionsAsync(String routeId, final ApiCallback<List<String>> _callback) throws ApiException {

        okhttp3.Call localVarCall = getDirectionsValidateBeforeCall(routeId, _callback);
        Type localVarReturnType = new TypeToken<List<String>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getRoutes
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of routes. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getRoutesCall(final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/routes";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getRoutesValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        return getRoutesCall(_callback);

    }

    /**
     * Retrieve the list of routes.
     * Retrieves the list of all available routes in the system.
     * @return List&lt;Route&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of routes. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
     </table>
     */
    public List<Route> getRoutes() throws ApiException {
        ApiResponse<List<Route>> localVarResp = getRoutesWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * Retrieve the list of routes.
     * Retrieves the list of all available routes in the system.
     * @return ApiResponse&lt;List&lt;Route&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of routes. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Route>> getRoutesWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = getRoutesValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<List<Route>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve the list of routes. (asynchronously)
     * Retrieves the list of all available routes in the system.
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of routes. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getRoutesAsync(final ApiCallback<List<Route>> _callback) throws ApiException {

        okhttp3.Call localVarCall = getRoutesValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<List<Route>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
    /**
     * Build call for getStops
     * @param routeId The unique ID of the route. (required)
     * @param direction The direction of the route (e.g., Northbound or Southbound). (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of stops for the route and direction. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Route or direction not found. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getStopsCall(String routeId, String direction, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[] {  };

        // Determine Base Path to Use
        if (localCustomBaseUrl != null){
            basePath = localCustomBaseUrl;
        } else if ( localBasePaths.length > 0 ) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/api/routes/{routeId}/directions/{direction}/stops"
            .replace("{" + "routeId" + "}", localVarApiClient.escapeString(routeId.toString()))
            .replace("{" + "direction" + "}", localVarApiClient.escapeString(direction.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[] {  };
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call getStopsValidateBeforeCall(String routeId, String direction, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'routeId' is set
        if (routeId == null) {
            throw new ApiException("Missing the required parameter 'routeId' when calling getStops(Async)");
        }

        // verify the required parameter 'direction' is set
        if (direction == null) {
            throw new ApiException("Missing the required parameter 'direction' when calling getStops(Async)");
        }

        return getStopsCall(routeId, direction, _callback);

    }

    /**
     * Retrieve stops for a route and direction.
     * Retrieves a list of stops for a specific route and direction, identified by the route ID and direction.
     * @param routeId The unique ID of the route. (required)
     * @param direction The direction of the route (e.g., Northbound or Southbound). (required)
     * @return List&lt;Stop&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of stops for the route and direction. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Route or direction not found. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
     </table>
     */
    public List<Stop> getStops(String routeId, String direction) throws ApiException {
        ApiResponse<List<Stop>> localVarResp = getStopsWithHttpInfo(routeId, direction);
        return localVarResp.getData();
    }

    /**
     * Retrieve stops for a route and direction.
     * Retrieves a list of stops for a specific route and direction, identified by the route ID and direction.
     * @param routeId The unique ID of the route. (required)
     * @param direction The direction of the route (e.g., Northbound or Southbound). (required)
     * @return ApiResponse&lt;List&lt;Stop&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of stops for the route and direction. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Route or direction not found. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Stop>> getStopsWithHttpInfo(String routeId, String direction) throws ApiException {
        okhttp3.Call localVarCall = getStopsValidateBeforeCall(routeId, direction, null);
        Type localVarReturnType = new TypeToken<List<Stop>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve stops for a route and direction. (asynchronously)
     * Retrieves a list of stops for a specific route and direction, identified by the route ID and direction.
     * @param routeId The unique ID of the route. (required)
     * @param direction The direction of the route (e.g., Northbound or Southbound). (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of stops for the route and direction. </td><td>  -  </td></tr>
        <tr><td> 404 </td><td> Route or direction not found. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getStopsAsync(String routeId, String direction, final ApiCallback<List<Stop>> _callback) throws ApiException {

        okhttp3.Call localVarCall = getStopsValidateBeforeCall(routeId, direction, _callback);
        Type localVarReturnType = new TypeToken<List<Stop>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}