import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    private int[][] imageMatrix;
    private int heightPixels = 0;
    private int widthPixels = 0;

    public Image(String filePath) throws IOException {
        BufferedImage image = ImageIO.read(Image.class.getResourceAsStream(filePath));
        heightPixels = image.getHeight();
        widthPixels = image.getWidth();

        imageMatrix = new int[heightPixels][widthPixels];
        for (int row = 0; row < heightPixels; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                imageMatrix[row][col] = image.getRGB(col, row);
            }
        }
    }

    public void saveBrightnessFile() {
        File output = new File("C:\\Users\\chaya\\Downloads\\EnergyFile2.jpg");
        BufferedImage bufferedImage =
                new BufferedImage(widthPixels, heightPixels, BufferedImage.TYPE_INT_RGB);

        Energy energy = new Energy(widthPixels, heightPixels, imageMatrix);
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

}
