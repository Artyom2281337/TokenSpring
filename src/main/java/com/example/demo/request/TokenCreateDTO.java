package com.example.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TokenCreateDTO {

    @NotEmpty
    @JsonProperty("customer_key")
    private String customerKey;

    @NotEmpty
    @JsonProperty("task_id")
    private String taskId;

    @NotEmpty
    @JsonProperty("token_unique_reference")
    private String tokenUniqueReference;

    @NotEmpty
    @JsonProperty("pan_unique_reference")
    private String panUniqueReference;

    @NotEmpty
    @JsonProperty("token_pan_suffix")
    private String tokenPanSuffix;

    @NotEmpty
    @JsonProperty("account_pan_suffix")
    private String accountPanSuffix;

    @NotEmpty
    @JsonProperty("token_expiry")
    private String tokenExpiry;

    @NotEmpty
    @JsonProperty("account_pan_expiry")
    private String accountPanExpiry;

    @NotEmpty
    @JsonProperty("dsrp_capable")
    private String dsrpCapable;

    @NotEmpty
    @JsonProperty("token_assurance_level")
    private String tokenAssuranceLevel;

}
