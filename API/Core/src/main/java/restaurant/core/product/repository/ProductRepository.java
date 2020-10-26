package restaurant.core.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import restaurant.core.product.domain.product.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
