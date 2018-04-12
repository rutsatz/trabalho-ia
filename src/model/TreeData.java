/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import NineMensMorris.GameInfo;

/**
 * Classe contendo os dados de cada nó.
 *
 * @author raffa
 */
public class TreeData {

    private String jogada;

    private int[][] gameInfo;

    private boolean oponente;

    /**
     *
     * @param gameInfo
     */
    public TreeData(int[][] gameInfo, boolean oponente) {
        setGameInfo(gameInfo);
        setOponente(oponente);
    }

    /**
     *
     * @param gameInfo
     * @param jogada
     */
    public TreeData(int[][] gameInfo, String jogada) {
        setGameInfo(gameInfo);
        setJogada(jogada);
    }

    @Override
    public String toString() {
        return "TreeData{" + "jogada=" + jogada + ", gameInfo=" + gameInfo + ", oponente=" + oponente + '}';
    }

    
    
    /**
     * @return the jogada
     */
    public String getJogada() {
        return jogada;
    }

    /**
     * @param jogada the jogada to set
     */
    public void setJogada(String jogada) {
        this.jogada = jogada;
    }

    /**
     * @return the gameInfo
     */
    public int[][] getGameInfo() {
        return gameInfo;
    }

    /**
     * @param gameInfo the gameInfo to set
     */
    public void setGameInfo(int[][] gameInfo) {
        this.gameInfo = gameInfo;
    }

    /**
     * @return the oponente
     */
    public boolean isOponente() {
        return oponente;
    }

    /**
     * @param oponente the oponente to set
     */
    public void setOponente(boolean oponente) {
        this.oponente = oponente;
    }

}
