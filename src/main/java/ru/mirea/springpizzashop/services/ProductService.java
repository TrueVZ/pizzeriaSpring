package ru.mirea.springpizzashop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.springpizzashop.models.Product;
import ru.mirea.springpizzashop.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Transactional
    public Product getProductById(Long id){
        return productRepository.findProductById(id);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public List<Product> getAllProductByTypeId(Long id){
        return productRepository.findAllByProductTypeId(id);
    }

    public void addProduct(Product product){
        productRepository.save(product);
    }

    @Transactional
    public byte[] getPictureById(Long id){
        return productRepository.getPictureById(id);
    }
    @Transactional
    public void deleteProduct(Long id){
        productRepository.deleteProductById(id);
    }
}
