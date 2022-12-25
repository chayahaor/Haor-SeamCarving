import java.util.ArrayList;

public class Seam {

    private int widthPixels;
    private int heightPixels;
    private Pixel[][] startingTable;
    private ArrayList<Integer> lowestSeam;

    public Seam() {
    }


    public void calculateLowestVerticalSeam(Pixel[][] pixelTable) {
        widthPixels = pixelTable[0].length;
        heightPixels = pixelTable.length;
        startingTable = pixelTable;
        lowestSeam = new ArrayList<>();
        calculateVerticalEnergy(); //right before calculate seam, update the vertical energy
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

    public ArrayList<Integer> getLowestVerticalSeam() {
        return lowestSeam;
    }


    public void calculateLowestHorizontalSeam(Pixel[][] pixelTable) {
        widthPixels = pixelTable[0].length;
        heightPixels = pixelTable.length;
        startingTable = pixelTable;
        lowestSeam = new ArrayList<>();

        calculateHorizontalEnergy(); //right before calculate seam, update the horizontal energy
        int indexLowestRow = 0;
        //special case for right col - find the lowest pixel energy up/down
        for (int row = 0; row < heightPixels - 1; row++)
        {
            if (startingTable[indexLowestRow][widthPixels - 1].getEnergyH()
                < startingTable[row + 1][widthPixels - 1].getEnergyH())
            {
                indexLowestRow = row;
            }
        }
        lowestSeam.add(indexLowestRow);

        //for the lowest pixel in the col,
        //look left of it and set the smallest one to the left as next lowest pixel
        //and add the lowest pixel to the seam
        //special cases added in for edges since cannot check both diagonally left
        for (int col = widthPixels - 1; col > 0; col--)
        {
            if (indexLowestRow != 0 && indexLowestRow != heightPixels) //not a border, can check all 3 spots to the left
            {
                indexLowestRow = findLowestIndexLeft(indexLowestRow, col);
                lowestSeam.add(indexLowestRow);
            } else if (indexLowestRow == 0) //can only look down left and directly left
            {
                indexLowestRow = findLowestIndexDownLeft(indexLowestRow, col);
                lowestSeam.add(indexLowestRow);
            } else // can only look up left and directly left
            {
                indexLowestRow = findLowestIndexUpLeft(indexLowestRow, col);
                lowestSeam.add(indexLowestRow);
            }

        }
    }

    private void calculateHorizontalEnergy() {
        for (int row = 0; row < heightPixels; row++)
        {
            for (int col = 0; col < widthPixels - 1; col++)
            {
                if (row == 0)
                {
                    //at the top - don't check up right
                    findLowestHorizontalRight(startingTable[row][col],
                            startingTable[row][col + 1],
                            startingTable[row + 1][col + 1]);
                } else if (row == heightPixels - 1)
                {
                    //at the bottom - don't check down right
                    findLowestHorizontalRight(startingTable[row][col],
                            startingTable[row][col + 1],
                            startingTable[row - 1][col + 1]);

                } else
                {
                    //regular pixel -> check for low pixel in three spots
                    findLowestHorizontalRight(startingTable[row][col],
                            startingTable[row - 1][col + 1],
                            startingTable[row][col + 1],
                            startingTable[row + 1][col + 1]);
                }
            }
        }
    }

    private void findLowestHorizontalRight(Pixel self, Pixel upLeft, Pixel right, Pixel downLeft) {
        if (right.getCellEnergy() < upLeft.getCellEnergy()
            && right.getCellEnergy() < downLeft.getCellEnergy())
        { //directly right is smallest
            right.setEnergyH(right.getCellEnergy() + self.getEnergyH());
        } else if (upLeft.getCellEnergy() < right.getCellEnergy()
                   && upLeft.getCellEnergy() < downLeft.getCellEnergy())
        { //up right is smallest
            upLeft.setEnergyH(upLeft.getCellEnergy() + self.getEnergyH());
        } else //down right is smallest
        {
            downLeft.setEnergyH(downLeft.getCellEnergy() + self.getEnergyH());
        }
    }

    private void findLowestHorizontalRight(Pixel self, Pixel directLeft, Pixel diagonal) {
        if (directLeft.getCellEnergy() < diagonal.getCellEnergy())
        {
            directLeft.setEnergyH(directLeft.getCellEnergy() + self.getEnergyH());
        } else
        {
            diagonal.setEnergyH(diagonal.getCellEnergy() + self.getEnergyH());
        }
    }

    private int findLowestIndexLeft(int indexLowest, int col) {
        if (startingTable[indexLowest + 1][col - 1].getEnergyH()
            < startingTable[indexLowest][col - 1].getEnergyH()
            && startingTable[indexLowest + 1][col - 1].getEnergyH()
               < startingTable[indexLowest - 1][col - 1].getEnergyH())
        {
            //then down left is smallest
            return indexLowest + 1;
        } else if (startingTable[indexLowest - 1][col - 1].getEnergyH()
                   < startingTable[indexLowest][col - 1].getEnergyH()
                   && startingTable[indexLowest - 1][col - 1].getEnergyH()
                      < startingTable[indexLowest + 1][col - 1].getEnergyH())
        {
            //then up left is smallest
            return indexLowest - 1;
        }
        return indexLowest;
    }

    private int findLowestIndexUpLeft(int indexLowest, int col) {
        if (startingTable[indexLowest][col - 1].getEnergyH()
            < startingTable[indexLowest - 1][col - 1].getEnergyH())
        {
            return indexLowest;
        } else
        {
            return indexLowest - 1;
        }
    }

    private int findLowestIndexDownLeft(int indexLowest, int col) {
        if (startingTable[indexLowest][col - 1].getEnergyH()
            < startingTable[indexLowest + 1][col - 1].getEnergyH())
        {
            return indexLowest;
        } else
        {
            return indexLowest + 1;
        }
    }

    public ArrayList<Integer> getLowestHorizontalSeam() {
        return lowestSeam;
    }

}
