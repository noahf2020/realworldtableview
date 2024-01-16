package com.example.realworldtableview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

import java.io.File;
import java.io.SyncFailedException;
import java.util.Scanner;

public class HelloController {
    @FXML
    private Label welcomeText;
    public TableView UsNewsTable;
    public TableView CollegeRankingsTable;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void initialize() throws Exception {

        TableColumn<CollegeRankings, String> collegeName = new TableColumn<>("collegeName");
        collegeName.setCellValueFactory(new PropertyValueFactory<CollegeRankings, String>("collegeName"));

        TableColumn<CollegeRankings, Integer> rank = new TableColumn<>("AdjRank");
        rank.setCellValueFactory(new PropertyValueFactory<CollegeRankings, Integer>("AdjRank"));

        TableColumn<CollegeRankings, Long> Tuition = new TableColumn<>("Tuition");
        Tuition.setCellValueFactory(new PropertyValueFactory<CollegeRankings, Long>("Tuition"));

        TableColumn<CollegeRankings, String> Enrollment = new TableColumn<>("Enrollment");
        Enrollment.setCellValueFactory(new PropertyValueFactory<CollegeRankings, String>("Enrollment"));



        TableColumn<UsNewsClass, String> collegeName2 = new TableColumn<>("collegeName");
        collegeName2.setCellValueFactory(new PropertyValueFactory<UsNewsClass, String>("collegeName"));

        TableColumn<UsNewsClass, Integer> rank2 = new TableColumn<>("adjRank");
        rank2.setCellValueFactory(new PropertyValueFactory<UsNewsClass, Integer>("adjRank"));

        TableColumn<UsNewsClass, String> status = new TableColumn<>("status");
        status.setCellValueFactory(new PropertyValueFactory<UsNewsClass, String>("status"));

        TableColumn<UsNewsClass, Integer> score = new TableColumn<>("score");
        score.setCellValueFactory(new PropertyValueFactory<UsNewsClass, Integer>("score"));



        //Couldnt get image to work
//        ObservableList<CustomImage> imgList = FXCollections.observableArrayList();
//        CustomImage item_1 = new CustomImage(new ImageView(new Image("src/main/java/com/example/realworldtableview/test.png")));
//        imgList.addAll(item_1);
//
//        TableColumn<CustomImage, ImageView> imageColumn = new TableColumn<CustomImage, ImageView>("Images");
//        imageColumn.setCellValueFactory(new PropertyValueFactory<CustomImage, ImageView>("image"));


        CollegeRankingsTable.getColumns().add(collegeName);
        CollegeRankingsTable.getColumns().add(rank);
        CollegeRankingsTable.getColumns().add(Tuition);
        CollegeRankingsTable.getColumns().add(Enrollment);
      //  CollegeRankingsTable.getColumns().add(imageColumn);

        UsNewsTable.getColumns().add(collegeName2);
        UsNewsTable.getColumns().add(rank2);
        UsNewsTable.getColumns().add(status);
        UsNewsTable.getColumns().add(score);

        CollegeRankingsTable.setEditable(true);
        UsNewsTable.setEditable(true);


        collegeName.setCellFactory(TextFieldTableCell.forTableColumn());
        collegeName.setOnEditCommit(event -> {
            CollegeRankings cellData = event.getRowValue();
            cellData.setCollegeName(event.getNewValue());
        });

        rank.setCellFactory(TextFieldTableCell.<CollegeRankings, Integer>forTableColumn(new IntegerStringConverter()));
        rank.setOnEditCommit(event -> {
            CollegeRankings cellData = event.getRowValue();
            cellData.setAdjRank(event.getNewValue());
        });

        Tuition.setCellFactory(TextFieldTableCell.<CollegeRankings, Long>forTableColumn(new LongStringConverter()));
        Tuition.setOnEditCommit(event -> {
            CollegeRankings cellData = event.getRowValue();
            cellData.setTuition(event.getNewValue());
        });

        Enrollment.setCellFactory(TextFieldTableCell.forTableColumn());
        Enrollment.setOnEditCommit(event -> {
            CollegeRankings cellData = event.getRowValue();
            cellData.setEnrollment(event.getNewValue());
        });


        collegeName2.setCellFactory(TextFieldTableCell.forTableColumn());
        collegeName2.setOnEditCommit(event -> {
            UsNewsClass cellData = event.getRowValue();
            cellData.setCollegeName(event.getNewValue());
        });

        rank2.setCellFactory(TextFieldTableCell.<UsNewsClass, Integer>forTableColumn(new IntegerStringConverter()));
        rank2.setOnEditCommit(event -> {
            UsNewsClass cellData = event.getRowValue();
            cellData.setAdjRank(event.getNewValue());
        });

        status.setCellFactory(TextFieldTableCell.forTableColumn());
        status.setOnEditCommit(event -> {
            UsNewsClass cellData = event.getRowValue();
            cellData.setStatus(event.getNewValue());
        });

        score.setCellFactory(TextFieldTableCell.<UsNewsClass, Integer>forTableColumn(new IntegerStringConverter()));
        score.setOnEditCommit(event -> {
            UsNewsClass cellData = event.getRowValue();
            cellData.setScore(event.getNewValue());
        });

        try {
            restore();
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    void restore() throws Exception {
        File dataFile = new File("src/main/java/com/example/realworldtableview/CollegeRankingData");
        Scanner dataScanner = new Scanner(dataFile);
        dataScanner.useDelimiter("\t|\n");

        while (dataScanner.hasNext()) {

            String CollegeName = dataScanner.next();
            int Adj = dataScanner.nextInt();
            long Tuition = dataScanner.nextLong();
            String Enrollment = dataScanner.next();
            CollegeRankingsTable.getItems().add(new CollegeRankings(CollegeName, Adj, Tuition, Enrollment));

        }

        File dataFile2 = new File("src/main/java/com/example/realworldtableview/UsNewsRankingData");
        Scanner dataScanner2 = new Scanner(dataFile2);
        dataScanner2.useDelimiter("\t|\n");

        while (dataScanner2.hasNext()) {
            int Adj = dataScanner2.nextInt();
            String CollegeName = dataScanner2.next();
            String Status = dataScanner2.next();
            int Score = dataScanner2.nextInt();
            UsNewsTable.getItems().add(new UsNewsClass(CollegeName,Adj,Status,Score));


        }

    }
}
