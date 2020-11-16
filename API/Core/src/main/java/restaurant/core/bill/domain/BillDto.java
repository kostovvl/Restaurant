package restaurant.core.bill.domain;
import restaurant.core.configuration.BaseDto;

import java.util.Map;

public class BillDto extends BaseDto {

    private long waiterId;
    private long tableId;
    private long tableNumber;
    private Map<Long, ProductInfoDto> products;
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

    public Map<Long, ProductInfoDto> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, ProductInfoDto> products) {
        this.products = products;
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
