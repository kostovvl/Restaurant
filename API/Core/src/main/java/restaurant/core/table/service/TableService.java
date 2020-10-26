package restaurant.core.table.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.table.repository.TableRepository;

@Service
public class TableService {

    private final TableRepository tableRepository;
    private final ModelMapper mapper;

    public TableService(TableRepository tableRepository, ModelMapper mapper) {
        this.tableRepository = tableRepository;
        this.mapper = mapper;
    }
}
