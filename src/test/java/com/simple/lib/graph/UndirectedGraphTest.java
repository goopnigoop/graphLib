package com.simple.lib.graph;

import com.simple.lib.Vertex;
import com.simple.lib.edge.Edge;
import com.simple.lib.edge.impl.UndirectedEdge;
import com.simple.lib.graph.impl.UndirectedGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.apache.commons.lang3.math.NumberUtils.INTEGER_MINUS_ONE;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UndirectedGraphTest extends AbstractGraphTest {

    @Before
    public void init() {
        graph = new UndirectedGraph<>();
        optimizedGraph = new UndirectedGraph<>();
        graph.addVertex(startVertexInGraph);
        graph.addVertex(endVertexInGraph);
        graph.addEdge(startVertexInGraph, endVertexInGraph);
    }

    @Test
    public void shouldAddNewUndirectedEdgeToTheUndirectedGraph() {
        graph.addVertex(newVertex);
        graph.addEdge(newVertex, endVertexInGraph);
        assertThat(graph.getEdges(), hasItem(new UndirectedEdge<>(newVertex, endVertexInGraph)));
    }

    @Test
    public void shouldReturnListWithThreeEdgesWhenDirectionDoesntMatter() {
        final Vertex<String> afterStart = new Vertex<>(AFTER_START_VERTEX_NAME);
        final Vertex<String> beforeEnd = new Vertex<>(BEFORE_END_VERTEX_NAME);
        graph = new UndirectedGraph<>();
        graph.addVertex(startVertexInGraph);
        graph.addVertex(endVertexInGraph);
        graph.addVertex(afterStart);
        graph.addVertex(beforeEnd);
        graph.addEdge(startVertexInGraph, afterStart);
        graph.addEdge(beforeEnd, afterStart);
        graph.addEdge(beforeEnd, endVertexInGraph);
        final Optional<List<Edge<String>>> path = graph.getPath(startVertexInGraph, endVertexInGraph);
        assertThat(path.map(List::size).orElse(INTEGER_MINUS_ONE), is(INTEGER_THREE));
    }
}
