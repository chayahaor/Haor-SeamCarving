import java.util.ArrayList;

public class Seam {

    private final int widthPixels;
    private final int heightPixels;
    private Pixel[][] startingTable;
    private ArrayList<Integer> seam;

    public Seam(Pixel[][] energyTable) {
        widthPixels = energyTable[0].length;
        heightPixels = energyTable.length;
        startingTable = energyTable;
        seam = new ArrayList<>();
    }

    private ArrayList<Integer> findSmallestSeam() {
        ArrayList<Integer> smallSeam = new ArrayList<>();
        for (int row = 0; row < heightPixels - 1; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                if (col == 0)
                {
                    //left row -> don't check for low pixel to bottom left
                    checkForLowest(startingTable[row][col],
                            startingTable[row + 1][col],
                            startingTable[row + 1][col + 1]);

                } else if (col == widthPixels - 1)
                {
                    //right row -> don't check for low pixel to bottom right
                    checkForLowest(startingTable[row][col],
                            startingTable[row + 1][col],
                            startingTable[row + 1][col - 1]);

                } else
                {
                    //regular pixel -> check for low pixel in three spots
                    checkForLowest(startingTable[row][col],
                            startingTable[row + 1][col - 1],
                            startingTable[row + 1][col],
                            startingTable[row + 1][col + 1]);
                }
            }
        }
        return smallSeam;
    }

    private void checkForLowest(Pixel self, Pixel downLeft, Pixel directlyDown, Pixel downRight) {
        if (directlyDown.getCellEnergy() < downLeft.getCellEnergy()
            && directlyDown.getCellEnergy() < downRight.getCellEnergy())
        { //down is smallest
            directlyDown.setEnergyV(directlyDown.getCellEnergy() + self.getEnergyV());
        } else if (downLeft.getCellEnergy() < directlyDown.getCellEnergy()
                   && downLeft.getCellEnergy() < downRight.getCellEnergy())
        { //left is smallest
            downLeft.setEnergyV(downLeft.getEnergyV() + self.getEnergyV());
        } else //right is smallest
        {
            downRight.setEnergyV(downRight.getEnergyV() + self.getEnergyV());
        }
    }

    private void checkForLowest(Pixel self, Pixel directlyDown, Pixel diagonal) {
        if (directlyDown.getCellEnergy() < diagonal.getCellEnergy())
        {
            directlyDown.setEnergyV(directlyDown.getCellEnergy() + self.getEnergyV());
        } else
        {
            diagonal.setEnergyV(diagonal.getCellEnergy() + self.getEnergyV());
        }
    }

    public ArrayList<Integer> getSeam() {
        return seam;
    }
}
