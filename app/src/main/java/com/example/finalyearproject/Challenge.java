package com.example.finalyearproject;

import androidx.annotation.NonNull;

public class Challenge {
    private String challenge;
    private int progress;
    private int duration;

    public Challenge(){

    }

    public String getChallenge() {
        return challenge;
    }

    public void setChallenge(String challenge) {
        this.challenge = challenge;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Challenge(String challenge, int progress, int duration){
        this.setChallenge(challenge);
        this.setProgress(progress);
        this.setDuration(duration);
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
