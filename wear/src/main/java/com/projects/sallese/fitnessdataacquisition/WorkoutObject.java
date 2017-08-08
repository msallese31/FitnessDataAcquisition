package com.projects.sallese.fitnessdataacquisition;

/**
 * Created by sallese on 8/7/17.
 */

public class WorkoutObject {
    public int numberOfReps;
    public String exerciseName;
    public int weightUsed;

    public float[] XAcc;
    public float[] YAcc;
    public float[] ZAcc;

    public float[] XGyro;
    public float[] YGyro;
    public float[] ZGyro;

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public void setNumberOfReps(int numberOfReps) {
        this.numberOfReps = numberOfReps;
    }

    public void setWeightUsed(int weightUsed) {
        this.weightUsed = weightUsed;
    }

    public void setAccel(float[] xacc, float[] yacc, float[] zacc){
        this.XAcc = xacc;
        this.YAcc = yacc;
        this.ZAcc = zacc;
    }

    public void setGyro(float[] xgy, float[] ygy, float[] zgy){
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

    public float[] getXAcc() {
        return XAcc;
    }

    public float[] getYAcc() {
        return YAcc;
    }

    public float[] getZAcc() {
        return ZAcc;
    }

    public float[] getXGyro() {
        return XGyro;
    }

    public float[] getYGyro() {
        return YGyro;
    }

    public float[] getZGyro() {
        return ZGyro;
    }
}
