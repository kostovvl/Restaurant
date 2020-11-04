package restaurant.core.bill.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.bill.domain.Bill;
import restaurant.core.bill.repository.BillRepository;
import restaurant.core.table.domain.TableEntity;
import restaurant.core.table.repository.TableRepository;
import restaurant.core.user.domain.userEntity.UserEntity;
import restaurant.core.user.repository.UserEntityRepository;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final UserEntityRepository userEntityRepository;
    private final TableRepository tableRepository;
    private final ModelMapper mapper;

    public BillService(BillRepository billRepository, UserEntityRepository userEntityRepository,
                       TableRepository tableRepository, ModelMapper mapper) {
        this.billRepository = billRepository;
        this.userEntityRepository = userEntityRepository;
        this.tableRepository = tableRepository;
        this.mapper = mapper;
    }

    public long createNewBill(long waiterId, long tableId) {
        Bill bill = new Bill();
        UserEntity waiter = this.userEntityRepository.getOne(waiterId);
        TableEntity table = null;

        if (tableId != 0) {
            table = this.tableRepository.getOne(tableId);
        }

        bill.setWaiter(waiter);
        bill.setTable(table);
        this.billRepository.saveAndFlush(bill);

        return bill.getId();
    }

}
