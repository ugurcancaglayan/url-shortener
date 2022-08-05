package com.caglayan.urlshortener.repository;

import com.caglayan.urlshortener.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {

    Url findByCode(String code);

    List<Url> getAllByLimitlessIsFalse();
}
