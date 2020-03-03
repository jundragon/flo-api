package com.example.demo.album.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SearchCondition {
    @NotEmpty
    private String title;
    @NotEmpty
    private String locale;
}
