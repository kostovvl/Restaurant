package api.userservice.user.domain.userEntity;

import java.util.List;

public class UserEntityContainer {

    private List<UserEntityDto> users;

    public UserEntityContainer() {
    }

    public UserEntityContainer(List<UserEntityDto> users) {
        this.users = users;
    }

    public List<UserEntityDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserEntityDto> users) {
        this.users = users;
    }
}
