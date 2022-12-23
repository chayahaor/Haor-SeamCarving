import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EnergyTest {

    //TODO: change from using pixel class to using mock

    @Test
    void energy4x4() {
        //given
        Color color1 = new Color(255, 0, 0);
        Color color2 = new Color(0, 255, 0);
        Color color3 = new Color(0, 0, 255);

        Pixel[][] image = new Pixel[][]{
                {new Pixel(color1.getRGB()),
                        new Pixel(color2.getRGB()),
                        new Pixel(color3.getRGB()),
                        new Pixel(color1.getRGB())},
                {new Pixel(color2.getRGB()),
                        new Pixel(color3.getRGB()),
                        new Pixel(color1.getRGB()),
                        new Pixel(color1.getRGB())},
                {new Pixel(color3.getRGB()),
                        new Pixel(color1.getRGB()),
                        new Pixel(color1.getRGB()),
                        new Pixel(color2.getRGB())},
                {new Pixel(color1.getRGB()),
                        new Pixel(color1.getRGB()),
                        new Pixel(color2.getRGB()),
                        new Pixel(color3.getRGB())}};
        Energy energy = new Energy();
        energy.updateEnergy(image);
        double[][] calculatedEnergy = new double[image.length][image[0].length];
        for (int row = 0; row < calculatedEnergy[0].length; row++)
        {
            for (int col = 0; col < calculatedEnergy.length; col++)
            {
                calculatedEnergy[row][col] = image[row][col].getCellEnergy();
            }
        }
        //when
        double[][] expected = new double[][]{
                {255.0, 255.0, 255.0, 255.0},
                {255.0, 0.0, 0.0, 255.0},
                {255.0, 0.0, 0.0, 255.0},
                {255.0, 255.0, 255.0, 255.0}};

        //then
        assertTrue(Arrays.deepEquals(expected, calculatedEnergy));
    }


    @Test
    void calculateEnergy() {
        //given
        Color color1 = new Color(255, 0, 0);
        Color color2 = new Color(0, 255, 0);
        Color color3 = new Color(0, 0, 255);

        Pixel[][] image = new Pixel[][]{
                {new Pixel(color1.getRGB()),
                        new Pixel(color2.getRGB()),
                        new Pixel(color3.getRGB())},
                {new Pixel(color2.getRGB()),
                        new Pixel(color3.getRGB()),
                        new Pixel(color1.getRGB())},
                {new Pixel(color3.getRGB()),
                        new Pixel(color1.getRGB()),
                        new Pixel(color2.getRGB())}};
        Energy energy = new Energy();
        energy.updateEnergy(image);
        double[][] calculatedEnergy = new double[image.length][image[0].length];
        for (int row = 0; row < calculatedEnergy.length; row++)
        {
            for (int col = 0; col < calculatedEnergy[0].length; col++)
            {
                calculatedEnergy[row][col] = image[row][col].getCellEnergy();
            }
        }
        //when
        double[][] expected = new double[][]{
                {255.0, 255.0, 255.0},
                {255.0, 0.0, 255.0},
                {255.0, 255.0, 255.0}};

        //then
        assertTrue(Arrays.deepEquals(expected, calculatedEnergy));
    }

}