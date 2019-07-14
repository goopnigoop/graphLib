package com.simple.lib.graph;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.simple.lib.Vertex;
import com.simple.lib.edge.Edge;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractGraph<T> implements Graph<T> {
    protected final static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    public static final String THE_INPUT_VERTEX_IS_NULL = "The input vertex is null";
    public static final String THE_INPUT_VERTICES_CANNOT_BE_NULL = "The input vertices cannot be null";
    public static final String GRAPH_DOESNT_CONTAIN_ONE_OF_INPUT_VERTICES = "Can not add new edge graph doesn't contain one of input vertices";

    private Set<Vertex<T>> vertices;
    private Set<Edge<T>> edges;

    public AbstractGraph() {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();
    }

    @Override
    public void addVertex(Vertex<T> vertex) {
        final boolean isAdded = Optional.ofNullable(vertex)
                .map(this.vertices::add)
                .orElseThrow(() -> new IllegalArgumentException(THE_INPUT_VERTEX_IS_NULL));
        if (!isAdded) {
            logger.warn("Graph already contains vertex: {}", vertex);
        } else {
            logger.info("Vertex {} was successfully added", vertex);
        }
    }

    @Override
    public void addEdge(Vertex<T> vertexFrom, Vertex<T> vertexTo) {
        checkForNull(vertexFrom, vertexTo);
        if (!doesGraphContainsBothVertices(vertexFrom, vertexTo)) {
            logger.error(GRAPH_DOESNT_CONTAIN_ONE_OF_INPUT_VERTICES);
            throw new IllegalArgumentException(GRAPH_DOESNT_CONTAIN_ONE_OF_INPUT_VERTICES);
        }
        Edge<T> edge = createEdge(vertexFrom, vertexTo);
        final boolean isAdded = this.edges.add(edge);
        if (!isAdded) {
            logger.warn("Graph already contains edge: {}", edge);
        } else {
            logger.info("{} {} was successfully added", edge.getClass().getSimpleName(), edge);
        }
    }

    protected abstract Edge<T> createEdge(Vertex<T> vertexFrom, Vertex<T> vertexTo);

    @Override
    public Optional<List<Edge<T>>> getPath(Vertex<T> from, Vertex<T> to) {
        checkForNull(from, to);
        if (from.equals(to)) {
            return Optional.of(ImmutableList.of());
        }
        Map<Vertex<T>, List<Edge<T>>> resultedMap = new HashMap<>();

        if (doesGraphContainsBothVertices(from, to)) {
            Deque<Vertex<T>> vertexes = new LinkedList<>();
            Set<Vertex<T>> setOfVisited = new HashSet<>();
            vertexes.push(from);
            do {
                final Vertex<T> currentVertex = vertexes.pop();
                setOfVisited.add(currentVertex);
                final Map<Vertex<T>, Edge<T>> neighboursWithEdges = getNeighboursMap(currentVertex);
                neighboursWithEdges.keySet().removeAll(setOfVisited);

                for (Vertex<T> tVertex : neighboursWithEdges.keySet()) {
                    final Edge<T> edge = neighboursWithEdges.get(tVertex);
                    final List<Edge<T>> previousEdgesOfStartVertex = resultedMap.getOrDefault(edge.getStartVertex(tVertex), ImmutableList.of());
                    resultedMap.merge(tVertex, ListUtils.sum(previousEdgesOfStartVertex, ImmutableList.of(edge)), (edgesPrevious, edgesNew) -> getBestEdgeList(from, edgesPrevious, edgesNew));
                }

                neighboursWithEdges.keySet().forEach(vertexes::push);
            } while (!resultedMap.containsKey(to) && !vertexes.isEmpty());
            return Optional.ofNullable(resultedMap.get(to));
        }
        return Optional.empty();
    }

    private List<Edge<T>> getBestEdgeList(Vertex<T> from, List<Edge<T>> edgesPrevious, List<Edge<T>> edgesNew) {
        if (edgesPrevious.stream().anyMatch(previousEdge -> previousEdge.doesEdgeContainProperVertex(from))) {
            return edgesPrevious.size() > edgesNew.size() ? edgesNew : edgesPrevious;
        }
        else return edgesNew;
    }

    private Map<Vertex<T>, Edge<T>> getNeighboursMap(Vertex<T> currentVertex) {
        return edges.stream()
                .filter(edge -> edge.doesEdgeContainProperVertex(currentVertex))
                .collect(Collectors.toMap(o -> o.getEndVertex(currentVertex), Function.identity()));
    }

    private boolean doesGraphContainsBothVertices(Vertex<T> from, Vertex<T> to) {
        final boolean doesGraphContainsBothVertices = Stream.of(from, to).allMatch(this.vertices::contains);
        if (!doesGraphContainsBothVertices) {
            logger.warn("Graph doesn't contain both vertices: from: {}, to: {}", from, to);
        }
        return doesGraphContainsBothVertices;
    }

    private void checkForNull(Vertex<T> vertexFrom, Vertex<T> vertexTo) {
        if (!ObjectUtils.allNotNull(vertexFrom, vertexTo)) {
            throw new IllegalArgumentException(THE_INPUT_VERTICES_CANNOT_BE_NULL);
        }
    }

    public Set<Vertex<T>> getVertices() {
        return ImmutableSet.copyOf(this.vertices);
    }

    public Set<Edge<T>> getEdges() {
        return ImmutableSet.copyOf(this.edges);
    }
}
