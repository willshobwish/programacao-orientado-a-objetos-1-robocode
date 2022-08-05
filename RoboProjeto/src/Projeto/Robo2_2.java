//Andressa Yida Pinheiro de Souza
//Willian Yoshio Murayama
package Projeto;

import Pai.MeuRobo2;
import robocode.*;

public class Robo2_2 extends MeuRobo2 {

    protected double distance;

    public void onScannedRobot(ScannedRobotEvent e) {
        double distance = e.getDistance();
    }

    @Override
    public void run() {
        if (distance < 50) {

        }
// Robot main loop
        while (true) {
            mover();
        }
    }
}
