/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import NineMensMorris.GameInfo;
import java.util.ArrayList;
import java.util.List;
import model.Tree;

/**
 *
 * @author m72887
 */
public class TreeGenerator {

    GameInfo gi;

    int profundidade;

    public TreeGenerator(GameInfo gi, int profundidade) {
        setGameInfo(gi);
    }

    public List<Tree> generate() {

        int[][] estadoAtual = gi.getSpots();

        List<Tree> tree = new ArrayList<>();

        for (int i = 1; i <= profundidade; i++) {
            
            estadoAtual = addLevel(estadoAtual);
        }
        return tree;
    }

    private int[][] addLevel(int[][] estadoAtual) {

        return estadoAtual;
    }

    public void setGameInfo(GameInfo gi) {
        this.gi = gi;
    }

    public void setProfundidade(int profundidade) {
        this.profundidade = profundidade;
    }

}
