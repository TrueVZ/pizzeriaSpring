package ru.mirea.springpizzashop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.springpizzashop.models.ProductType;
import ru.mirea.springpizzashop.repositories.ProductTypeRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeService {
    private ProductTypeRepository productTypeRepository;

    @Autowired
    public ProductTypeService(ProductTypeRepository productTypeRepository) {
        this.productTypeRepository = productTypeRepository;
    }

    public void addProductType(ProductType productType){
        productTypeRepository.save(productType);
    }

    public void deleteProductType(Long id){
        productTypeRepository.deleteProductTypeById(id);
    }

    public ProductType getProductTypeById(Long id){
        return productTypeRepository.findProductTypeById(id);
    }

    public List<ProductType> getAllProductTypes(){
        return productTypeRepository.findAll();
    }
}
