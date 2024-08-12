package com.backend.ingresso.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenSentToEmailDTO {
    private Boolean TokenSentToEmail;
    private String MessageToken;

    public TokenSentToEmailDTO(Boolean tokenSentToEmail, String messageToken) {
        TokenSentToEmail = tokenSentToEmail;
        MessageToken = messageToken;
    }

    public Boolean getTokenAlreadyVisualized() {
        return TokenSentToEmail;
    }

    public String getMessageToken() {
        return MessageToken;
    }
}
