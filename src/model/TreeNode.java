/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;

/**
 * Classe representando um nó da árvore.
 *
 * @author raffa
 * @param <TreeData>
 */
public class TreeNode<TreeData> {

    private TreeData data;

    private LinkedList<TreeNode<TreeData>> children = new LinkedList<>();

    public TreeNode(TreeData treeData) {
        setData(treeData);
    }

    /**
     * @return the data
     */
    public TreeData getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(TreeData data) {
        this.data = data;
    }

    /**
     * @return the children
     */
    public LinkedList<TreeNode<TreeData>> getChildren() {
        return children;
    }

    /**
     * @param children the children to set
     */
    public void setChildren(LinkedList<TreeNode<TreeData>> children) {
        this.children = children;
    }

}
