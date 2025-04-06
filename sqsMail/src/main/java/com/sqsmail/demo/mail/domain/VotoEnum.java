package com.sqsmail.demo.mail.domain;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum VotoEnum {
    SIM, NAO;

    @JsonCreator
    public static VotoEnum fromString(String value) {
        return VotoEnum.valueOf(value.toUpperCase());
    }
}
