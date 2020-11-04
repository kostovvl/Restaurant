package restaurant.core.user.domain.userEntity;

import restaurant.core.configuration.BaseEntity;
import restaurant.core.user.domain.userEntiryRole.UserEntityRole;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    private String name;
    private String password;
    private Set<UserEntityRole> roles;

    public UserEntity() {
    }

    @Column(name = "name", unique = true, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "user", targetEntity = UserEntityRole.class,
    fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<UserEntityRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserEntityRole> roles) {
        this.roles = roles;
    }
}
