// Final Lab Test - COMP228-005
// Trent B Minia 301041132
// 2019-12-14

package professor;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {
    private PreparedStatement preparedStatement;

    private TextField txtProfId = new TextField();
    private TextField txtProfName = new TextField();
    private TextField txtProfSal = new TextField();
    private TextField txtProfBonus = new TextField();

    private Label lblStatus = new Label();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Professor Salary");

        // Root Pane
        VBox rootPane = new VBox();
        rootPane.setPadding(new Insets(10, 20, 5, 20));

        // GridPane
        GridPane gridPaneDetails = new GridPane();
        gridPaneDetails.setAlignment(Pos.CENTER);
        gridPaneDetails.setPadding(new Insets(5, 20, 5, 20));
        gridPaneDetails.setHgap(5);
        gridPaneDetails.setVgap(5);

        // HBox For Buttons
        HBox hboxButtons = new HBox(5);
        hboxButtons.setAlignment(Pos.CENTER);

        // Labels and TextFields
        Label lblProfId = new Label("Professor ID:");

        Label lblProfName = new Label("Professor Name:");
        txtProfName.setEditable(false);

        Label lblProfSal = new Label("Professor Salary:");
        txtProfSal.setEditable(false);

        Label lblProfBonus = new Label("Bonus Amount:");
        txtProfBonus.setEditable(false);

        // Buttons
        Button btnDisplay = new Button("Display");
        Button btnReset = new Button("Reset");
        Button btnDelete = new Button("Delete");
        Button btnQuit = new Button("Quit");

        // Event Handlers to Methods
        btnDisplay.setOnAction(e -> display());
        btnReset.setOnAction(e -> reset());
        btnDelete.setOnAction(e -> delete());
        btnQuit.setOnAction(e -> quit());

        // Populate Scene
        gridPaneDetails.add(lblProfId, 0, 0); gridPaneDetails.add(txtProfId, 1, 0);
        gridPaneDetails.add(lblProfName, 0, 1); gridPaneDetails.add(txtProfName, 1, 1);
        gridPaneDetails.add(lblProfSal, 0, 2); gridPaneDetails.add(txtProfSal, 1, 2);
        gridPaneDetails.add(lblProfBonus, 0, 3); gridPaneDetails.add(txtProfBonus, 1, 3);

        // Populate HBox Buttons
        hboxButtons.getChildren().addAll(
                btnDisplay,
                btnReset,
                btnDelete,
                btnQuit
        );

        // Populate Root Pane
        rootPane.getChildren().addAll(
                gridPaneDetails,
                hboxButtons,
                lblStatus
        );
        Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // -----------
    // = Methods =
    // -----------

    // Calculate Bonus
    private String calculateBonus(String strSalary) {
        // Convert Salary String to Double
        int intSalary = Integer.parseInt(strSalary);

        // Calculate Bonus
        double dblBonus = intSalary + (intSalary * 0.03);

        // Convert Bonus Double to String
        String strBonus = Double.toString(dblBonus);

        return strBonus;
    }

    // Clear Fields
    private void reset() {
        lblStatus.setText("Fields cleared.");
        txtProfId.setText(null);
        txtProfName.setText(null);
        txtProfSal.setText(null);
        txtProfBonus.setText(null);
    }

    // SQL - Display Record
    private void display() {
        String profId = txtProfId.getText();
        String profName;
        String profSalary;
        String profBonus;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD",
                    "comp228_f19_sy_81",
                    "password"
            );

            String queryString = "select ProfId, ProfName, ProfSal from Professor where ProfId = ?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, profId);
            ResultSet resultset = preparedStatement.executeQuery();

            if (resultset.next()) {
                // Extract results
                profName = resultset.getString(2);
                profSalary = resultset.getString(3);

                // Calculate Bonus
                profBonus = calculateBonus(profSalary);

                // Display Results in Text Boxes
                txtProfId.setText(profId);
                txtProfName.setText(profName);
                txtProfSal.setText(profSalary);
                txtProfBonus.setText(profBonus);

                // Update Status Label
                lblStatus.setText("Displaying Record for Professor ID " + profId);

            }
            else {
                lblStatus.setText("Record for Professor ID " + profId + " not found.");

            }

        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    // SQL - Delete Record
    private void delete() {
        String profId = txtProfId.getText();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD",
                    "comp228_f19_sy_81",
                    "password"
            );

            String queryString = "delete from Professor where ProfId = ?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, profId);

            int i = preparedStatement.executeUpdate();
            lblStatus.setText("Deleted record for Professor ID " + profId + ". :(");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Quit Application
    private void quit() {
        System.out.println("Closing application. Goodbye!");
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
