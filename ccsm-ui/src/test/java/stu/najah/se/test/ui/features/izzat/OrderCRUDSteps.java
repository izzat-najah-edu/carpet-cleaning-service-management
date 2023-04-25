package stu.najah.se.test.ui.features.izzat;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import javafx.application.Platform;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import org.testfx.framework.junit5.ApplicationTest;
import stu.najah.se.core.entity.CustomerEntity;
import stu.najah.se.core.entity.OrderEntity;
import stu.najah.se.core.entity.OrderProductEntity;
import stu.najah.se.core.entity.ProductEntity;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class OrderCRUDSteps extends ApplicationTest {

    private static final OrderProductEntity ORDER_PRODUCT = new OrderProductEntity(
            "cleaning", (byte) 0, 50
    );

    private LocalDateTime now;

    private final TableView<OrderProductEntity> tableOrderProducts
            = lookup("#tableOrderProducts").queryTableView();

    private final TableView<OrderEntity> tableOrders
            = lookup("#tableOrders").queryTableView();

    private final ComboBox<CustomerEntity> comboBoxCustomer
            = lookup("#comboBoxCustomer").queryComboBox();

    private final ComboBox<ProductEntity> comboBoxProduct
            = lookup("#comboBoxAvailableProducts").queryComboBox();

    @And("orders tab is opened")
    public void ordersTabIsOpened() {
        clickOn("#tabOrders");
    }

    @And("I select a customer from the customer box")
    public void iSelectACustomerFromTheCustomerBox() {
        Platform.runLater(() ->
                comboBoxCustomer.getSelectionModel().clearAndSelect(0));
    }

    @And("I select an order from the order list")
    public void iSelectAnOrderFromTheOrderList() {
        Platform.runLater(() ->
                tableOrders.getSelectionModel().clearAndSelect(0));
    }

    @Given("I click add order button")
    public void iClickAddOrderButton() {
        clickOn("#buttonCreateOrder");
    }

    @Then("a new order is added to the order list")
    public void aNewOrderIsAddedToTheOrderList() {
        now = LocalDateTime.now().withSecond(0).withNano(0);
        var matchers = tableOrders.getItems().stream().filter(orderEntity -> {
            // created within the last minute
            var orderCreatedAt = orderEntity.getCreatedAt().toLocalDateTime().withSecond(0).withNano(0);
            return orderCreatedAt.equals(now);
        });
        assertEquals(1, matchers.count());
    }

    @Given("I select the new order from the order list")
    public void iSelectTheNewOrderFromTheOrderList() {
        for (int i = 0; i < tableOrders.getItems().size(); i++) {
            var orderCreatedAt = tableOrders.getItems().get(i)
                    .getCreatedAt().toLocalDateTime().withSecond(0).withNano(0);
            if (Objects.equals(now, orderCreatedAt)) {
                // select it
                tableOrders.getSelectionModel().clearAndSelect(i);
                break;
            }
        }
    }

    @And("I click delete order button")
    public void iClickDeleteOrderButton() {
        clickOn("#buttonDeleteOrder");
    }

    @Then("the selected order is deleted")
    public void theSelectedOrderIsDeleted() {
        var matchers = tableOrders.getItems().stream().filter(orderEntity -> {
            var orderCreatedAt = orderEntity.getCreatedAt().toLocalDateTime().withSecond(0).withNano(0);
            return orderCreatedAt.equals(now);
        });
        assertEquals(0, matchers.count());
    }

    @Given("I enter order product information")
    public void iEnterOrderProductInformation() {
        clickOn("#textFieldSpecialTreatment");
        write(ORDER_PRODUCT.getSpecialTreatment());
        for (byte i = 0; i < ORDER_PRODUCT.getFinished(); i++) {
            clickOn("#checkBoxFinished");
        }
        clickOn("#textFieldPrice");
        write(String.valueOf(ORDER_PRODUCT.getPrice()));
    }

    @And("I select a product from the product box")
    public void iSelectAProductFromTheProductBox() {
        Platform.runLater(() ->
                comboBoxProduct.getSelectionModel().clearAndSelect(0));
    }

    @And("I click add order product button")
    public void iClickAddOrderProductButton() {
        clickOn("#buttonCreateOrderProduct");
    }

    @Then("a new product is added to the order product list")
    public void aNewProductIsAddedToTheOrderProductList() {
        var matches = tableOrderProducts.getItems().stream().filter(orderProductEntity ->
                Objects.equals(ORDER_PRODUCT.getSpecialTreatment(), orderProductEntity.getSpecialTreatment()));
        assertEquals(1, matches.count());
    }

    @Given("I select a product from the order product list")
    public void iSelectAProductFromTheOrderProductList() {
        for (int i = 0; i < tableOrderProducts.getItems().size(); i++) {
            var orderProduct = tableOrderProducts.getItems().get(i);
            if (Objects.equals(ORDER_PRODUCT.getSpecialTreatment(), orderProduct.getSpecialTreatment())) {
                tableOrderProducts.getSelectionModel().clearAndSelect(i);
                ORDER_PRODUCT.setOrderId(orderProduct.getOrderId());
                ORDER_PRODUCT.setProductId(orderProduct.getProductId());
                break;
            }
        }
    }

    @And("I enter a different order product information")
    public void iEnterADifferentOrderProductInformation() {
        clickOn("#textFieldSpecialTreatment");
        write("-new");
        ORDER_PRODUCT.setSpecialTreatment(
                ORDER_PRODUCT.getSpecialTreatment() + "-new"
        );
    }

    @And("I click update order product button")
    public void iClickUpdateOrderProductButton() {
        clickOn("#buttonUpdateOrderProduct");
    }

    @Then("the selected order product is updated")
    public void theSelectedOrderProductIsUpdated() {
        for (int i = 0; i < tableOrderProducts.getItems().size(); i++) {
            var orderProduct = tableOrderProducts.getItems().get(i);
            if (Objects.equals(ORDER_PRODUCT.getOrderId(), orderProduct.getOrderId())
                    && Objects.equals(ORDER_PRODUCT.getProductId(), orderProduct.getProductId())) {
                assertEquals(
                        ORDER_PRODUCT.getSpecialTreatment(),
                        orderProduct.getSpecialTreatment()
                );
                return;
            }
        }
        fail();
    }

    @And("I click delete order product button")
    public void iClickDeleteOrderProductButton() {
        clickOn("#buttonDeleteOrderProduct");
    }

    @Then("the selected order product is deleted")
    public void theSelectedOrderProductIsDeleted() {
        for (int i = 0; i < tableOrderProducts.getItems().size(); i++) {
            var orderProduct = tableOrderProducts.getItems().get(i);
            if (Objects.equals(ORDER_PRODUCT.getOrderId(), orderProduct.getOrderId())
                    && Objects.equals(ORDER_PRODUCT.getProductId(), orderProduct.getProductId())) {
                fail();
            }
        }
    }
}
