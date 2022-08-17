//Andressa Yida Pinheiro de Souza
//Willian Yoshio Murayama
package Pai;

import java.awt.Color;
import robocode.*;

public class RoboBasico extends AdvancedRobot {

    public void configurarCor(String time) {
        if (null != time)//colorir o tanque
        {
            switch (time) {
                case "São Paulo" -> {
                    setBodyColor(Color.red);
                    setGunColor(Color.black);
                    setRadarColor(Color.black);
                    setScanColor(Color.white);
                    setBulletColor(Color.white);
                }
                case "Palmeiras" -> {
                    setBodyColor(Color.green);
                    setGunColor(Color.white);
                    setRadarColor(Color.green);
                    setScanColor(Color.white);
                    setBulletColor(Color.green);
                }
                case "Corinthians" -> {
                    setBodyColor(Color.white);
                    setGunColor(Color.black);
                    setRadarColor(Color.black);
                    setScanColor(Color.white);
                    setBulletColor(Color.white);
                }
                case "Santos" -> {
                    setBodyColor(Color.black);
                    setGunColor(Color.white);
                    setRadarColor(Color.white);
                    setScanColor(Color.black);
                    setBulletColor(Color.black);
                }
                default -> {
                }
            }
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
