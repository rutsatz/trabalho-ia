/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaliacao;

import static avaliacao.PesoFolhaColocacaoPeca.ROW;

/**
 *
 * @author will
 */
public class FuncaoAvaliacao {

    public static final int NENHUMA_PECA = 0;
    public static final int AGENTE = 1;
    public static final int ADVERSARIO = 2;

    public static int avaliar(int[][] multi) {

        int result = 0;

        for (int x = 0; x < multi.length; x++) {
            for (int y = 0; y < multi[0].length; y++) {
                
                int pecaAtual = multi[x][y];
                int movimentoHorario = multi[x][y + 1 > 7 ? 0 : y + 1];
                int movimentoAntiHorario = multi[x][y - 1 < 0 ? 7 : y - 1];
                int movimentoHorario2;
                int movimentoAntiHorario2;
                
                if (y + 2 == 8) {
                    movimentoHorario2 = multi[x][0];
                } else if (y + 2 == 9) {
                    movimentoHorario2 = multi[x][1];
                } else {
                    movimentoHorario2 = multi[x][y + 2];
                }
                
                if (y - 2 == -1) {
                    movimentoAntiHorario2 = multi[x][0];
                } else if (y - 2 == -2) {
                    movimentoAntiHorario2 = multi[x][1];
                } else {
                    movimentoAntiHorario2 = multi[x][y - 2];
                }
                
                int acessarNovoAndar = -1;
                int recuarAndar = -1;
                
                if (y % 2 != 0) {
                    acessarNovoAndar = multi[x + 1 > 2 ? 2 : x + 1][y];
                    recuarAndar = multi[x - 1 < 0 ? 0 : x - 1][y];
                }
                
                if (movimentoHorario == ADVERSARIO) {
                    result -= calcularPontuacaoJogadasHorizontais(y);
                } else if (movimentoHorario == AGENTE) {
                    result += calcularPontuacaoJogadasHorizontais(y);
                }
                if (movimentoAntiHorario == ADVERSARIO) {
                    result -= calcularPontuacaoJogadasHorizontais(y);
                } else if (movimentoAntiHorario == AGENTE) {
                    result += calcularPontuacaoJogadasHorizontais(y);
                }
                if (movimentoHorario2 == ADVERSARIO) {
                    result -= calcularPontuacaoJogadasHorizontais(y);
                } else if (movimentoHorario2 == AGENTE) {
                    result += calcularPontuacaoJogadasHorizontais(y);
                }
                if (movimentoAntiHorario2 == ADVERSARIO) {
                    result -= calcularPontuacaoJogadasHorizontais(y);
                } else if (movimentoAntiHorario2 == AGENTE) {
                    result += calcularPontuacaoJogadasHorizontais(y);
                }
                if (acessarNovoAndar == ADVERSARIO) {
                    result -= 2;
                } else if (acessarNovoAndar == AGENTE) {
                    result += 2;
                }
                if (recuarAndar == ADVERSARIO) {
                    result -= 2;
                } else if (recuarAndar == AGENTE) {
                    result += 2;
                }
            }
        }
        return result;
    }
    
    private static int calcularPontuacaoJogadasHorizontais(int y) {
        
        int result = 3;
        
        int coordenadaYPecaAtual = y;
        int coordenadaYMovimentoHorario = y + 1 > 7 ? 0 : y + 1;
        int coordenadaYMovimentoAntiHorario = y - 1 < 0 ? 7 : y - 1;
        
        if ( 
            (coordenadaYPecaAtual >= 0 && coordenadaYPecaAtual <= 2) || 
            (coordenadaYPecaAtual >= 2 && coordenadaYPecaAtual <= 4) || 
            (coordenadaYPecaAtual >= 4 && coordenadaYPecaAtual <= 6) || 
            (coordenadaYPecaAtual >= 6 && coordenadaYPecaAtual <= 7 )
        ) {
            result += 1;
        }
        
        if ( 
            (coordenadaYMovimentoHorario >= 0 && coordenadaYMovimentoHorario <= 2) || 
            (coordenadaYMovimentoHorario >= 2 && coordenadaYMovimentoHorario <= 4) || 
            (coordenadaYMovimentoHorario >= 4 && coordenadaYMovimentoHorario <= 6) || 
            (coordenadaYMovimentoHorario >= 6 && coordenadaYMovimentoHorario <= 7 )
        ) {
            result += 1;
        }
        
        if ( 
            (coordenadaYMovimentoAntiHorario >= 0 && coordenadaYMovimentoAntiHorario <= 2) || 
            (coordenadaYMovimentoAntiHorario >= 2 && coordenadaYMovimentoAntiHorario <= 4) || 
            (coordenadaYMovimentoAntiHorario >= 4 && coordenadaYMovimentoAntiHorario <= 6) || 
            (coordenadaYMovimentoAntiHorario >= 6 && coordenadaYMovimentoAntiHorario <= 7 )
        ) {
            result += 1;
        }
        
        return result;
//        return 1;
    }
}
