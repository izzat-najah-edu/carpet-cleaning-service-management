package stu.najah.se.test.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import stu.najah.se.core.DatabaseErrorListener;
import stu.najah.se.core.DatabaseOperationException;
import stu.najah.se.core.EntityListener;
import stu.najah.se.core.dao.ProductDAO;
import stu.najah.se.core.entity.ProductEntity;
import stu.najah.se.core.service.ProductService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private static final ProductEntity PRODUCT = new ProductEntity(1, "Product 1");

    private final List<ProductEntity> productList = new ArrayList<>();

    private ProductDAO productDAO;

    private ProductService productService;

    private DatabaseErrorListener errorListener;

    @BeforeEach
    public void setUp() {
        productDAO = Mockito.mock(ProductDAO.class);
        errorListener = Mockito.mock(DatabaseErrorListener.class);
        productService = new ProductService(productDAO, errorListener);
    }

    @Test
    public void testSelectProduct() {
        when(productDAO.get(PRODUCT.getId())).thenReturn(PRODUCT);
        var optional = productService.selectProduct(PRODUCT.getId());
        assertTrue(optional.isPresent());
        var product = optional.get();
        assertEquals(PRODUCT, product);
    }

    @Test
    public void testClearProduct() {
        productService.setProduct(PRODUCT);
        productService.clearProduct();
        assertTrue(productService.getProduct().isEmpty());
    }

    @Test
    public void testCreateAndSelectProduct() {
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
        var optional = productService.createAndSelectProduct(PRODUCT);
        assertTrue(optional.isPresent());
        var product = optional.get();
        assertEquals(PRODUCT, product);
    }

    @Test
    public void testUpdateProductNoProductSelected() {
        assertThrows(IllegalStateException.class, () -> productService.updateProduct(PRODUCT));
    }

    @Test
    public void testUpdateProduct() {
        var updatedProduct = new ProductEntity(PRODUCT.getId(), "Updated Product 1");
        productList.add(PRODUCT);
        when(productDAO.get(PRODUCT.getId())).thenReturn(productList.get(0));
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
        assertEquals(updatedProduct, product);
    }

    @Test
    public void testDeleteProductNoProductSelected() {
        assertThrows(IllegalStateException.class, () -> productService.deleteProduct());
    }

    @Test
    public void testDeleteProduct() {
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
    public void testGetProductsByCustomer() {
        int customerId = 1;
        var product1 = new ProductEntity(1, "Product 1");
        var product2 = new ProductEntity(1, "Product 2");
        when(productDAO.getAll(customerId)).thenReturn(List.of(product1, product2));
        var result = productService.getProductsByCustomer(customerId);
        assertEquals(2, result.size());
        assertEquals(product1, result.get(0));
        assertEquals(product2, result.get(1));
    }

    @Test
    public void testCreateAndSelectProductError() {
        doThrow(new DatabaseOperationException("Error creating product")).when(productDAO).insert(PRODUCT);
        assertTrue(productService.createAndSelectProduct(PRODUCT).isEmpty());
        verify(errorListener, times(1)).onTransactionError("Error creating product");
    }

    @Test
    public void testUpdateProductError() {
        var newProduct = new ProductEntity(2, "New Product");
        doThrow(new DatabaseOperationException("Error updating product")).when(productDAO).update(newProduct);
        productService.setProduct(PRODUCT);
        productService.updateProduct(newProduct);
        verify(errorListener, times(1)).onTransactionError("Error updating product");
    }

    @Test
    public void testSubscribeAndUnsubscribe() {
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
