import java.awt.*;

public class Energy
{

    private int widthPixels;
    private int heightPixels;
    private Pixel[][] startingImage;
    private static final int MAX_POSSIBLE_VALUE = 255 * 255 * 6;
    private double minEnergy = MAX_POSSIBLE_VALUE;
    private double maxEnergy = MAX_POSSIBLE_VALUE;
    private double[][] energyTable;

    public Energy()
    {

    }

    public void updateCellEnergy(Pixel[][] startImage)
    {
        this.widthPixels = startImage[0].length;
        this.heightPixels = startImage.length;
        energyTable = new double[heightPixels][widthPixels];
        this.startingImage = startImage;
        calculateOriginalEnergy();
        //adjustCellEnergy();
    }

    private void calculateOriginalEnergy()
    {
        double currentEnergy;
        for (int row = 0; row < heightPixels; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                if (row == 0 || row == heightPixels - 1
                    || col == 0 || col == widthPixels - 1) //along an edge
                {
                    startingImage[row][col].setCellEnergy(MAX_POSSIBLE_VALUE);
                } else
                {
                    //middle case
                    Color up = startingImage[row + 1][col].getColor();
                    Color down = startingImage[row - 1][col].getColor();
                    Color left = startingImage[row][col - 1].getColor();
                    Color right = startingImage[row][col + 1].getColor();
                    currentEnergy = middleEnergy(up, down, left, right);
                    startingImage[row][col].setCellEnergy(currentEnergy);
                    /*energyTable[row][col] = currentEnergy;
                    adjustMin(currentEnergy);*/
                }
            }
        }
    }

    private double middleEnergy(Color up, Color down, Color left, Color right)
    {

        return ((up.getRed() - down.getRed()) * (up.getRed() - down.getRed())
                + (up.getGreen() - down.getGreen()) * (up.getGreen() - down.getGreen())
                + (up.getBlue() - down.getBlue()) * (up.getBlue() - down.getBlue()))
               + (left.getRed() - right.getRed()) * (left.getRed() - right.getRed())
               + (left.getGreen() - right.getGreen()) * (left.getGreen() - right.getGreen())
               + (left.getBlue() - right.getBlue()) * (left.getBlue() - right.getBlue());
    }


    private void adjustMin(double currentEnergy)
    {
        if (currentEnergy < minEnergy)
        {
            minEnergy = currentEnergy;
        }
    }

    private void adjustCellEnergy()
    {
        for (int row = 0; row < heightPixels; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                startingImage[row][col].setCellEnergy(
                        ((energyTable[row][col] - minEnergy) * 255.0)
                        / (maxEnergy - minEnergy));
            }
        }
    }

}
