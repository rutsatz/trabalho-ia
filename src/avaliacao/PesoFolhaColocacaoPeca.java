/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaliacao;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Arthur
 */
public class PesoFolhaColocacaoPeca {

    static int COL = 8;
    static int ROW = 3;

    public static int NUMEROINIMIGO = 2;
    public static int NUMEROAGENTE = 1;

    static int PESOAGENTECANTO = 110;
    static int PESOAGENTECENTRO = 128;

    static int PESOINIMIGOCANTO = 110;
    static int PESOINIMIGOCENTRO = 128;

    static int GANHOABRIREFECHAR = 25;
    static int GANHOFECHADO = 120;

    static int VALORPECA = 100;

    static int QUANTIDADEFECHADO = 0;
    static int QUANTIDADEFECHADOANTERIOR = 0;

    static List<Integer> FECHADOIDAGENTE;
    static List<Integer> FECHADOIDAINIMIGO;

    static List<Integer> ANTERIORFECHADOIDAGENTE;
    static List<Integer> ANTERIORFECHADOIDAINIMIGO;

    static int ALTERACAOLINHASFECHADAS = 60;

    public PesoFolhaColocacaoPeca() {
        FECHADOIDAGENTE = new LinkedList<>();
        FECHADOIDAINIMIGO = new LinkedList<>();
        ANTERIORFECHADOIDAGENTE = new LinkedList<>();
        ANTERIORFECHADOIDAINIMIGO = new LinkedList<>();
    }

    static boolean COLOCACAO_PECA = false;

    public static Folha avalia(int[][] multi, boolean colocacaoPesas, List<Integer> anteriorAgente, List<Integer> anteriorInimigo) {
        QUANTIDADEFECHADOANTERIOR = QUANTIDADEFECHADO;
        QUANTIDADEFECHADO = 0;

        ANTERIORFECHADOIDAINIMIGO = anteriorInimigo;
        ANTERIORFECHADOIDAGENTE = anteriorAgente;

        COLOCACAO_PECA = colocacaoPesas;

        int result = 0;
        int pontosRodada;

        for (int j = 0; j < ROW; j++) {

            for (int k = 0; k < COL; k++) {
                if (multi[j][k] == NUMEROAGENTE) {
                    result += VALORPECA;
                } else {
                    result -= VALORPECA + 1;
                }
            }

            for (int i = 0; i < COL - 1; i = i + 2) {
                pontosRodada = 0;

                pontosRodada = calculoPontuacao(
                        multi[j][i],
                        multi[j][i + 1],
                        multi[j][i + 2 == 8 ? 0 : i + 2],
                        (j * 5 * 3) + ((i + 1) * 5) + ((i + 2 == 8 ? 0 : i + 2) + 1) * 5);

                result += pontosRodada;
                pontosRodada = 0;
                if (j == 0) {
                    if (i == 0) {
                        pontosRodada = calculoPontuacao(
                                multi[0][7],
                                multi[1][7],
                                multi[2][7],
                                (1 * 7 * 300 * 2 * 300 * 3 * 300));
                    } else {
                        pontosRodada = calculoPontuacao(
                                multi[0][i - 1],
                                multi[1][i - 1],
                                multi[2][i - 1],
                                ((1 * (i + 1) * 700)) + (2 * (i + 1) * 700) + (3 * (i + 1) * 700));
                    }
                }
                result += pontosRodada;
            }
        }
        /*
        if (QUANTIDADEFECHADO != QUANTIDADEFECHADOANTERIOR) {
            result += GANHOABRIREFECHAR;
        }
         */

        if (verificaAlteracaoLinhasFechadas(ANTERIORFECHADOIDAINIMIGO, FECHADOIDAINIMIGO)) {
            result -= ALTERACAOLINHASFECHADAS;
        } else {
            result += ALTERACAOLINHASFECHADAS;
        }

        if (verificaAlteracaoLinhasFechadas(ANTERIORFECHADOIDAGENTE, FECHADOIDAGENTE)) {
            result += ALTERACAOLINHASFECHADAS;
        } else {
            result -= ALTERACAOLINHASFECHADAS;
        }

        Folha folha = new Folha(result, FECHADOIDAINIMIGO, FECHADOIDAGENTE);
        
        return folha;
    }

    private static int calculoPontuacao(int v1, int v2, int v3, int id) {
//        System.out.println(v1 + " " + v2 + " " + v3);
        int pontos = 0;

        if (v1 == v2 && v2 == v3) {
            if (v1 == NUMEROAGENTE) {
                QUANTIDADEFECHADO++;
                pontos += GANHOFECHADO;
                FECHADOIDAGENTE.add(id);
            } else {
                pontos -= GANHOFECHADO;
                FECHADOIDAINIMIGO.add(id);
            }
        }

        if (v1 == NUMEROAGENTE) {
            pontos += PESOAGENTECANTO;
        } else if (v1 == NUMEROINIMIGO) {
            pontos -= PESOINIMIGOCANTO;
        }
        if (v2 == NUMEROAGENTE) {
            pontos += PESOAGENTECENTRO;
        } else if (v2 == NUMEROINIMIGO) {
            pontos -= PESOINIMIGOCENTRO;
        }
        if (v3 == NUMEROAGENTE) {
            pontos += PESOAGENTECANTO;
        } else if (v3 == NUMEROINIMIGO) {
            pontos -= PESOINIMIGOCANTO;
        }
        return pontos;
    }

    public static boolean verificaAlteracaoLinhasFechadas(List<Integer> l1, List<Integer> l2) {
        if (l1.size() != l2.size()) {
            return true;
        } else {
            if (l1.size() > l2.size()) {
                for (Integer integerl2 : l2) {
                    for (Integer integerl1 : l1) {
                        if (integerl1 != integerl2) {
                            return true;
                        }
                    }
                }
            } else {
                for (Integer integerl1 : l1) {
                    for (Integer integerl2 : l2) {
                        if (integerl1 != integerl2) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

    public static void main(String[] args) {
        Folha folha = new Folha();
        folha.setAgente(new LinkedList<>());
        folha.setInimigo(new LinkedList<>());
        int[][] multi = new int[][]{
            {1, 0, 0, 1, 2, 0, 2, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {1, 0, 0, 2, 0, 1, 0, 0},};

        System.out.println("Peso: " + avalia(multi, false, folha.getAgente(), folha.getInimigo()));
    }

}
