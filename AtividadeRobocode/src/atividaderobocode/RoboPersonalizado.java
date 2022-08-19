package atividaderobocode;

import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class RoboPersonalizado extends MeuRobo1_1 {
    
    boolean movingForward;
    boolean inWall;
    
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        setAdjustRadarForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForGunTurn(true);
        setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
        setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
        if (movingForward){
            setTurnRight(normalRelativeAngleDegrees(e.getBearing() + 80));
            } else {
            setTurnRight(normalRelativeAngleDegrees(e.getBearing() + 100));
            }
        
        if (e.getDistance() < 100) {
            fire(2);
        }
	}
    
    @Override
    public void run() {

	setAdjustRadarForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForGunTurn(true);
        
        calcularDistancia();
        
        if (distancia <= limite) {
            inWall=true;
        } else {
            inWall=false;
        }
        
        setAhead(40000);
	setTurnRadarRight(360);
	movingForward = true;
        
	while (true) {
            if (distancia > limite) {
		inWall = false;
            }
            if (distancia <= limite){
                if (inWall == false) {
		reverseDirection();
		inWall = true;                    
                }
            }
            if (getRadarTurnRemaining() == 0.0){
		setTurnRadarRight(360);
            }
			
	execute();
			
	}
      }
    
    @Override
    public void onHitWall(HitWallEvent e) {
	reverseDirection();
	}


    public void reverseDirection() {
	if (movingForward) {
            setBack(40000);
            movingForward = false;
            } else {
		setAhead(40000);
		movingForward = true;
		}
	}

    @Override
    public void onHitRobot(HitRobotEvent e) {
	fire(3);
        reverseDirection();
	}

}
