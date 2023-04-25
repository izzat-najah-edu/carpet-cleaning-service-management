package stu.najah.se.test.ui.features.izzat;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import org.testfx.framework.junit5.ApplicationTest;
import stu.najah.se.core.entity.CustomerEntity;
import stu.najah.se.core.entity.ProductEntity;

import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ProductCRUDSteps extends ApplicationTest {

    private static final ProductEntity PRODUCT = new ProductEntity(
            "some description"
    );

    private final TableView<ProductEntity> tableProducts
            = lookup("#tableProducts").queryTableView();

    private final ListView<CustomerEntity> listCustomers
            = lookup("#listCustomers").queryListView();

    @And("products tab is opened")
    public void productsTabIsOpened() {
        clickOn("#tabProducts");
    }

    @And("I select a customer from the customer list")
    public void iSelectACustomerFromTheCustomerList() {
        listCustomers.getSelectionModel().clearAndSelect(0);
    }

    @Given("I enter product information")
    public void iEnterProductInformation() {
        clickOn("#textFieldDescription");
        write(PRODUCT.getDescription());
    }

    @And("I click add product button")
    public void iClickAddProductButton() {
        clickOn("#buttonCreateProduct");
    }

    @Then("a new product is added to the list")
    public void aNewProductIsAddedToTheList() {
        var matches = tableProducts.getItems().stream().filter(productEntity ->
                Objects.equals(PRODUCT.getDescription(), productEntity.getDescription()));
        assertEquals(1, matches.count());
    }

    @Given("I select a product from the list")
    public void iSelectAProductFromTheList() {
        for (int i = 0; i < tableProducts.getItems().size(); i++) {
            if (Objects.equals(PRODUCT.getDescription(), tableProducts.getItems().get(i).getDescription())) {
                tableProducts.getSelectionModel().clearAndSelect(i);
                PRODUCT.setId(tableProducts.getItems().get(i).getId());
                break;
            }
        }
    }

    @And("I enter a different product information")
    public void iEnterADifferentProductInformation() {
        clickOn("#textFieldDescription");
        write("-new");
        PRODUCT.setDescription(PRODUCT.getDescription() + "-new");
    }

    @And("I click update product button")
    public void iClickUpdateProductButton() {
        clickOn("#buttonUpdateProduct");
    }

    @Then("the selected product is updated")
    public void theSelectedProductIsUpdated() {
        for (int i = 0; i < tableProducts.getItems().size(); i++) {
            if (Objects.equals(PRODUCT.getId(), tableProducts.getItems().get(i).getId())) {
                assertEquals(
                        PRODUCT.getDescription(),
                        tableProducts.getItems().get(i).getDescription()
                );
                return;
            }
        }
        fail();
    }

    @And("I click delete product button")
    public void iClickDeleteProductButton() {
        clickOn("#buttonDeleteProduct");
    }

    @Then("the selected product is deleted")
    public void theSelectedProductIsDeleted() {
        for (int i = 0; i < tableProducts.getItems().size(); i++) {
            if (Objects.equals(PRODUCT.getId(), tableProducts.getItems().get(i).getId())) {
                fail();
            }
        }
    }
}
