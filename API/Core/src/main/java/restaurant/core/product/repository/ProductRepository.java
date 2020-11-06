package restaurant.core.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import restaurant.core.product.domain.product.Product;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByName(String name);

    @Query("select p from Product as p order by p.name")
    List<Product> findAllProducts();

    @Query("select p from Product as p where p.category.id =:categoryId order by p.name")
    List<Product> findAllProductsByCategory(long categoryId);

}
