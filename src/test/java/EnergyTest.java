import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EnergyTest
{

    @Test
    void energy4x4()
    {
        //given
        int red = Color.RED.getRGB();
        int green = Color.GREEN.getRGB();
        int blue = Color.BLUE.getRGB();

        Pixel[][] image = new Pixel[][]{
                {new Pixel(red),
                        new Pixel(green),
                        new Pixel(blue),
                        new Pixel(red)},
                {new Pixel(green),
                        new Pixel(blue),
                        new Pixel(red),
                        new Pixel(red)},
                {new Pixel(blue),
                        new Pixel(red),
                        new Pixel(red),
                        new Pixel(green)},
                {new Pixel(red),
                        new Pixel(red),
                        new Pixel(green),
                        new Pixel(blue)}};
        Energy energy = new Energy();
        energy.updateCellEnergy(image);
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
    void calculateEnergy()
    {
        //given
        int red = Color.RED.getRGB();
        int green = Color.GREEN.getRGB();
        int blue = Color.BLUE.getRGB();

        Pixel[][] image = new Pixel[][]{
                {new Pixel(red),
                        new Pixel(green),
                        new Pixel(blue)},
                {new Pixel(green),
                        new Pixel(blue),
                        new Pixel(red)},
                {new Pixel(blue),
                        new Pixel(red),
                        new Pixel(green)}};
        Energy energy = new Energy();
        energy.updateCellEnergy(image);
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