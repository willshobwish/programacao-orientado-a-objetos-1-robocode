/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Projeto;

import robocode.HitByBulletEvent;

/**
 *
 * @author Willian
 */
public class Robo2_1 extends MeuRobo2 {

    @Override
    public void onHitByBullet(HitByBulletEvent event) {
        mover();
    }

    @Override
    public void mover() {
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
