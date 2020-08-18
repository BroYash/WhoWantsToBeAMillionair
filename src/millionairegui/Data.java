/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package millionairegui;

import java.util.ArrayList;
import java.util.Collections;

/**
 * data class used to hold data for questions and users
 *
 * @author Yashjeet Bajwa 17995368, Ranish Chand 17981697
 */
public class Data {
    
    /**
     * Creating variables, to store data and make decisions.
     */

    private boolean hasLogin;
    private boolean hasQuit;
    public boolean userAlreadyExists;
    private boolean correct;
    private boolean check;

    private int currentMoney;
    private int correctAns;
    private int totalMoney;
    private int currentQuestion;

    private String question;
    private String[] choices;
    public ArrayList<Leaderboard> leaderboard;
    /**
     * Initializing the Variables in a default constructor
    */
    public Data() {
        this.leaderboard = new ArrayList<>();
        this.choices = new String[4];
        this.question = null;
        this.hasLogin = false;
        this.hasQuit = false;
        this.userAlreadyExists = false;
        this.correct = true;
        this.check = false;
        this.currentMoney = 0;
        this.correctAns = 0;
        this.totalMoney = 0;
        this.currentQuestion = 1;

    }
    /**
     * Adds data to the Leader board arrayList.
     * @param leaderboard 
     */
    public void add(Leaderboard leaderboard) {
        this.leaderboard.add(leaderboard);
    }
    
    /**
     * Sorts the leader board ArrayList in the descending order from users with highest total money to lowest.
     * then converts the arrayList into a LeaderBoard[] array and returns it.
     * @return 
     */

    public Leaderboard[] getLeaderboardArray() {
        Collections.sort(leaderboard, Collections.reverseOrder((Leaderboard leaderboard1, Leaderboard leaderboard2) -> leaderboard1.getMoney().compareTo(leaderboard2.getMoney())));
        Leaderboard[] array = new Leaderboard[this.leaderboard.size()];

        for (int i = 0; i < array.length; i++) {
            array[i] = this.leaderboard.get(i);
        }

        return array;
    }

    /**
     * getters and setters for the variables.
     *
     * @return
     */
    public boolean getHasLogin() {
        return hasLogin;
    }

    public void setHasLogin(boolean hasLogin) {
        this.hasLogin = hasLogin;
    }

    public boolean getHasQuit() {
        return hasQuit;
    }

    public void setHasQuit(boolean hasQuit) {
        this.hasQuit = hasQuit;
    }

    public boolean getCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public boolean getCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public int getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(int currentMoney) {
        this.currentMoney = currentMoney;
    }

    public int getCorrectAns() {
        return correctAns;
    }

    public void setCorrectAns(int correctAns) {
        this.correctAns = correctAns;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(int currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getChoices() {
        return choices;
    }

    public void setChoices(String[] choices) {
        this.choices = choices;
    }

}
