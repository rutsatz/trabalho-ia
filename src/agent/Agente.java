package agent;

import NineMensMorris.GameInfo;
import agent.MinimaxTree;
import NineMensMorris.PlayerAgent;
import avaliacao.PesoFolhaColocacaoPeca;

public class Agente implements PlayerAgent {

    public static final int PROFUNDIDADE = 4;

    @Override
    public String setPiece(GameInfo info) {
        MinimaxTree tree = new MinimaxTree(PROFUNDIDADE, MinimaxTree.SET_ACTION, info) {
            @Override
            public int scoreGameState(Node node) {
                return score(info, node);
            }
        };

        return tree.executeMinimax();
    }

    @Override
    public String movePiece(GameInfo info) {
        MinimaxTree tree = new MinimaxTree(PROFUNDIDADE, MinimaxTree.MOVE_ACTION, info) {
            @Override
            public int scoreGameState(Node node) {
                return score(info, node);
            }
        };

        return tree.executeMinimax();
    }

    @Override
    public String removePiece(GameInfo info) {
        MinimaxTree tree = new MinimaxTree(PROFUNDIDADE, MinimaxTree.REMOVE_ACTION, info) {
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
}
