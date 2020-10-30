package restaurant.core.product.domain.category;

import restaurant.core.configuration.BaseDto;

public class ProductCategoryDto extends BaseDto {

    private String name;

    public ProductCategoryDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
