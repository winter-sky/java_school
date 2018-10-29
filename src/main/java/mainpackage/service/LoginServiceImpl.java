package mainpackage.service;

import mainpackage.model.Logins;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginServiceImpl implements LoginService {
    /** */
    private static final Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    /** */
    private ConcurrentHashMap<Long, Boolean> logins = new ConcurrentHashMap();

    @Override
    public Logins getLogin(String username) {
        // TODO: get from database
        return null;
    }

    @Override
    public void login(long loginId) {
        // TODO: get from database
        Logins login = null;

        if (log.isDebugEnabled()) {
            log.debug("User registered  [id=" + loginId + " is-admin=" + false + ']');
        }

        // TODO: isAdmin
        logins.put((long)login.getLoginId(), false);
    }

    @Override
    public void logout(long loginId) {
        logins.remove(loginId);

        if (log.isDebugEnabled()) {
            log.debug("User logged out  [id=" + loginId + ']');
        }
    }

    @Override
    public boolean isAdmin(long loginId) {
        Boolean b = logins.get(loginId);

        if (b != null) {
            return b;
        }

        // TODO: from database
        boolean isAdmin = false;

        log.warn("User is not logged in [id=" + loginId + ", is-admin=" + isAdmin + ']');

        return isAdmin;
    }

    private Logins getLogin0(String username) {
        // TODO: get from database
        return null;
    }
}
