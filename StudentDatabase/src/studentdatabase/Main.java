package studentdatabase;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;


public class Main extends Application
{

    private PreparedStatement preparedStatement;

    private TextField tfDisplaySIN = new TextField();

    private TextField txtCurrentSIN = new TextField();
    private TextField txtFirstName = new TextField();
    private TextField txtMI = new TextField();
    private TextField txtLastName = new TextField();
    private TextField txtGrade = new TextField();
    private TextField txtChangeSIN = new TextField();

    private TextArea txtRecords = new TextArea();

    private Label lblStatus = new Label();

    @Override
    public void start(Stage primaryStage)
    {
        //initialize database connection and create a statement object
        //initializeDB();

        // Show Grade Section
        // ------------------
        Label lblHeader_ShowGrade = new Label("Show Grade:");
        Button btShowGrade = new Button(" Show Grade");
        HBox hBox = new HBox(10);
        hBox.getChildren().addAll(new Label(" SIN"), tfDisplaySIN, (btShowGrade));
        tfDisplaySIN.setPrefWidth(200);
        tfDisplaySIN.setPrefColumnCount(5);

        //Register handler
        btShowGrade.setOnAction(e -> showGrade());

        // Crud Section
        // ------------
        Label lblHeader_Crud = new Label("Edit Records:");

        GridPane gridPaneInput = new GridPane();
        gridPaneInput.setAlignment(Pos.CENTER);
        gridPaneInput.setPadding(new Insets(5, 20, 5, 20));
        gridPaneInput.setHgap(5);
        gridPaneInput.setVgap(5);

        Label lblSIN = new Label("SIN:");
        Label lblFirstName = new Label("First Name:");
        Label lblMI = new Label("Middle Name:");
        Label lblLastName = new Label("Last Name:");
        Label lblGrade = new Label("Grade:");
        Label lblChangeSIN = new Label("Update SIN:");

        gridPaneInput.add(lblSIN, 0, 0); gridPaneInput.add(txtCurrentSIN, 1, 0);
        gridPaneInput.add(lblFirstName, 0, 1); gridPaneInput.add(txtFirstName, 1, 1);
        gridPaneInput.add(lblMI, 0, 2); gridPaneInput.add(txtMI, 1, 2);
        gridPaneInput.add(lblLastName, 0, 3); gridPaneInput.add(txtLastName, 1, 3);
        gridPaneInput.add(lblGrade, 0, 4); gridPaneInput.add(txtGrade, 1, 4);
        gridPaneInput.add(lblChangeSIN, 0, 5); gridPaneInput.add(txtChangeSIN, 1, 5);

        Button btnInsert = new Button("Insert");
        Button btnUpdate = new Button("Update");
        Button btnDelete = new Button("Delete");
        Button btnClear = new Button("Clear");
        btnInsert.setOnAction(e -> insert());
        btnUpdate.setOnAction(e -> update());
        btnDelete.setOnAction(e -> delete());
        btnClear.setOnAction(e -> clear());

        HBox crudBox = new HBox(10);
        crudBox.getChildren().addAll(btnInsert, btnUpdate, btnDelete, btnClear);

        // Show All Section
        Button btnShowAll = new Button("Show All Records");
        txtRecords.setEditable(false);
        btnShowAll.setOnAction(e -> showAll());

        //routine for UI
        VBox vBox = new VBox(10);
        btShowGrade.setMouseTransparent(false);
        vBox.setStyle("-fx-margin:20,10,10,10; -fx-padding:10,10,10,10;-fx-background-color:#ffff99;-fx-border-radius:10;-fx-padding:5; -fx-background-radius:10;");
        vBox.getChildren().addAll(
                lblStatus,
                lblHeader_ShowGrade,
                hBox,
                lblHeader_Crud,
                gridPaneInput,
                crudBox,
                btnShowAll,
                txtRecords
        );

        Scene scene = new Scene(vBox, 420, 600);
        primaryStage.setTitle("FindGrade");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    /*
    private void initializeDB()
    {
        try
        {
            //load jdbc driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //for sqlserver
            //Class.forName(com.mysql.jdbc.Driver");
            System.out.println("Driver loaded.");

            //establish a connection
            // for centennial
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD",
                    "comp228_f19_sy_81",
                    "password"
            );
            // for outside centennial
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@199.212.26.208:1521:SQLD",
                    "COMP214_F19_BRB_13",
                    "password"
            );
            System.out.println("Database connected.");

            String queryString = "select firstName, mi, lastName, grade from Student1 where SIN = ?";

            //create a statement
            preparedStatement = connection.prepareStatement(queryString);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }
    */

    // Show Grade, Single Entry
    private void showGrade()
    {
        String sin = tfDisplaySIN.getText();
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD",
                    "comp228_f19_sy_81",
                    "password"
            );

            String queryString = "select firstName, mi, lastName, grade from Student1 where SIN = ?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, sin);
            ResultSet rset = preparedStatement.executeQuery();

            if(rset.next())
            {
                String firstName = rset.getNString(1);
                String mi = rset.getString(2);
                String lastName = rset.getString(3);
                String grade = rset.getString(4);

                //display result in a label
                lblStatus.setText("Displaying Grade for " + firstName + " " + mi + " " + lastName + ": " + grade);
            }
            else
            {
                lblStatus.setText("Not found.");

            }

        }

        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    // Show All
    private void showAll() {
        txtRecords.setText(null);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD",
                    "comp228_f19_sy_81",
                    "password"
            );

            String queryString = "select * from Student1";
            preparedStatement = connection.prepareStatement(queryString);

            ResultSet result = preparedStatement.executeQuery();
            lblStatus.setText("Showing all records.");
            while (result.next()) {
                txtRecords.appendText(
                        result.getString(1) + " " +
                        result.getString(2) + " " +
                        result.getString(3) + " " +
                        result.getString(4) + ": " +
                        result.getString(5) + "\n"
                );
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    // Crud Ops
    // --------

    // Insert
    private void insert() {
        String sin = txtCurrentSIN.getText();
        String firstName = txtFirstName.getText();
        String mi = txtMI.getText();
        String lastName = txtLastName.getText();
        String grade = txtGrade.getText();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD",
                    "comp228_f19_sy_81",
                    "password"
            );

            String queryString = "insert into Student1 values (?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, sin);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, mi);
            preparedStatement.setString(4, lastName);
            preparedStatement.setString(5, grade);

            int i = preparedStatement.executeUpdate();
            lblStatus.setText("Successfully inserted record for student " + sin + " " + firstName + " " + mi + " " + lastName);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Update
    private void update() {
        String sin = txtCurrentSIN.getText();
        String firstName = txtFirstName.getText();
        String mi = txtMI.getText();
        String lastName = txtLastName.getText();
        String grade = txtGrade.getText();
        String newsin = txtChangeSIN.getText();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD",
                    "comp228_f19_sy_81",
                    "password"
            );

            String queryString = "update Student1 set sin = ?, firstname = ?, mi = ?, lastname = ?, grade = ? where sin = ?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, newsin);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, mi);
            preparedStatement.setString(4, lastName);
            preparedStatement.setString(5, grade);
            preparedStatement.setString(6, sin);

            int i = preparedStatement.executeUpdate();
            lblStatus.setText("Successfully updated record for student " + sin + " " + firstName + " " + mi + " " + lastName);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Delete
    private void delete() {
        String sin = txtCurrentSIN.getText();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@oracle1.centennialcollege.ca:1521:SQLD",
                    "comp228_f19_sy_81",
                    "password"
            );

            String queryString = "delete from Student1 where sin = ?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, sin);

            int i = preparedStatement.executeUpdate();
            lblStatus.setText("Successfully deleted record for student " + sin + ". Goodbye :(");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Clear Fields

    private void clear() {
        txtCurrentSIN.setText(null);
        txtFirstName.setText(null);
        txtMI.setText(null);
        txtLastName.setText(null);
        txtGrade.setText(null);
        txtChangeSIN.setText(null);
    }

    public static void main(String[] args)
    {
        Application.launch();

    }

}