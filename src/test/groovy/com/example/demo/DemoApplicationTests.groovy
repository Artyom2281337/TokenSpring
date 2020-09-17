package com.example.demo

import com.example.demo.entities.Token
import com.example.demo.repository.TokenRepository
import com.example.demo.request.TokenCreateDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

import javax.transaction.Transactional

import static org.hamcrest.Matchers.hasSize
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.junit.Assert.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class DemoApplicationTests {

    @Autowired
    private MockMvc mockMvc

    @Autowired
    private ObjectMapper objectMapper

    @Autowired
    private TokenRepository tokenRepository

    private static final String CREATE = "/token/create";
    private static final String UPDATE = "/token/update/{id}"
    private static final String DELETE = "/token/delete/{id}"
    private static final String GET = "/token/{id}"
    private static final String GET_ALL = "/token/all"

    @Test
    @Transactional
    void createToken() {
        given:
        def request = new TokenCreateDTO();
        request.customerKey = "1"
        request.taskId = "2"
        request.tokenUniqueReference = "a"
        request.panUniqueReference = "b"
        request.tokenPanSuffix = "c"
        request.accountPanSuffix = "d"
        request.tokenExpiry = "e"
        request.accountPanExpiry = "f"
        request.dsrpCapable = "g"
        request.tokenAssuranceLevel = "h"
        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.post(CREATE)
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
        then:
        response.andExpect(status().isOk())
        def cdcf = tokenRepository.findAll()
        assertEquals(request.customerKey, cdcf.get(0).customerKey)
        assertEquals(request.taskId, cdcf.get(0).taskId)
        assertEquals(request.tokenUniqueReference, cdcf.get(0).tokenUniqueReference)
        assertEquals(request.panUniqueReference, cdcf.get(0).panUniqueReference)
        assertEquals(request.tokenPanSuffix, cdcf.get(0).tokenPanSuffix)
        assertEquals(request.accountPanSuffix, cdcf.get(0).accountPanSuffix)
        assertEquals(request.tokenExpiry, cdcf.get(0).tokenExpiry)
        assertEquals(request.accountPanExpiry, cdcf.get(0).accountPanExpiry)
        assertEquals(request.dsrpCapable, cdcf.get(0).dsrpCapable)
        assertEquals(request.tokenAssuranceLevel, cdcf.get(0).tokenAssuranceLevel)

    }

    @Test
    @Transactional
    void updateToken(){
        given:
        def request = new TokenCreateDTO();
        request.customerKey = "11"
        request.taskId = "22"
        request.tokenUniqueReference = "aa"
        request.panUniqueReference = "bb"
        request.tokenPanSuffix = "cc"
        request.accountPanSuffix = "dd"
        request.tokenExpiry = "ee"
        request.accountPanExpiry = "ff"
        request.dsrpCapable = "gg"
        request.tokenAssuranceLevel = "hh"


        def token = new Token()
        token.setCustomerKey("0");
        token.setTaskId("0");
        token.setTokenUniqueReference("0");
        token.setPanUniqueReference("0");
        token.setTokenPanSuffix("0");
        token.setAccountPanSuffix("0");
        token.setTokenExpiry("0");
        token.setAccountPanExpiry("0");
        token.setDsrpCapable("0");
        token.setTokenAssuranceLevel("0");
        tokenRepository.save(token)

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.put(UPDATE, token.getId())
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON))
        then:
        response.andExpect(status().isOk())

        def cdcf = tokenRepository.findById(token.getId())

        assertEquals(request.customerKey, cdcf.get().customerKey)
        assertEquals(request.taskId, cdcf.get().taskId)
        assertEquals(request.tokenUniqueReference, cdcf.get().tokenUniqueReference)
        assertEquals(request.panUniqueReference, cdcf.get().panUniqueReference)
        assertEquals(request.tokenPanSuffix, cdcf.get().tokenPanSuffix)
        assertEquals(request.accountPanSuffix, cdcf.get().accountPanSuffix)
        assertEquals(request.tokenExpiry, cdcf.get().tokenExpiry)
        assertEquals(request.accountPanExpiry, cdcf.get().accountPanExpiry)
        assertEquals(request.dsrpCapable, cdcf.get().dsrpCapable)
        assertEquals(request.tokenAssuranceLevel, cdcf.get().tokenAssuranceLevel)
    }

    @Test
    @Transactional
    void deleteToken(){
        def token = new Token()
        token.setCustomerKey("0");
        token.setTaskId("0");
        token.setTokenUniqueReference("0");
        token.setPanUniqueReference("0");
        token.setTokenPanSuffix("0");
        token.setAccountPanSuffix("0");
        token.setTokenExpiry("0");
        token.setAccountPanExpiry("0");
        token.setDsrpCapable("0");
        token.setTokenAssuranceLevel("0");
        tokenRepository.save(token)

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.delete(DELETE, token.getId()))
        then:
        response.andExpect(status().isOk())

        def cdcf = tokenRepository.findById(token.getId()).orElse(null)

        assertEquals(null, cdcf)
    }

    @Test
    @Transactional
    void getToken(){
        def token = new Token();
        token.setCustomerKey("0");
        token.setTaskId("0");
        token.setTokenUniqueReference("0");
        token.setPanUniqueReference("0");
        token.setTokenPanSuffix("0");
        token.setAccountPanSuffix("0");
        token.setTokenExpiry("0");
        token.setAccountPanExpiry("0");
        token.setDsrpCapable("0");
        token.setTokenAssuranceLevel("0");
        tokenRepository.save(token)

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get(GET, token.getId())
                .contentType(MediaType.APPLICATION_JSON))
        then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$.customer_key').value("0"))
                .andExpect(jsonPath('$.task_id').value("0"))
                .andExpect(jsonPath('$.token_unique_reference').value("0"))
                .andExpect(jsonPath('$.pan_unique_reference').value("0"))
                .andExpect(jsonPath('$.token_pan_suffix').value("0"))
                .andExpect(jsonPath('$.account_pan_suffix').value("0"))
                .andExpect(jsonPath('$.token_expiry').value("0"))
                .andExpect(jsonPath('$.account_pan_expiry').value("0"))
                .andExpect(jsonPath('$.dsrp_capable').value("0"))
                .andExpect(jsonPath('$.token_assurance_level').value("0"))

    }

    @Test
    @Transactional
    void getTokens(){
        List<Token> tokenList = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            def token = new Token();
            token.setCustomerKey(i.toString());
            token.setTaskId(i.toString());
            token.setTokenUniqueReference(i.toString());
            token.setPanUniqueReference(i.toString());
            token.setTokenPanSuffix(i.toString());
            token.setAccountPanSuffix(i.toString());
            token.setTokenExpiry(i.toString());
            token.setAccountPanExpiry(i.toString());
            token.setDsrpCapable(i.toString());
            token.setTokenAssuranceLevel(i.toString());
            tokenList.add(token)
        }

        tokenRepository.saveAll(tokenList)

        when:
        def response = mockMvc.perform(MockMvcRequestBuilders.get(GET_ALL)
                .contentType(MediaType.APPLICATION_JSON))
        then:
        response.andExpect(status().isOk())
                .andExpect(jsonPath('$', hasSize(3)))
                .andExpect(jsonPath('$.[0].customer_key').value("0"))
                .andExpect(jsonPath('$.[0].task_id').value("0"))
                .andExpect(jsonPath('$.[1].token_unique_reference').value("1"))
                .andExpect(jsonPath('$.[1].pan_unique_reference').value("1"))
                .andExpect(jsonPath('$.[2].token_pan_suffix').value("2"))
                .andExpect(jsonPath('$.[0].account_pan_suffix').value("0"))
                .andExpect(jsonPath('$.[2].token_expiry').value("2"))
    }
}
