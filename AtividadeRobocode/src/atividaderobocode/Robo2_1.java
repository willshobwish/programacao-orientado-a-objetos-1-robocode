package atividaderobocode;

import robocode.*;

public class Robo2_1 extends MeuRobo2 {
    
    @Override
    public void configurarRobo() {
        configurarCor("Santos");
    }
    
    @Override
    public void onHitByBullet(HitByBulletEvent event) {
       back(100);
       turnRight(90);
       ahead(50);
   }
}
