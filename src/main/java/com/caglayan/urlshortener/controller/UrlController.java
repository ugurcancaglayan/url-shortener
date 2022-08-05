package com.caglayan.urlshortener.controller;

import com.caglayan.urlshortener.model.Url;
import com.caglayan.urlshortener.model.dto.UrlDto;
import com.caglayan.urlshortener.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/url")
public class UrlController {

    private final UrlService urlService;

    @GetMapping
    public ResponseEntity<List<Url>> listAll() {
        return ResponseEntity.ok(urlService.listAll());
    }

    @PostMapping
    public ResponseEntity<Url> shortUrl(@Valid @RequestBody UrlDto urlDto) {
        return ResponseEntity.ok(urlService.shortUrl(urlDto));
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> redirect(@PathVariable String code) {
        Url url = urlService.findByCode(code);
        URI uri = URI.create(url.getUrl());

        return ResponseEntity.status(HttpStatus.SEE_OTHER)
                .location(uri)
                .build();
    }

}
