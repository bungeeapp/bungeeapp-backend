package com.bungeeinc.bungeeapp.api.service.model.endpoint.account.update.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UpdateAccountRequest {
    String username;
    String password;
    Boolean deleted;
    Boolean enabled;
}
