package com.caglayan.urlshortener.service;

import com.caglayan.urlshortener.model.Url;
import com.caglayan.urlshortener.model.dto.UrlDto;

import java.util.List;

public interface UrlService {

    List<Url> listAll();

    void checkCodeDuration();

    Url shortUrl(UrlDto urlDto);

    Url findByCode(String code);
}
