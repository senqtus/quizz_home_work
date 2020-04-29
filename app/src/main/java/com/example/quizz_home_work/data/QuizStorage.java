package com.example.quizz_home_work.data;

import java.util.ArrayList;

public class QuizStorage {

  private ArrayList<QuizQuestion> questions = new ArrayList<>();


  public ArrayList<QuizQuestion> getQuestions() {
    return questions;
  }

  public void setQuestions(ArrayList<QuizQuestion> questions) {
    this.questions = questions;
  }
}
