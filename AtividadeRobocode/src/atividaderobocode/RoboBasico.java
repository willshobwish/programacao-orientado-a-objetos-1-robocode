package atividaderobocode;

import robocode.*;
import java.awt.Color;
/**
 *
 * @author Danilo Medeiros Eler (FCT-UNESP) - https://daniloeler.github.io/
 */
public class RoboBasico extends AdvancedRobot {

    public void configurarCor(String time) {
        if (null != time)//colorir o tanque
        switch (time) {
            case "São Paulo":
                setBodyColor(Color.red);
                setGunColor(Color.black);
                setRadarColor(Color.black);
                setScanColor(Color.white);
                setBulletColor(Color.white);
                break;
            case "Palmeiras":
                setBodyColor(Color.green);
                setGunColor(Color.white);
                setRadarColor(Color.green);
                setScanColor(Color.white);
                setBulletColor(Color.green);
                break;
            case "Corinthians":
                setBodyColor(Color.white);
                setGunColor(Color.black);
                setRadarColor(Color.black);
                setScanColor(Color.white);
                setBulletColor(Color.white);
                break;
            case "Santos":
                setBodyColor(Color.black);
                setGunColor(Color.white);
                setRadarColor(Color.white);
                setScanColor(Color.black);
                setBulletColor(Color.black);
                break;
            default:
                break;
        }
    }

    public void mover() {
        ahead(100);
        turnGunRight(180);
        back(100);
        turnGunRight(180);
    }

    public void configurarRobo() {
        configurarCor("São Paulo");
    }

    @Override
    public void run() {
        configurarRobo();
// Robot main loop
        while (true) {
            mover();
        }
    }

}