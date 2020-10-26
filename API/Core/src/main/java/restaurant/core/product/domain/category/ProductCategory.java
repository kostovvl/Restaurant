package restaurant.core.product.domain.category;

import restaurant.core.configuration.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "product_categories")
public class ProductCategory extends BaseEntity {

    private String name;

    public ProductCategory() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
