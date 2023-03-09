package stu.najah.se.gui.fxml;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public interface Controller {

    /**
     * Note: for this to work,
     * the title of the column must be exactly like the name of the property
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
        tableView.getColumns().forEach(Controller::setUpColumn);
    }

    /**
     * Resets the scene to initial state.
     * Clears all fields and variables.
     */
    void reset();

}
