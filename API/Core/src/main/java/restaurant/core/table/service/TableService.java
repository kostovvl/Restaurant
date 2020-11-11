package restaurant.core.table.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.table.domain.TableEntity;
import restaurant.core.table.domain.TableEntityDto;
import restaurant.core.table.repository.TableRepository;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TableService {

    private final TableRepository tableRepository;
    private final ModelMapper mapper;

    public TableService(TableRepository tableRepository, ModelMapper mapper) {
        this.tableRepository = tableRepository;
        this.mapper = mapper;
    }

    public void createTable(int tableNumber) {

        if (this.tableExists(tableNumber)) {
            throw new EntityExistsException("Table with such number already exists in DB!");
        }

        TableEntity table = new TableEntity(tableNumber);
        this.tableRepository.saveAndFlush(table);

    }

    public List<TableEntityDto> getAllTables() {
        return this.tableRepository.findAll()
                .stream()
                .map(t -> this.mapper.map(t, TableEntityDto.class))
                .collect(Collectors.toList());
    }

    public List<TableEntityDto> getAllFreeTables() {
        return this.tableRepository.getAllFreeTables()
                .stream()
                .map(t -> this.mapper.map(t, TableEntityDto.class))
                .collect(Collectors.toList());
    }


    //********** Private methods ***********//

    private boolean tableExists(int tableNumber) {
        return this.tableRepository.findByNumber(tableNumber).orElse(null) != null;
    }


}
