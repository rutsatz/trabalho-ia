package agent;

import NineMensMorris.GameInfo;
import agent.MinimaxTree;
import NineMensMorris.PlayerAgent;
import avaliacao.Folha;
import avaliacao.FuncaoAvaliacao;
import avaliacao.PesoFolhaColocacaoPeca;
import java.util.logging.Level;
import java.util.logging.Logger;
import trabalhominmax.TrabalhoMinmax;

public class Agente implements PlayerAgent {

    public static final int PROFUNDIDADE = 5;
    public static PesoFolhaColocacaoPeca pesoFolhaColocacaoPeca = new PesoFolhaColocacaoPeca();
    

    public static Folha folha = new Folha();
    
    
    @Override
    public String setPiece(GameInfo info) {

        MinimaxTree tree = new MinimaxTree(calcularProfundidade(info), MinimaxTree.SET_ACTION, info) {
            @Override
            public int scoreGameState(Node node) {
                return score(info, node);
            }
        };

        try {
            Thread.sleep(TrabalhoMinmax.DELAY_ENTRE_CADA_JOGADA);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
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

        try {
            Thread.sleep(TrabalhoMinmax.DELAY_ENTRE_CADA_JOGADA);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
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

        try {
            Thread.sleep(TrabalhoMinmax.DELAY_ENTRE_CADA_JOGADA);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return tree.executeMinimax();
    }

    //private int score( GameInfo info, int[][] gameState, int playerTurn, int piecesToPlace, int opponentPiecesToPlace )
    private int score(GameInfo info, MinimaxTree.Node node) {
        boolean colocacaoPesas = info.getPiecesToPlace() == 0 ? false : true;
        folha = pesoFolhaColocacaoPeca.avalia(node.getGameState(), colocacaoPesas, folha.getAgente(), folha.getInimigo());
        return folha.getResult();
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
        return 4;
    }
}
