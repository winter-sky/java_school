package mainpackage.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="roles")
public class Roles {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "role_id")
//    private int rolesId;

//    @ManyToOne
//    @JoinColumn(name="logins_id")
//    private Logins login;
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "role")
    private List<Logins> logins;



//    public int getRolesId() {
//        return rolesId;
//    }
//
//    public void setRolesId(int rolesId) {
//        this.rolesId = rolesId;
//    }

//    public Logins getLogin() {
//        return login;
//    }
//
//    public void setLogin(Logins login) {
//        this.login = login;
//    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Logins> getLogins() {
        return logins;
    }

    public void setLogins(List<Logins> logins) {
        this.logins = logins;
    }
}
