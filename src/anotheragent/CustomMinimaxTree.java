/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anotheragent;

import NineMensMorris.GameInfo;

/**
 *
 * @author Fabio, Maike
 */
public class CustomMinimaxTree extends MinimaxTree {

    public CustomMinimaxTree(int depth, int action, GameInfo gameInfo) {
        super(depth, action, gameInfo);
    }

    @Override
    public int scoreGameState( Node node  ) {

        GameInfo gameInfo = this.getGameInfo();

        // verificar se cada jogador pode completar linha de 3 com a posiçao atual;
        int playerHasLine = (gameInfo.canPlayerFormLineOfThree(node.getValue(), node.getGameState())) ? 5 : 0;
        // opponent completara linha de 3 com a posiçao atual;
        int opponentHasLine = (gameInfo.canOpponentFormLineOfThree(node.getValue(), node.getGameState())) ? 4 : 0;

        // verifica se posicao e uma linha de 3
        int playerLineOfThree = (gameInfo.isPlayerLineOfThree(node.getValue(), node.getGameState())) ? 1 : 0;
        int opponentLineOfThree = (gameInfo.isOpponentLineOfThree(node.getValue(), node.getGameState())) ? 1 : 0;

        // verifica se posicao e uma linha de 2
        int playerLineOfTwo = (gameInfo.isPlayerLineOfTwo(node.getValue(), node.getGameState())) ? 1 : 0;
        int opponentLineOfTwo = (gameInfo.isOpponentLineOfTwo(node.getValue(), node.getGameState())) ? 1 : 0;

        // contabiliza as pecas ocupadas por cada jogador
        int playerSpots = gameInfo.getPlayerSpots(node.getGameState()).size();
        int opponentSpots = gameInfo.getOpponentSpots(node.getGameState()).size();

        return (playerSpots - opponentSpots) + (playerHasLine - opponentHasLine) + (playerLineOfTwo + opponentLineOfTwo) + (playerLineOfThree + opponentLineOfThree);
    };

}
