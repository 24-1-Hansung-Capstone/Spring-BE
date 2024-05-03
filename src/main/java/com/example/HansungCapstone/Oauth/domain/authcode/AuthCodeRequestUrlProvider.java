package com.example.HansungCapstone.Oauth.domain.authcode;

import com.example.HansungCapstone.Oauth.domain.OauthServerType;

public interface AuthCodeRequestUrlProvider {

    OauthServerType supportServer();

    String provide();
}
