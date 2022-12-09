package usingInts;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;

class EnergyTest {

    @Test
    void calculateEnergy() {
        //given
        Color color1 = new Color(255, 0, 0);
        Color color2 = new Color(0, 255, 0);
        Color color3 = new Color(0, 0, 255);

        int one = color1.getRGB();
        int two = color2.getRGB();
        int three = color3.getRGB();

        int[][] image = new int[][]{{one, two, three}, {two, three, one}, {three, one, two}};
        Energy energy = new Energy(image.length, image[0].length, image);
        int[][] calculatedEnergy = energy.getAdjustedEnergyTable();

        //when
        int[][] expected = new int[][]{{255, 255, 255}, {255, 0, 255}, {255, 255, 255}};

        //then
        assert (Arrays.deepEquals(expected, calculatedEnergy));
    }

}