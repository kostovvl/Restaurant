package restaurant.core.user.domain;

import restaurant.core.configuration.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class UserEntityRole extends BaseEntity {

    private String role;
    private UserEntity user;

    public UserEntityRole() {
    }

    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
