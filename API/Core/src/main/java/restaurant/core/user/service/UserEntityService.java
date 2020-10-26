package restaurant.core.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.user.repository.UserEntityRepository;
import restaurant.core.user.repository.UserEntityRoleRepository;

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
}
