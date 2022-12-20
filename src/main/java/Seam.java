import java.util.ArrayList;

public class Seam {

    private final int widthPixels;
    private final int heightPixels;
    private Pixel[][] startingTable;
    private ArrayList<Integer> lowestSeam;

    public Seam(Pixel[][] energyTable) {
        widthPixels = energyTable[0].length;
        heightPixels = energyTable.length;
        startingTable = energyTable;
        lowestSeam = new ArrayList<>();
    }

    private void calculateVerticalEnergy() {
        for (int row = 0; row < heightPixels - 1; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                if (col == 0)
                {
                    //left row -> don't check for low pixel to bottom left
                    checkForVertical(startingTable[row][col],
                            startingTable[row + 1][col],
                            startingTable[row + 1][col + 1]); //diagonal is to the right

                } else if (col == widthPixels - 1)
                {
                    //right row -> don't check for low pixel to bottom right
                    checkForVertical(startingTable[row][col],
                            startingTable[row + 1][col],
                            startingTable[row + 1][col - 1]); //diagonal is to the left

                } else
                {
                    //regular pixel -> check for low pixel in three spots
                    checkForVertical(startingTable[row][col],
                            startingTable[row + 1][col - 1],
                            startingTable[row + 1][col],
                            startingTable[row + 1][col + 1]);
                }
            }
        }
    }

    private void checkForVertical(Pixel self, Pixel downLeft,
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

    private void checkForVertical(Pixel self, Pixel directlyDown,
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
        //special case for bottom row - looking across instead of up
        for (int col = 0; col < widthPixels - 1; col++)
        {
            if (startingTable[heightPixels][col].getEnergyV()
                < startingTable[heightPixels][col + 1].getEnergyV())
            {
                indexLowest = col;
            }
        }
        lowestSeam.add(indexLowest);


        for (int row = 0; row < heightPixels; row++)
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
}
