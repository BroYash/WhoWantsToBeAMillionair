/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package millionairegui;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  Creates a connection with the player database
 * @author Yashjeet Bajwa 17995368, Ranish Chand 17981697
 */
public class PlayerDatabase {
    /**
     * Declaring variables
     */
    Connection conn = null;
    String url = "jdbc:derby:PlayerDB;create=true";
    String playerDbUsername = "pdc";
    String playerDbPassword = "pdc";
    Statement statement = null;
    
    /**
     * Creates a database connection, if there is no existing database, then it creates a new empty database in the users project directory. 
     */

    public void playerDbSetup() {
        try {
            conn = DriverManager.getConnection(url, playerDbUsername, playerDbPassword);
            statement = conn.createStatement();
            String tableName = "UserInfo";

            if (!checkTableExisting(tableName)) {
                statement.executeUpdate("CREATE TABLE " + tableName + " (userid VARCHAR(20), password VARCHAR(20), money INT)");
            }
            statement.close();

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDatabase.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
    /**
     * Checks the name and password of user in the database, if the user name and password match the database
     * then it updates the Data class.
     * otherwise the user will be given invalid input from the view class.
     * @param username
     * @param password
     * @return data
     */

    public Data checkName(String username, String password) {
        Data data = new Data(); // Initialize an instance of Data.
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT userid, password, money FROM UserInfo "
                    + "WHERE userid = '" + username + "'");
            if (rs.next()) {
                String pass = rs.getString("password");
                System.out.println("found user " + username);

                if (password.compareTo(pass) == 0) {;
                    data.setHasLogin(true);
                    data.setCheck(true);
                } else {
                    data.setHasLogin(false);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    /**
     * Selects userId and their total money from database and adds it to
     * the arrayList<Leaderboard> in data class.
     * @param data
     * @return data
     */
    public Data getLeaderboard(Data data){
        try {
            ResultSet rs = statement.executeQuery("SELECT userid, money FROM UserInfo");
            while(rs.next()){
                String name = rs.getString("userid");
                Integer money = rs.getInt("money");
                Leaderboard l = new Leaderboard(name,money);
                data.leaderboard.add(l);  
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    /**
     * Creates a new record for a new user in the database, if the new username is already taken
     * then the user will be asked to enter a new username.
     * @param username
     * @param password
     * @return data
     */
    public Data newUser(String username, String password) {
        Data data = new Data();
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT userid FROM UserInfo "
                    + "WHERE userid = '" + username + "'");
            if (rs.next()) {
                data.userAlreadyExists = true;
            } else {
                statement.executeUpdate("INSERT INTO UserInfo " + "VALUES('" + username + "', '" + password + "', 0)");
                data.setCurrentMoney(0);
                data.userAlreadyExists = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        data.setHasLogin(true);
        return data;
    }
    /**
     * Checks if the userInfo table is in the database, if it is there then no new table will be created.
     * @param newTableName
     * @return flag boolean
     */
    private boolean checkTableExisting(String newTableName) {
        boolean flag = false;
        try {

            System.out.println("check existing tables.... ");
            String[] types = {"TABLE"};
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rsDBMeta = dbmd.getTables(null, null, null, null);//types);
            //Statement dropStatement=null;
            while (rsDBMeta.next()) {
                String tableName = rsDBMeta.getString("TABLE_NAME");
                if (tableName.compareToIgnoreCase(newTableName) == 0) {
                    System.out.println(tableName + "  is there");
                    flag = true;
                }
            }
            if (rsDBMeta != null) {
                rsDBMeta.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlayerDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    /**
     * Updates users total money in the database.
     * @param money
     * @param username
     * @return total money
     */
    public int quitGame(int money, String username) {
        Statement statement;
        int total = 0;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT money FROM UserInfo "
                    + "WHERE userid = '" + username + "'");
            if (rs.next()) {
                total = rs.getInt("money");
            }
            total = total + money;

            statement.executeUpdate("UPDATE UserInfo SET money=" + total + " WHERE userid='" + username + "'");

        } catch (SQLException ex) {
            Logger.getLogger(PlayerDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

}
