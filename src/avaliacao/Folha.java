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
public class Folha {

    private int result;
    private List<Integer> inimigo;
    private List<Integer> agente;
    
    

    public Folha(int result, List<Integer> inimigo, List<Integer> agente) {
        this.result = result;
        this.inimigo = inimigo;
        this.agente = agente;
    }
    
    public Folha() {
        this.result = 0;
        this.inimigo = new LinkedList<>();
        this.agente = new LinkedList<>();
    }
    
    public  int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<Integer> getInimigo() {
        return inimigo;
    }

    public void setInimigo(List<Integer> inimigo) {
        this.inimigo = inimigo;
    }

    public List<Integer> getAgente() {
        return agente;
    }

    public void setAgente(List<Integer> agente) {
        this.agente = agente;
    }


    

}
