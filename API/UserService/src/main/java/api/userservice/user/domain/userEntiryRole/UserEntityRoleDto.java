package api.userservice.user.domain.userEntiryRole;


import api.userservice.user.domain.baseEntity.BaseDto;

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
