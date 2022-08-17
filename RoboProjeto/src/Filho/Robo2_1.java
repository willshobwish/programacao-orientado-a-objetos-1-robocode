//Andressa Yida Pinheiro de Souza
//Willian Yoshio Murayama
package Filho;

import Pai.MeuRobo2;
import robocode.*;

public class Robo2_1 extends MeuRobo2 {

    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        setBack(100);
        setTurnRight(90);
        setAhead(50);
    }

    @Override
    public void configurarRobo() {
        configurarCor("Santos");
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
