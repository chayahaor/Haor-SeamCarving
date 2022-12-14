import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image
{

    private Pixel[][] pixelMatrix;
    private final int heightPixels;
    private final int widthPixels;

    public Image(BufferedImage image)
    {
        heightPixels = image.getHeight();
        widthPixels = image.getWidth();

        pixelMatrix = new Pixel[heightPixels][widthPixels];
        for (int row = 0; row < heightPixels; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                //image.getRGB goes in a different order than my brain
                pixelMatrix[row][col] = new Pixel(image.getRGB(col, row));
            }
        }
    }

    public void saveBrightnessFile()
    {
        BufferedImage bufferedImage =
                new BufferedImage(widthPixels, heightPixels, BufferedImage.TYPE_INT_RGB);

        for (int row = 0; row < heightPixels; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                int spot = (int) pixelMatrix[row][col].getCellEnergy();
                Color color = new Color(spot, spot, spot);

                bufferedImage.setRGB(col, row, color.getRGB());
            }
        }
        try
        {
            File file = new File("brightness.jpg");
            ImageIO.write(bufferedImage, "jpg", file);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public Pixel[][] getPixelMatrix()
    {
        return pixelMatrix;
    }


}
