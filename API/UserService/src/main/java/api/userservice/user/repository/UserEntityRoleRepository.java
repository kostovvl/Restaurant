package api.userservice.user.repository;

import api.userservice.user.domain.userEntiryRole.UserEntityRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserEntityRoleRepository extends JpaRepository<UserEntityRole, Long> {
}
