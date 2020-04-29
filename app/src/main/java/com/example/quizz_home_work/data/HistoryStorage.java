package com.example.quizz_home_work.data;

import java.util.ArrayList;
public class HistoryStorage {

    private ArrayList<HistoryRow> history = new ArrayList<>();


    public ArrayList<HistoryRow> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<HistoryRow> history) {
        this.history = history;
    }
}
