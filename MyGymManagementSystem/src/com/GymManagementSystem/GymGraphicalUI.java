package com.GymManagementSystem;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class GymGraphicalUI extends Application {

    private MyGymManager manager = new MyGymManager();
    public static void display(){
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        Stage memberDisplay = new Stage();
        memberDisplay.setTitle("GymManagementSystem");
        memberDisplay.setMinWidth(1300);
        memberDisplay.setMinHeight(800);

        // Creating labels and adding content
        Label lblDefaultMember = TableCreatorForGraphicalUI.defaultMemberTable();
        Label lblStudentMember = TableCreatorForGraphicalUI.studentMemberTable();
        Label lblOver60Member = TableCreatorForGraphicalUI.over60MemberTable();

        // Creating the interface of the search tab

        // Toggle group is used for grouping radio buttons
        final ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton searchByMemberId = new RadioButton("Search by member id");
        searchByMemberId.setSelected(true); // Get selected by default

        RadioButton searchByName = new RadioButton("Search by name");
        TextField txtSearchBar = new TextField();

        // Grouping is done here by declaring the same group name to both radio buttons.
        searchByMemberId.setToggleGroup(toggleGroup);
        searchByName.setToggleGroup(toggleGroup);

        // Creating the search button
        Button btnSearch = new Button("Search");

        // Creating the label that is for displaying member details

        Label lblDisplayMember = new Label("-----------------------------------------------------------------------------------------------");

        // Populating the above elements in a grid pane

        GridPane gridPane = new GridPane();
        gridPane.setVgap(15);
        gridPane.setHgap(15);

        GridPane.setConstraints(searchByMemberId,1,1);
        GridPane.setConstraints(searchByName,1,2);
        GridPane.setConstraints(txtSearchBar,3,1,1,2);
        GridPane.setConstraints(btnSearch,5,1,1,2);
        GridPane.setConstraints(lblDisplayMember,1,4,5,1);

        gridPane.getChildren().addAll(searchByMemberId, searchByName, txtSearchBar, btnSearch, lblDisplayMember);
        TabPane tabPane = new TabPane();

        btnSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if(searchByMemberId.isSelected()){
                    String str = manager.searchByMembershipNumber(txtSearchBar.getText());
                    lblDisplayMember.setText(str);
                }else{
                    String str = manager.searchByMemberName(txtSearchBar.getText());
                    lblDisplayMember.setText(str);
                }
            }
        });
        // Creating tabs
        Tab tabDefaultMember = new Tab("Default Member");
        Tab tabStudentMember = new Tab("Student Member");
        Tab tabOver60Member = new Tab("Over 60 Member");
        Tab tabSearch = new Tab("Search");

        // Setting content to the tabs
        tabDefaultMember.setContent(lblDefaultMember);
        tabStudentMember.setContent(lblStudentMember);
        tabOver60Member.setContent(lblOver60Member);
        tabSearch.setContent(gridPane);

        // Disabling the closing ability of all tabs
        tabStudentMember.setClosable(false);
        tabDefaultMember.setClosable(false);
        tabOver60Member.setClosable(false);
        tabSearch.setClosable(false);

        // Adding the tabs to the tab pane
        tabPane.getTabs().add(tabDefaultMember);
        tabPane.getTabs().add(tabStudentMember);
        tabPane.getTabs().add(tabOver60Member);
        tabPane.getTabs().add(tabSearch);

        // Setting the tab pane as scene
        Scene scene = new Scene(tabPane, 1300, 800);
        scene.getStylesheets().add("com/css/gymManagementSystem.css");
        memberDisplay.setResizable(false);
        memberDisplay.setScene(scene);
        memberDisplay.show();
    }
}
