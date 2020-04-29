package com.example.quizz_home_work.data;

import java.util.ArrayList;

public class QuizQuestion {

  private String question;
  private ArrayList<String>  answer;

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public ArrayList<String>  getAnswer() {
    return answer;
  }

  public void setAnswer(ArrayList<String> answer) {
    this.answer = answer;
  }
}
