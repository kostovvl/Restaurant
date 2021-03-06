package api.userservice.user.service;

import api.userservice.user.domain.userEntiryRole.UserEntityRole;
import api.userservice.user.domain.userEntity.UserEntity;
import api.userservice.user.domain.userEntity.UserEntityDto;
import api.userservice.user.repository.UserEntityRepository;
import api.userservice.user.repository.UserEntityRoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final UserEntityRoleRepository userEntityRoleRepository;
//    private final TableRepository tableRepository;
    private final ModelMapper mapper;

    public UserEntityService(UserEntityRepository userEntityRepository,
                             UserEntityRoleRepository userEntityRoleRepository,
                             ModelMapper mapper) {
        this.userEntityRepository = userEntityRepository;
        this.userEntityRoleRepository = userEntityRoleRepository;
//        this.tableRepository = tableRepository;
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

    //Mock login just for development purposes
    public UserEntityDto login(UserEntityDto loginUser) {
        return this.userEntityRepository.findByName(loginUser.getName())
                .map(u -> {
                    UserEntityDto result = this.mapper.map(u, UserEntityDto.class);
                    Set<String> dtoRoles = u.getRoles().stream().map(UserEntityRole::getRole).collect(Collectors.toSet());
                    result.setRoles(dtoRoles);
                    return result;
                })
                .orElse(null);
    }
//
//    public String addTableToWaiter(long waiterId, long tableId) {
//        TableEntity tableEntity = this.tableRepository.getOne(tableId);
//        UserEntity waiter = this.userEntityRepository.getOne(waiterId);
//
//        tableEntity.setWaiter(waiter);
//
//        this.tableRepository.saveAndFlush(tableEntity);
//
//        return waiter.getName();
//    }
//
//
//
//    public String removeTableFromWaiter(long waiterId, long tableId) {
//        TableEntity tableEntity = this.tableRepository.getOne(tableId);
//        UserEntity waiter = this.userEntityRepository.getOne(waiterId);
//
//        tableEntity.setWaiter(null);
//
//        this.tableRepository.saveAndFlush(tableEntity);
//
//        return waiter.getName();
//    }

    public List<UserEntityDto> getAllWaiters() {

        return this.userEntityRepository.findAll()
                .stream()
                .filter(u -> u.getRoles().size() == 1)
                .map(u -> {
                    UserEntityDto result = this.mapper.map(u, UserEntityDto.class);
                    Set<String> roles = u.getRoles().stream().map(UserEntityRole::getRole).collect(Collectors.toSet());
                    result.setRoles(roles);
                    return result;
                }).collect(Collectors.toList());

    }

    private boolean userExists(String name) {
        return this.userEntityRepository.findByName(name).orElse(null) != null;
    }



}
