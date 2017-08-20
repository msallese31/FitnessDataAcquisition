package com.projects.sallese.fitnessdataacquisition;

import java.util.ArrayList;

/**
 * Created by sallese on 8/7/17.
 */

public class WorkoutObject {
    public int numberOfReps;
    public String exerciseName;
    public int weightUsed;

    public ArrayList<Float> XAcc;
    public ArrayList<Float> YAcc;
    public ArrayList<Float> ZAcc;

    public ArrayList<Float> XGyro;
    public ArrayList<Float> YGyro;
    public ArrayList<Float> ZGyro;

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setNumberOfReps(int numberOfReps) {
        this.numberOfReps = numberOfReps;
    }

    public void setWeightUsed(int weightUsed) {
        this.weightUsed = weightUsed;
    }

    public void setAccel(ArrayList<Float> xacc, ArrayList<Float> yacc, ArrayList<Float> zacc){
        this.XAcc = xacc;
        this.YAcc = yacc;
        this.ZAcc = zacc;
    }

    public void setGyro(ArrayList<Float> xgy, ArrayList<Float> ygy, ArrayList<Float> zgy){
        this.XGyro = xgy;
        this.YGyro = ygy;
        this.ZGyro = zgy;
    }
    public int getNumberOfReps() {
        return numberOfReps;
    }

    public int getWeightUsed() {
        return weightUsed;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public ArrayList<Float> getXAcc() {
        return XAcc;
    }

    public ArrayList<Float> getYAcc() {
        return YAcc;
    }

    public ArrayList<Float> getZAcc() {
        return ZAcc;
    }

    public ArrayList<Float> getXGyro() {
        return XGyro;
    }

    public ArrayList<Float> getYGyro() {
        return YGyro;
    }

    public ArrayList<Float> getZGyro() {
        return ZGyro;
    }
}
