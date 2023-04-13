package stu.najah.se.test.core;

import stu.najah.se.core.Authenticator;

class AuthenticatorMock
        implements Authenticator {

    private static AuthenticatorMock instance;

    private AuthenticatorMock() {
    }

    public static AuthenticatorMock getInstance() {
        if (instance == null) {
            instance = new AuthenticatorMock();
        }
        return instance;
    }

    private boolean loginRequested = false;
    private boolean logoutRequested = false;

    @Override
    public void login() {
        loginRequested = true;
    }

    @Override
    public void logout() {
        logoutRequested = true;
    }

    public void resetRequests() {
        loginRequested = false;
        logoutRequested = false;
    }

    public boolean isLoginRequested() {
        return loginRequested;
    }

    public boolean isLogoutRequested() {
        return logoutRequested;
    }
}
