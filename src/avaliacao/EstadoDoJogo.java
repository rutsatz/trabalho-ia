/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaliacao;

/**
 *
 * @author will
 */
public class EstadoDoJogo {
    
    public static final int EMPATE = 0;
    public static final int AGENTE = 1;
    public static final int ADVERSARIO = 2;
    
    // Cenario em que o agente ganha, pois o adversario nao consegue realizar nenhum movimento
    int[][] jogadaGenerica = {
        {2, 1, 0, 0, 0, 1, 2, 1}, 
        {0, 1, 2, 1, 0, 0, 0, 0}, 
        {0, 1, 0, 0, 0, 0, 0, 0}
    };
    
    /**
     * @param estadoDoJogo Estado atual do jogo
     * @return 0 caso o jogo esteja empatado
     *         1 caso o AGENTE ganhou o jogo
     *         2 caso o adversario ganhou o jogo
     */
    public int verificarEstadoDoJogo(int[][] estadoDoJogo) {
        int numPecasAgente = 0;
        int numPecasAdversario = 0;
        int numMovimentosPermitidosAgente = 0;
        int numMovimentosPermitidosAdversario = 0;
        
        for (int x = 0; x < estadoDoJogo.length; x++) {
            for (int y = 0; y < estadoDoJogo[0].length; y++) {
                
                int pecaAtual = estadoDoJogo[x][y];
                
                int movimentoHorario = estadoDoJogo[x][y + 1 > 7 ? 0 : y + 1];
                int movimentoAntiHorario = estadoDoJogo[x][y - 1 < 0 ? 7 : y - 1];
                
                int acessarNovoAndar = -1;
                int recuarAndar = -1;
                
                if (y % 2 != 0) {
                    acessarNovoAndar = estadoDoJogo[x + 1 > 2 ? 2 : x + 1][y];
                    recuarAndar = estadoDoJogo[x - 1 < 0 ? 0 : x - 1][y];
                }
                
                if (pecaAtual == AGENTE) {
                    numPecasAgente += 1;
                    
                    if (movimentoHorario == 0) {
                        numMovimentosPermitidosAgente ++;
                    }
                    if (movimentoAntiHorario == 0) {
                        numMovimentosPermitidosAgente ++;
                    }
                    if (acessarNovoAndar == 0) {
                        numMovimentosPermitidosAgente ++;
                    }
                    if (recuarAndar == 0) {
                        numMovimentosPermitidosAgente ++;
                    }
                }
                if (pecaAtual == ADVERSARIO) {
                    numPecasAdversario += 1;
                    
                    if (movimentoHorario == 0) {
                        numMovimentosPermitidosAdversario ++;
                    }
                    if (movimentoAntiHorario == 0) {
                        numMovimentosPermitidosAdversario ++;
                    }
                    if (acessarNovoAndar == 0) {
                        numMovimentosPermitidosAdversario ++;
                    }
                    if (recuarAndar == 0) {
                        numMovimentosPermitidosAdversario ++;
                    }
                }
            }
        }
        
        if ((numPecasAgente <= 2 && numPecasAdversario > 2) || numMovimentosPermitidosAgente == 0) {
            System.out.println("Adversário ganhou o jogo!");
            return ADVERSARIO;
        }
        if ((numPecasAdversario <= 2 && numPecasAgente > 2) || numMovimentosPermitidosAdversario == 0) {
            System.out.println("Agente ganhou o jogo!");
            return AGENTE;
        }
        
        return EMPATE;
    }
}
