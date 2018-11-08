package mainpackage.dto;

import mainpackage.model.Categories;

public class ItemDTO {

    private int itemId;
    private String itemName;
    private double price;
    private double weight;
    private String volume;
    private int availableCount;
    private String pic;
    private CategoryDTO category;
    private ParamDTO param;

    public ItemDTO() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", volume='" + volume + '\'' +
                ", availableCount=" + availableCount +
                ", pic='" + pic + '\'' +
                ", category=" + category +
                ", param=" + param +
                '}';
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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public ParamDTO getParam() {
        return param;
    }

    public void setParam(ParamDTO param) {
        this.param = param;
    }
}
