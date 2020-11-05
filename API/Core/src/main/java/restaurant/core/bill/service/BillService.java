package restaurant.core.bill.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.bill.domain.Bill;
import restaurant.core.bill.domain.BillDto;
import restaurant.core.bill.repository.BillRepository;
import restaurant.core.table.domain.TableEntity;
import restaurant.core.table.repository.TableRepository;
import restaurant.core.user.domain.userEntity.UserEntity;
import restaurant.core.user.repository.UserEntityRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public BillDto createNewBill(long waiterId, long tableId) {
        Bill bill = new Bill();
        UserEntity waiter = this.userEntityRepository.getOne(waiterId);
        TableEntity table = null;

        if (tableId != 0) {
            table = this.tableRepository.getOne(tableId);
        }

        bill.setWaiter(waiter);
        bill.setTable(table);


        return this.mapper.map(this.billRepository.saveAndFlush(bill), BillDto.class);
    }

    //*** Get Bill Methods ***//

    public List<BillDto> getAllBills() {
        return this.billRepository.findAll()
                .stream()
                .map(this::mapBill)
                .collect(Collectors.toList());
    }

    public List<BillDto> getBillsByWaiter(long waiterId) {

        return  this.billRepository.findByWaiterId(waiterId)
                .stream()
                .map(this::mapBill)
                .collect(Collectors.toList());
    }

    public List<BillDto> getBillsByTable(long tableId) {

        if (tableId == 0) {
           return  this.billRepository.findByForTakeAway()
                    .stream()
                    .map(this::mapBill)
                    .collect(Collectors.toList());
        } else {

        return  this.billRepository.findByTableId(tableId)
                .stream()
                .map(this::mapBill)
                .collect(Collectors.toList());
        }
    }

    public List<BillDto> getBillsByTableAndWaiter(long tableId, long waiterId) {

        if (tableId == 0) {
            return  this.billRepository.findByForTakeAwayAndWaiterId(waiterId)
                    .stream()
                    .map(this::mapBill)
                    .collect(Collectors.toList());
        } else {
        return  this.billRepository.findByTableAndWaiterId(tableId, waiterId)
                .stream()
                .map(this::mapBill)
                .collect(Collectors.toList());
        }
    }

    //**** Finish of Get Bill Methods ****//

    public BillDto addProduct(long billId, Map<Long, Integer> products) {
        Bill bill = this.billRepository.getOne(billId);
        bill.addProducts(products);

        return this.mapper.map(
                this.billRepository.saveAndFlush(bill), BillDto.class);
    }

    //********** Private Methods **********//

    private BillDto mapBill(Bill bill) {
        BillDto result = new BillDto();
        result.setId(bill.getId());
        result.setWaiterId(bill.getWaiter().getId());
        if (bill.getTable() == null) {
            result.setTableId(0);
        } else {
        result.setTableId(bill.getTable().getId());
        }
        result.setProducts(bill.getProducts());
        return result;
    }


}
