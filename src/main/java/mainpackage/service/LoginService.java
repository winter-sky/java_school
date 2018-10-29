package mainpackage.service;

import mainpackage.model.Logins;

public interface LoginService {
    Logins getLogin(String username);

    void login(long loginId);

    void logout(long loginId);

    boolean isAdmin(long loginId);
}

