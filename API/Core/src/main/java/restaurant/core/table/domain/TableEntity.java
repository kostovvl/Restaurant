package restaurant.core.table.domain;

import restaurant.core.configuration.BaseEntity;
import restaurant.core.user.domain.userEntity.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "tables")
public class TableEntity extends BaseEntity {

    private int number;
    private long waiterId;

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

    @Column(name = "waiter_id")
    public long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(long waiterId) {
        this.waiterId = waiterId;
    }
}
