package com.tatyana.model;

import java.util.List;

public class Parameters {

    private List<Node> nodes;
    private int countProcessors;

    public Parameters(List<Node> nodes, int countProcessors) {
        this.nodes = nodes;
        this.countProcessors = countProcessors;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public int getCountProcessors() {
        return countProcessors;
    }

    public void setCountProcessors(int countProcessors) {
        this.countProcessors = countProcessors;
    }
}
