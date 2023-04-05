package stu.najah.se.gui.fxml;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Contains JavaFX-related helper methods
 */
final class FXUtility {

    /**
     * Creates a cell value factory for the table column.
     * The title of the column must be exactly like the name of the property.
     *
     * @param column uses the title of the column as the name of the property
     */
    public static void setUpColumn(TableColumn<?, ?> column) {
        column.setCellValueFactory(new PropertyValueFactory<>(column.getText()));
    }

    /**
     * @param tableView applies setUpColumn() for every column in the table
     */
    public static void setUpTable(TableView<?> tableView) {
        tableView.getColumns().forEach(FXUtility::setUpColumn);
    }

}
