package com.example.realworldtableview;

import java.util.Scanner;
import java.io.File;
public class CollegeRankings{


    private long Tuition;
    private String Enrollment;
    private String collegeName;

    private int AdjRank;
    public CollegeRankings(String collegeName, int AdjRank, long Tuition, String Enrollment ) {
        this.collegeName = collegeName;
        this.AdjRank = AdjRank;
        this.Tuition = Tuition;
        this.Enrollment = Enrollment;
    }

    public String getCollegeName() {
        return this.collegeName;
    }
    public void setCollegeName(String name) {
        this.collegeName = name;
    }
    public int getAdjRank() {
        return this.AdjRank;
    }
    public void setAdjRank(int rank) {
        this.AdjRank = rank;
    }
    public long getTuition(){
        return this.Tuition;
    }
    public void setTuition(long tuition){
        this.Tuition = tuition;
    }
    public String getEnrollment(){
        return this.Enrollment;
    }
    public void setEnrollment(String enrollment){
        this.Enrollment = enrollment;
    }

    public String toString() {

        return getCollegeName() + " - [College Rankings] - Tuition: "+ getTuition() + " Enrollment: " + getEnrollment();
    }

    public static void readData() throws Exception {

        File dataFile = new File("src/main/java/com/example/realworldtableview/CollegeRankingData");
        Scanner dataScanner = new Scanner(dataFile);
        dataScanner.useDelimiter("\t|\n");

        while (dataScanner.hasNext()) {

            String CollegeName = dataScanner.next();
            int Adj = dataScanner.nextInt();
            long Tuition = dataScanner.nextLong();
            String Enrollment = dataScanner.next();

            CollegeRankings newRanking = new CollegeRankings(CollegeName,Adj,Tuition,Enrollment);
            System.out.println(newRanking);

        }
    }

}