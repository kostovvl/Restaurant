package restaurant.core.bill.domain;

import restaurant.core.configuration.BaseDto;

public class ProductInfoDto extends BaseDto {

    private String name;
    private double price;

    public ProductInfoDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
