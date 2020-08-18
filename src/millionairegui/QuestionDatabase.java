/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package millionairegui;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * creates a connection with the question database.
 *
 * @author Yashjeet Bajwa 17995368, Ranish Chand 17981697
 */
public class QuestionDatabase {

    /**
     * Declaring variables.
     */
    String user = "pdc";
    String password = "pdc";
    String url = "jdbc:derby:QuestionDB;create=true";
    Connection conn = null;
    Statement statement = null;
    final int NO_OF_ANSWERS = 4; //no. of correct answers

    /**
     * Creates a database connection and checks for existing question table, if
     * it doesn't exists then creates a new one with questions otherwise it
     * deletes previously stored question so they don't stack, before importing
     * questions again.
     */
    public void questionDbSetup() 
    {
        try 
        {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Success");
            statement = conn.createStatement();
            String tableName = "Question";
            if (!checkTableExisting(tableName)) 
            {
                statement.executeUpdate("CREATE TABLE " + tableName + " (questionno INT,question VARCHAR(200), correctanswer INT, a0 VARCHAR(200),a1 VARCHAR(200),a2 VARCHAR(200),a3 VARCHAR(200))");
                this.addDataFromFile();
            } else
            {
                statement.executeUpdate("DELETE FROM Question");
                this.addDataFromFile();
            }

        } catch (IOException | SQLException e) 
        {
            System.out.println("error");
        }

    }

    /**
     * Reads the questions.txt file and adds questions to the database.
     *
     * @throws IOException
     * @throws SQLException
     */
    public void addDataFromFile() throws IOException, SQLException
    {
        try 
        {
            String[] answers = new String[NO_OF_ANSWERS];
            String question;
            String correctAnswer;
            int cAnswer;
            BufferedReader bufferReader;
            Scanner scan;
            int counter = 1;
            try (FileReader fileScan = new FileReader("src/millionairegui/Questions.txt")) 
            {
                bufferReader = new BufferedReader(fileScan);
                scan = new Scanner(bufferReader);
                do 
                {
                    question = scan.nextLine();
                    correctAnswer = scan.nextLine();
                    cAnswer = Integer.parseInt(correctAnswer);
                    for (int j = 0; j < answers.length; j++) 
                    {
                        answers[j] = scan.nextLine();
                    }
                    statement.addBatch("INSERT INTO Question VALUES(" + counter + ",'" + question + "'," + cAnswer + ",'" + answers[0] + "','" + answers[1] + "','" + answers[2] + "','" + answers[3] + "')");
                    statement.executeBatch();
                    counter++;
                    answers = new String[NO_OF_ANSWERS];
                } while (scan.hasNext());
            }
            bufferReader.close();
            scan.close();
        } catch (FileNotFoundException e) 
        {
            System.out.println("File not found");
        }
    }

    /**
     * retrieves question data from database and updates the Data class.
     *
     * @param qData
     */
    public void newQuestion(Data qData) 
    {
        try 
        {
            ResultSet rs = statement.executeQuery("SELECT question , correctanswer, a0, a1 ,a2,a3 FROM Question WHERE questionno =" + qData.getCurrentQuestion());
            if (rs.next()) 
            {
                qData.setQuestion(rs.getString("question"));
                qData.getChoices()[0] = rs.getString("a0");
                qData.getChoices()[1] = rs.getString("a1");
                qData.getChoices()[2] = rs.getString("a2");
                qData.getChoices()[3] = rs.getString("a3");
                qData.setCorrectAns(rs.getInt("correctanswer"));
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(QuestionDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * retrieves current question data from Data class and removes two random
     * wrong answers.
     *
     * @param qData
     */

    public void fiftyFifty(Data qData) 
    {
        int[] ans = new int[qData.getChoices().length];
        int[] falseAns = new int[qData.getChoices().length];
        Random rand = new Random();
        boolean same = true;
        try 
        {
            ResultSet rs = statement.executeQuery("SELECT question , correctanswer, a0, a1 ,a2,a3 FROM Question WHERE questionno =" + qData.getCurrentQuestion());
            if (rs.next()) 
            {
                int cAns = rs.getInt("correctanswer");
                
                for (int i = 0; i < ans.length; i++)
                {
                    ans[i] = Character.getNumericValue(rs.getString("a" + i).charAt(0));
                    qData.getChoices()[i] = "";
                }
                for (int i = 0; i < ans.length; i++)
                {
                    if (cAns == ans[i])
                    {
                        qData.getChoices()[i] = rs.getString("a" + (ans[i] - 1));
                    } else 
                    {
                        falseAns[i] = ans[i] - 1;
                    }
                }
                while (same) 
                {
                    int randIndexFalseAns = rand.nextInt(qData.getChoices().length);
                    if (randIndexFalseAns != cAns - 1)
                    {
                        int index = falseAns[randIndexFalseAns];
                        qData.getChoices()[randIndexFalseAns] = rs.getString("a" + index);
                        same = false;
                    }
                }
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(QuestionDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Selects all possible answers from the database and removes all the incorrect ones,
     * then adds it to the choices array in the data class.
     * @param qData 
     */

    public void askGod(Data qData) 
    {
        try 
        {
            ResultSet rs = statement.executeQuery("SELECT a0, a1 ,a2,a3 FROM Question WHERE questionno =" + qData.getCurrentQuestion());
            if (rs.next()) 
            {
                for (int i = 0; i < qData.getChoices().length; i++) 
                {
                    if (Character.getNumericValue(rs.getString("a" + i).charAt(0)) == qData.getCorrectAns()) {
                        qData.getChoices()[i] = rs.getString("a"+i);
                    }
                    else
                    {
                        qData.getChoices()[i] = "";
                    }
                }
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(QuestionDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Check if the questions table exists in the database, if it does then it
     * returns true.
     *
     * @param newTableName
     * @return flag boolean.
     */
    private boolean checkTableExisting(String newTableName) 
    {
        boolean flag = false;
        try 
        {
            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);//types);
            //Statement dropStatement=null;
            while (rsDBMeta.next()) 
            {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0)
                {
                    System.out.println(tableName + "  is there");
                    flag = true;
                }
            }
            if (rsDBMeta != null)
            {
                rsDBMeta.close();
            }
        } catch (SQLException ex) 
        {
            Logger.getLogger(QuestionDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

}
