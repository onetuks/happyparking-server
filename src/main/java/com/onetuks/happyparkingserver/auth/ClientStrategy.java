package com.onetuks.happyparkingserver.auth;

public interface ClientStrategy {

    Member getUserData(String accessToken);

}
