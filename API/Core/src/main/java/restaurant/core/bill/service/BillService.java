package restaurant.core.bill.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import restaurant.core.bill.repository.BillRepository;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final ModelMapper mapper;

    public BillService(BillRepository billRepository, ModelMapper mapper) {
        this.billRepository = billRepository;
        this.mapper = mapper;
    }
}
