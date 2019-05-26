package com.tatyana.model;

public class Node {

    private String node;
    private int count;

    public Node(String node, int count) {
        this.node = node;
        this.count = count;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
