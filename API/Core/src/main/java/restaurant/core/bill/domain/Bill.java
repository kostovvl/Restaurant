package restaurant.core.bill.domain;
import restaurant.core.configuration.BaseEntity;
import restaurant.core.table.domain.TableEntity;
import restaurant.core.user.domain.userEntity.UserEntity;

import javax.persistence.*;
import java.util.Map;

@Entity
@Table(name = "bills")
public class Bill extends BaseEntity {

    private UserEntity waiter;
    private TableEntity table;
    private Map<Long, Integer> products;
    private double totalPrice;

    public Bill() {
    }

    @ManyToOne()
    @JoinColumn(name = "waiter_id", referencedColumnName = "id")
    public UserEntity getWaiter() {
        return waiter;
    }

    public void setWaiter(UserEntity waiter) {
        this.waiter = waiter;
    }

    @ManyToOne()
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    public TableEntity getTable() {
        return table;
    }

    public void setTable(TableEntity table) {
        this.table = table;
    }

    @Column(name = "products")
    @ElementCollection()
    public Map<Long, Integer> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, Integer> products) {
        this.products = products;
    }

    @Column(name = "total_price")
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void addProduct(long productId, int quantity) {
        this.products.putIfAbsent(productId, 0);
            int currentQuantity = this.products.get(productId);
            this.products.put(productId, currentQuantity + quantity);

    }

    public void deleteProduct(long productId, double price) {

        double priceToSubtract = this.products.get(productId) * price;

        this.products.remove(productId);
        this.removeFromToTotalPrice(priceToSubtract);
    }

    public void addToTotalPrice(double price) {
        this.totalPrice += price;
    }

    public void removeFromToTotalPrice(double price) {
        this.totalPrice -= price;
    }
}
