package mainpackage.configuration;

import mainpackage.security.DefaultUserDetailsService;
import mainpackage.security.UserSessionDetails;
import mainpackage.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * The type Irs security config.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    /** */
    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    /** */
    @Autowired
    private UserDetailsService userDetailsService;

    /** */
    @Autowired
    private LoginService loginSrv;
    /** */
    private String secIgnored;

    /**
     * Instantiates a new Irs security config.
     *
     * @param secIgnored the sec ignored
     */
    public SecurityConfig(@Value("${security.ignored:@null}") String secIgnored) {
        this.secIgnored = secIgnored;
    }

    /**
     * User details service user details service.
     *
     * @param loginSrv Login service
     * @return the user details service
     */
//    @Bean
//    public UserDetailsService userDetailsService(LoginService loginSrv) {
//        return new DefaultUserDetailsService(loginSrv);
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
//    }

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select login, password, enabled"
                        + " from logins where login=?")
                .authoritiesByUsernameQuery("select username, role "
                        + "from roles where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/searchclientbylogin/{clientLogin}","/editprofile")
                .hasRole("USER")
                .antMatchers("/hello")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .defaultSuccessUrl("/hello")
                ;
                //.httpBasic();

    }

    public void encodeString() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("12345"));

    }

//    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
    //}

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        if (secIgnored != null && !secIgnored.isEmpty()) {
//            http.authorizeRequests().antMatchers(secIgnored).permitAll();
//        }
//
//        http.
//                authorizeRequests().
//                antMatchers("/price/*").permitAll().
//                antMatchers("/login").permitAll().
//                antMatchers("/management/getStatus").permitAll().
//                anyRequest().authenticated().
//                and().
//                csrf().disable().
//                logout().logoutUrl("/logout").
//                permitAll().
//                logoutSuccessHandler((req, resp, auth) -> {
//                    if (auth != null) {
//                        try {
//                            SecurityContextHolder.getContext().setAuthentication(auth);
//
//                            UserSessionDetails ud = (UserSessionDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//                            loginSrv.logout(ud.getUser().getId());
//                        }
//                        catch (Exception e) {
//                            log.error("Error during logout.", e);
//                        }
//                    }
//
//                    // TODO:
////                resp.setStatus(HttpServletResponse.SC_OK);
////                resp.getWriter().print(RestStatus.NORMAL.getValue());
//                });
//    }

}
