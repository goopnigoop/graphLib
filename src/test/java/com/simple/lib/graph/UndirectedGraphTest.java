package com.simple.lib.graph;

import com.simple.lib.Vertex;
import com.simple.lib.edge.impl.UndirectedEdge;
import com.simple.lib.graph.impl.UndirectedGraph;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.assertThat;

public class UndirectedGraphTest extends AbstractGraphTest {


    @Before
    public void init() {
        graph = new UndirectedGraph<>();
        startVertexInGraph = new Vertex<>("start");
        endVertexInGraph = new Vertex<>("end");
        graph.addVertex(startVertexInGraph);
        graph.addVertex(endVertexInGraph);
        graph.addEdge(startVertexInGraph, endVertexInGraph);
        newVertex = new Vertex<>("newVertex");
    }

    @Test
    public void shouldAddNewUndirectedEdgeToTheUndirectedGraph() {
        graph.addVertex(newVertex);
        graph.addEdge(newVertex, endVertexInGraph);
        assertThat(graph.getEdges(), hasItem(new UndirectedEdge<>(newVertex, endVertexInGraph)));
    }
}
