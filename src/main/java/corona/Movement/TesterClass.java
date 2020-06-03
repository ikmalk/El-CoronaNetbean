package corona.Movement;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Oliver
 */
public class TesterClass {

    public static void main(String[] args) {
        try {
            RandomizeInitiation ri = new RandomizeInitiation();
            ri.writeLogFile();
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }
}
