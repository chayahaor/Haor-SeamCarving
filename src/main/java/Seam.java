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

    private void findSmallestVerticalSeam() {
        for (int row = 0; row < heightPixels - 1; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                if (col == 0)
                {
                    //left row -> don't check for low pixel to bottom left
                    checkForVertical(startingTable[row][col],
                            startingTable[row + 1][col],
                            startingTable[row + 1][col + 1],
                            col, 1); //diagonal is to the right

                } else if (col == widthPixels - 1)
                {
                    //right row -> don't check for low pixel to bottom right
                    checkForVertical(startingTable[row][col],
                            startingTable[row + 1][col],
                            startingTable[row + 1][col - 1],
                            col, -1); //diagonal is to the left

                } else
                {
                    //regular pixel -> check for low pixel in three spots
                    checkForVertical(startingTable[row][col],
                            startingTable[row + 1][col - 1],
                            startingTable[row + 1][col],
                            startingTable[row + 1][col + 1],
                            col);
                }
            }
        }
    }

    private void checkForVertical(Pixel self, Pixel downLeft, Pixel directlyDown, Pixel downRight, int col) {
        if (directlyDown.getCellEnergy() < downLeft.getCellEnergy()
            && directlyDown.getCellEnergy() < downRight.getCellEnergy())
        { //down is smallest
            directlyDown.setEnergyV(directlyDown.getCellEnergy() + self.getEnergyV());
            seam.add(col);
        } else if (downLeft.getCellEnergy() < directlyDown.getCellEnergy()
                   && downLeft.getCellEnergy() < downRight.getCellEnergy())
        { //left is smallest
            downLeft.setEnergyV(downLeft.getEnergyV() + self.getEnergyV());
            seam.add(col-1);
        } else //right is smallest
        {
            downRight.setEnergyV(downRight.getEnergyV() + self.getEnergyV());
            seam.add(col+1);
        }
    }

    private void checkForVertical(Pixel self, Pixel directlyDown, Pixel diagonal, int col, int moveOver) {
        if (directlyDown.getCellEnergy() < diagonal.getCellEnergy())
        {
            directlyDown.setEnergyV(directlyDown.getCellEnergy() + self.getEnergyV());
            seam.add(col);
        } else
        {
            diagonal.setEnergyV(diagonal.getCellEnergy() + self.getEnergyV());
            seam.add(moveOver);
        }
    }

    public ArrayList<Integer> getSeam() {
        return seam;
    }
}
