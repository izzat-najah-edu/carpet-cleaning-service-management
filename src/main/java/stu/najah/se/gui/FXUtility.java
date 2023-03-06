package stu.najah.se.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

public interface FXUtility {

    /**
     * Note: for this to work,
     * the title of the column must be exactly like the name of the property
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

    /**
     * Displays an Information alert
     * @param message to be displayed
     */
    static void promptAlert(String message) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
