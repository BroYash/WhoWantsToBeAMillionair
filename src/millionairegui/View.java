package millionairegui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;

/**
 * view of the App, contains all JComponents to display the model class.
 *
 * @author Yashjeet Bajwa 17995368, Ranish Chand 17981697
 */
public class View extends JFrame implements Observer {

    /**
     * Declaring all variables.
     */
    private final JPanel userPanel = new JPanel();
    private final JPanel gamePanel = new JPanel();
    private final JPanel createNewUserPanel = new JPanel();
    private final JPanel quitPanel = new JPanel();

    private final JLabel uName = new JLabel("Username: ");
    private final JLabel pWord = new JLabel("Password: ");
    private final JLabel createUname = new JLabel("Set UserName");
    private final JLabel createPword = new JLabel("Set Password");
    private final JLabel question = new JLabel("", JLabel.CENTER);
    private final JLabel message = new JLabel("Welcome!", JLabel.CENTER);
    private final JLabel welcome = new JLabel("Who Wants to Be a Millionaire", JLabel.CENTER);
    private final JLabel newULabel = new JLabel("Create User", JLabel.CENTER);
    private final JLabel endMessage = new JLabel();

    private final JTextField unInput = new JTextField(10);
    private final JPasswordField pwInput = new JPasswordField(10);

    private final JButton choiceOne = new JButton();
    private final JButton choiceTwo = new JButton();
    private final JButton choiceThree = new JButton();
    private final JButton choiceFour = new JButton();
    private final JButton fiftyFifty = new JButton("50/50");
    private final JButton callTheGod = new JButton("Ask God");
    private final JButton quitButton = new JButton("Walk away");
    private final JButton loginButton = new JButton("Log in");
    private final JButton createUser = new JButton("Create new user");
    private final JButton create = new JButton("Create");
    private final JButton returnButton = new JButton("Return");

    private boolean started = false;
    private int fiftyFiftyCounter = 1;
    private int walkedAway = 0;


    BufferedImage myPicture = ImageIO.read(new File("src/millionairegui/BackgroundLogin.jpg"));
    JLabel picLabel = new JLabel(new ImageIcon(myPicture));

    /**
     * View constructor starts by showing the login screen.
     *
     * @throws java.io.IOException
     */
    public View() throws IOException {

        /*
            Null layout on all JPanels.
         */
        this.userPanel.setLayout(null);
        this.gamePanel.setLayout(null);
        this.createNewUserPanel.setLayout(null);
        this.quitPanel.setLayout(null);

        super.setTitle("Who Wants to Be a Millionaire");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setSize(500, 300); // Set frame size
        super.setLocationRelativeTo(null); // Make the frame located at the absolute center of the screen.
        super.setResizable(false); // Make the frame not resizeable

        /*
            Disabling focus for all the buttons so they are not highlighted.
         */
        choiceOne.setFocusable(false);
        choiceTwo.setFocusable(false);
        choiceThree.setFocusable(false);
        choiceFour.setFocusable(false);
        this.quitButton.setFocusable(false);
        this.fiftyFifty.setFocusable(false);
        this.callTheGod.setFocusable(false);
        this.returnButton.setFocusable(false);
        this.create.setFocusable(false);
        this.createUser.setFocusable(false);

        /*
            Defines bounds for all buttons and labels
            and also their foreground.
            adds a background to the frame.
         */
        this.loginButton.setBounds(175, 173, 150, 40);
        this.createUser.setBounds(175, 220, 150, 40);
        this.createUname.setBounds(80, 111, 90, 20);
        this.createUname.setForeground(Color.white);
        this.createPword.setBounds(80, 143, 90, 20);
        this.createPword.setForeground(Color.white);
        this.create.setBounds(170, 190, 160, 50);
        this.uName.setBounds(105, 110, 70, 20);
        this.uName.setForeground(Color.white);
        this.pWord.setBounds(105, 140, 70, 20);
        this.pWord.setForeground(Color.white);
        this.message.setBounds(0, 80, 500, 20);
        this.message.setForeground(Color.white);
        this.welcome.setBounds(0, 30, 500, 20);
        this.welcome.setFont(new Font("Courier", Font.BOLD | Font.ITALIC, 20));
        this.welcome.setForeground(Color.white);
        this.newULabel.setBounds(100, 30, 300, 20);
        this.newULabel.setFont(new Font("Courier", Font.BOLD | Font.ITALIC, 20));
        this.newULabel.setForeground(Color.white);
        this.picLabel.setBounds(0, 0, 500, 300);
        this.picLabel.setIcon(new ImageIcon(myPicture.getScaledInstance(500, 300, Image.SCALE_SMOOTH)));
        this.picLabel.setIcon(new ImageIcon(myPicture.getScaledInstance(500, 300, Image.SCALE_SMOOTH)));
        this.question.setBounds(2, 65, 490, 30);
        this.question.setForeground(Color.white);
        this.question.setFont(new Font("Courier", Font.BOLD | Font.ITALIC, 14));
        this.question.setBorder(new LineBorder(Color.white, 4));
        this.choiceOne.setBounds(20, 113, 220, 35);
        this.choiceTwo.setBounds(260, 113, 220, 35);
        this.choiceThree.setBounds(20, 168, 220, 35);
        this.choiceFour.setBounds(260, 168, 220, 35);
        this.fiftyFifty.setBounds(145, 10, 100, 40);
        this.quitButton.setBounds(390, 225, 100, 40);
        this.callTheGod.setBounds(255, 10, 100, 40);
        this.returnButton.setBounds(10, 10, 80, 40);
        this.userPanel.add(this.message);

        this.userLogin(); //calls userLogin function

    }

    /**
     * Adds all the buttons and labels required in the login screen to the
     * userPanel.
     */
    public void userLogin() {
        this.unInput.setBounds(175, 110, 150, 25);
        this.pwInput.setBounds(175, 140, 150, 25);

        this.pWord.setText("Password: ");
        this.uName.setText("Username: ");
        this.userPanel.add(uName);
        this.userPanel.add(unInput);
        this.userPanel.add(pWord);
        this.userPanel.add(pwInput);
        this.userPanel.add(loginButton);
        this.userPanel.add(createUser);
        this.userPanel.add(this.message);
        this.userPanel.add(this.welcome);
        this.userPanel.add(picLabel);
        this.getContentPane().removeAll();
        this.getContentPane().add(userPanel);
        this.setVisible(true);
        this.repaint();
        this.revalidate();
    }

    /**
     * Action listeners for all the buttons which will be defined in the
     * controller class.
     *
     * @param listener
     */
    public void addActionListener(ActionListener listener) {
        this.loginButton.addActionListener(listener);
        this.choiceOne.addActionListener(listener);
        this.choiceTwo.addActionListener(listener);
        this.choiceThree.addActionListener(listener);
        this.choiceFour.addActionListener(listener);
        this.quitButton.addActionListener(listener);
        this.fiftyFifty.addActionListener(listener);
        this.createUser.addActionListener(listener);
        this.create.addActionListener(listener);
        this.returnButton.addActionListener(listener);
        this.callTheGod.addActionListener(listener);
    }

    /**
     * adds all the required buttons and labels to the gamePanel. And define
     * actionCommands for the possible answers respectively. game screen,
     * displays question, answers and lifelines.
     *
     */
    public void startGame() {
        gamePanel.add(question);
        gamePanel.add(choiceOne);
        gamePanel.add(choiceTwo);
        gamePanel.add(choiceThree);
        gamePanel.add(choiceFour);
        gamePanel.add(quitButton);
        gamePanel.add(fiftyFifty);
        gamePanel.add(callTheGod);
        this.choiceOne.setActionCommand("1");
        this.choiceTwo.setActionCommand("2");
        this.choiceThree.setActionCommand("3");
        this.choiceFour.setActionCommand("4");
        this.gamePanel.add(picLabel);

        this.getContentPane().removeAll();
        gamePanel.setVisible(true);
        this.add(gamePanel);
        this.repaint();
        this.revalidate();

    }

    /**
     * Checks the button state and disables/enables them respectively when a
     * lifeline or possible answers are clicked.
     */
    public void checkButtonState() {
        if (this.fiftyFiftyCounter == 1) {
            this.fiftyFifty.setEnabled(true);
        }
        if (choiceOne.getText().isEmpty()) {
            choiceOne.setEnabled(false);
        } else {
            choiceOne.setEnabled(true);
        }
        if (choiceTwo.getText().isEmpty()) {
            choiceTwo.setEnabled(false);
        } else {
            choiceTwo.setEnabled(true);
        }
        if (choiceThree.getText().isEmpty()) {
            choiceThree.setEnabled(false);
        } else {
            choiceThree.setEnabled(true);
        }
        if (choiceFour.getText().isEmpty()) {
            choiceFour.setEnabled(false);
        } else {
            choiceFour.setEnabled(true);
        }

    }

    /**
     *
     * Sets the question data to the buttons/labels.
     *
     * @param question
     * @param choices
     * @param questionNumber
     */
    public void setQuestion(String question, String[] choices, int questionNumber) {
        this.question.setText("Q" + questionNumber + ": " + question);

        this.choiceOne.setText(choices[0]);
        this.choiceTwo.setText(choices[1]);
        this.choiceThree.setText(choices[2]);
        this.choiceFour.setText(choices[3]);

    }

    /**
     * Adds the required buttons and labels to the createPanel
     *
     * Create new user screen
     */
    public void createNewUser() {
        this.unInput.setBounds(170, 110, 160, 25);
        this.pwInput.setBounds(170, 140, 160, 25);

        this.createNewUserPanel.add(returnButton);
        this.createNewUserPanel.add(newULabel);
        this.createNewUserPanel.add(createUname);
        this.createNewUserPanel.add(unInput);
        this.createNewUserPanel.add(createPword);
        this.createNewUserPanel.add(pwInput);
        this.createNewUserPanel.add(create);
        this.createNewUserPanel.add(this.message);
        this.getContentPane().removeAll();
        createNewUserPanel.setVisible(true);
        this.add(createNewUserPanel);
        this.createNewUserPanel.add(picLabel);
        this.repaint();
        this.revalidate();

    }

    /**
     * Disables the 50/50 button after one use.
     */
    public void fiftyFifty() {
        this.fiftyFiftyCounter--;
        this.fiftyFifty.setEnabled(false);

    }

    /**
     * Disables the AskGod button after being clicked and also the 50/50 for
     * that question which will be enabled in the next question if not already
     * used.
     */
    public void askGod() {
        this.callTheGod.setEnabled(false);
        this.fiftyFifty.setEnabled(false);
    }

    /**
     * Closes the jFrame
     */
    public void disposeFrame() {
        super.dispose();
    }

    /**
     * Adds all the required buttons and labels to the quitPanel. Quit game
     * screen , shows users winnings and total earnings and a leader board where
     * the users can compare their total earnings with other users.
     *
     * @param money
     * @param total
     */
    private void quitGame(int money, int total, Leaderboard[] leaderboard) {
        JList<Leaderboard> x = new JList(leaderboard);
        JLabel leaderboardTitle = new JLabel("Leaderboard");
        JLabel scoreLabel = new JLabel("You have won: " + money);
        JLabel scoreLabel2 = new JLabel("\n Your total earnings: " + total);
        x.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(x, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        scrollPane.setBounds(100, 140, 300, 130);
        scoreLabel.setBounds(200, 40, 300, 15);
        scoreLabel.setForeground(Color.white);
        scoreLabel2.setBounds(197, 60, 300, 15);
        scoreLabel2.setForeground(Color.white);
        leaderboardTitle.setBounds(190, 110, 600, 20);
        leaderboardTitle.setFont(new Font("Courier", Font.BOLD | Font.ITALIC, 20));
        leaderboardTitle.setForeground(Color.white);
        this.endMessage.setBounds(200, 20, 500, 15);
        this.endMessage.setForeground(Color.white);

        quitPanel.add(scrollPane);
        quitPanel.add(this.endMessage);
        quitPanel.add(scoreLabel);
        quitPanel.add(scoreLabel2);
        quitPanel.add(returnButton);
        quitPanel.add(leaderboardTitle);
        quitPanel.add(picLabel);
        this.getContentPane().removeAll();

        this.add(quitPanel);
        quitPanel.setVisible(true);
        this.repaint();
        this.revalidate();

    }
    
    /*
        Getters and setters
    */
    public JTextField getUnInput() {
        return unInput;
    }

    public JPasswordField getPwInput() {
        return pwInput;
    }

    public JLabel getMessage() {
        return message;
    }

    public void setFiftyFiftyCounter(int fiftyFiftyCounter) {
        this.fiftyFiftyCounter = fiftyFiftyCounter;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isStarted() {
        return started;
    }
    
     public int getWalkedAway() {
        return walkedAway;
    }

    public void setWalkedAway(int walkedAway) {
        this.walkedAway = walkedAway;
    }

    /**
     * updates the view whenever Data class is updated in model class and the notify observer is called
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        Data data = (Data) arg;
        if (!data.getCheck()) {
            if (!data.getHasLogin()) {
                this.unInput.setText("");
                this.pwInput.setText("");
                this.message.setText("Invalid username or password.");
            } else if (data.userAlreadyExists) {
                this.unInput.setText("");
                this.pwInput.setText("");
                this.message.setText("User already exists please input another username");
                this.createNewUser();
            } else {
                this.unInput.setText("");
                this.pwInput.setText("");
                this.message.setText("Successfully created user");
                this.userLogin();
            }
        } else if (data.getHasQuit()) {
            if(data.getCurrentQuestion() == 16){
                this.endMessage.setText("Congratulations! you have won");
            }
            if (!data.getCorrect()) {
                this.endMessage.setText("IncorrectAnswer");
            } else if(this.getWalkedAway() ==1){
                this.endMessage.setText("You walked away");
            }
            this.quitGame(data.getCurrentMoney(), data.getTotalMoney(), data.getLeaderboardArray());
        } else if (!this.started) {
            this.startGame();
            this.started = true;
            this.setQuestion(data.getQuestion(), data.getChoices(), data.getCurrentQuestion());
        } else {
            if (choiceOne.getText().trim().isEmpty()) {
                choiceOne.setEnabled(false);
            } else {
                choiceOne.setEnabled(true);
            }
            this.setQuestion(data.getQuestion(), data.getChoices(), data.getCurrentQuestion());
        }
    }

}
