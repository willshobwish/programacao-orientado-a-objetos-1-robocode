package atividaderobocode;

import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.geom.*;

public class RoboPersonalizado  extends AdvancedRobot {
    
    boolean movingForward;
    boolean inWall;
    final double limite = 80;

 
    @Override
    public void run() {

	setAdjustRadarForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForGunTurn(true);
        
        if (getX() <= limite || getY() <= limite || getBattleFieldWidth() - getX() <= limite || getBattleFieldHeight() - getY() <= limite) {
            inWall = true;
        } else {
            inWall = false;
        }

        setAhead(40000);
        setTurnRadarRight(360);
        movingForward = true;

        while (true) {
            if (getX() > limite && getY() > limite && getBattleFieldWidth() - getX() > limite && getBattleFieldHeight() - getY() > limite && inWall == true) {
                inWall = false;
            }
            if (getX() <= limite || getY() <= limite || getBattleFieldWidth() - getX() <= limite || getBattleFieldHeight() - getY() <= limite ){
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
        
        if (getGunHeat() == 0 && getEnergy() > .6) {
            setFire(Math.min(400 / e.getDistance(), 3));
        }
	}


    @Override
    public void onHitRobot(HitRobotEvent e) {
	setFire(3);
        reverseDirection();
	}

}

