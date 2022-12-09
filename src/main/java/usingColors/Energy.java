package usingColors;

import java.awt.*;

public class Energy {

    private int widthPixels;
    private int heightPixels;
    private Color[][] startingImage;
    private int minEnergy = (255 * 255) * 6;
    private int maxEnergy = (255 * 255) * 6;
    private int[][] energyTable;
    private int[][] adjusted;

    public Energy(int width, int height, Color[][] startImage) {
        widthPixels = width;
        heightPixels = height;
        startingImage = startImage;
        energyTable = new int[heightPixels][widthPixels];
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
                    Color up = startingImage[row + 1][col];
                    Color down = startingImage[row - 1][col];
                    Color left = startingImage[row][col - 1];
                    Color right = startingImage[row][col + 1];
                    currentEnergy = middleEnergy(up, down, left, right);
                    energyTable[row][col] = currentEnergy;
                    adjustMaxAndMin(currentEnergy);
                }
            }
        }
    }

    private int middleEnergy(Color up, Color down, Color left, Color right) {

        int upDown = (up.getRed() - down.getRed()) ^ 2;
        upDown += (up.getGreen() - down.getGreen()) ^ 2;
        upDown += (up.getBlue() - down.getBlue()) ^ 2;

        int leftRight = (left.getRed() - right.getRed()) ^ 2;
        leftRight += (left.getGreen() - right.getGreen()) ^ 2;
        leftRight += (left.getBlue() - right.getBlue()) ^ 2;

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
            adjusted = new int[heightPixels][widthPixels];
            for (int row = 0; row < heightPixels; row++)
            {
                for (int col = 0; col < widthPixels; col++)
                {
                    adjusted[row][col] = adjust(energyTable[row][col]);
                }
            }
        }
    }

    private int adjust(int origEnergy) {
        return (origEnergy - minEnergy) * 255 / (maxEnergy - minEnergy);
    }

    public int[][] getAdjustedEnergyTable() {
        return adjusted;
    }

}
