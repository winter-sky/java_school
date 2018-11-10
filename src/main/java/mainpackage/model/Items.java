package mainpackage.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="items")
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "price")
    private double price;

    @Column(name = "weight")
    private double weight;

    @Column(name = "volume")
    private String volume;

    @Column(name = "available_count")
    private int availableCount;

    @Column(name = "pic")
    private String pic;

    @ManyToOne
    @JoinColumn(name="item_category")
    private Categories category;

    @OneToOne(cascade = { CascadeType.ALL })//??
    @JoinColumn(name="params_id")
    private Params params;

    @OneToMany(mappedBy = "item")
    private List<OrderItems> orderItems;

    @Override
    public String toString() {
        return "Items [id=" + itemId + ",item name= " + itemName + ", category= "+category+"]";
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getAvailableCount() {
        return availableCount;
    }

    public void setAvailableCount(int availableCount) {
        this.availableCount = availableCount;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Params getParams() {
        return params;
    }

    public void setParams(Params params) {
        this.params = params;
    }

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }
}
