package com.example.demo.service.Impl;

import com.example.demo.entities.Token;
import com.example.demo.exeptions.NotFoundExeption;
import com.example.demo.repository.TokenRepository;
import com.example.demo.request.TokenCreateDTO;
import com.example.demo.service.TokenizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenizeServiceImpl implements TokenizeService {

    private final TokenRepository tokenRepository;

    @Override
    public void create(TokenCreateDTO request) {
        Token token = new Token();
        token.setCustomerKey(request.getCustomerKey());
        token.setTaskId(request.getTaskId());
        token.setTokenUniqueReference(request.getTokenUniqueReference());
        token.setPanUniqueReference(request.getPanUniqueReference());
        token.setTokenPanSuffix(request.getTokenPanSuffix());
        token.setAccountPanSuffix(request.getAccountPanSuffix());
        token.setTokenExpiry(request.getTokenExpiry());
        token.setAccountPanExpiry(request.getAccountPanExpiry());
        token.setDsrpCapable(request.getDsrpCapable());
        token.setTokenAssuranceLevel(request.getTokenAssuranceLevel());
        tokenRepository.save(token);
    }

    @Override
    public TokenCreateDTO read(Long id) {
        Token token = tokenRepository.findById(id)
                .orElseThrow(NotFoundExeption::new);

        return createDTO(token);
    }

    public List<TokenCreateDTO> readAll(){
        List<Token> tokenList = tokenRepository.findAll();
        return tokenList.stream().map(this::createDTO).collect(Collectors.toList());
    }

    @Override
    public List<TokenCreateDTO> readAllWithForEach() {
        List<Token> tokenList = tokenRepository.findAll();

        List<TokenCreateDTO> tokenCreateDTOList = new ArrayList<>();
        tokenList.forEach(token -> tokenCreateDTOList.add(createDTO(token)));
        return tokenCreateDTOList;
    }

    @Override
    public void update(TokenCreateDTO request, Long id) {
        Token token = tokenRepository.findById(id)
                .orElseThrow(NotFoundExeption::new);
        token.setCustomerKey(request.getCustomerKey());
        token.setTaskId(request.getTaskId());
        token.setTokenUniqueReference(request.getTokenUniqueReference());
        token.setPanUniqueReference(request.getPanUniqueReference());
        token.setTokenPanSuffix(request.getTokenPanSuffix());
        token.setAccountPanSuffix(request.getAccountPanSuffix());
        token.setTokenExpiry(request.getTokenExpiry());
        token.setAccountPanExpiry(request.getAccountPanExpiry());
        token.setDsrpCapable(request.getDsrpCapable());
        token.setTokenAssuranceLevel(request.getTokenAssuranceLevel());
        tokenRepository.save(token);
    }

    @Override
    public void delete(Long id) {
        Token token = tokenRepository.findById(id).
                orElseThrow(NotFoundExeption::new);
        tokenRepository.delete(token);
    }

    private TokenCreateDTO createDTO(Token token){
        TokenCreateDTO tokenCreateDTO = new TokenCreateDTO();
        tokenCreateDTO.setCustomerKey(token.getCustomerKey());
        tokenCreateDTO.setTaskId(token.getTaskId());
        tokenCreateDTO.setTokenUniqueReference(token.getTokenUniqueReference());
        tokenCreateDTO.setPanUniqueReference(token.getPanUniqueReference());
        tokenCreateDTO.setTokenPanSuffix(token.getTokenPanSuffix());
        tokenCreateDTO.setAccountPanSuffix(token.getAccountPanSuffix());
        tokenCreateDTO.setTokenExpiry(token.getTokenExpiry());
        tokenCreateDTO.setAccountPanExpiry(token.getAccountPanExpiry());
        tokenCreateDTO.setDsrpCapable(token.getDsrpCapable());
        tokenCreateDTO.setTokenAssuranceLevel(token.getTokenAssuranceLevel());
        return tokenCreateDTO;
    }
}
