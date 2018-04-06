package model;

import java.util.ArrayList;
import java.util.List;

public class Tree<T> {

    private final Node<T> root;

    public Tree(T rootData) {

        root = new Node<>();

        root.data = rootData;

        root.children = new ArrayList<>();

    }

    public static class Node<T> {

        private T data;

        private Node<T> parent;

        private List<Node<T>> children;

    }

}
