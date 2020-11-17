package api.gateway.domain;

import java.util.List;

public class UserContainer {

    private List<UserEntityDto>users;

    public UserContainer() {
    }

    public List<UserEntityDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntityDto> users) {
        this.users = users;
    }
}
