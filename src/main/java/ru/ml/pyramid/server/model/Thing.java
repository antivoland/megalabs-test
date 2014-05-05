package ru.ml.pyramid.server.model;

public class Thing {
    private final float weight;
    private final float humanEdgeWeight;

    public Thing(float weight, Thing left, Thing right) {
        this.weight = weight;
        this.humanEdgeWeight = getHumanEdgeWeight(left, right);
    }

    public float getHumanEdgeWeight() {
        return humanEdgeWeight;
    }

    private static float getHumanEdgeWeight(Thing left, Thing right) {
        float w = 0;
        if (left != null) {
            w += (left.weight + left.getHumanEdgeWeight()) / 2;
        }
        if (right != null) {
            w += (right.weight + right.getHumanEdgeWeight()) / 2;
        }
        return w;
    }
}
