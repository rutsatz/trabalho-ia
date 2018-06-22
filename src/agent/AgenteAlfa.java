package agent;

import NineMensMorris.GameInfo;
import NineMensMorris.PlayerAgent;
import avaliacao.AvaliacaoAlfa;
import trabalhominmax.TrabalhoMinmax;

public class AgenteAlfa implements PlayerAgent {

    @Override
    public String setPiece(GameInfo info) {
        int profundidade = 3;
        //int quantidadePecasColocar = info.getPiecesToPlace();
        int quantidadeLugaresLivres = info.getEmptySpots().size();

        if (quantidadeLugaresLivres <= 5) {
            profundidade += quantidadeLugaresLivres;
        }

        MinimaxTree tree = new MinimaxTree(profundidade, MinimaxTree.SET_ACTION, info) {
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
        int profundidade = 3;
        int quantidadeMovimentosPermitidos = info.getAllowedMoves().size();
        switch (quantidadeMovimentosPermitidos) {
            case 9:
                break;
            case 8:
                break;
            case 7:
                break;
            case 6:
                profundidade += 1;
                break;
            case 5:
                profundidade += 1;
                break;
            case 4:
                profundidade += 2;
                break;
            case 3:
                profundidade += 3;
                break;
            case 2:
                profundidade += 4;
                break;
            case 1:
                profundidade += 5;
                break;
            default:
                break;

        }

        MinimaxTree tree = new MinimaxTree(profundidade, MinimaxTree.MOVE_ACTION, info) {
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
        int profundidade = 5;
        int quantidadePecasRemover = info.getAllowedRemoves().size();
        
        switch (quantidadePecasRemover) {
            case 9:
                break;
            case 8:
                break;
            case 7:
                break;
            case 6:
                profundidade += 1;
                break;
            case 5:
                profundidade += 1;
                break;
            case 4:
                profundidade += 2;
                break;
            case 3:
                profundidade += 3;
                break;
            case 2:
                profundidade += 4;
                break;
            case 1:
                profundidade += 5;
                break;
            default:
                break;
        }

        MinimaxTree tree = new MinimaxTree(profundidade, MinimaxTree.REMOVE_ACTION, info) {
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
        return AvaliacaoAlfa.avalia(info);
    }
}
