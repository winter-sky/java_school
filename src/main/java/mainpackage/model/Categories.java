package mainpackage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name="categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_level")
    private int categoryLevel;

    @Column(name = "parent_id",insertable = false, updatable = false)
    private Integer parentId;

    @ManyToOne
    @JoinColumn(name="parent_id")
    @JsonIgnore
    private Categories parent;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "parent")//
    @JsonIgnore
    private List<Categories> subCategories;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "category")//
    @JsonIgnore
    private List<Items> items;

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(int categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Categories getParent() {
        return parent;
    }

    public void setParent(Categories parent) {
        this.parent = parent;
    }

    public List<Categories> getSubCategories() {

        return subCategories;
    }

    public void setSubCategories(List<Categories> subCategories) {
        this.subCategories = subCategories;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Categories{" +
            "categoryId=" + categoryId +
            ", categoryName='" + categoryName + '\'' +
            ", categoryLevel=" + categoryLevel +
            ", parentId=" + parentId +
            ", parent=" + (parent != null ? parent.categoryName + '(' + parentId + ')' : null) +
            ", subCategories=" + (subCategories != null ? subCategories.stream().map(sc -> sc.categoryName +
            '(' + sc.categoryId + ')').collect(Collectors.joining(",")) : null) +
            ", items=" + (items != null ? items.stream().map(i -> i.getItemName() +
            '(' + i.getItemId() + ')').collect(Collectors.joining(",")) : null) +
        '}';
    }
}
