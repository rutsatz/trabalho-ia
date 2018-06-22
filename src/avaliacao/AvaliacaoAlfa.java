package avaliacao;

import NineMensMorris.GameInfo;

/**
 *
 * @author Arthur
 */
public class AvaliacaoAlfa {

    static int COL = 8;
    static int ROW = 3;

    public static int NUMEROINIMIGO = 2;
    public static int NUMEROAGENTE = 1;

    static int PESOAGENTECANTO = 6;
    static int PESOAGENTECENTRO = 5;

    static int PESOINIMIGOCANTO = 6;
    static int PESOINIMIGOCENTRO = 5;

    public static int avalia(GameInfo info) {
        
        int[][] multi = info.getSpots();

        int result = 0;
        int pontosRodada;
        
        //result += info.getPlayerSpots().size() * 20;
        //result -= info.getEmptySpots().size() * 20;

        for (int j = 0; j < ROW; j++) {
            for (int i = 0; i < COL - 1; i = i + 2) {
                pontosRodada = calculoPontuacao(
                        multi[j][i],
                        multi[j][i + 1],
                        multi[j][i + 2 == 8 ? 0 : i + 2]);

                result += pontosRodada;
                pontosRodada = 0;
                if (j == 0) {
                    if (i == 0) {
                        pontosRodada = calculoPontuacao(
                                multi[0][7],
                                multi[1][7],
                                multi[2][7]);
                    } else {
                        pontosRodada = calculoPontuacao(
                                multi[0][i - 1],
                                multi[1][i - 1],
                                multi[2][i - 1]);
                    }
                }
                result += pontosRodada;
        }
        }
        return result;
    }

    private static int calculoPontuacao(int v1, int v2, int v3) {
        int pontos = 0;
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
/*
    public static void main(String[] args) {

        int[][] multi = new int[][]{
            {1, 0, 0, 1, 2, 0, 2, 0},
            {0, 0, 1, 0, 0, 0, 0, 0},
            {1, 0, 0, 2, 0, 1, 0, 0},};

        System.out.println("Peso: " + avalia(multi));
    }
*/
}
