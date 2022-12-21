import java.util.ArrayList;

public class Seam {

    private final int widthPixels;
    private final int heightPixels;
    private Pixel[][] startingTable;
    private ArrayList<Integer> lowestSeam;

    public Seam(Pixel[][] pixelTable) {
        widthPixels = pixelTable[0].length;
        heightPixels = pixelTable.length;
        startingTable = pixelTable;
        lowestSeam = new ArrayList<>();
        calculateVerticalEnergy();
        calculateLowestVerticalSeam();
    }

    private void calculateVerticalEnergy() {
        for (int row = 0; row < heightPixels - 1; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                if (col == 0)
                {
                    //left row -> don't check for low pixel to bottom left
                    findLowestVerticalBelow(startingTable[row][col],
                            startingTable[row + 1][col],
                            startingTable[row + 1][col + 1]); //diagonal is to the right

                } else if (col == widthPixels - 1)
                {
                    //right row -> don't check for low pixel to bottom right
                    findLowestVerticalBelow(startingTable[row][col],
                            startingTable[row + 1][col],
                            startingTable[row + 1][col - 1]); //diagonal is to the left

                } else
                {
                    //regular pixel -> check for low pixel in three spots
                    findLowestVerticalBelow(startingTable[row][col],
                            startingTable[row + 1][col - 1],
                            startingTable[row + 1][col],
                            startingTable[row + 1][col + 1]);
                }
            }
        }
    }

    private void findLowestVerticalBelow(Pixel self, Pixel downLeft,
                                         Pixel directlyDown, Pixel downRight) {
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

    private void findLowestVerticalBelow(Pixel self, Pixel directlyDown,
                                         Pixel diagonal) {
        if (directlyDown.getCellEnergy() < diagonal.getCellEnergy())
        {
            directlyDown.setEnergyV(directlyDown.getCellEnergy() + self.getEnergyV());
        } else
        {
            diagonal.setEnergyV(diagonal.getCellEnergy() + self.getEnergyV());
        }
    }


    private void calculateLowestVerticalSeam() {

        int indexLowest = 0;
        //special case for bottom row - find the lowest pixel energy across
        for (int col = 0; col < widthPixels - 1; col++)
        {
            if (startingTable[heightPixels - 1][col].getEnergyV()
                < startingTable[heightPixels - 1][col + 1].getEnergyV())
            {
                indexLowest = col;
            }
        }
        lowestSeam.add(indexLowest);

        //for the lowest pixel in the row,
        // look above it and set the smallest one above as next lowest pixel
        //and add the lowest pixel to the seam
        //special cases added in for edges since cannot check both diagonally up
        for (int row = 1; row < heightPixels; row++)
        {
            if (indexLowest != 0 && indexLowest != widthPixels)
            {
                indexLowest = findLowestIndexAbove(indexLowest, row);
                lowestSeam.add(indexLowest);
            } else if (indexLowest == 0)
            {
                indexLowest = findLowestIndexTopRight(indexLowest, row);
                lowestSeam.add(indexLowest);
            } else
            {
                indexLowest = findLowestIndexTopLeft(indexLowest, row);
                lowestSeam.add(indexLowest);
            }

        }
    }

    private int findLowestIndexAbove(int indexLowest, int row) {
        if (startingTable[row][indexLowest - 1].getEnergyV()
            < startingTable[row][indexLowest + 1].getEnergyV()
            && startingTable[row][indexLowest - 1].getEnergyV()
               < startingTable[row][indexLowest].getEnergyV())
        {
            //then above left is smallest
            return indexLowest - 1;
        } else if (startingTable[row][indexLowest + 1].getEnergyV()
                   < startingTable[row][indexLowest - 1].getEnergyV()
                   && startingTable[row][indexLowest + 1].getEnergyV()
                      < startingTable[row][indexLowest].getEnergyV())
        {
            //then above right is smallest
            return indexLowest + 1;
        }
        return indexLowest;
    }

    private int findLowestIndexTopRight(int indexLowest, int row) {
        if (startingTable[row][indexLowest].getEnergyV()
            < startingTable[row][indexLowest + 1].getEnergyV())
        {
            return indexLowest;
        } else
        {
            return indexLowest + 1;
        }
    }

    private int findLowestIndexTopLeft(int indexLowest, int row) {
        if (startingTable[row][indexLowest].getEnergyV()
            < startingTable[row][indexLowest - 1].getEnergyV())
        {
            return indexLowest;
        } else
        {
            return indexLowest - 1;
        }
    }

    public ArrayList<Integer> getLowestSeam() {
        return lowestSeam;
    }
}
