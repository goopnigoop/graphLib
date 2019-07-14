package com.simple.lib.graph;

import com.simple.lib.Vertex;
import com.simple.lib.edge.Edge;
import com.simple.lib.edge.impl.DirectedEdge;
import com.simple.lib.graph.impl.DirectedGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static java.lang.Boolean.FALSE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DirectedGraphTest extends AbstractGraphTest {

    private static final int INTEGER_THREE = 3;

    @Before
    public void init() {
        graph = new DirectedGraph<>();
        startVertexInGraph = new Vertex<>("start");
        endVertexInGraph = new Vertex<>("end");
        graph.addVertex(startVertexInGraph);
        graph.addVertex(endVertexInGraph);
        graph.addEdge(startVertexInGraph, endVertexInGraph);
        newVertex = new Vertex<>("newVertex");
    }

    @Test
    public void shouldAddNewDirectedEdgeToTheDirectedGraph() {
        graph.addVertex(newVertex);
        graph.addEdge(newVertex, endVertexInGraph);
        assertThat(graph.getEdges(), hasItem(new DirectedEdge<>(newVertex, endVertexInGraph)));
    }

    @Test
    public void shouldReturnOptionalEmptyWhenThereIsNoDirection() {
        final Optional<List<Edge<String>>> path = graph.getPath(endVertexInGraph, startVertexInGraph);
        assertThat(path.isPresent(), is(FALSE));
    }

    @Test
    public void shouldReturnListWith2EdgesWhenThereIsDirection() {
        final Vertex<String> afterStart = new Vertex<>("afterStart");
        final Vertex<String> beforeEnd = new Vertex<>("beforeEnd");
        graph = new DirectedGraph<>();
        graph.addVertex(startVertexInGraph);
        graph.addVertex(endVertexInGraph);
        graph.addVertex(afterStart);
        graph.addVertex(beforeEnd);
        graph.addEdge(startVertexInGraph, afterStart);
        graph.addEdge(afterStart, beforeEnd);
        graph.addEdge(beforeEnd, endVertexInGraph);
        endVertexInGraph = new Vertex<>("end");
        final Optional<List<Edge<String>>> path = graph.getPath(startVertexInGraph, endVertexInGraph);
        assertThat(path.map(List::size).orElse(INTEGER_MINUS_ONE), is(INTEGER_THREE));
    }
}
