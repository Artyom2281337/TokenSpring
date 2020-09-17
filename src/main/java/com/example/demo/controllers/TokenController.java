package com.example.demo.controllers;

import com.example.demo.entities.Token;
import com.example.demo.request.TokenCreateDTO;
import com.example.demo.service.TokenizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "token", produces = MediaType.APPLICATION_JSON_VALUE)
public class TokenController {

    private final TokenizeService tokenizeService;

    @PostMapping("/create")
    public void createToken(@Valid @RequestBody TokenCreateDTO request){
        tokenizeService.create(request);
    }

    @GetMapping("/{id}")
    public TokenCreateDTO getToken(@PathVariable("id") Long id){
        return tokenizeService.read(id);
    }

    @GetMapping("/all")
    public List<TokenCreateDTO> getTokens(){
        return tokenizeService.readAll();
    }

    @GetMapping("/all/foreach")
    public List<TokenCreateDTO> getTokensWithForEach(){
        return tokenizeService.readAllWithForEach();
    }

    @PutMapping("/update/{id}")
    public void updateToken(@Valid @RequestBody TokenCreateDTO request,
                            @PathVariable(value = "id") Long id){
        tokenizeService.update(request, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteToken(@PathVariable(value = "id") Long id){
        tokenizeService.delete(id);
    }

}
