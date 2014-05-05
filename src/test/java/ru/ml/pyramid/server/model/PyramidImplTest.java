package ru.ml.pyramid.server.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PyramidImplTest {
    private static final int HEIGHT = 20;
    private static final float THING_WEIGHT = 50.0f;
    private static final float DELTA = 1;

    private Pyramid pyramid;

    @Before
    public void before() {
        pyramid = new PyramidImpl(HEIGHT, THING_WEIGHT);
    }

    @Test
    public void testLevelBounds() {
        testLevelBound(-1, 0);

        for (int level = 0; level < HEIGHT; ++level) {
            testLevelBound(level, -1);
            testLevelBound(level, level + 1);
        }

        for (int index = 0; index < HEIGHT; ++index) {
            testLevelBound(HEIGHT, index);
        }
    }

    private void testLevelBound(int level, int index) {
        try {
            pyramid.getHumanEdgeWeight(level, level + 1);
            Assert.fail();
        } catch (PyramidException e) {
            System.out.println("Thing at level " + level + " and index " + index + " is out of bounds");
        }
    }

    @Test
    public void testLevelWeights() throws PyramidException {
        for (int level = 0; level < HEIGHT; ++level) {
            float levelPressure = 0;
            for (int index = 0; index <= level; ++index) {
                levelPressure += pyramid.getHumanEdgeWeight(level, index);
            }
            float upperLevelsWeight = baseWeightAt(level - 1);
            System.out.println("level=" + level + ", upper=" + upperLevelsWeight + ", pressure=" + levelPressure);
            Assert.assertEquals(upperLevelsWeight, levelPressure, DELTA);
        }
    }

    private float baseWeightAt(int level) {
        return (level + 1) * (level + 2) / 2 * THING_WEIGHT;
    }
}
