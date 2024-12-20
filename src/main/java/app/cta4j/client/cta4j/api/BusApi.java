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
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public BusApi() {
        this(Configuration.getDefaultApiClient());
    }

    public BusApi(ApiClient apiClient) {
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
     * Build call for getUpcomingStops
     * @param id The unique ID of the bus. (required)
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 404 </td><td> Bus not found. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of upcoming stops. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getUpcomingStopsCall(Integer id, final ApiCallback _callback) throws ApiException {
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
        String localVarPath = "/api/buses/{id}/stops"
            .replace("{" + "id" + "}", localVarApiClient.escapeString(id.toString()));

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
    private okhttp3.Call getUpcomingStopsValidateBeforeCall(Integer id, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getUpcomingStops(Async)");
        }

        return getUpcomingStopsCall(id, _callback);

    }

    /**
     * Retrieve upcoming stops for a bus.
     * Retrieves a list of upcoming stops for a specific bus, identified by its ID.
     * @param id The unique ID of the bus. (required)
     * @return List&lt;Bus&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 404 </td><td> Bus not found. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of upcoming stops. </td><td>  -  </td></tr>
     </table>
     */
    public List<Bus> getUpcomingStops(Integer id) throws ApiException {
        ApiResponse<List<Bus>> localVarResp = getUpcomingStopsWithHttpInfo(id);
        return localVarResp.getData();
    }

    /**
     * Retrieve upcoming stops for a bus.
     * Retrieves a list of upcoming stops for a specific bus, identified by its ID.
     * @param id The unique ID of the bus. (required)
     * @return ApiResponse&lt;List&lt;Bus&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 404 </td><td> Bus not found. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of upcoming stops. </td><td>  -  </td></tr>
     </table>
     */
    public ApiResponse<List<Bus>> getUpcomingStopsWithHttpInfo(Integer id) throws ApiException {
        okhttp3.Call localVarCall = getUpcomingStopsValidateBeforeCall(id, null);
        Type localVarReturnType = new TypeToken<List<Bus>>(){}.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Retrieve upcoming stops for a bus. (asynchronously)
     * Retrieves a list of upcoming stops for a specific bus, identified by its ID.
     * @param id The unique ID of the bus. (required)
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details
     <table summary="Response Details" border="1">
        <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
        <tr><td> 404 </td><td> Bus not found. </td><td>  -  </td></tr>
        <tr><td> 500 </td><td> Internal server error. </td><td>  -  </td></tr>
        <tr><td> 200 </td><td> Successfully retrieved the list of upcoming stops. </td><td>  -  </td></tr>
     </table>
     */
    public okhttp3.Call getUpcomingStopsAsync(Integer id, final ApiCallback<List<Bus>> _callback) throws ApiException {

        okhttp3.Call localVarCall = getUpcomingStopsValidateBeforeCall(id, _callback);
        Type localVarReturnType = new TypeToken<List<Bus>>(){}.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
