package stu.najah.se.test.core.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import stu.najah.se.core.DatabaseErrorListener;
import stu.najah.se.core.DatabaseOperationException;
import stu.najah.se.core.EntityListener;
import stu.najah.se.core.dao.CustomerDAO;
import stu.najah.se.core.entity.CustomerEntity;
import stu.najah.se.core.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    private static final CustomerEntity CUSTOMER =
            new CustomerEntity("username", "phone", "address", "email");

    private final List<CustomerEntity> customerList = new ArrayList<>();

    private CustomerDAO customerDAO;

    private CustomerService customerService;

    private DatabaseErrorListener errorListener;

    @BeforeEach
    public void setUp() {
        customerDAO = Mockito.mock(CustomerDAO.class);
        errorListener = Mockito.mock(DatabaseErrorListener.class);
        customerService = new CustomerService(customerDAO, errorListener);
    }

    @Test
    void testSelectCustomerByNameNotFound() {
        when(customerDAO.get(CUSTOMER.getName())).thenReturn(null);
        var optional = customerService.selectCustomer(CUSTOMER.getName());
        assertTrue(optional.isEmpty());
    }

    @Test
    void testSelectCustomerByIdNotFound() {
        when(customerDAO.get(0)).thenReturn(null);
        var optional = customerService.selectCustomer(0);
        assertTrue(optional.isEmpty());
    }

    @Test
    void testSelectCustomerByEntityNotFound() {
        when(customerDAO.get(CUSTOMER.getName())).thenReturn(null);
        var optional = customerService.selectCustomer(CUSTOMER);
        assertTrue(optional.isEmpty());
    }

    @Test
    void testSelectCustomerByNameFound() {
        when(customerDAO.get(CUSTOMER.getName())).thenReturn(CUSTOMER);
        var optional = customerService.selectCustomer(CUSTOMER.getName());
        assertTrue(optional.isPresent());
        var customer = optional.get();
        assertEquals(CUSTOMER.getName(), customer.getName());
        assertEquals(CUSTOMER.getPhone(), customer.getPhone());
        assertEquals(CUSTOMER.getAddress(), customer.getAddress());
        assertEquals(CUSTOMER.getEmail(), customer.getEmail());
    }

    @Test
    void testSelectCustomerByIdFound() {
        when(customerDAO.get(0)).thenReturn(CUSTOMER);
        var optional = customerService.selectCustomer(0);
        assertTrue(optional.isPresent());
        var customer = optional.get();
        assertEquals(CUSTOMER.getName(), customer.getName());
        assertEquals(CUSTOMER.getPhone(), customer.getPhone());
        assertEquals(CUSTOMER.getAddress(), customer.getAddress());
        assertEquals(CUSTOMER.getEmail(), customer.getEmail());
    }

    @Test
    void testSelectCustomerByEntityFound() {
        when(customerDAO.get(CUSTOMER.getName())).thenReturn(CUSTOMER);
        var optional = customerService.selectCustomer(CUSTOMER);
        assertTrue(optional.isPresent());
        var customer = optional.get();
        assertEquals(CUSTOMER.getName(), customer.getName());
        assertEquals(CUSTOMER.getPhone(), customer.getPhone());
        assertEquals(CUSTOMER.getAddress(), customer.getAddress());
        assertEquals(CUSTOMER.getEmail(), customer.getEmail());
    }

    @Test
    void testClearCustomer() {
        customerService.setCustomer(CUSTOMER);
        customerService.clearCustomer();
        assertTrue(customerService.getCustomer().isEmpty());
    }

    @Test
    void testCreateAndSelectCustomer() {
        doAnswer(invocation -> {
            customerList.add(CUSTOMER);
            return null;
        }).when(customerDAO).insert(CUSTOMER);
        doAnswer(invocation -> {
            if (customerList.contains(CUSTOMER)) {
                return CUSTOMER;
            }
            return null;
        }).when(customerDAO).get(CUSTOMER.getName());
        var optional = customerService.createAndSelectCustomer(CUSTOMER);
        assertTrue(optional.isPresent());
        var customer = optional.get();
        assertEquals(CUSTOMER.getName(), customer.getName());
        assertEquals(CUSTOMER.getPhone(), customer.getPhone());
        assertEquals(CUSTOMER.getAddress(), customer.getAddress());
        assertEquals(CUSTOMER.getEmail(), customer.getEmail());
    }

    @Test
    void testUpdateCustomerNoCustomerSelected() {
        assertThrows(IllegalStateException.class, () -> customerService.updateCustomer(CUSTOMER));
    }

    @Test
    void testUpdateCustomer() {
        var updatedCustomer = new CustomerEntity(
                CUSTOMER.getName(), "other phone", "other address", "other email"
        );
        customerList.add(CUSTOMER);
        when(customerDAO.get(CUSTOMER.getName())).thenReturn(customerList.get(0));
        doAnswer(invocation -> {
            var optional = customerService.getCustomer();
            assertTrue(optional.isPresent());
            var customer = optional.get();
            assertEquals(CUSTOMER.getName(), customer.getName());
            assertEquals(CUSTOMER.getPhone(), customer.getPhone());
            assertEquals(CUSTOMER.getAddress(), customer.getAddress());
            customerList.add(updatedCustomer);
            return null;
        }).when(customerDAO).update(updatedCustomer);
        customerService.setCustomer(CUSTOMER);
        customerService.updateCustomer(updatedCustomer);
        var optional = customerService.getCustomer();
        assertTrue(optional.isPresent());
        var customer = optional.get();
        assertEquals(updatedCustomer.getName(), customer.getName());
        assertEquals(updatedCustomer.getAddress(), customer.getAddress());
        assertEquals(updatedCustomer.getPhone(), customer.getPhone());
        assertEquals(updatedCustomer.getEmail(), customer.getEmail());
    }

    @Test
    void testDeleteCustomerNoCustomerSelected() {
        assertThrows(IllegalStateException.class, () -> customerService.deleteCustomer());
    }

    @Test
    void testDeleteCustomer() {
        customerList.add(CUSTOMER);
        doAnswer(invocation -> {
            customerList.remove(CUSTOMER);
            return null;
        }).when(customerDAO).delete(CUSTOMER);
        customerService.setCustomer(CUSTOMER);
        customerService.deleteCustomer();
        assertTrue(customerList.isEmpty());
        assertTrue(customerService.getCustomer().isEmpty());
        assertTrue(customerService.selectCustomer(CUSTOMER).isEmpty());
    }

    @Test
    void testGetAllCustomers() {
        var user1 = new CustomerEntity("user1", "phone", "address", "email");
        var user2 = new CustomerEntity("user2", "phone", "address", "email");
        when(customerDAO.getAll()).thenReturn(List.of(user1, user2));
        var result = customerService.getAllCustomers();
        assertEquals(2, result.size());
        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));
    }

    @Test
    void testGetAllCustomersWith() {
        var user1 = new CustomerEntity("user1", "phone", "address", "email");
        var user2 = new CustomerEntity("user2", "phone", "address", "email");
        when(customerDAO.getAllLike("user")).thenReturn(List.of(user1, user2));
        var result = customerService.getAllCustomersWith("user");
        assertEquals(2, result.size());
        assertEquals(user1, result.get(0));
        assertEquals(user2, result.get(1));
    }

    @Test
    void testCreateAndSelectCustomerError() {
        doThrow(new DatabaseOperationException("Error creating customer")).when(customerDAO).insert(CUSTOMER);
        assertTrue(customerService.createAndSelectCustomer(CUSTOMER).isEmpty());
        verify(errorListener, times(1)).onTransactionError("Error creating customer");
    }

    @Test
    void testUpdateCustomerError() {
        var newCustomer = new CustomerEntity("user", "phone", "address", "email");
        doThrow(new DatabaseOperationException("Error updating customer")).when(customerDAO).update(newCustomer);
        customerService.setCustomer(CUSTOMER);
        customerService.updateCustomer(newCustomer);
        verify(errorListener, times(1)).onTransactionError("Error updating customer");
    }

    @Test
    void testSubscribeAndUnsubscribe() {
        EntityListener<CustomerEntity> listener = Mockito.mock(EntityListener.class);
        customerService.watchCustomer(listener);
        customerService.setCustomer(CUSTOMER);
        verify(listener, times(1)).onEntityChanged(CUSTOMER);
        customerService.setCustomer(null);
        verify(listener, times(1)).onEntityCleared();
        customerService.unwatchCustomer(listener);
        customerService.clearCustomer();
        verify(listener, times(1)).onEntityCleared();
    }

}
