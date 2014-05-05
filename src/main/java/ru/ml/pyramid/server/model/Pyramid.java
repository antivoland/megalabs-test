package ru.ml.pyramid.server.model;

public interface Pyramid {
    float getHumanEdgeWeight(int level, int index) throws PyramidException;
}

