package br.com.lanchonete.mongo.entity;

import java.util.Arrays;

public enum StatusPaymentType {
    PENDING,
    REJECTED,
    PAID;

    public static StatusPaymentType get(String type) {
        return Arrays.stream(values()).filter(t -> t.name().equalsIgnoreCase(type)).findFirst().orElse(null);
    }

}
