package ru.ml.pyramid.server.model;

import com.google.inject.Inject;
import com.google.inject.name.Named;

public class PyramidImpl implements Pyramid {
    private final Thing[][] levels;

    @Inject
    public PyramidImpl(@Named("pyramid-height") int height, @Named("thing-weight") float thingWeight) {
        this.levels = createLevels(height, thingWeight);
    }

    @Override
    public float getHumanEdgeWeight(int level, int index) throws PyramidException {
        if (level < 0 || level >= levels.length) {
            throw new PyramidException("Level " + level + " is out of range. Must be non-negative and less than " + levels.length + ".");
        }
        Thing[] theLevel = levels[level];
        if (index < 0 || index >= theLevel.length) {
            throw new PyramidException("Index " + index + " at level " + level + " is out of range. Must be non-negative and less than " + theLevel.length + ".");
        }
        return theLevel[index].getHumanEdgeWeight();
    }

    private static Thing[][] createLevels(int height, float thingWeight) {
        Thing[][] levels = new Thing[height][];
        for (int levelIndex = 0; levelIndex < height; ++levelIndex) {
            Thing[] level = new Thing[levelIndex + 1];
            for (int thingIndex = 0; thingIndex < level.length; ++thingIndex) {
                Thing left = thingIndex > 0 ? levels[levelIndex - 1][thingIndex - 1] : null;
                Thing right = thingIndex < level.length - 1 ? levels[levelIndex - 1][thingIndex] : null;
                level[thingIndex] = new Thing(thingWeight, left, right);
            }
            levels[levelIndex] = level;
        }
        return levels;
    }
}
