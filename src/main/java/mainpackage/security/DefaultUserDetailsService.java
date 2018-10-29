package mainpackage.security;

import mainpackage.model.Logins;
import mainpackage.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * The type Default user details service.
 */
@Service
public class DefaultUserDetailsService implements UserDetailsService {
    /** */
    private static final Logger log = LoggerFactory.getLogger(DefaultUserDetailsService.class);

    /** */
    private static final String REGISTERED_USER_ROLE = "ROLE_REGISTERED";

    /** */
    private final LoginService loginSrv;

    /**
     * Instantiates a new Default user details service.
     *
     * @param loginSrv the db
     */
    @Autowired
    public DefaultUserDetailsService(LoginService loginSrv) {
        this.loginSrv = loginSrv;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Logins login;

        try {
            login = loginSrv.getLogin(username);
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("User '" + username + "' was not found.", e);
        }

        if (log.isInfoEnabled()) {
            log.info("User logged in [id={}, username={}]", login.getLoginId(), login.getLogin());
        }

        return new UserSessionDetails(
                new User(login.getLoginId(), login.getLogin(), login.getPassword()),
                Collections.singletonList(new SimpleGrantedAuthority(REGISTERED_USER_ROLE))
        );
    }
}

