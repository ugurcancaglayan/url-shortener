package com.caglayan.urlshortener.service.impl;

import com.caglayan.urlshortener.model.Url;
import com.caglayan.urlshortener.model.dto.UrlDto;
import com.caglayan.urlshortener.repository.UrlRepository;
import com.caglayan.urlshortener.service.UrlService;
import com.caglayan.urlshortener.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Service
public class UrlServiceImpl implements UrlService {

    private final UrlRepository repository;

    @Override
    public List<Url> listAll() {
        return repository.findAll();
    }

    @Scheduled(fixedRate = 5000)
    @Override
    public void checkCodeDuration() {
        List<Url> urls = repository.getAllByLimitlessIsFalse();
        for (Url url : urls) {
            long diff = (new Date()).getTime() - (url.getCreationDate()).getTime();
            long day = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            if (day >= 1) {
                repository.delete(url);
            }
        }
    }

    @Override
    public Url shortUrl(UrlDto urlDto) {
        Url url = Url.builder()
                .url(urlDto.getUrl())
                .code(urlDto.getCode())
                .limitless(urlDto.getLimitless())
                .message("Code is generated with '" + urlDto.getCode() + "' characters")
                .build();

        while (repository.findByCode(url.getCode()) != null) {
            url.setCode(StringUtil.generateCode());
            url.setMessage(urlDto.getCode() + " is already using. " +
                    "Your link prepared with '" + url.getCode() + "' characters");
        }
        return repository.save(url);
    }

    @Override
    public Url findByCode(String code) {
        return repository.findByCode(code);
    }

}
