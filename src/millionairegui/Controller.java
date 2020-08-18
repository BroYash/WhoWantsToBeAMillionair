/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package millionairegui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controller class defines actions for button presses, and create a relationship between the view and model
 *
 * @author Yashjeet Bajwa 17995368, Ranish Chand 17981697
 */
public class Controller implements ActionListener {

    /**
     * Creating model and view variables
     */
    public View view;
    public Model model;

    /**
     * Constructor initializes model and view classes.
     *
     * @param view
     * @param model
     */
    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);
    }

    /**
     * Defining actions for button presses.
     *
     * @param e
     */
    @Override
    @SuppressWarnings( "deprecation" )
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String username;
        String password;
        switch (command) {
            case "Log in":
                username = this.view.getUnInput().getText();
                password = this.view.getPwInput().getText();
                this.model.checkName(username, password);
                break;
            case "Walk away":
                this.view.setWalkedAway(this.view.getWalkedAway()+1);
                this.model.quitGame();   
                break;
            case "Create new user":
                this.view.getMessage().setText("");
                this.view.getUnInput().setText("");
                this.view.getPwInput().setText("");
                this.view.createNewUser();
                break;
            case "Create":
                if (this.view.getUnInput().getText().length() > 0 && this.view.getPwInput().getText().length() > 0) {
                    username = this.view.getUnInput().getText();
                    password = this.view.getPwInput().getText();
                    this.model.newUser(username, password);
                } else {
                    this.view.getMessage().setText("Please input a username or password of length greater than 0");
                    this.view.createNewUser();
                }
                break;
            case "50/50":
                this.model.fiftyFifty();
                this.view.checkButtonState();
                view.fiftyFifty();          
                break;
            case "Ask God":
                this.model.askGod();
                this.view.checkButtonState();
                this.view.askGod();           
                break;
            case "Return":
                this.view.disposeFrame();
                GameMain game = new GameMain();
                break;
            default:
                this.model.checkAnswer(command);
                this.view.checkButtonState();
                break;
        }
    }

}
