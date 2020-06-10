package corona.Movement;
import test.*;

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
            Map map=new Map();
            ri.setMap(map);
            ri.initiate();
            map.show();
            ri.writeLogFile();
            //for example, gym1 by date1
            map.map[3][3].displayVisitorsByDate(0);
            map.map[3][4].displayVisitorsByDate(0);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
