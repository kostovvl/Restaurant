package api.tableservice.table.service;

import api.tableservice.table.domain.TableEntity;
import api.tableservice.table.domain.TableEntityDto;
import api.tableservice.table.repository.TableRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


import javax.persistence.EntityExistsException;
import javax.persistence.Table;
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

    public void addWaiter(long waiterId, long tableId) {
        TableEntity table = this.tableRepository.getOne(tableId);
        table.setWaiterId(waiterId);

        this.tableRepository.saveAndFlush(table);
    }

    public void removeWaiter(long tableId) {
        TableEntity table = this.tableRepository.getOne(tableId);
        table.setWaiterId(0);

        this.tableRepository.saveAndFlush(table);
    }



    //********** Private methods ***********//

    private boolean tableExists(int tableNumber) {
        return this.tableRepository.findByNumber(tableNumber).orElse(null) != null;
    }


}
