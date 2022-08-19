package atividaderobocode;

import robocode.*;

public class Robo2_2 extends MeuRobo2 {
    
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        atirar(forca_tiro);
        if (e.getDistance() < 50) {
           forca_tiro = 3;
        }  
        if (e.getDistance() >= 50 && e.getDistance() < 100) {
           forca_tiro = 2;
        }  
        else {
            forca_tiro=1;
            }
    }
}
