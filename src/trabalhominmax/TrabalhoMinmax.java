/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalhominmax;

import NineMensMorris.Game;
import agent.AgenteAlfa;
import anotheragent.FabioAgente;
import java.awt.EventQueue;

/**
 *
 * @author m72887
 */
public class TrabalhoMinmax {
    
    public static int DELAY_ENTRE_CADA_JOGADA = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game moinho = new Game();
                moinho.setVisible(true);
                moinho.addAgent(new FabioAgente());
                moinho.addAgent(new AgenteAlfa());
            }
        });
    }
    
}
