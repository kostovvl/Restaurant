package restaurant.core.user.domain.userEntity;

import restaurant.core.configuration.BaseDto;
import restaurant.core.user.domain.userEntiryRole.UserEntityRoleDto;

import java.util.Set;

public class UserEntityDto extends BaseDto {

    private String name;
    private String password;
    private Set<UserEntityRoleDto>roles;

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

    public Set<UserEntityRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserEntityRoleDto> roles) {
        this.roles = roles;
    }
}