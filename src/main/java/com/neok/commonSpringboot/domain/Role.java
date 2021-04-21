package com.neok.commonSpringboot.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	ROLE_LOGIN("ROLE_LOGIN"),
    ROLE_1("ROLE_TEMP_1"),
    ROLE_2("ROLE_TEMP_2"),
    ROLE_3("ROLE_TEMP_3");

    private String value;
}