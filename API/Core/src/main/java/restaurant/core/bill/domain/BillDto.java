package restaurant.core.bill.domain;
import restaurant.core.configuration.BaseDto;
import restaurant.core.product.domain.product.ProductDto;

import java.util.Map;

public class BillDto extends BaseDto {

    private long waiterId;
    private long tableId;
    private Map<Long, Integer> products;
    private Map<Long, Double> productPrices;

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
}
