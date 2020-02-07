package com.example.flo.songs.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CountryCode {
    KO("ko"),
    EN("en"),
    JA("ja");

    private String code;
    public String getCode() {
        return this.code;
    }

    public static CountryCode getEnum(String code) {
        for(CountryCode v : values())
            if(v.getCode().equalsIgnoreCase(code)) return v;
        throw new IllegalArgumentException();
    }
}
