package restaurant.core.table.domain;

import restaurant.core.configuration.BaseDto;

public class TableEntityDto extends BaseDto {

    private int number;
    private long waiterId;

    public TableEntityDto() {
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(long waiterId) {
        this.waiterId = waiterId;
    }
}
