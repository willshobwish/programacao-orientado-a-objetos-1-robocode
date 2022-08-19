package atividaderobocode;

import java.awt.geom.*;

/**
 *
 * @author Danilo Medeiros Eler (FCT-UNESP) - https://daniloeler.github.io/
 */
public class MeuRobo1_1 extends MeuRobo1 {

    Point2D direcao;  // Vetor normalizado com a direção de movimento do tanque
    Point2D canto;    // Vetor do tanque até o canto mais próximo a que ele se aproxima
    double distancia; // Distância até as paredes
    final double limite = 80; // Limite utilizado para troca de direção

    public void calcularDistancia() {

        // Pega a altura e largura do campo de batalha e posição x,y do tanque
        double h = getBattleFieldHeight(); // Altura
        double w = getBattleFieldWidth();  // Largura
        double x = getX();
        double y = getY();

        // Pega a direção em que o tank se move e a sua posição atual (x, y) no campo de batalha
        double ang = getHeading(); // O ângulo está em graus, variando entre 0 (apontando pra cima) e 359) no sentido horário

        // Calcula o vetor normal de direção do tanque
        double dx = Math.sin(Math.toRadians(ang));
        double dy = Math.cos(Math.toRadians(ang));
        direcao = new Point2D.Double(dx, dy);

        // Calcula o vetor do tanque em direção ao canto mais próximo da direção e sentido que ele segue
        dx = (direcao.getX() > 0) ? w - x : -x;
        dy = (direcao.getY() > 0) ? h - y : -y;
        canto = new Point2D.Double(dx, dy);

        // Calcula os angulos entre o vetor de direcao e os vetores dos eixos x e y
        double angX = Math.acos(Math.abs(direcao.getX()));
        double angY = Math.acos(Math.abs(direcao.getY()));

        // A distância é o cateto adjascente do menor ângulo
        if (angY < angX) {
            distancia = Math.abs(canto.getY() / Math.cos(angY));
        } else {
            distancia = Math.abs(canto.getX() / Math.cos(angX));
        }
    }

    @Override
    public void configurarRobo() {
        configurarCor("Palmeiras");
    }

    @Override
    public void mover() {
        ahead(20);
        turnGunRight(20);

        calcularDistancia();

        if (distancia <= limite) {
            turnLeft(180);
        }
    }

}
