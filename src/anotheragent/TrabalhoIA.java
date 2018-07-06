/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anotheragent;

import NineMensMorris.Game;
import java.awt.EventQueue;

/**
 *
 * @author mnunes
 */
public class TrabalhoIA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run()
            {
                Game moinho = new Game();
                moinho.setVisible(true);
                moinho.addAgent(new FabioAgente());
            }
        });
    }
    
}
