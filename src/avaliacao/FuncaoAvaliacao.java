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
    
    public static final int PESO_HORIZONTAL = 2;
    public static final int PESO_VERTICAL = 2;

    public static int avaliar(int[][] multi) {

        int result = 0;

        for (int x = 0; x < multi.length; x++) {
            for (int y = 0; y < multi[0].length; y++) {
                
                int pecaAtual = multi[x][y];
                int movimentoHorario = multi[x][y + 1 > 7 ? 0 : y + 1];
                int movimentoAntiHorario = multi[x][y - 1 < 0 ? 7 : y - 1];

                int acessarNovoAndar = -1;
                int recuarAndar = -1;
                
                if (y % 2 != 0) {
                    if (x + 1 <= 2) {
                        acessarNovoAndar = multi[x + 1][y];
                    }
                    if (x - 1 >= 0) {
                        acessarNovoAndar = multi[x - 1][y];
                    }
                }
                
                if (pecaAtual == ADVERSARIO) {
                    result -= 40;
                } else if (pecaAtual == AGENTE) {
                    result += 40;
                }
                
                if (pecaAtual == ADVERSARIO) {
                    result -= calcularPontuacaoJogadasHorizontais(y);
                } else if (pecaAtual == AGENTE) {
                    result += calcularPontuacaoJogadasHorizontais(y);
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

                if (acessarNovoAndar == ADVERSARIO) {
                    result -= PESO_VERTICAL;
                } else if (acessarNovoAndar == AGENTE) {
                    result += PESO_VERTICAL;
                }
                if (recuarAndar == ADVERSARIO) {
                    result -= PESO_VERTICAL;
                } else if (recuarAndar == AGENTE) {
                    result += PESO_VERTICAL;
                }
            }
        }
        return result;
    }
    
    private static int calcularPontuacaoJogadasHorizontais(int y) {
        
        int result = PESO_HORIZONTAL;
        
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
    
    public static int avaliaPeloNumeroDePecas(int[][] multi) {

        int result = 0;

        for (int x = 0; x < multi.length; x++) {
            for (int y = 0; y < multi[0].length; y++) {
                
                int pecaAtual = multi[x][y];
                
                if (pecaAtual == ADVERSARIO) {
                    result -= 2;
                } else if (pecaAtual == AGENTE) {
                    result += 2;
                }
            }
        }
        return result;
    }
}
