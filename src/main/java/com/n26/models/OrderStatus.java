package com.n26.models;

public enum OrderStatus {
    placed("placed"),
    approved("approved"),
    delivered("delivered");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
