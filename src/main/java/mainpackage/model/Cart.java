package mainpackage.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;

    @ManyToMany(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinTable(
            name = "cart_items",
            joinColumns = { @JoinColumn(name = "id_cart") },
            inverseJoinColumns = { @JoinColumn(name = "id_item") }
    )
    private List<Items> items;

    @OneToOne
    @JoinColumn(name ="id_client")
    private Clients client;

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public  void addItem(Items item) {items.add(item);}

}
