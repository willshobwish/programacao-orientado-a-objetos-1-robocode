//Andressa Yida Pinheiro de Souza
//Willian Yoshio Murayama
package Projeto;

import robocode.*;

public class MeuRobo2 extends RoboBasico {

    int forca_tiro = 1;

    public void atirar() {
        fire(1);
    }

    public void atirar(int forca) {
        fire(forca);
    }

    @Override
    public void configurarRobo() {
        configurarCor("Corinthians");
    }

    @Override
    public void mover() {
        setAhead(200);
        setTurnRight(30);
        turnGunRight(10);
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        atirar(forca_tiro);
        if (forca_tiro <= 3) {
            forca_tiro++;
        } else {
            forca_tiro = 1;
        }
        atirar();
    }

    @Override
    public void onHitWall(HitWallEvent e) {
// quando bater na parede, recuar 50 e virar 45 graus
        back(50);
        turnRight(45);
    }
}
