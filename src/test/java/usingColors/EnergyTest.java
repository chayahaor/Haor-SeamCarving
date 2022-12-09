package usingColors;

import org.junit.jupiter.api.Test;
import usingColors.Energy;

import java.awt.*;
import java.util.Arrays;

class EnergyTest {

    @Test
    void calculateEnergy() {
        //given
        Color one = new Color(255, 0, 0);
        Color two = new Color(0, 255, 0);
        Color three = new Color(0, 0, 255);
        Color[][] image = new Color[][]{{one, two, three}, {two, three, one}, {three, one, two}};
        Energy energy = new Energy(image.length, image[0].length, image);
        int[][] calculatedEnergy = energy.getAdjustedEnergyTable();

        //when
        int[][] expected = new int[][]{{255, 255, 255}, {255, 0, 255}, {255, 255, 255}};

        //then
        assert(Arrays.deepEquals(expected, calculatedEnergy));
    }

}