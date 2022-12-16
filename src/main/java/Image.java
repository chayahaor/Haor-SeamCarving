import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {

    private Pixel[][] pixelMatrix;
    private int heightPixels;
    private int widthPixels;

    public Image(String filePath) throws IOException {
        BufferedImage image = ImageIO.read(Image.class.getResourceAsStream(filePath));
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
        new Energy(widthPixels, heightPixels, getPixelMatrix()); //calculates energy

    }

    public void saveBrightnessFile() {
        File output = new File("C:\\Users\\chaya\\Downloads\\EnergyFile2.jpg");
        BufferedImage bufferedImage =
                new BufferedImage(widthPixels, heightPixels, BufferedImage.TYPE_INT_RGB);

        double[][] energyTable = new double[heightPixels][widthPixels];
        for (int row = 0; row < heightPixels; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                energyTable[row][col] = pixelMatrix[row][col].getCellEnergy();
            }
        }

        try
        {
            for (int row = 0; row < heightPixels; row++)
            {
                for (int col = 0; col < widthPixels; col++)
                {
                    int spot = (int) energyTable[row][col];
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


    public Pixel[][] getPixelMatrix() {
        return pixelMatrix;
    }

    public void setPixelMatrix(Pixel[][] pixelMatrix) {
        this.pixelMatrix = pixelMatrix;
    }

    public int getHeightPixels() {
        return heightPixels;
    }

    public void setHeightPixels(int heightPixels) {
        this.heightPixels = heightPixels;
    }

    public int getWidthPixels() {
        return widthPixels;
    }

    public void setWidthPixels(int widthPixels) {
        this.widthPixels = widthPixels;
    }
}
