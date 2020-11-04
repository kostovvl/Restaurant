package restaurant.core.table.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.table.domain.TableEntity;
import restaurant.core.table.repository.TableRepository;

import javax.persistence.EntityExistsException;

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


    //********** Private methods ***********//

    private boolean tableExists(int tableNumber) {
        return this.tableRepository.findByNumber(tableNumber).orElse(null) != null;
    }
}
