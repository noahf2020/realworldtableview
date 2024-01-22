package com.example.realworldtableview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LongStringConverter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelloController {
    @FXML
    private Label welcomeText;
    public TableView UsNewsTable;
    public TableView CollegeRankingsTable;

    public Button save;

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
//        Image test = new Image("src/main/java/com/example/realworldtableview/test.png");
//        System.out.println(test);
//
//        CustomImage item_1 = new CustomImage(new ImageView(new Image("src/main/java/com/example/realworldtableview/test.png")));
//        System.out.println(item_1);
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

    public void save() throws Exception{
        FileOutputStream outputStream = new FileOutputStream("data");
        ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
        ObservableList<CollegeRankings> activities = CollegeRankingsTable.getItems();
        objOutputStream.writeInt(activities.size());
        for (CollegeRankings activity : activities) {
            objOutputStream.writeObject(activity);
        }
        objOutputStream.flush();
        objOutputStream.close();
        outputStream.close();


        FileOutputStream outputStream1 = new FileOutputStream("data1");
        ObjectOutputStream objOutputStream1 = new ObjectOutputStream(outputStream1);
        ObservableList<UsNewsClass> activities1 = UsNewsTable.getItems();
        objOutputStream1.writeInt(activities1.size());
        for (UsNewsClass activity : activities1) {
            objOutputStream1.writeObject(activity);
        }
        objOutputStream1.flush();
        objOutputStream1.close();
        outputStream1.close();
    }


    void restore() throws Exception {
        FileInputStream inputStream = new FileInputStream("data");
        ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
        int numOfSavedObjects = objInputStream.readInt();
        List<CollegeRankings> activities = new ArrayList<>();
        for (int i = 0; i < numOfSavedObjects; i++) {
            CollegeRankings activity = (CollegeRankings) objInputStream.readObject();
            activities.add(activity);
        }
        objInputStream.close();
        inputStream.close();
        CollegeRankingsTable.getItems().addAll(activities);

        FileInputStream inputStream2 = new FileInputStream("data1");
        ObjectInputStream objInputStream2 = new ObjectInputStream(inputStream2);
        int numOfSavedObjects2 = objInputStream2.readInt();
        List<UsNewsClass> activities2 = new ArrayList<>();
        for (int i = 0; i < numOfSavedObjects2; i++) {
            UsNewsClass r = (UsNewsClass) objInputStream2.readObject();
            activities2.add(r);
        }
        objInputStream2.close();
        inputStream2.close();
        UsNewsTable.getItems().addAll(activities2);
    }
}
