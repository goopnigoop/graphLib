package com.simple.lib.edge;

import com.simple.lib.Vertex;

import java.util.Objects;

/**
 * The type Abstract edge.
 *
 * @param <T> the type parameter
 */
public abstract class AbstractEdge<T> implements Edge<T> {

    /**
     * The Vertex start.
     */
    protected Vertex<T> vertexStart;
    /**
     * The Vertex end.
     */
    protected Vertex<T> vertexEnd;

    /**
     * Instantiates a new Abstract edge.
     *
     * @param vertex    the vertex
     * @param vertexEnd the vertex end
     */
    public AbstractEdge(Vertex<T> vertex, Vertex<T> vertexEnd) {
        this.vertexStart = vertex;
        this.vertexEnd = vertexEnd;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()+
                "{" +
                "vertexStart=" + vertexStart +
                ", vertexEnd=" + vertexEnd +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEdge<?> that = (AbstractEdge<?>) o;
        return Objects.equals(vertexStart, that.vertexStart) &&
                Objects.equals(vertexEnd, that.vertexEnd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexStart, vertexEnd);
    }
}
