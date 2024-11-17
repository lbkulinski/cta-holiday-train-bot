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


package app.cta4j.client.model;

import app.cta4j.client.invoker.JSON;
import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a bus and its schedule information.
 */
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2024-11-17T15:20:11.832778-06:00[America/Chicago]", comments = "Generator version: 7.7.0")
public class Bus {
  public static final String SERIALIZED_NAME_ID = "id";
  @SerializedName(SERIALIZED_NAME_ID)
  private Integer id;

  /**
   * Represents the type of stop event, indicating whether the vehicle is arriving at or departing from a stop.
   */
  @JsonAdapter(TypeEnum.Adapter.class)
  public enum TypeEnum {
    ARRIVAL("ARRIVAL"),
    
    DEPARTURE("DEPARTURE");

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static class Adapter extends TypeAdapter<TypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final TypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public TypeEnum read(final JsonReader jsonReader) throws IOException {
        String value =  jsonReader.nextString();
        return TypeEnum.fromValue(value);
      }
    }

    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      String value = jsonElement.getAsString();
      TypeEnum.fromValue(value);
    }
  }

  public static final String SERIALIZED_NAME_TYPE = "type";
  @SerializedName(SERIALIZED_NAME_TYPE)
  private TypeEnum type;

  public static final String SERIALIZED_NAME_STOP = "stop";
  @SerializedName(SERIALIZED_NAME_STOP)
  private String stop;

  public static final String SERIALIZED_NAME_ROUTE = "route";
  @SerializedName(SERIALIZED_NAME_ROUTE)
  private String route;

  public static final String SERIALIZED_NAME_DESTINATION = "destination";
  @SerializedName(SERIALIZED_NAME_DESTINATION)
  private String destination;

  public static final String SERIALIZED_NAME_PREDICTION_TIME = "predictionTime";
  @SerializedName(SERIALIZED_NAME_PREDICTION_TIME)
  private OffsetDateTime predictionTime;

  public static final String SERIALIZED_NAME_ARRIVAL_TIME = "arrivalTime";
  @SerializedName(SERIALIZED_NAME_ARRIVAL_TIME)
  private OffsetDateTime arrivalTime;

  public static final String SERIALIZED_NAME_DELAYED = "delayed";
  @SerializedName(SERIALIZED_NAME_DELAYED)
  private Boolean delayed;

  public Bus() {
  }

  public Bus id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * The unique identifier of the bus.
   * @return id
   */
  @jakarta.annotation.Nonnull
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public Bus type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Represents the type of stop event, indicating whether the vehicle is arriving at or departing from a stop.
   * @return type
   */
  @jakarta.annotation.Nonnull
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }


  public Bus stop(String stop) {
    this.stop = stop;
    return this;
  }

  /**
   * The stop where the bus will next arrive or depart.
   * @return stop
   */
  @jakarta.annotation.Nonnull
  public String getStop() {
    return stop;
  }

  public void setStop(String stop) {
    this.stop = stop;
  }


  public Bus route(String route) {
    this.route = route;
    return this;
  }

  /**
   * The route on which the bus is operating.
   * @return route
   */
  @jakarta.annotation.Nonnull
  public String getRoute() {
    return route;
  }

  public void setRoute(String route) {
    this.route = route;
  }


  public Bus destination(String destination) {
    this.destination = destination;
    return this;
  }

  /**
   * The final destination of the bus.
   * @return destination
   */
  @jakarta.annotation.Nonnull
  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }


  public Bus predictionTime(OffsetDateTime predictionTime) {
    this.predictionTime = predictionTime;
    return this;
  }

  /**
   * The time when the arrival or departure prediction was made.
   * @return predictionTime
   */
  @jakarta.annotation.Nonnull
  public OffsetDateTime getPredictionTime() {
    return predictionTime;
  }

  public void setPredictionTime(OffsetDateTime predictionTime) {
    this.predictionTime = predictionTime;
  }


  public Bus arrivalTime(OffsetDateTime arrivalTime) {
    this.arrivalTime = arrivalTime;
    return this;
  }

  /**
   * The estimated arrival or departure time of the bus at the stop.
   * @return arrivalTime
   */
  @jakarta.annotation.Nonnull
  public OffsetDateTime getArrivalTime() {
    return arrivalTime;
  }

  public void setArrivalTime(OffsetDateTime arrivalTime) {
    this.arrivalTime = arrivalTime;
  }


  public Bus delayed(Boolean delayed) {
    this.delayed = delayed;
    return this;
  }

  /**
   * Indicates whether the bus is delayed.
   * @return delayed
   */
  @jakarta.annotation.Nonnull
  public Boolean getDelayed() {
    return delayed;
  }

  public void setDelayed(Boolean delayed) {
    this.delayed = delayed;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Bus bus = (Bus) o;
    return Objects.equals(this.id, bus.id) &&
        Objects.equals(this.type, bus.type) &&
        Objects.equals(this.stop, bus.stop) &&
        Objects.equals(this.route, bus.route) &&
        Objects.equals(this.destination, bus.destination) &&
        Objects.equals(this.predictionTime, bus.predictionTime) &&
        Objects.equals(this.arrivalTime, bus.arrivalTime) &&
        Objects.equals(this.delayed, bus.delayed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, stop, route, destination, predictionTime, arrivalTime, delayed);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Bus {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    stop: ").append(toIndentedString(stop)).append("\n");
    sb.append("    route: ").append(toIndentedString(route)).append("\n");
    sb.append("    destination: ").append(toIndentedString(destination)).append("\n");
    sb.append("    predictionTime: ").append(toIndentedString(predictionTime)).append("\n");
    sb.append("    arrivalTime: ").append(toIndentedString(arrivalTime)).append("\n");
    sb.append("    delayed: ").append(toIndentedString(delayed)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


  public static HashSet<String> openapiFields;
  public static HashSet<String> openapiRequiredFields;

  static {
    // a set of all properties/fields (JSON key names)
    openapiFields = new HashSet<String>();
    openapiFields.add("id");
    openapiFields.add("type");
    openapiFields.add("stop");
    openapiFields.add("route");
    openapiFields.add("destination");
    openapiFields.add("predictionTime");
    openapiFields.add("arrivalTime");
    openapiFields.add("delayed");

    // a set of required properties/fields (JSON key names)
    openapiRequiredFields = new HashSet<String>();
    openapiRequiredFields.add("id");
    openapiRequiredFields.add("type");
    openapiRequiredFields.add("stop");
    openapiRequiredFields.add("route");
    openapiRequiredFields.add("destination");
    openapiRequiredFields.add("predictionTime");
    openapiRequiredFields.add("arrivalTime");
    openapiRequiredFields.add("delayed");
  }

  /**
   * Validates the JSON Element and throws an exception if issues found
   *
   * @param jsonElement JSON Element
   * @throws IOException if the JSON Element is invalid with respect to Bus
   */
  public static void validateJsonElement(JsonElement jsonElement) throws IOException {
      if (jsonElement == null) {
        if (!Bus.openapiRequiredFields.isEmpty()) { // has required fields but JSON element is null
          throw new IllegalArgumentException(String.format("The required field(s) %s in Bus is not found in the empty JSON string", Bus.openapiRequiredFields.toString()));
        }
      }

      Set<Map.Entry<String, JsonElement>> entries = jsonElement.getAsJsonObject().entrySet();
      // check to see if the JSON string contains additional fields
      for (Map.Entry<String, JsonElement> entry : entries) {
        if (!Bus.openapiFields.contains(entry.getKey())) {
          throw new IllegalArgumentException(String.format("The field `%s` in the JSON string is not defined in the `Bus` properties. JSON: %s", entry.getKey(), jsonElement.toString()));
        }
      }

      // check to make sure all required properties/fields are present in the JSON string
      for (String requiredField : Bus.openapiRequiredFields) {
        if (jsonElement.getAsJsonObject().get(requiredField) == null) {
          throw new IllegalArgumentException(String.format("The required field `%s` is not found in the JSON string: %s", requiredField, jsonElement.toString()));
        }
      }
        JsonObject jsonObj = jsonElement.getAsJsonObject();
      if (!jsonObj.get("type").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `type` to be a primitive type in the JSON string but got `%s`", jsonObj.get("type").toString()));
      }
      // validate the required field `type`
      TypeEnum.validateJsonElement(jsonObj.get("type"));
      if (!jsonObj.get("stop").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `stop` to be a primitive type in the JSON string but got `%s`", jsonObj.get("stop").toString()));
      }
      if (!jsonObj.get("route").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `route` to be a primitive type in the JSON string but got `%s`", jsonObj.get("route").toString()));
      }
      if (!jsonObj.get("destination").isJsonPrimitive()) {
        throw new IllegalArgumentException(String.format("Expected the field `destination` to be a primitive type in the JSON string but got `%s`", jsonObj.get("destination").toString()));
      }
  }

  public static class CustomTypeAdapterFactory implements TypeAdapterFactory {
    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
       if (!Bus.class.isAssignableFrom(type.getRawType())) {
         return null; // this class only serializes 'Bus' and its subtypes
       }
       final TypeAdapter<JsonElement> elementAdapter = gson.getAdapter(JsonElement.class);
       final TypeAdapter<Bus> thisAdapter
                        = gson.getDelegateAdapter(this, TypeToken.get(Bus.class));

       return (TypeAdapter<T>) new TypeAdapter<Bus>() {
           @Override
           public void write(JsonWriter out, Bus value) throws IOException {
             JsonObject obj = thisAdapter.toJsonTree(value).getAsJsonObject();
             elementAdapter.write(out, obj);
           }

           @Override
           public Bus read(JsonReader in) throws IOException {
             JsonElement jsonElement = elementAdapter.read(in);
             validateJsonElement(jsonElement);
             return thisAdapter.fromJsonTree(jsonElement);
           }

       }.nullSafe();
    }
  }

  /**
   * Create an instance of Bus given an JSON string
   *
   * @param jsonString JSON string
   * @return An instance of Bus
   * @throws IOException if the JSON string is invalid with respect to Bus
   */
  public static Bus fromJson(String jsonString) throws IOException {
    return JSON.getGson().fromJson(jsonString, Bus.class);
  }

  /**
   * Convert an instance of Bus to an JSON string
   *
   * @return JSON string
   */
  public String toJson() {
    return JSON.getGson().toJson(this);
  }
}

