package com.simple.lib.graph;

import com.simple.lib.Vertex;
import com.simple.lib.edge.Edge;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.Optional;

import static com.simple.lib.graph.AbstractGraph.*;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ONE;
import static org.apache.commons.lang3.math.NumberUtils.INTEGER_ZERO;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public abstract class AbstractGraphTest {

    protected Graph<String> graph;
    protected Vertex<String> startVertexInGraph;
    protected Vertex<String> endVertexInGraph;
    protected Vertex<String> newVertex;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void shouldAddNewVertexToGraphVertices() {
        graph.addVertex(newVertex);
        assertThat(graph.getVertices(), hasItem(newVertex));
    }

    @Test
    public void shouldThrowExceptionWhenAddNullVertex() {
        newVertex = null;
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(THE_INPUT_VERTEX_IS_NULL);
        graph.addVertex(newVertex);
    }

    @Test
    public void shouldThrowExceptionWhenAddEdgeIfOneOrMoreInputVerticesIsNull() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(THE_INPUT_VERTICES_CANNOT_BE_NULL);
        graph.addEdge(newVertex, null);
    }

    @Test
    public void shouldThrowExceptionWhenAddEdgeIfVertexNotExistsInGraph() {
        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(GRAPH_DOESNT_CONTAIN_ONE_OF_INPUT_VERTICES);
        graph.addEdge(newVertex, startVertexInGraph);
    }

    @Test
    public void shouldReturnEmptyOptionalAsPathWhenNoVertexInGraph() {
        newVertex = new Vertex<>("newVertex");
        final Optional<List<Edge<String>>> path = graph.getPath(startVertexInGraph, newVertex);
        assertThat(path.isPresent(), is(false));
    }

    @Test
    public void shouldReturnEmptyListAsPathWhenVerticesMatch() {
        final Optional<List<Edge<String>>> path = graph.getPath(startVertexInGraph, startVertexInGraph);
        assertThat(path.map(List::size).orElse(-1), is(INTEGER_ZERO));
    }

    @Test
    public void shouldReturnListAsPath() {
        final Optional<List<Edge<String>>> path = graph.getPath(startVertexInGraph, endVertexInGraph);
        assertThat(path.map(List::size).orElse(-1), is(INTEGER_ONE));
    }

}