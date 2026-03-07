package com.bca.ecommerce.service;

import com.bca.ecommerce.entity.Product;
import com.bca.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

	@Override
	public void saveProduct(Product product) {
		repo.save(product);
	}
	
	@Transactional
	public void deleteProduct(Integer productId){

	    Product product =
	    		repo.findById(productId)
	                    .orElseThrow(() ->
	                            new RuntimeException("Product not found"));

	    repo.delete(product);
	}
	
	@Override
	public void updateProduct(Product updatedProduct) {

	    Product existingProduct =
	            repo.findById(
	                updatedProduct.getProductId()
	            ).orElseThrow(() ->
	                new RuntimeException("Product not found"));

	    existingProduct.setName(updatedProduct.getName());
	    existingProduct.setDescription(updatedProduct.getDescription());
	    existingProduct.setPrice(updatedProduct.getPrice());
	    existingProduct.setStock(updatedProduct.getStock());

	    repo.save(existingProduct);
	}

	@Override
	public Product findById(Integer id) {
		return repo.findById(id)
	            .orElseThrow(() ->
	                new RuntimeException("Product not found"));
	}
	
}

