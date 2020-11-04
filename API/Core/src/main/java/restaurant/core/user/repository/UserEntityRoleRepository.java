package restaurant.core.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.core.user.domain.userEntiryRole.UserEntityRole;

@Repository
public interface UserEntityRoleRepository extends JpaRepository<UserEntityRole, Long> {
}
