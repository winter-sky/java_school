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
    private Integer clientId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birthdate")
    private Date birthDate;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="client_login")
    private Logins login;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "clients_client_addresses",
            joinColumns = { @JoinColumn(name = "id_clients") },
            inverseJoinColumns = { @JoinColumn(name = "id_client_addresses") }
    )
    private List<ClientAddresses> clientAddress;

    @OneToMany(mappedBy = "client")
    private List<Orders> orders;

    @OneToOne(mappedBy = "client")
    private Cart cart;

    public Integer getClientId() {
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

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public List<ClientAddresses> getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(List<ClientAddresses> clientAddress) {
        this.clientAddress = clientAddress;
    }
}
