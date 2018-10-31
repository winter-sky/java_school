package mainpackage.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name="clients")
public class Clients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private int clientId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "email")
    private String email;

    @OneToOne
    @JoinColumn(name ="client_login")
    private Logins login;

    @ManyToOne
    @JoinColumn(name="client_address")
    private ClientAddresses clientAddress;

    @OneToMany(mappedBy = "client")
    private List<Orders> orders;

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Logins getLogin() {
        return login;
    }

    public void setLogin(Logins login) {
        this.login = login;
    }

    public ClientAddresses getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(ClientAddresses clientAddress) {
        this.clientAddress = clientAddress;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
