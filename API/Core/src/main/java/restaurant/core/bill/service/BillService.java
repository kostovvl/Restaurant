package restaurant.core.bill.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.bill.domain.Bill;
import restaurant.core.bill.domain.BillDto;
import restaurant.core.bill.repository.BillRepository;
import restaurant.core.product.domain.product.Product;
import restaurant.core.product.domain.product.ProductDto;
import restaurant.core.product.repository.ProductRepository;
import restaurant.core.table.domain.TableEntity;
import restaurant.core.table.repository.TableRepository;
import restaurant.core.user.domain.userEntity.UserEntity;
import restaurant.core.user.repository.UserEntityRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final UserEntityRepository userEntityRepository;
    private final TableRepository tableRepository;
    private final ProductRepository productRepository;
    private final ModelMapper mapper;

    public BillService(BillRepository billRepository, UserEntityRepository userEntityRepository,
                       TableRepository tableRepository, ProductRepository productRepository, ModelMapper mapper) {
        this.billRepository = billRepository;
        this.userEntityRepository = userEntityRepository;
        this.tableRepository = tableRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public BillDto createNewBill(long waiterId, long tableId) {
        Bill bill = new Bill();
        UserEntity waiter = this.userEntityRepository.getOne(waiterId);
        TableEntity table = null;

        if (tableId != 0) {
            table = this.tableRepository.getOne(tableId);
        }

        bill.setActive(true);
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

        System.out.println();

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

    public BillDto findById(long id) {
        return this.billRepository.findById(id)
                .map(this::mapBill)
                .orElse(null);
    }

    //**** Finish of Get Bill Methods ****//

    public void addProduct(long billId, Map<Long, Integer> products) {
        Bill bill = this.billRepository.getOne(billId);
        for (Map.Entry<Long, Integer> product : products.entrySet()) {
            long id = product.getKey();
            int quantity = product.getValue();

            double price = this.productRepository.findById(id).orElse(null).getPrice() * quantity;

            bill.addProduct(id, quantity);
            bill.addToTotalPrice(price);

        }

        this.billRepository.saveAndFlush(bill);

    }

    public void deleteProduct(long billId, long productId) {
        Bill bill = this.billRepository.getOne(billId);

        double price = this.productRepository.findById(productId).orElse(null).getPrice();

        bill.deleteProduct(productId, price);

        this.billRepository.saveAndFlush(bill);
    }


    public void closeBill(long billId) {
        Bill bill = this.billRepository.getOne(billId);
        bill.setActive(false);

        this.billRepository.saveAndFlush(bill);
    }


    //********** Private Methods **********//

    private BillDto mapBill(Bill bill) {
        BillDto result = new BillDto();
        result.setId(bill.getId());
        result.setWaiterId(bill.getWaiter().getId());

        if (bill.getTable() == null) {
            result.setTableId(0);
            result.setTableNumber(0);
        } else {
        result.setTableId(bill.getTable().getId());
        result.setTableNumber(bill.getTable().getNumber());
        }

        result.setProducts(bill.getProducts());
        result.setProductPrices(calculatePrices(bill.getProducts()));
        result.setTotalPrice(bill.getTotalPrice());
        return result;
    }

    private Map<Long, Double> calculatePrices(Map<Long, Integer> products) {
        Map<Long, Double> result = new HashMap<>();

        if (products.size() == 0) {
            return  result;
        }

        for (Map.Entry<Long, Integer> product : products.entrySet()) {
            Long id = product.getKey();
            double price = this.productRepository.findById(id).orElse(null).getPrice() * product.getValue();
            result.put(id, price);
        }
        return result;
    }


}
