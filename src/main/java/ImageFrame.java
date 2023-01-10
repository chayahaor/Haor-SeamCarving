import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageFrame extends JFrame
{

    private final JLabel imageLabel;
    private Image startingImage;

    public ImageFrame()
    {

        setLayout(new BorderLayout());

        // This is where the image will be stored.
        imageLabel = new JLabel();

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        JButton resizeButton = new JButton("Resize");
        resizeButton.addActionListener(event -> {
            // This will set the image based on the current size of the label
            setSeamImageSize(imageLabel.getWidth(), imageLabel.getHeight());
        });
        northPanel.add(resizeButton);

        JButton loadButton = new JButton("Load");
        northPanel.add(loadButton);
        loadButton.addActionListener(event -> {
            chooseFile();
        });

        add(northPanel, BorderLayout.NORTH);
        add(imageLabel, BorderLayout.CENTER);

        BufferedImage image;
        try
        {
            image = ImageIO.read(ImageFrame.class.getResourceAsStream("/Seam.jpg"));
            startingImage = new Image(image);
            loadSeamImage(image);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        setTitle("Seam Carving");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void chooseFile()
    {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            try
            {
                startingImage = new Image(ImageIO.read(file));
                loadSeamImage(ImageIO.read(file));
            } catch (IOException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public void loadSeamImage(BufferedImage image)
    {
        // add code here to load the image into your seam carver code
        //TODO: set starting image here to be image selected

        ImageIcon imageIcon = new ImageIcon(image);
        imageLabel.setIcon(imageIcon);

        setSize(image.getWidth(null), image.getHeight(null));
        pack();
    }

    private void setSeamImageSize(int width, int height)
    {
        // generate a newImage with the new width and height
        Pixel[][] pixels = startingImage.getPixelMatrix();
        Energy energy = new Energy();
        Seam seam = new Seam();
        SeamRemover seamRemover = new SeamRemover();

        for (int col = startingImage.getPixelMatrix().length; col > width; col--)
        {
            energy.updateCellEnergy(pixels);
            pixels = seamRemover.removeVertical(
                    pixels, seam.getLowestVerticalSeam(pixels));
        }

        for (int row = startingImage.getPixelMatrix()[0].length; row > height; row--)
        {
            energy.updateCellEnergy(pixels);
            pixels = seamRemover.removeHorizontal(
                    pixels, seam.getLowestHorizontalSeam(pixels));
        }

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < height; row++)
        {
            for (int col = 0; col < width; col++)
            {
                bufferedImage.setRGB(col, row, pixels[row][col].getColor().getRGB());
            }
        }
        ImageIcon imageIcon = new ImageIcon(bufferedImage);
        imageLabel.setIcon(imageIcon);
    }

    public static void main(String[] args)
    {
        new ImageFrame().setVisible(true);
    }

}
