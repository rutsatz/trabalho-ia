/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anotheragent;

import NineMensMorris.GameInfo;
import NineMensMorris.PlayerAgent;

/**
 *
 * @author Fabio, Maike
 */
public class FabioAgente implements PlayerAgent 
{
    /**
     * Profundidade da arvore: 4 utilizamos esse valor pq do contratio a maquina era sobrecarregada
     */
    private int depth = 4;
    @Override
    public String setPiece(GameInfo info) 
    {
        CustomMinimaxTree tree = new CustomMinimaxTree(this.depth, CustomMinimaxTree.SET_ACTION, info);
        return tree.executeMinimax();
    }

    @Override
    public String removePiece(GameInfo info) 
    {
        CustomMinimaxTree tree = new CustomMinimaxTree(this.depth, CustomMinimaxTree.REMOVE_ACTION, info);
        return tree.executeMinimax();
    }

    @Override
    public String movePiece(GameInfo info)
    {
        CustomMinimaxTree tree = new CustomMinimaxTree(this.depth, CustomMinimaxTree.MOVE_ACTION, info);
        return tree.executeMinimax();
    }
}
