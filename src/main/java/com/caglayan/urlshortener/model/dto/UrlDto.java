package com.caglayan.urlshortener.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UrlDto {

    private String url;
    private String code;
    private Boolean limitless;
}
