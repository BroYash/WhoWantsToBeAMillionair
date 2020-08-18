/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package millionairegui;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Yashjeet Bajwa 17995368, Ranish Chand 17981697
 */
public class ModelTest {

    public Model model;
    public Data data;
    public PlayerDatabase playerDB;
    public QuestionDatabase questionDB;

    
    @Before
    public void setUp() {
        this.model = new Model();
        this.data = new Data();
        this.playerDB = new PlayerDatabase();
        this.playerDB.playerDbSetup();
        this.questionDB = new QuestionDatabase();
        this.questionDB.questionDbSetup();
    }

    /**
     * Test of checkName method, of class Model.
     * This test searches the data base for a existing user, with correct login information
     */
    @Test
    public void testCheckNameForValidRecord() {
        System.out.println("checkName");
        String username = "x";
        String password = "x";
        this.data = this.playerDB.checkName(username, password);
        boolean result = data.getHasLogin();
        boolean expResult = true;
        assertEquals(expResult, result);
    }

    /**
     * Test of checkName method, of class Model. Expected result = false,
     * because the existing user has a incorrect password.
     */
    @Test
    public void testCheckNameForValidUsernameButInvalidPassoword() {
        System.out.println("newUser");
        String username = "x";
        String password = "z";
        this.data = this.playerDB.checkName(username, password);
        boolean result = data.getHasLogin();
        boolean expResult = false;
        assertEquals(expResult, result);
    }

    /**
     * Test of checkName method, of class Model. Expected result = false,
     * because the user does not exist in the database.
     */
    @Test
    public void testCheckNameForUserNotInRecord() {
        System.out.println("newUser");
        String username = "someRandomUsernameInput";
        String password = "RandomPassword";
        this.data = this.playerDB.checkName(username, password);
        boolean result = data.getHasLogin();
        boolean expResult = false;
        assertEquals(expResult, result);
    }



    /**
     * Test of askGod method, of class Model.
     * Tests the functionality of askGod function, which should only return a 
     * array which contains the correct answers in this case 4)  and the other three answers are 
     * made empty.
     * Test case where the answer is correct.
     */
    @Test
    public void testAskGod() {
        System.out.println("askGod");
        this.data.setQuestion("How long was World War 2?");
        this.data.getChoices()[0] = "1) about 1 year";
        this.data.getChoices()[1] = "2) about 9 years";
        this.data.getChoices()[2] = "3) about 4 years";
        this.data.getChoices()[3] = "4) about 6 years";
        this.data.setCorrectAns(4);
        
        String[] expected = {"","","","4) about 6 years"};
        this.questionDB.askGod(data);
        String[] acutal = this.data.getChoices();
        
        assertArrayEquals(expected, acutal);
         
    }
    
    /**
     * Test of askGod method, of class Model.
     * Tests the functionality of askGod function, which should only return a 
     * array which contains the correct answers in this case 4)  and the other three answers are 
     * made empty.
     * The expected correct answer is 4 but the expected array has an incorrect answer at index 1
     * so the test will fail. this is used to tell user that their answer is wrong in game.
     */
    @Test
    public void testAskGodWithFalseAnswer() {
        System.out.println("askGod");
        this.data.setQuestion("How long was World War 2?");
        this.data.getChoices()[0] = "1) about 1 year";
        this.data.getChoices()[1] = "2) about 9 years";
        this.data.getChoices()[2] = "3) about 4 years";
        this.data.getChoices()[3] = "4) about 6 years";
        this.data.setCorrectAns(4);
        
        String[] expected = {"","2) about 9 years","",""};
        this.questionDB.askGod(data);
        String[] acutal = this.data.getChoices();
        
        assertArrayEquals(expected, acutal);
         
    }

}
