package com.example.demo.service;

import com.example.demo.entities.Token;
import com.example.demo.request.TokenCreateDTO;

import java.util.List;

public interface TokenizeService {

    void create(TokenCreateDTO request);

    TokenCreateDTO read(Long id);

    List<TokenCreateDTO> readAll();

    List<TokenCreateDTO> readAllWithForEach();

    void update(TokenCreateDTO request, Long id);

    void delete(Long id);
}
