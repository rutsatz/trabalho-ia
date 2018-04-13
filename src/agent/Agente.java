package agent;

import NineMensMorris.GameInfo;
import agent.MinimaxTree;
import NineMensMorris.PlayerAgent;
import avaliacao.PesoFolhaColocacaoPeca;

public class Agente implements PlayerAgent {

    public static final int PROFUNDIDADE = 5;

    @Override
    public String setPiece(GameInfo info) {

        MinimaxTree tree = new MinimaxTree(calcularProfundidade(info), MinimaxTree.SET_ACTION, info) {
            @Override
            public int scoreGameState(Node node) {
                return score(info, node);
            }
        };

        return tree.executeMinimax();
    }

    @Override
    public String movePiece(GameInfo info) {
        MinimaxTree tree = new MinimaxTree(calcularProfundidade(info), MinimaxTree.MOVE_ACTION, info) {
            @Override
            public int scoreGameState(Node node) {
                return score(info, node);
            }
        };

        return tree.executeMinimax();
    }

    @Override
    public String removePiece(GameInfo info) {
        MinimaxTree tree = new MinimaxTree(calcularProfundidade(info), MinimaxTree.REMOVE_ACTION, info) {
            @Override
            public int scoreGameState(Node node) {
                return score(info, node);
            }
        };

        return tree.executeMinimax();
    }

    //private int score( GameInfo info, int[][] gameState, int playerTurn, int piecesToPlace, int opponentPiecesToPlace )
    private int score(GameInfo info, MinimaxTree.Node node) {
        return PesoFolhaColocacaoPeca.avalia(node.getGameState());
    }

    public int calcularProfundidade(GameInfo info) {
        int totalPecasOponente = info.getOpponentSpots().size();
        int totalPecasAgente = info.getPlayerSpots().size();

        boolean colocacaoPesas = info.getPiecesToPlace() == 0 ? false : true;
        int totalPecas = totalPecasOponente + totalPecasAgente;

        if (colocacaoPesas) {

            if (totalPecas > 15) {
                return 7;
            }
            if (totalPecas > 10) {
                return 5;
            }
            if (totalPecas < 5) {
                return 3;
            }

        } else {
            if (totalPecas > 15) {
                return 3;
            }
            if (totalPecas > 10) {
                return 5;
            }
            if (totalPecas < 5) {
                return 7;
            }
        }

        return 3;
    }
}
