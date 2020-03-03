package com.example.demo.album.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Locale {
    KO("ko"),
    EN("en"),
    JA("ja");

    private String code;

    public String getCode() {
        return this.code;
    }

    public static Locale getEnum(String code) {
        for(Locale v : values())
            if(v.getCode().equalsIgnoreCase(code)) return v;
        throw new IllegalArgumentException("존재하지 않는 코드 값 입니다.");
    }
}
