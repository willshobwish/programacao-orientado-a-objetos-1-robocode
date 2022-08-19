package atividaderobocode;

import robocode.*;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.Color;

public class RoboPersonalizado  extends AdvancedRobot {
    
    boolean direcaoFrente;
    boolean direcaoParede;
    //Limite até onde o tanque pode chegar próximo à parede
    final double limite = 80;

 
    @Override
    public void run() {
        
        setColors(Color.black, Color.black, Color.black, Color.green, Color.green);

        //Esse conjunto de sets permite que o radar, a arma e o corpo se movam independentemente
	setAdjustRadarForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForGunTurn(true);
        
        //Pegamos a altura e comprimento do campo de batalha, e a posição x e y do tanque
        //Se estiver aproximando da parede além do limite: direcaoParede=true
        if (getX() <= limite || getY() <= limite || getBattleFieldWidth() - getX() <= limite || getBattleFieldHeight() - getY() <= limite) {
            direcaoParede = true;
        } else {
            direcaoParede = false;
        }

        //Movimento padrão frente, e girar radar 360º para escanear inimigos
        setAhead(40000);
        setTurnRadarRight(360);
        direcaoFrente = true;

        //Se ultrapassar o limite, o método inverterDirecao é invocado
        while (true) {
            if (getX() > limite && getY() > limite && getBattleFieldWidth() - getX() > limite && getBattleFieldHeight() - getY() > limite && direcaoParede == true) {
                direcaoParede = false;
            }
            if (getX() <= limite || getY() <= limite || getBattleFieldWidth() - getX() <= limite || getBattleFieldHeight() - getY() <= limite ){
                if (direcaoParede == false) {
                inverterDirecao();
                direcaoParede = true;                    
                }
            }
            //Se o radar parar, volta a girar para escanear o campo de batalha
            if (getRadarTurnRemaining() == 0.0){
                setTurnRadarRight(360);
            }

            execute();
	}
    }
    
    
    @Override
    public void onHitWall(HitWallEvent e) {
	inverterDirecao();
	}
    
    
    //Inverte a direção do tanque
    public void inverterDirecao() {
	if (direcaoFrente) {
            setBack(40000);
            direcaoFrente = false;
        } else {
            setAhead(40000);
            direcaoFrente = true;
		}
	}
    
    
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        setAdjustRadarForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForGunTurn(true);
        
        //Rastreia o inimigo com o radar e a arma
        //Encontramos a diferença entre o ângulo do tanque (getHeading) e o ângulo do radar/arma, e adicionamos o ângulo do adversário em relação ao nosso tanque (e.getBearing)
        setTurnRadarRight(getHeading() - getRadarHeading() + e.getBearing());
        setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
        
        //Move em círculos em torno do inimigo. Adicionando 80º ou 100º no ângulo de curvatura faz com que o tanque se aproxime cada vez mais
        //normalRelativeAngleDegrees() normaliza o ângulo para a faixa entre -180 e 180 (graus)
        if (direcaoFrente){
            setTurnRight(normalRelativeAngleDegrees(e.getBearing() + 80));
            } else {
            setTurnRight(normalRelativeAngleDegrees(e.getBearing() + 100));
            }
        
        //Todas as armas começam a rodada com gunHeat máximo. A arma só dispara quando esse atributo está em 0
        //Chamar fire quando o valor é diferente de 0 não resulta em erro, mas o disparo simplesmente não ocorre
        //Tanques gastam energia para atirar. Para evitar que gastemos até o fim e o tanque entre em estado "disabled", guarda um mínimo de 6
        //O tanque pode atirar antes de alinhar a arma com o alvo. Para prevenir isso, esperamos até que a arma esteja quase totalmente posicionada
            //getGunTurnRemaining() retorna o ângulo que falta para a arma virar, em graus.
                //Positivo: está virando para direita. Negativo: virando para a esquerda. Se 0, a arma não está girando. Aqui, setamos um máximo de até 10º faltantes
        if (getGunHeat() == 0 && getEnergy() > .6 && Math.abs(getGunTurnRemaining()) < 10) {
            //Força dos disparos é calculada com base na distância do alvo, em pixels. Força máxima é 3; mesmo que o cálculo retorne valor maior, dispara com força 3
            //Quanto mais próximo, maior a força
            setFire(Math.min(400 / e.getDistance(), 3));
        }
	}


    @Override
    public void onHitRobot(HitRobotEvent e) {
	setFire(3);
        inverterDirecao();
	}

}

