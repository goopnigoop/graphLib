package com.simple.lib.graph.impl;

import com.simple.lib.Vertex;
import com.simple.lib.edge.Edge;
import com.simple.lib.edge.impl.UndirectedEdge;
import com.simple.lib.graph.AbstractGraph;
import com.simple.lib.graph.Graph;

/**
 * The implementation of {@link Graph} represents undirected
 * version of graph. Order of input vertices for method
 * {@link UndirectedGraph#createEdge(com.simple.lib.Vertex, com.simple.lib.Vertex)}
 * doesn't matter for created edge.
 *
 * @param <T> the type parameter
 */
public class UndirectedGraph<T> extends AbstractGraph<T> {

    @Override
    protected Edge<T> createEdge(Vertex<T> vertexFrom, Vertex<T> vertexTo) {
        return new UndirectedEdge<>(vertexFrom, vertexTo);
    }
}
