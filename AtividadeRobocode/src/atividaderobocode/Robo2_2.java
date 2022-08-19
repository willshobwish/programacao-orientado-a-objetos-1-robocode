package atividaderobocode;

import robocode.*;

public class Robo2_2 extends MeuRobo2 {
    
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        if (e.getDistance() < 50) {
           setFire(3);
        }  
        if (e.getDistance() >= 50 && e.getDistance() < 100) {
           setFire(2);
        }  
        else {
            setFire(1);
            }
    }
}
