package com.n26.models;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "petId",
        "quantity",
        "shipDate",
        "status",
        "complete"
})
public class Store {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("petId")
    private Long petId;
    @JsonProperty("quantity")
    private Long quantity;
    @JsonProperty("shipDate")
    private String shipDate;
    //    @JsonProperty("status")
//    private String status;
    @JsonProperty("complete")
    private boolean complete;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("status")
    private OrderStatus status;

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("petId")
    public Long getPetId() {
        return petId;
    }

    @JsonProperty("petId")
    public void setPetId(Long petId) {
        this.petId = petId;
    }

    @JsonProperty("quantity")
    public Long getQuantity() {
        return quantity;
    }

    @JsonProperty("quantity")
    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @JsonProperty("shipDate")
    public String getShipDate() {
        return shipDate;
    }

    @JsonProperty("shipDate")
    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    @JsonProperty("status")
    public OrderStatus getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @JsonProperty("complete")
    public boolean getComplete() {
        return complete;
    }

    @JsonProperty("complete")
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Store.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null) ? "<null>" : this.id));
        sb.append(',');
        sb.append("petId");
        sb.append('=');
        sb.append(((this.petId == null) ? "<null>" : this.petId));
        sb.append(',');
        sb.append("quantity");
        sb.append('=');
        sb.append(((this.quantity == null) ? "<null>" : this.quantity));
        sb.append(',');
        sb.append("shipDate");
        sb.append('=');
        sb.append(((this.shipDate == null) ? "<null>" : this.shipDate));
        sb.append(',');
        sb.append("status");
        sb.append('=');
        sb.append(((this.status == null) ? "<null>" : this.status));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null) ? "<null>" : this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result * 31) + ((this.petId == null) ? 0 : this.petId.hashCode()));
        result = ((result * 31) + ((this.quantity == null) ? 0 : this.quantity.hashCode()));
        result = ((result * 31) + ((this.id == null) ? 0 : this.id.hashCode()));
        result = ((result * 31) + ((this.additionalProperties == null) ? 0 : this.additionalProperties.hashCode()));
        result = ((result * 31) + ((this.shipDate == null) ? 0 : this.shipDate.hashCode()));
        result = ((result * 31) + ((this.status == null) ? 0 : this.status.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Store) == false) {
            return false;
        }
        Store rhs = ((Store) other);
        return ((((((((this.petId == rhs.petId) || ((this.petId != null) && this.petId.equals(rhs.petId))) && ((this.quantity == rhs.quantity) || ((this.quantity != null) && this.quantity.equals(rhs.quantity)))) && ((this.id == rhs.id) || ((this.id != null) && this.id.equals(rhs.id)))) && ((this.additionalProperties == rhs.additionalProperties) || ((this.additionalProperties != null) && this.additionalProperties.equals(rhs.additionalProperties)))) && ((this.shipDate == rhs.shipDate) || ((this.shipDate != null) && this.shipDate.equals(rhs.shipDate)))) && ((this.complete == rhs.complete)) && ((this.status == rhs.status) || ((this.status != null) && this.status.equals(rhs.status)))));
    }

}