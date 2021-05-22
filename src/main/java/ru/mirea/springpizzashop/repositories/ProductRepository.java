package ru.mirea.springpizzashop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.mirea.springpizzashop.models.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p.picture from Product p where p.id = :id")
    byte[] getPictureById(Long id);
    List<Product> findAllByProductTypeId(Long productTypeId);
    Product findProductById(Long id);
    void deleteProductById(Long id);

}
