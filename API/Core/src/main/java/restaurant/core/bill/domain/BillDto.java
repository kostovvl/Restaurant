package restaurant.core.bill.domain;
import restaurant.core.configuration.BaseDto;

import java.util.Map;

public class BillDto extends BaseDto {

    private long waiterId;
    private long tableId;
    private long tableNumber;
    private Map<Long, Integer> products;
    private Map<Long, Double> productPrices;
    private Map<Long, String> productNames;
    private double totalPrice;

    public BillDto() {
    }

    public long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(long waiterId) {
        this.waiterId = waiterId;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

    public Map<Long, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, Integer> products) {
        this.products = products;
    }

    public Map<Long, Double> getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(Map<Long, Double> productPrices) {
        this.productPrices = productPrices;
    }

    public Map<Long, String> getProductNames() {
        return productNames;
    }

    public void setProductNames(Map<Long, String> productNames) {
        this.productNames = productNames;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(long tableNumber) {
        this.tableNumber = tableNumber;
    }
}
