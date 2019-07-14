package com.simple.lib.edge.impl;

import com.simple.lib.Vertex;
import com.simple.lib.edge.AbstractEdge;

/**
 * The type Directed edge.
 *
 * @param <T> the type parameter
 */
public class DirectedEdge<T> extends AbstractEdge<T> {

    /**
     * Instantiates a new Directed edge.
     *
     * @param vertex    the vertex
     * @param vertexEnd the vertex end
     */
    public DirectedEdge(Vertex<T> vertex, Vertex<T> vertexEnd) {
        super(vertex, vertexEnd);
    }

    @Override
    public Vertex<T> getStartVertex(Vertex<T> endVertex) {
        return vertexStart;
    }

    @Override
    public Vertex<T> getEndVertex(Vertex<T> startVertex) {
        return vertexEnd;
    }

    @Override
    public boolean doesEdgeContainProperVertex(Vertex<T> vertex) {
        return vertexStart.equals(vertex);
    }
}
