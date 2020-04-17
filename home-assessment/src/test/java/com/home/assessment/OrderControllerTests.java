package com.home.assessment;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.home.assessment.controller.OrderController;
import com.home.assessment.repository.OrderMasterDao;
import com.home.assessment.repository.ProductMasterDao;
import com.home.assessment.request.OrderCreateRequest;
import com.home.assessment.request.OrderedItems;
import com.home.assessment.services.impl.OrderServiceImpl;
import com.home.assessment.util.Constants;


@SpringBootTest
public class OrderControllerTests {
	

	@InjectMocks
	private OrderServiceImpl orderService;
    
	@Mock
	private OrderMasterDao orderMasterDao;
    
    @Mock
	private OrderController orderController;
    
    @Mock
	private ProductMasterDao productMasterDao;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
	public void contexLoads() throws Exception {
		assertThat(orderController).isNotNull();
	}
    
    @Test
    public void testAddOrder() {
    	OrderCreateRequest productMaster = new OrderCreateRequest();
        productMaster.setBuyerEmailId("prem2029@gmail.com");

        Map<String, Object> response = orderService.createOrder(productMaster);        
        assertThat(response.get(Constants.STATUS)).isEqualTo(Constants.PRODUCT_INVALID);
        
        
        productMaster = new OrderCreateRequest();
        productMaster.setBuyerEmailId("prem2029@gmail.com");
        OrderedItems item = new OrderedItems();
        item.setQuantity(1);
        item.setSku("123-AB-345");
        productMaster.getItems().add(item);
        response = orderService.createOrder(productMaster);        
        assertThat(response.get(Constants.STATUS)).isEqualTo(Constants.PRODUCT_INVALID);
    }	

}
