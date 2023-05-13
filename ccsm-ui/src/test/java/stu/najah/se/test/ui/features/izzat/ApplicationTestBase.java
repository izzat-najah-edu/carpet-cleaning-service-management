package stu.najah.se.test.ui.features.izzat;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputControl;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ApplicationTestBase extends ApplicationTest {

    public void clickOn(String query) {
        this.clickOn(lookup(query).query());
    }

    public void clickOn(Node node) {
        Platform.runLater(() -> {
            node.requestFocus();
            if (node instanceof ButtonBase button) {
                button.fire();
            }
        });
    }

    public void clickOkToAlert() {
        Node alert = lookup(".alert").query();
        assertNotNull(alert);
        assertTrue(alert.isVisible());
        clickOn("OK");
    }

    public void selectRow(int row, TableView<?> tableView) {
        Platform.runLater(() ->
                tableView.getSelectionModel().clearAndSelect(row));
        WaitForAsyncUtils.waitForFxEvents();
    }

    public void selectRow(int row, ComboBox<?> comboBox) {
        Platform.runLater(() ->
                comboBox.getSelectionModel().clearAndSelect(row));
        WaitForAsyncUtils.waitForFxEvents();
    }

    public void type(String query, String text) {
        var node = lookup(query).query();
        if (node instanceof TextInputControl inputControl) {
            Platform.runLater(() -> {
                inputControl.requestFocus();
                inputControl.setText(text);
            });
            WaitForAsyncUtils.waitForFxEvents();
        } else {
            throw new IllegalArgumentException(
                    "Unsupported Node class for typing text: " + node.getClass().getName()
            );
        }
    }

}
