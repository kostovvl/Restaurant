package restaurant.core.product.domain.product;

import restaurant.core.configuration.BaseEntity;
import restaurant.core.product.domain.category.ProductCategory;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    private String name;
    private double price;
    private ProductCategory category;

    public Product() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }
}
