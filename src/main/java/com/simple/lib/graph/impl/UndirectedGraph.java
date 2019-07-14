package com.simple.lib.graph.impl;

import com.simple.lib.Vertex;
import com.simple.lib.edge.Edge;
import com.simple.lib.edge.impl.UndirectedEdge;
import com.simple.lib.graph.AbstractGraph;
import com.simple.lib.graph.Graph;

public class UndirectedGraph<T> extends AbstractGraph<T> {

    @Override
    protected Edge<T> createEdge(Vertex<T> vertexFrom, Vertex<T> vertexTo) {
        return new UndirectedEdge<>(vertexFrom, vertexTo);
    }

    public static void main(String[] args) {
        Graph<Integer> graph = new UndirectedGraph<>();
        final Vertex<Integer> vertexFirst = new Vertex<>(1);
        final Vertex<Integer> vertexSecond = new Vertex<>(2);
        final Vertex<Integer> vertexThird = new Vertex<>(3);
        final Vertex<Integer> vertexFourth = new Vertex<>(4);
        final Vertex<Integer> vertexFifth = new Vertex<>(5);
        final Vertex<Integer> vertexSixth = new Vertex<>(6);
        final Vertex<Integer> vertexSeventh = new Vertex<>(7);
        graph.addVertex(vertexFirst);
        graph.addVertex(vertexSecond);
        graph.addVertex(vertexThird);
        graph.addVertex(vertexFourth);
        graph.addVertex(vertexFifth);
        graph.addVertex(vertexSixth);
        graph.addVertex(vertexSeventh);

        graph.addEdge(vertexFifth, vertexFirst);
        graph.addEdge(vertexFirst, vertexSecond);
        graph.addEdge(vertexThird, vertexSecond);
        graph.addEdge(vertexThird, vertexFourth);
        graph.addEdge(vertexFirst, vertexSixth);
        graph.addEdge(vertexSixth, vertexSecond);
        graph.addEdge(vertexSecond, vertexSeventh);

        System.out.println(graph.getPath(vertexFifth, vertexFourth));
    }
}
