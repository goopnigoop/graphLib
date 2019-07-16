package com.simple.lib.edge.impl;

import com.simple.lib.Vertex;
import com.simple.lib.edge.AbstractEdge;

/**
 * The type Undirected edge.
 *
 * @param <T> the type parameter
 */
public class UndirectedEdge<T> extends AbstractEdge<T> {

    /**
     * Instantiates a new Undirected edge.
     *
     * @param vertex    the vertex
     * @param vertexEnd the vertex end
     */
    public UndirectedEdge(Vertex<T> vertex, Vertex<T> vertexEnd) {
        super(vertex, vertexEnd);
    }

    @Override
    public boolean doesEdgeContainProperVertex(Vertex<T> vertex) {
        return vertexStart.equals(vertex) || vertexEnd.equals(vertex);
    }

    @Override
    public Vertex<T> getOppositeOrStartVertex(Vertex<T> endVertex) {
        return getPair(endVertex);
    }

    @Override
    public Vertex<T> getOppositeOrEndVertex(Vertex<T> startVertex) {
        return getPair(startVertex);
    }

    private Vertex<T> getPair(Vertex<T> vertex) {
        return vertexStart.equals(vertex) ? vertexEnd : vertexStart;
    }
}
