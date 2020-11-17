package api.tableservice.table.domain;

import javax.persistence.*;

@Entity
@Table(name = "tables")
public class TableEntity {

    private long id;
    private int number;
    private long waiterId;

    public TableEntity() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
