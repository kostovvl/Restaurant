package api.gateway.domain;

public class BaseDto {

    private long id;

    public BaseDto() {
    }

    public BaseDto(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
