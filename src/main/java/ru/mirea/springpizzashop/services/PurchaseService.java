package ru.mirea.springpizzashop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.springpizzashop.models.Purchase;
import ru.mirea.springpizzashop.repositories.PurchaseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    public void addPurchase(Purchase purchase){
        purchaseRepository.save(purchase);
    }

    public Purchase getPurchaseByUserIdAndProductId(long userId, long productId){
        return purchaseRepository.findByUserIdAndProductId(userId, productId);
    }

    public Purchase getPurchaseByIdAndUserId(long id, long userId){
        return purchaseRepository.findByIdAndUserId(id, userId);
    }

    public Purchase getPurchaseById(long id){
        return purchaseRepository.findById(id);
    }

    public List<Purchase> getAllPurchaseUser(long userId){
        return purchaseRepository.findAllByUserId(userId);
    }

    public void deletePurchase(long id){
        purchaseRepository.deleteById(id);
    }

    public void deleteAllPurchaseByUserId(long userId){
        purchaseRepository.deleteAllByUserId(userId);
    }

}
