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
import java.util.LinkedList;
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
        profundidade = 1;
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
//        int pecasLivres = allowedMoves.size();
        int[][] nextState = gi;

        // Controla o nível em que estou.
        int profundidadeAtual = profundidade;

        // Lista que representa a árvore.
        LinkedList<TreeNode> tree = new LinkedList<>();

        tree.push(rootNode); // Adiciona o nó raiz na árvore.

        // Verifica se atingiu o limite de jogadas.
        boolean atingiuLimiteJogadas = false;

        TreeNode lastNode = null;
        // Nó atual.
        TreeNode<TreeData> currentNode = tree.peek();
        // Percorre todas as jogadas possiveis para cada peca.
//        for (int i = 0; i < pecasLivres; i++) {
//        for (int i = profundidade; i != 0; i--) {
        while (!atingiuLimiteJogadas || profundidadeAtual != profundidade) {
//            String[] jogadasPeca = allowedMoves.get(i).split(";");

//            do {
            // Verifica se atingiu o limite de profundidade e precisa subir.
            if (profundidadeAtual == 0 || atingiuLimiteJogadas) {

                currentNode = lastNode;

                profundidadeAtual++;
                atingiuLimiteJogadas = false;

//                nextState = ((TreeData) ((TreeNode) currentNode.getChildren().peek()).getData()).getGameInfo();

                // Pega ultimo estado do nó pai.
                nextState = ((TreeData) lastNode.getData()).getGameInfo();

            } else {
                // Desce um nível.
                try {

                    oponente = !oponente; // Fica intercalando entre MIN e MAX.

                    // Calcula a próxima jogada.
                    String proximaJogada = proximaJogada(nextState, oponente);

                    // Monta a matriz com a proxima jogada válida encontrada.
                    nextState = montarMatrizProximaJogada(nextState, oponente, proximaJogada);

                    int qtdJogadas = profundidadeAtual - 1;
                    System.out.println(qtdJogadas);
                    // @@
//                    int qtdJogadas = 0;
//                    if (oponente) {
//                        qtdJogadas = nextState.getOpponentAllowedMoves().size();
//                    } else {
//                        qtdJogadas = nextState.getAllowedMoves().size();
//                    }
                    if (qtdJogadas == 0) {
                        atingiuLimiteJogadas = true;
                    }

                    // Cria o novo nível.
                    TreeNode newNode = addLevel(nextState, !oponente);

                    // Adiciona o nivel na lista.
//                    if (newNode != null) {
                    currentNode.getChildren().push(newNode);
//                    }

//                    currentNode.getData().setGameInfo(gi);

                    // Atualiza o ponteiro do nó anterior.
                    lastNode = currentNode;
                    // Atualiza o ponteiro para o novo nó.
                    currentNode = newNode;
                    // Atualiza o nível.
                    profundidadeAtual--;
                } catch (JogadaException exception) {
                    exception.printStackTrace();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            }
            // Percore enquanto houverem nós para adicionar e enquanto
            // não atingir a profundidade máxima.
//            } while (node != null && profundidade-- != 0);

            // Adiciona os filhos no elemento raiz.
//            rootNode.getChildren().add(node);
        }

        System.out.println(tree);

        return null;
    }

    /**
     * Adiciona um novo nivel/
     *
     * @param gi
     * @param profundidade
     * @param oponente
     * @return
     */
//    private TreeNode addLevel(int[][] nextState, int profundidade, boolean oponente) {
    private TreeNode addLevel(int[][] nextState, boolean oponente) {

//        TreeNode node = null;
//        TreeData data = new TreeData(gi, oponente);
        // Condição de parada.
//        if (profundidade != 0) {
        // Fica chamando a função até atingir a profundidade 0 (Limite).
//                node = addLevel(nextState, profundidade - 1, !oponente);
//                if (node != null) {
//                    return node;
//                }
        TreeData data = new TreeData(nextState, !oponente);
        TreeNode node = new TreeNode(data);
        return node;

//        }
//        return null;
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

        List<String> moves = null;

        if (oponente) {
            // Adicionar if. @@
            // Se existem pecas pendentes de serem colocadas, pegar emptySpots.
            // Senao, pegar allowedMoves.
//            List<String> opponentAllowedMoves = gi.getOpponentAllowedMoves();
            moves = Arrays.asList(new String[]{"0,5;0,6", "0,6;0,5;0,7", "2,3;1,3"});
        } else {
            moves = Arrays.asList(new String[]{"0,3;1,3", "1,2;1,3", "0,4;0,5"});
        }
        boolean jogadaValida = false;

        // Procura uma jogada disponível dentre todas as posições do tabuleiro.
        for (int i = 0; i < moves.size() && !jogadaValida; i++) {
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
// Posição origem mais posição destino.
                        proximaJogadaValida = jogadas[0] + ";" + jogadas[k];
                        return proximaJogadaValida;
                    }
                }
            }

        }
//        } else {

//            // Adicionar if. @@
//            // Se existem pecas pendentes de serem colocadas, pegar emptySpots.
//            // Senao, pegar allowedMoves.
////            List<String> allowedMoves = gi.getAllowedMoves();
//            List<String> allowedMoves = Arrays.asList(new String[]{"0,3;1,3", "1,2;1,3", "0,4;0,5"});
//            boolean jogadaValida = false;
//
//            // Procura uma jogada disponível dentre todas as posições do tabuleiro.
//            for (int i = 0; i < allowedMoves.size() && !jogadaValida; i++) {
//                String jogada = allowedMoves.get(i);
////                List<String> jogadasLivres = gi.getAllowedMoves(jogada);
//                List<String> jogadasLivres = Arrays.asList(new String[]{"0,3;1,3"});
//
//                // Verifica se a joga encontrada é válida.
//                for (int j = 0; j < jogadasLivres.size(); j++) {
////                    jogadaValida = gi.isSetAllowed(jogada); @@
//                    jogadaValida = true;
//                    // Retorna a joga válida.
//                    proximaJogadaValida = jogada;
//                }
//
//            }
//
//        }
        if (proximaJogadaValida == null) {
            throw new JogadaException("Erro. Nenhuma jogada válida encontrada.");
        }

        return proximaJogadaValida;
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
        String[] params = proximaJogada.split(";");
        String posOrig = params[0]; // Posição de origem.
        String posDest = params[1]; // Posição de destino.

        String[] coords = posDest.split(",");
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
