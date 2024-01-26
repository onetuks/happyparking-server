package com.onetuks.happyparkingserver.auth.oauth.strategy;

import com.onetuks.happyparkingserver.auth.model.Member;

public interface ClientProviderStrategy {

    Member getUserData(String accessToken);

}
