package com.simple.lib.graph;

import com.simple.lib.Vertex;
import com.simple.lib.edge.Edge;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;
import java.util.Optional;

import static com.simple.lib.graph.AbstractGraph.*;
import static java.lang.Boolean.FALSE;
import static org.apache.commons.lang3.math.NumberUtils.*;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public abstract class AbstractGraphTest {

    private static final String NEW_VERTEX_NAME = "newVertex";
    private static final String START_VERTEX_NAME = "start";
    private static final String END_VERTEX_NAME = "end";
    static final String AFTER_START_VERTEX_NAME = "afterStart";
    static final String BEFORE_END_VERTEX_NAME = "beforeEnd";
    static final int INTEGER_THREE = 3;

    Graph<String> graph;
    Graph<String> optimizedGraph;
    static final Vertex<String> startVertexInGraph = new Vertex<>(START_VERTEX_NAME);
    static final Vertex<String> endVertexInGraph = new Vertex<>(END_VERTEX_NAME);
    Vertex<String> newVertex = new Vertex<>(NEW_VERTEX_NAME);

    @Rule
    public final ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void shouldAddNewVertexToGraphVertices() {
        graph.addVertex(newVertex);
        assertThat(graph.getVertices(), hasItem(newVertex));
    }

    @Test
    public void shouldReturnEmptyOptionalAsPathWhenNoVertexInGraph() {
        newVertex = new Vertex<>("newVertex");
        final Optional<List<Edge<String>>> path = graph.getPath(startVertexInGraph, newVertex);
        assertThat(path.isPresent(), is(FALSE));
    }

    @Test
    public void shouldReturnEmptyListAsPathWhenVerticesMatch() {
        final Optional<List<Edge<String>>> path = graph.getPath(startVertexInGraph, startVertexInGraph);
        assertThat(path.map(List::size).orElse(INTEGER_MINUS_ONE), is(INTEGER_ZERO));
    }

    @Test
    public void shouldReturnListAsPath() {
        final Optional<List<Edge<String>>> path = graph.getPath(startVertexInGraph, endVertexInGraph);
        assertThat(path.map(List::size).orElse(INTEGER_MINUS_ONE), is(INTEGER_ONE));
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
    public void shouldReturnOptimizedListWithOneEdgeWhenThereIsAChoice() {
        final Vertex<String> afterStart = new Vertex<>(AFTER_START_VERTEX_NAME);
        final Vertex<String> beforeEnd = new Vertex<>(BEFORE_END_VERTEX_NAME);
        final Vertex<String> choice = new Vertex<>("choice");
        optimizedGraph.addVertex(startVertexInGraph);
        optimizedGraph.addVertex(endVertexInGraph);
        optimizedGraph.addVertex(afterStart);
        optimizedGraph.addVertex(beforeEnd);
        optimizedGraph.addVertex(choice);
        optimizedGraph.addEdge(startVertexInGraph, choice);
        optimizedGraph.addEdge(choice, endVertexInGraph);
        optimizedGraph.addEdge(startVertexInGraph, afterStart);
        optimizedGraph.addEdge(afterStart, beforeEnd);
        optimizedGraph.addEdge(beforeEnd, endVertexInGraph);
        optimizedGraph.addEdge(choice, afterStart);
        final Optional<List<Edge<String>>> path = optimizedGraph.getPath(startVertexInGraph, endVertexInGraph);
        assertThat(path.map(List::size).orElse(INTEGER_MINUS_ONE), is(INTEGER_TWO));
    }
}