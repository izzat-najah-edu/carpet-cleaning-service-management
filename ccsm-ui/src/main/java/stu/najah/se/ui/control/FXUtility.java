package stu.najah.se.ui.control;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Contains constants helper methods for the controllers
 */
final class FXUtility {

    static final String NO_SELECTED_CUSTOMER_MESSAGE
            = "No customer selected!";

    static final String NO_SELECTED_PRODUCT_MESSAGE
            = "No product selected!";

    static final String NO_SELECTED_ORDER_MESSAGE
            = "No order selected!";

    static final String NUMBER_FORMAT_ERROR_MESSAGE
            = "Incorrect number format!";

    /**
     * Creates a cell value factory for the table column.
     * The title of the column must be exactly like the name of the property.
     *
     * @param column uses the title of the column as the name of the property
     */
    static void setUpColumn(TableColumn<?, ?> column) {
        column.setCellValueFactory(new PropertyValueFactory<>(column.getText()));
    }

    /**
     * @param tableView applies setUpColumn() for every column in the table
     */
    static void setUpTable(TableView<?> tableView) {
        tableView.getColumns().forEach(FXUtility::setUpColumn);
    }

}
