/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package millionairegui;

import java.util.Observable;

/**
 *  Model class used to update variable information.
 * @author Yashjeet Bajwa 17995368, Ranish Chand 17981697
 */
public class Model extends Observable 
{
    /**
     * Declaring variables
     */
    public PlayerDatabase playerDB;
    public QuestionDatabase questionDB;
    public Data data;
    public String userName;
    public int[] prizes = {0, 100, 200, 300, 500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 125000, 250000, 500000, 1000000};
    boolean isCorrect = true;
    /**
     * Model class constructor, used to initialize variables and the database for player and questions.
     */
    public Model() 
    {
        this.playerDB = new PlayerDatabase();
        this.playerDB.playerDbSetup();
        this.questionDB = new QuestionDatabase();
        this.questionDB.questionDbSetup();
    }
    /**
     * Checks the name and password entered by the user and check if its correct. Compares to existing users in player database
     * @param username
     * @param password 
     */
    public void checkName(String username, String password) 
    {
        this.userName = username;

        this.data = this.playerDB.checkName(username, password);

        if (data.getHasLogin()) 
        {
            this.newQuestion(this.data);
        }
        this.setChanged();
        this.notifyObservers(this.data);
        
    }
    /**
     * Creates a new record for the player in the database.
     * @param username
     * @param password 
     */

    void newUser(String username, String password) 
    {
        this.userName = username;
        this.data = this.playerDB.newUser(username, password); 
        this.setChanged();
        this.notifyObservers(this.data);
    }
    /**
     * gets information for a new question.
     * @param data
     */
    public void newQuestion(Data data) 
    {
        this.questionDB.newQuestion(data);
    }
    /**
     * Removes 2 incorrect answers from the current question.
     */
    public void fiftyFifty() 
    {
        this.questionDB.fiftyFifty(this.data);
        this.setChanged();
        this.notifyObservers(this.data);

    }
    
    /**
     * Checks if the button corresponding to the answer is correct.
     * if correct then it creates a new question
     * otherwise it updates user money and calls the quit method.
     * @param answer 
     */
    public void checkAnswer(String answer) 
    {
        if (this.data.getCorrectAns() == Integer.parseInt(answer)) 
        {
            this.data.setCurrentMoney(this.prizes[data.getCurrentQuestion()]);
            data.setCurrentQuestion(data.getCurrentQuestion() + 1);
            this.newQuestion(data);
            if (data.getCurrentQuestion() == this.prizes.length) 
            {
                this.quitGame();
            }
            this.setChanged();
            this.notifyObservers(this.data);
        } 
        else 
        {
            this.data.setCurrentMoney(this.prizes[data.getCurrentQuestion() - 1]);
            this.data.setCorrect(false);
            this.setChanged();
            this.quitGame();
        }
    }
    
    /**
     * Removes all the incorrect answers, leaving the correct answer in the array.
     */
    public void askGod() 
    {
        this.questionDB.askGod(this.data);
        this.setChanged();
        this.notifyObservers(this.data);
    }
    /**
     * updates users total money and saves it in database, 
     * gets data for the leader board.
     * calls the view class for a update.
     * 
     */
    public void quitGame() 
    {
        int total = this.playerDB.quitGame(this.data.getCurrentMoney(), this.userName);
        this.data.setTotalMoney(total);
        this.data.setHasQuit(true);
        this.playerDB.getLeaderboard(data);
        this.setChanged();
        this.notifyObservers(this.data);
    }
}
