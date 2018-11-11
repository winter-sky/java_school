package mainpackage.dto;

public class ItemStatDTO {
    private ItemDTO item;

    private int quantitiesSold;

    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public int getQuantitiesSold() {
        return quantitiesSold;
    }

    public void setQuantitiesSold(int quantitiesSold) {
        this.quantitiesSold = quantitiesSold;
    }

    @Override
    public String toString() {
        return "ItemStatDTO{" +
            "item=" + item +
            ", quantitiesSold=" + quantitiesSold +
            '}';
    }
}
