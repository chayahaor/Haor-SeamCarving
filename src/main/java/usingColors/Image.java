package usingColors;

import usingInts.Driver;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    private Color[][] imageColors;
    private int heightPixels = 0;
    private int widthPixels = 0;

    public Image(String filePath) throws IOException {
        BufferedImage image = ImageIO.read(Driver.class.getResourceAsStream(filePath));
        heightPixels = image.getHeight();
        widthPixels = image.getWidth();

        imageColors = new Color[heightPixels][widthPixels];

        int[][] imageMatrix = new int[heightPixels][widthPixels];
        for (int i = 0; i < heightPixels; i++)
        {
            for (int j = 0; j < widthPixels; j++)
            {
                imageMatrix[i][j] = image.getRGB(i, j);
            }
        }



        for (int row = 0; row < heightPixels; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                imageColors[row][col] = image.getGraphics().getColor();
            }
        }
    }



    public void saveEnergyFile() {
        File output = new File("C:\\Users\\chaya\\Downloads\\EnergyFile.jpg");
        BufferedImage bufferedImage =
                new BufferedImage(widthPixels, heightPixels, BufferedImage.TYPE_INT_RGB);

        Energy energy = new Energy(widthPixels, heightPixels, imageColors);
        int[][] energyTable = energy.getAdjustedEnergyTable();

        try
        {
            for (int row = 0; row < heightPixels; row++)
            {
                for (int col = 0; col < widthPixels; col++)
                {
                    int spot = energyTable[row][col];
                    Color color = new Color(spot, spot, spot);

                    bufferedImage.setRGB(col, row, color.getRGB());
                }
            }
            ImageIO.write(bufferedImage, "jpg", output);
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public void saveOrigFile() {
        File output =
                new File("C:\\Users\\chaya\\Downloads\\Orig.jpg");
        BufferedImage bufferedImage =
                new BufferedImage(widthPixels, heightPixels, BufferedImage.TYPE_INT_RGB);

        try
        {
            for (int row = 0; row < heightPixels; row++)
            {
                for (int col = 0; col < widthPixels; col++)
                {
                    Color spot = imageColors[row][col];
                    bufferedImage.setRGB(col, row, spot.getRGB());
                }
            }
            ImageIO.write(bufferedImage, "jpg", output);
        } catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }




}
