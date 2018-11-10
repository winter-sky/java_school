package mainpackage.model;

import mainpackage.type.Role;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="logins")
public class Logins {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "login_id")
    private int loginId;

    @Column(name = "login",insertable = false, updatable = false)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled")
    private boolean enabled;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="login")
    private Roles role;

    @OneToOne(mappedBy = "login")
    private Clients client;

    public int getLoginId() {
        return loginId;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//
//    public List<Roles> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Roles> roles) {
//        this.roles = roles;
//    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }
}
