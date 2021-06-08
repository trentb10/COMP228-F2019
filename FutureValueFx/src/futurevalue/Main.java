package futurevalue;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javax.swing.JOptionPane;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Future Value Calculator");

        // Instantiate Root Pane
        VBox rootPane = new VBox();
        rootPane.setPadding(new Insets(10, 20, 5, 20));

        // Instantiate GridPane Input
        GridPane gridPaneInput = new GridPane();
        gridPaneInput.setAlignment(Pos.CENTER);
        gridPaneInput.setPadding(new Insets(5, 20, 5, 20));
        gridPaneInput.setHgap(5);
        gridPaneInput.setVgap(5);

        // Instantiate HBox Buttons
        HBox hboxButtons = new HBox(5);
        hboxButtons.setAlignment(Pos.CENTER);

        // Instantiate GridPane Output
        GridPane gridPaneOutput = new GridPane();
        gridPaneOutput.setAlignment(Pos.CENTER);
        gridPaneOutput.setPadding(new Insets(5, 20, 5, 20));
        gridPaneOutput.setHgap(5);
        gridPaneOutput.setVgap(5);

        // Instantiate Controls
        Label lblInvestmentAmount = new Label("Investment Amount:");
        TextField txtInvestmentAmount = new TextField();

        Label lblInterestRate = new Label("Interest Rate:");
        TextField txtInterestRate = new TextField();

        Label lblNumberOfYears = new Label("Number of Years:");
        TextField txtNumberOfYears = new TextField();

        Button btnCalculate = new Button("Calculate");

        Button btnReset = new Button("Reset");

        Label lblFutureValue = new Label("Future Value:");
        TextField txtFutureValue = new TextField();
        txtFutureValue.setEditable(false);

        // Event Handlers
        btnCalculate.setOnAction(e -> {
            try {
                double investmentAmount = Double.parseDouble(txtInvestmentAmount.getText());
                double annualInterestRate = Double.parseDouble(txtInterestRate.getText());
                double monthlyInterestRate = annualInterestRate / 1200;
                double numberOfYears = Double.parseDouble(txtNumberOfYears.getText());
                double futureValue = investmentAmount * Math.pow(1.0 + monthlyInterestRate, numberOfYears * 12);
                txtFutureValue.setText(String.format("%.2f", futureValue));
            } catch (Exception err) {
                JOptionPane.showMessageDialog(null, "Error: Invalid Input");
            }
        });

        btnReset.setOnAction(e -> {
           txtInvestmentAmount.setText(null);
           txtInterestRate.setText(null);
           txtNumberOfYears.setText(null);
           txtFutureValue.setText(null);
        });

        // Populate GridPane Input
        gridPaneInput.add(lblInvestmentAmount, 0, 0); gridPaneInput.add(txtInvestmentAmount, 1, 0);
        gridPaneInput.add(lblInterestRate, 0, 1); gridPaneInput.add(txtInterestRate, 1, 1);
        gridPaneInput.add(lblNumberOfYears, 0, 2); gridPaneInput.add(txtNumberOfYears, 1, 2);

        // Populate HBox Buttons
        hboxButtons.getChildren().addAll(btnCalculate, btnReset);

        // Populate GridPane Output
        gridPaneOutput.add(lblFutureValue, 0, 0); gridPaneOutput.add(txtFutureValue, 1, 0);

        // Add Panes to Root Pane
        rootPane.getChildren().addAll(gridPaneInput, hboxButtons, gridPaneOutput);

        Scene scene = new Scene(rootPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) { launch(args); }

}
