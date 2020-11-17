package api.userservice.user.domain.userEntity;



import api.userservice.user.domain.baseEntity.BaseDto;

import java.util.Set;

public class UserEntityDto extends BaseDto {

    private String name;
    private String password;
    private Set<String>roles;

    public UserEntityDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
