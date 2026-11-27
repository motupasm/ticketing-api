package com.motupa.ticketing.response;

public record TokenResponse(String accessToken , String tokenType , long expiresInSeconds) {

}
