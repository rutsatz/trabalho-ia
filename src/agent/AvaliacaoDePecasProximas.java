package agent;

import NineMensMorris.GameInfo;
import NineMensMorris.PlayerAgent;
import avaliacao.FuncaoAvaliacao;
import trabalhominmax.TrabalhoMinmax;

/**
 *
 * @author will
 */
public class AvaliacaoDePecasProximas implements PlayerAgent {

    public static final int PROFUNDIDADE = 5;

    @Override
    public String setPiece(GameInfo info) {

        MinimaxTree tree = new MinimaxTree(calcularProfundidade(info), MinimaxTree.SET_ACTION, info) {
            @Override
            public int scoreGameState(MinimaxTree.Node node) {
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
            public int scoreGameState(MinimaxTree.Node node) {
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
            public int scoreGameState(MinimaxTree.Node node) {
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

    private int score(GameInfo info, MinimaxTree.Node node) {
        return FuncaoAvaliacao.avaliar(node.getGameState());
    }

    public int calcularProfundidade(GameInfo info) {
        return 4;
    }
}
