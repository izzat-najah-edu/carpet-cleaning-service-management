package stu.najah.se.test.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import stu.najah.se.core.DatabaseErrorListener;
import stu.najah.se.core.DatabaseOperationException;
import stu.najah.se.core.EntityListener;
import stu.najah.se.core.dao.ProductDAO;
import stu.najah.se.core.entity.CustomerEntity;
import stu.najah.se.core.entity.ProductEntity;
import stu.najah.se.core.service.CustomerService;
import stu.najah.se.core.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    private static final ProductEntity PRODUCT = createProduct();

    private static ProductEntity createProduct() {
        var product = new ProductEntity("Product 1");
        product.setCustomerId(1);
        return product;
    }

    private final List<ProductEntity> productList = new ArrayList<>();

    private ProductDAO productDAO;

    private ProductService productService;

    private DatabaseErrorListener errorListener;

    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        productDAO = Mockito.mock(ProductDAO.class);
        errorListener = Mockito.mock(DatabaseErrorListener.class);
        customerService = Mockito.mock(CustomerService.class);
        productService = new ProductService(productDAO, errorListener, customerService);
    }

    @Test
    void testSelectProduct() {
        when(productDAO.get(PRODUCT.getId())).thenReturn(PRODUCT);
        var optional = productService.selectProduct(PRODUCT.getId());
        assertTrue(optional.isPresent());
        var product = optional.get();
        assertEquals(PRODUCT, product);
    }

    @Test
    void testClearProduct() {
        productService.setProduct(PRODUCT);
        productService.clearProduct();
        assertTrue(productService.getProduct().isEmpty());
    }

    @Test
    void testCreateAndSelectProduct() {
        doAnswer(invocation -> {
            productList.add(PRODUCT);
            return null;
        }).when(productDAO).insert(PRODUCT);
        doAnswer(invocation -> {
            if (productList.contains(PRODUCT)) {
                return PRODUCT;
            }
            return null;
        }).when(productDAO).get(PRODUCT.getId());
        var customer = new CustomerEntity("name", "phone", "address", "email");
        customer.setId(1);
        when(customerService.getCustomer()).thenReturn(Optional.of(customer));
        var optional = productService.createAndSelectProduct(PRODUCT);
        assertTrue(optional.isPresent());
        var product = optional.get();
        assertEquals(PRODUCT, product);
    }

    @Test
    void testUpdateProductNoProductSelected() {
        assertThrows(IllegalStateException.class, () -> productService.updateProduct(PRODUCT));
    }

    @Test
    void testUpdateProduct() {
        var updatedProduct = new ProductEntity("Updated Product 1");
        productList.add(PRODUCT);
        when(productDAO.get(PRODUCT.getId())).thenReturn(productList.get(0));
        var customer = new CustomerEntity("name", "phone", "address", "email");
        customer.setId(1);
        when(customerService.getCustomer()).thenReturn(Optional.of(customer));
        doAnswer(invocation -> {
            var optional = productService.getProduct();
            assertTrue(optional.isPresent());
            var product = optional.get();
            assertEquals(PRODUCT, product);
            productList.add(updatedProduct);
            return null;
        }).when(productDAO).update(updatedProduct);
        productService.setProduct(PRODUCT);
        productService.updateProduct(updatedProduct);
        var optional = productService.getProduct();
        assertTrue(optional.isPresent());
        var product = optional.get();
        assertEquals(product.getDescription(), updatedProduct.getDescription());
    }

    @Test
    void testDeleteProductNoProductSelected() {
        assertThrows(IllegalStateException.class, () -> productService.deleteProduct());
    }

    @Test
    void testDeleteProduct() {
        productList.add(PRODUCT);
        doAnswer(invocation -> {
            productList.remove(PRODUCT);
            return null;
        }).when(productDAO).delete(PRODUCT);
        productService.setProduct(PRODUCT);
        productService.deleteProduct();
        assertTrue(productList.isEmpty());
        assertTrue(productService.getProduct().isEmpty());
        assertTrue(productService.selectProduct(PRODUCT.getId()).isEmpty());
    }

    @Test
    void testGetProductsByCustomer() {
        int customerId = 1;
        var product1 = new ProductEntity("Product 1");
        product1.setCustomerId(1);
        var product2 = new ProductEntity("Product 2");
        product2.setCustomerId(1);
        var customer = new CustomerEntity("name", "phone", "address", "email");
        customer.setId(1);
        when(customerService.getCustomer()).thenReturn(Optional.of(customer));
        when(productDAO.getAll(customerId)).thenReturn(List.of(product1, product2));
        var result = productService.getAllCustomerProducts();
        assertEquals(2, result.size());
        assertEquals(product1, result.get(0));
        assertEquals(product2, result.get(1));
    }

    @Test
    void testCreateAndSelectProductError() {
        when(customerService.getCustomer()).thenReturn(Optional.empty());
        assertThrows(IllegalStateException.class, () -> productService.createAndSelectProduct(PRODUCT));
        assertTrue(productService.getProduct().isEmpty());
    }

    @Test
    void testUpdateProductError() {
        var newProduct = new ProductEntity("New Product");
        newProduct.setCustomerId(1);
        var customer = new CustomerEntity("name", "phone", "address", "email");
        customer.setId(1);
        when(customerService.getCustomer()).thenReturn(Optional.of(customer));
        doThrow(new DatabaseOperationException("Error updating product")).when(productDAO).update(newProduct);
        productService.setProduct(PRODUCT);
        productService.updateProduct(newProduct);
        verify(errorListener, times(1)).onTransactionError("Error updating product");
    }

    @Test
    void testSubscribeAndUnsubscribe() {
        EntityListener<ProductEntity> listener = Mockito.mock(EntityListener.class);
        productService.watchProduct(listener);
        productService.setProduct(PRODUCT);
        verify(listener, times(1)).onEntityChanged(PRODUCT);
        productService.setProduct(null);
        verify(listener, times(1)).onEntityCleared();
        productService.unwatchProduct(listener);
        productService.clearProduct();
        verify(listener, times(1)).onEntityCleared();
    }

}
