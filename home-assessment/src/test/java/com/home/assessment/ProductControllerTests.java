package com.home.assessment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.home.assessment.controller.ProductController;
import com.home.assessment.model.ProductMaster;
import com.home.assessment.repository.ProductMasterDao;
import com.home.assessment.request.ProductCreateRequest;
import com.home.assessment.request.ProductDeleteRequest;
import com.home.assessment.request.ProductUpdateRequest;
import com.home.assessment.services.impl.ProductServiceImpl;
import com.home.assessment.util.Constants;


@SpringBootTest
public class ProductControllerTests {
	
	@InjectMocks
	private ProductServiceImpl productService;
    
	@Mock
	private ProductMasterDao productMasterDao;
    
    @Mock
	private ProductController productController;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
	public void contexLoads() throws Exception {
		assertThat(productController).isNotNull();
	}
    
    @Test
    public void testAddProduct() {
        
    	// Checked product added successfully
    	ProductCreateRequest productMaster = new ProductCreateRequest();
        productMaster.setName("Product Name");
        productMaster.setPrice(100.90);
        productMaster.setSku("123-AB-345");

        Map<String, Object> response = productService.createProduct(productMaster);        
        assertThat(response.get(Constants.STATUS)).isEqualTo(Constants.PRODUCT_CREATED);
        
        // Checked without sku
    	productMaster = new ProductCreateRequest();
        productMaster.setName("Product Name");
        productMaster.setPrice(100.90);

        response = productService.createProduct(productMaster);
        
        assertThat(response.get(Constants.STATUS)).isNull();
    }	
    
    @Test
    public void testUpdateProduct() {
    	ProductUpdateRequest productMaster = new ProductUpdateRequest();
        productMaster.setName("Product Name");
        productMaster.setPrice(100.90);
        productMaster.setSku("123-AB-345");

        Map<String, Object> response = productService.updateProduct(productMaster);        
        assertThat(response.get(Constants.STATUS)).isEqualTo(Constants.PRODUCT_INVALID);
        
        // Checked without sku
    	productMaster = new ProductUpdateRequest();
        productMaster.setName("Product Name");
        productMaster.setPrice(100.90);

        response = productService.updateProduct(productMaster);
        
        assertThat(response.get(Constants.STATUS)).isNull();
    }	
    
    @Test
    public void testDeleteProduct() {
    	// Checked with sku
    	ProductDeleteRequest productMaster = new ProductDeleteRequest();
        productMaster.setSku("123-AB-345");

        Map<String, Object> response = productService.deleteProduct(productMaster);   
        assertThat(response.get(Constants.STATUS)).isEqualTo(Constants.PRODUCT_INVALID);
        
        // Checked without sku
    	productMaster = new ProductDeleteRequest();
        response = productService.deleteProduct(productMaster);        
        assertThat(response.get(Constants.STATUS)).isNull();
    }	
    
    
    @Test
    public void testListProduct() {
    	// Getting the active product list
        List<ProductMaster> response = productService.getActiveProductList(); 
        assertThat(response).isEmpty();
    }	
	
}