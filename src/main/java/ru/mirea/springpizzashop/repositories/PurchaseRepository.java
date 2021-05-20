package ru.mirea.springpizzashop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mirea.springpizzashop.models.Purchase;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Purchase findByUserIdAndProductId(long userId, long productId);
    Purchase findByIdAndUserId(long id, long userId);
    List<Purchase> findAllByUserId(long userId);
    Long deleteById(long id);
    Purchase findById(long id);
    @Transactional
    Long deleteAllByUserId(long userId);
}
