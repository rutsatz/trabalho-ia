/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agent;

import NineMensMorris.GameInfo;
import NineMensMorris.PlayerAgent;
import avaliacao.FuncaoAvaliacao;

/**
 *
 * @author will
 */
public class Agente2 implements PlayerAgent {
    public static final int PROFUNDIDADE = 5;

    @Override
    public String setPiece(GameInfo info) {

        MinimaxTree tree = new MinimaxTree(calcularProfundidade(info), MinimaxTree.SET_ACTION, info) {
            @Override
            public int scoreGameState(MinimaxTree.Node node) {
                return score(info, node);
            }
        };

        return tree.executeMinimax();
    }

    @Override
    public String movePiece(GameInfo info) {
        MinimaxTree tree = new MinimaxTree(calcularProfundidade(info), MinimaxTree.MOVE_ACTION, info) {
            @Override
            public int scoreGameState(MinimaxTree.Node node) {
                return score(info, node);
            }
        };

        return tree.executeMinimax();
    }

    @Override
    public String removePiece(GameInfo info) {
        MinimaxTree tree = new MinimaxTree(calcularProfundidade(info), MinimaxTree.REMOVE_ACTION, info) {
            @Override
            public int scoreGameState(MinimaxTree.Node node) {
                return score(info, node);
            }
        };

        return tree.executeMinimax();
    }

    //private int score( GameInfo info, int[][] gameState, int playerTurn, int piecesToPlace, int opponentPiecesToPlace )
    private int score(GameInfo info, MinimaxTree.Node node) {
//        boolean colocacaoPecas = info.getPiecesToPlace() == 0 ? false : true;
//        if (colocacaoPecas) {
//            return FuncaoAvaliacao.avaliarColocacao(node.getGameState());
//        } 
        return FuncaoAvaliacao.avaliar(node.getGameState());
    }

    public int calcularProfundidade(GameInfo info) {
//        int movimentosPermitidos = info.getAllowedMoves().size() + info.getOpponentAllowedMoves().size();
//
//        boolean colocacaoPesas = info.getPiecesToPlace() == 0 ? false : true;
//        
//        if (colocacaoPesas) {
//            if (movimentosPermitidos < 10) {
//                return 4;
//            }
//            if (movimentosPermitidos < 15) {
//                return 5;
//            }
//            if (movimentosPermitidos < 20) {
//                return 6;
//            }
//        } else {
//            if (movimentosPermitidos < 5) {
//                return 7;
//            }
//            if (movimentosPermitidos < 10) {
//                return 6;
//            }
//            if (movimentosPermitidos < 15) {
//                return 5;
//            }
//            if (movimentosPermitidos < 20) {
//                return 4;
//            }
//        }
        return 3;
    }
}
