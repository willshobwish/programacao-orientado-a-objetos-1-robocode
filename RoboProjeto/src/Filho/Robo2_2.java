//Andressa Yida Pinheiro de Souza
//Willian Yoshio Murayama
package Filho;

import Pai.MeuRobo2;
import robocode.*;

public class Robo2_2 extends MeuRobo2 {

    private double distance;

    public void onScannedRobot(ScannedRobotEvent e) {
        distance = e.getDistance();
    }

    @Override
    public void mover() {
        if (distance >= 0 && distance < 50) {
            setFire(3);
        } else if (distance >= 50 && distance < 100) {
            setFire(2);
        } else {
            setFire(1);
        }
// Robot main loop
        while (true) {
            mover();
        }
    }
}