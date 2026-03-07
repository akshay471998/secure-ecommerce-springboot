package com.bca.ecommerce.service;

import com.bca.ecommerce.entity.Product;
import java.util.List;

public interface ProductService {

    void saveProduct(Product product);

    List<Product> getAllProducts();

	void updateProduct(Product updatedProduct);
	
	Product findById(Integer id);
}
