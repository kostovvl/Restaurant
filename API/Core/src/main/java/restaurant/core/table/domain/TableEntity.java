package restaurant.core.table.domain;

import restaurant.core.configuration.BaseEntity;
import restaurant.core.user.domain.userEntity.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "tables")
public class TableEntity extends BaseEntity {

    private int number;
    private UserEntity waiter;

    public TableEntity() {
    }

    public TableEntity(int number) {
        this.number = number;
    }

    @Column(name = "number")
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @ManyToOne()
    @JoinColumn(name = "waiter_id", referencedColumnName = "id")
    public UserEntity getWaiter() {
        return waiter;
    }

    public void setWaiter(UserEntity waiter) {
        this.waiter = waiter;
    }
}
