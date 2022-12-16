import java.awt.*;

public class Energy {

    private final int widthPixels;
    private final int heightPixels;
    private final Pixel[][] startingImage;
    private double minEnergy = 255 * 255 * 6;
    private double maxEnergy = 255 * 255 * 6;
    private double[][] energyTable;

    public Energy(int width, int height, Pixel[][] startImage) {
        this.widthPixels = width;
        this.heightPixels = height;
        energyTable = new double[heightPixels][widthPixels];
        this.startingImage = startImage;
        calculateOrigEnergy();
        adjustEnergyTable();
    }

    private void calculateOrigEnergy() {
        double currentEnergy;
        for (int row = 0; row < heightPixels; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                if (row == 0 || row == heightPixels - 1
                    || col == 0 || col == widthPixels - 1) //along an edge
                {
                    energyTable[row][col] = nonMiddleEnergy();
                } else
                {
                    //middle case
                    Color up = startingImage[row + 1][col].getColor();
                    Color down = startingImage[row - 1][col].getColor();
                    Color left = startingImage[row][col - 1].getColor();
                    Color right = startingImage[row][col + 1].getColor();
                    currentEnergy = middleEnergy(up, down, left, right);
                    energyTable[row][col] = currentEnergy;
                    adjustMaxAndMin(currentEnergy);
                }
            }
        }
    }

    private double middleEnergy(Color up, Color down, Color left, Color right) {

        return ((up.getRed() - down.getRed()) * (up.getRed() - down.getRed())
                + (up.getGreen() - down.getGreen()) * (up.getGreen() - down.getGreen())
                + (up.getBlue() - down.getBlue()) * (up.getBlue() - down.getBlue()))
               + (left.getRed() - right.getRed()) * (left.getRed() - right.getRed())
               + (left.getGreen() - right.getGreen()) * (left.getGreen() - right.getGreen())
               + (left.getBlue() - right.getBlue()) * (left.getBlue() - right.getBlue());
    }

    private double nonMiddleEnergy() {
        return 255 * 255 * 6;
    }

    private void adjustMaxAndMin(double currentEnergy) {
        if (currentEnergy > maxEnergy) //TODO: is this needed? Or is max always going to be max?
        {
            maxEnergy = currentEnergy;
        }
        if (currentEnergy < minEnergy)
        {
            minEnergy = currentEnergy;
        }
    }

    private void adjustEnergyTable() {
        for (int row = 0; row < heightPixels; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                adjust(energyTable[row][col], startingImage[row][col]);
            }
        }
    }

    private void adjust(double origEnergy, Pixel spot) {
        spot.setCellEnergy(((origEnergy - minEnergy) * 255.0) / (maxEnergy - minEnergy));
    }
}
