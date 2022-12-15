import java.awt.*;

public class Energy {

    private final int widthPixels;
    private final int heightPixels;
    private int[][] startingImage;
    private int minEnergy = (255 * 255) * 6;
    private int maxEnergy = (255 * 255) * 6;
    private double[][] energyTable;
    private double[][] adjusted;

    public Energy(int width, int height, int[][] startImage) {
        widthPixels = width;
        heightPixels = height;
        energyTable = new double[heightPixels][widthPixels];
        startingImage = startImage;
        calculateOrigEnergy();
        adjustEnergyTable();
    }

    private void calculateOrigEnergy() {
        int currentEnergy = 0;
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
                    Color up = new Color(startingImage[row + 1][col]);
                    Color down = new Color(startingImage[row - 1][col]);
                    Color left = new Color(startingImage[row][col - 1]);
                    Color right = new Color(startingImage[row][col + 1]);
                    currentEnergy = middleEnergy(up, down, left, right);
                    energyTable[row][col] = currentEnergy;
                    adjustMaxAndMin(currentEnergy);
                }
            }
        }
    }

    private int middleEnergy(Color up, Color down, Color left, Color right) {

        int upDown = (up.getRed() - down.getRed()) * (up.getRed() - down.getRed());
        upDown += (up.getGreen() - down.getGreen()) * (up.getGreen() - down.getGreen());
        upDown += (up.getBlue() - down.getBlue()) * (up.getBlue() - down.getBlue());

        int leftRight = (left.getRed() - right.getRed()) * (left.getRed() - right.getRed());
        leftRight += (left.getGreen() - right.getGreen()) * (left.getGreen() - right.getGreen());
        leftRight += (left.getBlue() - right.getBlue()) * (left.getBlue() - right.getBlue());

        return upDown + leftRight;
    }

    private int nonMiddleEnergy() {
        return (255 * 255) * 6;
    }

    private void adjustMaxAndMin(int currentEnergy) {
        if (currentEnergy > maxEnergy)
        {
            maxEnergy = currentEnergy;
        }
        if (currentEnergy < minEnergy)
        {
            minEnergy = currentEnergy;
        }
    }

    private void adjustEnergyTable() {
        if (maxEnergy != 1)
        {
            adjusted = new double[heightPixels][widthPixels];
            for (int row = 0; row < heightPixels; row++)
            {
                for (int col = 0; col < widthPixels; col++)
                {
                    adjusted[row][col] = adjust(energyTable[row][col]);
                }
            }
        }
    }

    private double adjust(double origEnergy) {
        return ((origEnergy - minEnergy) * 255.0) / (maxEnergy - minEnergy);
    }

    public double[][] getAdjustedEnergyTable() {
        return adjusted;
    }

}
