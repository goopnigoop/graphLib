package com.simple.lib.graph;

import com.simple.lib.Vertex;
import com.simple.lib.edge.Edge;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The graph interface.
 *
 * @param <T> the type parameter of graph
 */
public interface Graph<T> {

    /**
     * Adds vertex to the graph
     *
     * @param vertex the vertex
     * @throws IllegalArgumentException if vertex is null
     */
    void addVertex(Vertex<T> vertex);

    /**
     * Add edge to the graph.
     *
     * @param vertexFrom the vertex from
     * @param vertexTo   the vertex to
     * @throws IllegalArgumentException if some of vertexes is null
     */
    void addEdge(Vertex<T> vertexFrom, Vertex<T> vertexTo);

    /**
     * Returns an optional object with list of edges
     * In case vertex from and vertex to is
     * the same object method returns optional
     * with empty list.
     * When graph doesn't contain both vertices
     * method returns {@link java.util.Optional#empty()}
     *
     * @param from the from vertex
     * @param to   the to vertex
     * @return {@link java.util.Optional<java.util.List>}
     */
    Optional<List<Edge<T>>> getPath(Vertex<T> from, Vertex<T> to);

    /**
     * Gets vertices of graph.
     *
     * @return the vertices
     */
    Set<Vertex<T>> getVertices();

    /**
     * Gets edges of graph.
     *
     * @return the edges
     */
    Set<Edge<T>> getEdges();
}
