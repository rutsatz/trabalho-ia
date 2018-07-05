/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avaliacao;

/**
 *
 * @author m96627
 */
public class AvaliaPeloNumeroDePecas {
    public static final int NENHUMA_PECA = 0;
    public static final int AGENTE = 1;
    public static final int ADVERSARIO = 2;
    
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
