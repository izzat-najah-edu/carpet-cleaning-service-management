package stu.najah.se.test.ui.features.izzat;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import stu.najah.se.core.entity.CustomerEntity;
import stu.najah.se.core.entity.OrderEntity;
import stu.najah.se.core.entity.OrderProductEntity;
import stu.najah.se.core.entity.ProductEntity;
import stu.najah.se.core.service.EmailUtil;
import stu.najah.se.core.service.OrderService;

import java.time.LocalDateTime;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.testfx.api.FxAssert.verifyThat;

public class OrderCRUDSteps extends ApplicationTestBase {

    private static final OrderProductEntity ORDER_PRODUCT = new OrderProductEntity(
            "cleaning", (byte) 0, 50
    );

    private final TableView<OrderProductEntity> tableOrderProducts
            = lookup("#tableOrderProducts").queryTableView();

    private final TableView<OrderEntity> tableOrders
            = lookup("#tableOrders").queryTableView();

    private final ComboBox<CustomerEntity> comboBoxCustomer
            = lookup("#comboBoxCustomer").queryComboBox();

    private final ComboBox<ProductEntity> comboBoxProduct
            = lookup("#comboBoxAvailableProducts").queryComboBox();

    private final CheckBox finishedCheckBox
            = lookup("#checkBoxFinished").query();

    @And("orders tab is opened")
    public void ordersTabIsOpened() {
        clickOn("#tabOrders");
    }

    @And("I select a customer from the customer box")
    public void iSelectACustomerFromTheCustomerBox() {
        selectRow(0, comboBoxCustomer);
    }

    @And("I select an order from the order list")
    public void iSelectAnOrderFromTheOrderList() {
        selectRow(0, tableOrders);
    }

    @Given("I click add order button")
    public void iClickAddOrderButton() {
        clickOn("#buttonCreateOrder");
    }

    @Then("a new order is added to the order list")
    public void aNewOrderIsAddedToTheOrderList() {
        var now = LocalDateTime.now().withSecond(0).withNano(0);
        var matchers = tableOrders.getItems().stream().filter(orderEntity -> {
            // created within the last minute
            var orderCreatedAt = orderEntity.getCreatedAt().toLocalDateTime().withSecond(0).withNano(0);
            return orderCreatedAt.equals(now);
        });
        assertEquals(1, matchers.count());
    }

    @Given("I select the new order from the order list")
    public void iSelectTheNewOrderFromTheOrderList() {
        var now = LocalDateTime.now().withSecond(0).withNano(0);
        for (int i = 0; i < tableOrders.getItems().size(); i++) {
            var orderCreatedAt = tableOrders.getItems().get(i)
                    .getCreatedAt().toLocalDateTime().withSecond(0).withNano(0);
            if (Objects.equals(now, orderCreatedAt)) {
                // select it
                selectRow(i, tableOrders);
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
        var now = LocalDateTime.now().withSecond(0).withNano(0);
        var matchers = tableOrders.getItems().stream().filter(orderEntity -> {
            var orderCreatedAt = orderEntity.getCreatedAt().toLocalDateTime().withSecond(0).withNano(0);
            return orderCreatedAt.equals(now);
        });
        assertEquals(0, matchers.count());
    }

    @Given("I enter order product information")
    public void iEnterOrderProductInformation() {
        type("#textFieldSpecialTreatment", ORDER_PRODUCT.getSpecialTreatment());
        for (byte i = 0; i < ORDER_PRODUCT.getFinished(); i++) {
            clickOn("#checkBoxFinished");
        }
        type("#textFieldPrice", String.valueOf(ORDER_PRODUCT.getPrice()));
    }

    @And("I select a product from the product box")
    public void iSelectAProductFromTheProductBox() {
        selectRow(0, comboBoxProduct);
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
                selectRow(i, tableOrderProducts);
                ORDER_PRODUCT.setOrderId(orderProduct.getOrderId());
                ORDER_PRODUCT.setProductId(orderProduct.getProductId());
                break;
            }
        }
    }

    @And("I enter a different order product information")
    public void iEnterADifferentOrderProductInformation() {
        type("#textFieldSpecialTreatment", ORDER_PRODUCT.getSpecialTreatment() + "-new");
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

    @And("some order products are not finished")
    public void someOrderProductsAreNotFinished() {
        // just make sure first order product is not selected
        selectRow(0, tableOrderProducts);
        if (finishedCheckBox.isSelected()) {
            clickOn(finishedCheckBox);
            clickOn("#buttonUpdateOrderProduct");
        }
    }

    @Then("email button is disabled")
    public void emailButtonIsDisabled() {
        verifyThat("#buttonNotifyCustomer", Node::isDisabled);
    }

    @And("all order products are finished")
    public void allOrderProductsAreFinished() {
        for (int i = 0; i < tableOrderProducts.getItems().size(); i++) {
            selectRow(i, tableOrderProducts);
            if (!finishedCheckBox.isSelected()) {
                clickOn(finishedCheckBox);
                clickOn("#buttonUpdateOrderProduct");
            }
        }
    }

    @Then("email button is enabled")
    public void emailButtonIsEnabled() {
        verifyThat("#buttonNotifyCustomer", node -> !node.isDisabled());
    }

    @And("I click email button")
    public void iClickEmailButton() {
        clickOn("#buttonNotifyCustomer");
    }

    @And("I confirm sending email")
    public void iConfirmSendingEmail() {
        clickOkToAlert();
    }

    @Then("an email is sent to the customer")
    public void anEmailIsSentToTheCustomer() {
        var email = EmailUtil.readLastSentMessage();
        assertEquals(OrderService.ORDER_IS_READY_MESSAGE, email.getSubject());
    }

    @And("a success message is displayed")
    public void aSuccessMessageIsDisplayed() {
        clickOkToAlert();
    }
}
