package restaurant.core.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.user.domain.userEntiryRole.UserEntityRole;
import restaurant.core.user.domain.userEntity.UserEntity;
import restaurant.core.user.domain.userEntity.UserEntityDto;
import restaurant.core.user.repository.UserEntityRepository;
import restaurant.core.user.repository.UserEntityRoleRepository;

import javax.persistence.EntityExistsException;
import java.util.Set;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final UserEntityRoleRepository userEntityRoleRepository;
    private final ModelMapper mapper;

    public UserEntityService(UserEntityRepository userEntityRepository,
                             UserEntityRoleRepository userEntityRoleRepository, ModelMapper mapper) {
        this.userEntityRepository = userEntityRepository;
        this.userEntityRoleRepository = userEntityRoleRepository;
        this.mapper = mapper;
    }

    public UserEntityDto createNewUser(UserEntityDto userEntityDto) {

        if (this.userExists(userEntityDto.getName())) {
            throw new EntityExistsException("User with such name already exists in DB!");
        }

        UserEntity userEntity = this.mapper.map(userEntityDto, UserEntity.class);

        UserEntityRole roleWaiter = new UserEntityRole("Waiter");
        roleWaiter.setUser(userEntity);

        userEntity.setRoles(Set.of(roleWaiter));

        return this.mapper.map(
                this.userEntityRepository.saveAndFlush(userEntity), UserEntityDto.class
        );
    }

    private boolean userExists(String name) {
        return this.userEntityRepository.findByName(name).orElse(null) != null;
    }

}
