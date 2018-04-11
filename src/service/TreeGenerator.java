/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import NineMensMorris.GameInfo;
import avaliacao.PesoFolhaColocacaoPeca;
import static avaliacao.PesoFolhaColocacaoPeca.NUMEROINIMIGO;
import exception.JogadaException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.Tree;
import model.Tree.Node;
import model.TreeData;
import model.TreeNode;

/**
 * Classe responsável por gerar a árvore.
 *
 * @author m72887
 */
public class TreeGenerator {

    int[][] gi;

    int profundidade;

    /**
     *
     * @param gi
     * @param profundidade
     */
    public TreeGenerator(int[][] gi, int profundidade) {
        setGameInfo(gi);
        setProfundidade(profundidade);
    }

    /**
     * Gera a árvore.
     */
    public void generate() {

        // Pega o estado atual.
        int[][] estadoAtual = gi;

        boolean oponente = false;

        // verifica quem eh o proximo a jogar.
//        if(isOponente){
//            oponente = true;
//        }else{
//            oponente = false;
//        }
        oponente = true; // Fixo para testes. @@
        TreeNode[][] tree = gerarProfundidade(gi, profundidade, oponente);
    }

    /**
     * Gera os níveis conforme o parâmetro de profundidade.
     *
     * @param gi
     * @param profundidade
     * @return
     */
    private TreeNode[][] gerarProfundidade(int[][] gi, int profundidade, boolean oponente) {

        // O primeiro nível sempre contem o estado atual do jogo.
        TreeNode rootNode = new TreeNode(new TreeData(gi, oponente));

        // Adicionar if. @@
        // Se existem pecas pendentes de serem colocadas, pegar emptySpots.
        // Senao, pegar allowedMoves.
//        List<String> allowedMoves = gi.getAllowedMoves();
        List<String> allowedMoves = Arrays.asList(new String[]{"0,0;0,7", "1,2;1,3", "2,0;2,7;2,1"});

        // Representa a quantidade de pecas que posso movimentar.
        int pecasLivres = allowedMoves.size();

        // Percorre todas as jogadas possiveis para cada peca.
        for (int i = 0; i < pecasLivres; i++) {
            String[] jogadasPeca = allowedMoves.get(i).split(";");

            TreeNode node;

            do {
                node = addLevel(gi, profundidade, !oponente);

                // Adiciona o nivel na lista.
//                node.getChildren().add(node);
            } while (node != null);

            // Adiciona os filhos no elemento raiz.
//            rootNode.getChildren().add(node);
        }

        return null;
    }

    /**
     * Retorna a posição da próxima jogada válida.
     *
     * @param gi
     * @param oponente
     * @return
     */
    private String proximaJogada(int[][] gi, boolean oponente) throws JogadaException {

        String proximaJogadaValida = null;

        if (oponente) {

            // Adicionar if. @@
            // Se existem pecas pendentes de serem colocadas, pegar emptySpots.
            // Senao, pegar allowedMoves.
//            List<String> opponentAllowedMoves = gi.getOpponentAllowedMoves();
            List<String> opponentAllowedMoves = Arrays.asList(new String[]{"0,5;0,6", "0,6;0,5;0,7", "2,3;1,3"});
            boolean jogadaValida = false;

            // Procura uma jogada disponível dentre todas as posições do tabuleiro.
            for (int i = 0; i < opponentAllowedMoves.size() && !jogadaValida; i++) {
//                String jogada = opponentAllowedMoves.get(i);

//                Percorre as pecas livres, que permitem alguma jogada valida.
//                List<String> pecasLivres = gi.getAllowedMoves(jogada);
                List<String> pecasLivres = Arrays.asList(new String[]{"0,6;0,5;0,7;1,3"});

                // Verifica se a joga encontrada é válida.
                for (int j = 0; j < pecasLivres.size(); j++) {

                    String[] jogadas = pecasLivres.get(j).split(";");

                    // Para cada peca livre, percorre as jogadas possiveis.
                    // O elemento 0 contem a posicao da peca.
                    for (int k = 1; k < jogadas.length; k++) {
//                         jogadaValida = gi.isSetAllowed(jogadas[k]);
                        jogadaValida = true;
                        if (jogadaValida) {
//                    Retorna a jogada válida.
                            proximaJogadaValida = jogadas[k];
                            return proximaJogadaValida;
                        }
                    }
                }

            }

        } else {

            // Adicionar if. @@
            // Se existem pecas pendentes de serem colocadas, pegar emptySpots.
            // Senao, pegar allowedMoves.
//            List<String> allowedMoves = gi.getAllowedMoves();
            List<String> allowedMoves = Arrays.asList(new String[]{"0,3;1,3", "1,2;1,3", "0,4;0,5"});
            boolean jogadaValida = false;

            // Procura uma jogada disponível dentre todas as posições do tabuleiro.
            for (int i = 0; i < allowedMoves.size() && !jogadaValida; i++) {
                String jogada = allowedMoves.get(i);
//                List<String> jogadasLivres = gi.getAllowedMoves(jogada);
                List<String> jogadasLivres = Arrays.asList(new String[]{"0,3;1,3"});

                // Verifica se a joga encontrada é válida.
                for (int j = 0; j < jogadasLivres.size(); j++) {
//                    jogadaValida = gi.isSetAllowed(jogada);
                    jogadaValida = true;
                    // Retorna a joga válida.
                    proximaJogadaValida = jogada;
                }

            }

        }

        if (proximaJogadaValida == null) {
            throw new JogadaException("Erro. Nenhuma jogada válida encontrada.");
        }

        return proximaJogadaValida;
    }

    /**
     * Adiciona um novo nivel/
     *
     * @param gi
     * @param profundidade
     * @param oponente
     * @return
     */
    private TreeNode addLevel(int[][] gi, int profundidade, boolean oponente) {

        TreeNode node = null;
        
//        TreeData data = new TreeData(gi, oponente);

        // Condição de parada.
        if (profundidade != 0) {

            try {
                // Calcula a próxima jogada.
                String proximaJogada = proximaJogada(gi, !oponente);

                // Monta a matriz com a proxima jogada válida encontrada.
                int[][] nextState = montarMatrizProximaJogada(gi, !oponente, proximaJogada);
                // Fica chamando a função até atingir a profundidade 0 (Limite).
                node = addLevel(nextState, profundidade - 1, !oponente);
                if (node != null) {
                    return node;
                }
            } catch (JogadaException exception) {
                exception.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return null;
    }

    /**
     * Adiciona a jogada calculada na matriz.
     *
     * @param gi
     * @param oponente
     * @param proximaJogada
     * @return
     */
    private int[][] montarMatrizProximaJogada(int[][] gi, boolean oponente, String proximaJogada) {
        // Posiciona a peca na posicao desejada.
//        gi.isSetAllowed(proximaJogada);
//        return gi;

        // Tratamento para a matriz. Ver como que faz com o GameInfo.
        String[] coords = proximaJogada.split(",");
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);

        if (oponente) {
            gi[x][y] = PesoFolhaColocacaoPeca.NUMEROINIMIGO; // 2
        } else {
            gi[x][y] = PesoFolhaColocacaoPeca.NUMEROAGENTE; // 1
        }

        return gi;
    }

    public static void main(String[] args) {

        int[][] multi = new int[][]{
            {1, 0, 0, 1, 2, 0, 2, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {1, 0, 0, 2, 0, 2, 0, 0}
        };

        TreeGenerator tg = new TreeGenerator(multi, 1);
        tg.generate();
    }

    /**
     *
     * @param gi
     */
    public void setGameInfo(int[][] gi) {
        this.gi = gi;
    }

    /**
     *
     * @param profundidade
     */
    public void setProfundidade(int profundidade) {
        this.profundidade = profundidade;
    }

}
