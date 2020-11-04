package restaurant.core.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.table.domain.TableEntity;
import restaurant.core.table.repository.TableRepository;
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
    private final TableRepository tableRepository;
    private final ModelMapper mapper;

    public UserEntityService(UserEntityRepository userEntityRepository,
                             UserEntityRoleRepository userEntityRoleRepository,
                             TableRepository tableRepository, ModelMapper mapper) {
        this.userEntityRepository = userEntityRepository;
        this.userEntityRoleRepository = userEntityRoleRepository;
        this.tableRepository = tableRepository;
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

    public String addTableToWaiter(long waiterId, long tableId) {
        TableEntity tableEntity = this.tableRepository.getOne(tableId);
        UserEntity waiter = this.userEntityRepository.getOne(waiterId);

        tableEntity.setWaiter(waiter);

        this.tableRepository.saveAndFlush(tableEntity);

        return waiter.getName();
    }



    public String removeTableFromWaiter(long waiterId, long tableId) {
        TableEntity tableEntity = this.tableRepository.getOne(tableId);
        UserEntity waiter = this.userEntityRepository.getOne(waiterId);

        tableEntity.setWaiter(null);

        this.tableRepository.saveAndFlush(tableEntity);

        return waiter.getName();
    }

    private boolean userExists(String name) {
        return this.userEntityRepository.findByName(name).orElse(null) != null;
    }
}
