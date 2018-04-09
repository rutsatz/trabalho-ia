/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import NineMensMorris.GameInfo;
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

        // O primeiro nível sempre é do oponente.
        TreeNode rootNode = new TreeNode(new TreeData(gi, true));

        TreeNode[][] tree = gerarProfundidade(gi, profundidade, false);
    }

    /**
     * Gera os níveis conforme o parâmetro de profundidade.
     *
     * @param gi
     * @param profundidade
     * @return
     */
    private TreeNode[][] gerarProfundidade(int[][] gi, int profundidade, boolean oponente) {

        List<TreeNode> tree = new ArrayList<>();

//        List<String> allowedMoves = gi.getAllowedMoves();
        List<String> allowedMoves = Arrays.asList(new String[]{"0,0;0,7", "1,1;1,0", "2,1;2,0;2,2"});

        int possibilidades = allowedMoves.size();

        // Percorre todas as possibilidades.
        for (int i = 0; i < possibilidades; i++) {
            String jogada = allowedMoves.get(i);

            TreeNode node;

            do {
                node = addLevel(gi, profundidade, !oponente);

            } while (node != null);

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
//            List<String> opponentAllowedMoves = gi.getOpponentAllowedMoves();
            List<String> opponentAllowedMoves = Arrays.asList(new String[]{"0,3;1,3", "1,2;1,3", "0,4;0,5"});
            boolean jogadaValida = false;

            // Procura uma jogada disponível dentre todas as posições do tabuleiro.
            for (int i = 0; i < opponentAllowedMoves.size() && jogadaValida; i++) {
                String jogada = opponentAllowedMoves.get(i);
//                List<String> jogadasLivres = gi.getAllowedMoves(jogada);
                List<String> jogadasLivres = Arrays.asList(new String[]{"0,3;1,3"});

                // Verifica se a joga encontrada é válida.
                for (int j = 0; j < jogadasLivres.size(); j++) {
//                    jogadaValida = gi.isSetAllowed(jogada);
                    jogadaValida = true;
//                    Retorna a jogada válida.
                    proximaJogadaValida = jogada;
                }

            }

        } else {

//            List<String> allowedMoves = gi.getAllowedMoves();
            List<String> allowedMoves = Arrays.asList(new String[]{"0,3;1,3", "1,2;1,3", "0,4;0,5"});
            boolean jogadaValida = false;

            // Procura uma jogada disponível dentre todas as posições do tabuleiro.
            for (int i = 0; i < allowedMoves.size() && jogadaValida; i++) {
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

    private TreeNode addLevel(int[][] gi, int profundidade, boolean oponente) {

        TreeNode node = null;
        TreeData data = new TreeData(gi, oponente);

        // Condição de parada.
        if (profundidade != 0) {

            try {
                // Calcula a próxima jogada.
                String proximaJogada = proximaJogada(gi, !oponente);

                // Monta a matriz com a proxima jogada válida encontrada.
//            int[][] nextState = montarMatrizProximaJogada(gi, !oponente, proximaJogada);
                // Fica chamando a função até atingir a profundidade 0 (Limite).
//            node = addLevel(nextState, profundidade - 1, !oponente);
//            node.getChildren().add(data);
            } catch (JogadaException exception) {
                exception.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return new TreeNode<>(data);
    }

    public static void main(String[] args) {

        int[][] multi = new int[][]{
            {1, 0, 0, 1, 2, 0, 2, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {1, 0, 0, 2, 0, 1, 0, 0}
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
