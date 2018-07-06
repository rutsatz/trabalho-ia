package agent;

import NineMensMorris.GameInfo;
import NineMensMorris.PlayerAgent;
import avaliacao.AvaliaPeloNumeroDePecas;
import avaliacao.Folha;
import avaliacao.FuncaoAvaliacao;
import avaliacao.PesoFolhaColocacaoPeca;

public class AgenteAlfa implements PlayerAgent {
    
    public static PesoFolhaColocacaoPeca pesoFolhaColocacaoPeca = new PesoFolhaColocacaoPeca();
    public static Folha folha = new Folha();

    @Override
    public String setPiece(GameInfo info) {
        Integer profundidade = 3;
        Integer quantidadeLugaresLivres = info.getEmptySpots().size();

        if (quantidadeLugaresLivres <= 10) {
            profundidade++;
        }

        if (quantidadeLugaresLivres <= 16) {
            profundidade++;
        }

        if (quantidadeLugaresLivres <= 20) {
            profundidade++;
        }
        MinimaxTree tree = new MinimaxTree(profundidade, MinimaxTree.SET_ACTION, info) {
            @Override
            public int scoreGameState(MinimaxTree.Node node) {
                return score(info, node);
            }
        };


        return tree.executeMinimax();
    }

    @Override
    public String movePiece(GameInfo info) {
        Integer profundidade = 3;
        Integer quantidadeMovimentosPermitidos = info.getPlayerSpots().size();

        if (quantidadeMovimentosPermitidos > 3) {
            profundidade++;
        }
        MinimaxTree tree = new MinimaxTree(profundidade, MinimaxTree.MOVE_ACTION, info) {
            @Override
            public int scoreGameState(MinimaxTree.Node node) {
                return score(info, node);
            }
        };


        return tree.executeMinimax();
    }

    @Override
    public String removePiece(GameInfo info) {
        Integer profundidade = 4;
        MinimaxTree tree = new MinimaxTree(profundidade, MinimaxTree.REMOVE_ACTION, info) {
            @Override
            public int scoreGameState(MinimaxTree.Node node) {
                return score(info, node);
            }
        };


        return tree.executeMinimax();
    }

    private int score(GameInfo info, MinimaxTree.Node node) {

        boolean colocacaoPecas = info.getPiecesToPlace() == 0 ? false : true;
        if (colocacaoPecas) {
            folha = pesoFolhaColocacaoPeca.avalia(node.getGameState(), colocacaoPecas, folha.getAgente(), folha.getInimigo());
            return folha.getResult();
        }

        if (info.getOpponentSpots().size() == info.getPlayerSpots().size()) {
            return FuncaoAvaliacao.avaliar(node.getGameState());
        } else {
            return AvaliaPeloNumeroDePecas.avaliaPeloNumeroDePecas(node.getGameState());
        }
    }
}
