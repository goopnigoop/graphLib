package com.simple.lib.graph.impl;

import com.simple.lib.Vertex;
import com.simple.lib.edge.Edge;
import com.simple.lib.edge.impl.DirectedEdge;
import com.simple.lib.graph.AbstractGraph;
import com.simple.lib.graph.Graph;

/**
 * The implementation of {@link Graph} represents directed
 * version of graph. Order of input vertices for method
 * {@link DirectedGraph#createEdge(com.simple.lib.Vertex, com.simple.lib.Vertex)}
 * matters for created edge.
 *
 * @param <T> the type parameter
 */
public class DirectedGraph<T> extends AbstractGraph<T> {

    @Override
    protected Edge<T> createEdge(Vertex<T> vertexFrom, Vertex<T> vertexTo) {
        return new DirectedEdge<>(vertexFrom, vertexTo);
    }

}
