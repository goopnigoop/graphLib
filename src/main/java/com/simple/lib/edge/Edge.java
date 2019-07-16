package com.simple.lib.edge;

import com.simple.lib.Vertex;

/**
 * The interface Edge represents
 * edge of the current graph.
 *
 * @param <T> the type parameter
 */
public interface Edge<T> {

    /**
     * Gets start vertex or opposite for directed version
     * after invoke of check method
     * {@link com.simple.lib.edge.Edge#doesEdgeContainProperVertex(com.simple.lib.Vertex)}.
     *
     * @param endVertex the end vertex
     * @return the start vertex
     */
    Vertex<T> getOppositeOrStartVertex(Vertex<T> endVertex);

    /**
     * Gets end vertex or opposite for directed version
     * after invoke of check method
     * {@link com.simple.lib.edge.Edge#doesEdgeContainProperVertex(com.simple.lib.Vertex)}.
     *
     * @param startVertex the start vertex
     * @return the end vertex
     */
    Vertex<T> getOppositeOrEndVertex(Vertex<T> startVertex);

    /**
     * Check whether edge contains proper vertex.
     *
     * @param vertex the vertex
     * @return the boolean
     */
    boolean doesEdgeContainProperVertex(Vertex<T> vertex);
}
