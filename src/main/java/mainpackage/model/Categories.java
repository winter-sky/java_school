package mainpackage.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="categories")
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catergory_id")
    private int categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_level")
    private int categoryLevel;

    @Column(name = "parent_id",insertable = false, updatable = false)
    private Integer parentId;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Categories category;

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "category")
    private List<Categories> categories;

    @Override
    public String toString() {
        return "catergory [id=" + categoryId + ", category_name = " + categoryName + ", category_level "+categoryLevel+
                ", parent_id "+parentId+"subcategories "+categories+"]";
    }

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

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public List<Categories> getCategories() {

        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }
}
