package com.example.realworldtableview;

import java.io.File;
import java.io.Serializable;
import java.util.Scanner;

public class UsNewsClass implements Serializable {
    private String Status;
    private int Score;
    private String collegeName;
    private int AdjRank;
    public UsNewsClass(String collegeName, int AdjRank, String Status, int Score){
        this.collegeName = collegeName;
        this.AdjRank = AdjRank;
        this.Status = Status;
        this.Score = Score;
    }
    public String getCollegeName(){
        return this.collegeName;
    }
    public void setCollegeName(String name){
        this.collegeName = name;
    }
    public int getAdjRank(){
        return this.AdjRank;
    }
    public void setAdjRank(int Rank){
        this.AdjRank = Rank;
    }
    public String getStatus(){
        return this.Status;
    }
    public void setStatus( String status){
        this.Status = status;
    }
    public int getScore(){
        return this.Score;
    }
    public void setScore(int score){
        this.Score = score;
    }

    public String toString() {
        String otherValues = super.toString();
        return otherValues + " - [Us News Rankings] - Status: " + getStatus() + " Score: "+ getScore();
    }

    public static void readData() throws Exception {

        File dataFile = new File("src/UsNewsRankingData");
        Scanner dataScanner = new Scanner(dataFile);
        dataScanner.useDelimiter("\t|\n");

        while (dataScanner.hasNext()) {
            int Adj = dataScanner.nextInt();
            String CollegeName = dataScanner.next();
            String Status = dataScanner.next();
            int Score = dataScanner.nextInt();


            UsNewsClass newRanking = new UsNewsClass(CollegeName,Adj,Status,Score);
            System.out.println(newRanking);

        }
    }
}