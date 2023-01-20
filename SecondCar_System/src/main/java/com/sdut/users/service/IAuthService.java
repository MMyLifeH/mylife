package com.sdut.users.service;

import com.sdut.users.pojo.Message;

import javax.servlet.http.HttpServletRequest;

public interface IAuthService {
    public Message addAuthInfo(HttpServletRequest request);

    public Message getAuthInfo(String authToken, String userId, boolean isScan);

    public Message setAuthState(String authToken, String userId);
}
