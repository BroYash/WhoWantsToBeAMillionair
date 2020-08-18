/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package millionairegui;

/**
 *
 * @@author Yashjeet Bajwa 17995368, Ranish Chand 17981697
 */
public class Leaderboard {
    private String name;
    private Integer totalMoney;
    
    /**
     * Constructor which sets the variables defined in the class to their respective values given through parameter.
     * @param name
     * @param money 
     */
    Leaderboard(String name , Integer money){
        this.name = name;
        this.totalMoney = money;
    }
    /**
     * Getters and setters
     * @return 
     */
     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMoney() {
        return totalMoney;
    }

    public void setMoney(Integer money) {
        this.totalMoney = money;
    }

    /**
     * Overrides the toString method to give a nice string representation of the 
     * data when called in JList from view class.
     * @return 
     */
    @Override
    public String toString()
	{
		return String.format("Name: %1$-30s TotalMoney: %2$-1d ", this.name,this.totalMoney);
                
	}
    
}
