package stu.najah.se.test.core;

import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import stu.najah.se.core.DatabaseErrorListener;
import stu.najah.se.core.dao.CustomerDAO;
import stu.najah.se.core.entity.CustomerEntity;
import stu.najah.se.core.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

public class CustomerServiceTest {

    private static final CustomerEntity CUSTOMER =
            new CustomerEntity("username", "phone", "address");

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
    public void testSelectCustomerByNameNotFound() {
        when(customerDAO.get(CUSTOMER.getName())).thenReturn(null);
        var optional = customerService.selectCustomer(CUSTOMER.getName());
        assertTrue(optional.isEmpty());
    }

    @Test
    public void testSelectCustomerByIdNotFound() {
        when(customerDAO.get(0)).thenReturn(null);
        var optional = customerService.selectCustomer(0);
        assertTrue(optional.isEmpty());
    }

    @Test
    public void testSelectCustomerByNameFound() {
        when(customerDAO.get(CUSTOMER.getName())).thenReturn(CUSTOMER);
        var optional = customerService.selectCustomer(CUSTOMER.getName());
        assertTrue(optional.isPresent());
        var customer = optional.get();
        assertEquals(CUSTOMER.getName(), customer.getName());
        assertEquals(CUSTOMER.getPhone(), customer.getPhone());
        assertEquals(CUSTOMER.getAddress(), customer.getAddress());
    }

    @Test
    public void testSelectCustomerById() {
        when(customerDAO.get(0)).thenReturn(CUSTOMER);
        var optional = customerService.selectCustomer(0);
        assertTrue(optional.isPresent());
        var customer = optional.get();
        assertEquals(CUSTOMER.getName(), customer.getName());
        assertEquals(CUSTOMER.getPhone(), customer.getPhone());
        assertEquals(CUSTOMER.getAddress(), customer.getAddress());
    }

    @Test
    public void testCreateAndSelectCustomer() {
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
    }

    @Test
    public void testUpdateCustomer() {
        var updatedCustomer = new CustomerEntity(
                CUSTOMER.getName(), "other phone", "other address"
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
    }

    @Ignore
    @Test
    public void testDeleteCustomer() {
        //var testCustomer = createNewTestCustomer("");
        //customerService.setCustomer(testCustomer);
        //customerService.deleteCustomer();
        //var optional = customerService.selectCustomer(testCustomer);
        //verify(optional.isEmpty());
    }

    @Ignore
    @Test
    public void testGetAllCustomers() {
        //var result = customerService.getAllCustomers();
        //assertEquals(2, result.size());
        //assertEquals(createNewTestCustomer("1"), result.get(0));
        //assertEquals(createNewTestCustomer("2"), result.get(1));
    }

    @Ignore
    @Test
    public void testGetAllCustomersWith() {
        //var result = customerService.getAllCustomersWith(TEST_NAME);
        //assertEquals(2, result.size());
        //assertEquals(createNewTestCustomer("1"), result.get(0));
        //assertEquals(createNewTestCustomer("2"), result.get(1));
    }

    @Ignore
    @Test
    public void testCreateAndSelectCustomerError() {
        //var invalidCustomer = createNewTestCustomer("");
        //invalidCustomer.setName("");
        //var optional = customerService.createAndSelectCustomer(invalidCustomer);
        //assertTrue(optional.isEmpty());
        //verify(errorListener, times(1)).onTransactionError("Error creating customer");
    }

    @Ignore
    @Test
    public void testUpdateCustomerError() {
        //var invalidCustomer = createNewTestCustomer("");
        //invalidCustomer.setName("");
        //customerService.updateCustomer(invalidCustomer);
        //verify(errorListener, times(1)).onTransactionError("Error updating customer");
    }

}
