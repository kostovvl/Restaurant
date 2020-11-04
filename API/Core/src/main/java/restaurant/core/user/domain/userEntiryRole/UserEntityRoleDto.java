package restaurant.core.user.domain.userEntiryRole;

import restaurant.core.configuration.BaseDto;

public class UserEntityRoleDto extends BaseDto {

    private String role;

    public UserEntityRoleDto() {
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
