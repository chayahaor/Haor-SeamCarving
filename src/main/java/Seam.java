import java.util.ArrayList;

public class Seam
{

    private int widthPixels;
    private int heightPixels;
    private Pixel[][] startingTable;

    public Seam()
    {
    }

    public ArrayList<Integer> getLowestVerticalSeam(Pixel[][] pixelTable)
    {
        widthPixels = pixelTable[0].length;
        heightPixels = pixelTable.length;
        startingTable = pixelTable;
        calculateVerticalEnergy(); //right before calculate seam, update the vertical energy
        int indexLowest = 0;
        ArrayList<Integer> lowestSeam = new ArrayList<>();
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
        for (int row = heightPixels - 2; row >= 0; row--)
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
        return lowestSeam;
    }

    private void calculateVerticalEnergy()
    {
        for (int row = 0; row < heightPixels; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                startingTable[row][col].setEnergyV(getLowestVerticalEnergy(row, col)
                                                   + startingTable[row][col].getCellEnergy());
            }
        }
    }

    private double getLowestVerticalEnergy(int row, int col)
    {
        int rowAbove = row - 1;
        double minEnergy;

        if (row == 0) //all the way on top
        {
            minEnergy = 0;
        } else if (col == 0) //all the way on the left
        {
            minEnergy = Math.min(startingTable[rowAbove][col].getEnergyH(),
                    startingTable[rowAbove][col + 1].getEnergyH());
        } else if (col == widthPixels - 1) //all the way on the right
        {
            minEnergy = Math.min(startingTable[rowAbove][col].getEnergyH(),
                    startingTable[rowAbove][col - 1].getEnergyH());
        } else
        {
            minEnergy = Math.min(startingTable[rowAbove][col].getEnergyH(),
                    startingTable[rowAbove][col + 1].getEnergyH());
            minEnergy = Math.min(minEnergy, startingTable[rowAbove][col - 1].getEnergyH());
        }
        return minEnergy;
    }

    private int findLowestIndexAbove(int indexLowest, int row)
    {
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

    private int findLowestIndexTopRight(int indexLowest, int row)
    {
        if (startingTable[row][indexLowest].getEnergyV()
            < startingTable[row][indexLowest + 1].getEnergyV())
        {
            return indexLowest;
        } else
        {
            return indexLowest + 1;
        }
    }

    private int findLowestIndexTopLeft(int indexLowest, int row)
    {
        if (startingTable[row][indexLowest].getEnergyV()
            < startingTable[row][indexLowest - 1].getEnergyV())
        {
            return indexLowest;
        } else
        {
            return indexLowest - 1;
        }
    }


    public ArrayList<Integer> getLowestHorizontalSeam(Pixel[][] pixelTable)
    {
        widthPixels = pixelTable[0].length;
        heightPixels = pixelTable.length;
        startingTable = pixelTable;

        calculateHorizontalEnergy(); //right before calculate seam, update the horizontal energy
        int indexLowestRow = 0;
        ArrayList<Integer> lowestSeam = new ArrayList<>();
        //special case for right col - find the lowest pixel energy up/down
        for (int row = 0; row < heightPixels - 1; row++)
        {
            if (startingTable[indexLowestRow][widthPixels - 1].getEnergyH()
                < startingTable[row + 1][widthPixels - 1].getEnergyH())
            {
                indexLowestRow = indexLowestRow;
            } else
            {
                indexLowestRow = row + 1;
            }
        }
        lowestSeam.add(indexLowestRow);

        //go through rest of columns using lowestIndex as row
        //check for lowest horizontal energy to the left
        for (int col = widthPixels - 1; col > 0; col--)
        {
            indexLowestRow = findLowestIndexLeft(indexLowestRow, col);
            lowestSeam.add(indexLowestRow);
        }
        return lowestSeam;
    }

    private void calculateHorizontalEnergy()
    {
        for (int row = 0; row < heightPixels; row++)
        {
            startingTable[row][0].setEnergyH(startingTable[row][0].getCellEnergy());
        }

        for (int col = 0; col < widthPixels; col++)
        {
            for (int row = 0; row < heightPixels; row++)
            {
                startingTable[row][col].setEnergyH(getLowestHorizontalEnergy(row, col)
                                                   + startingTable[row][col].getCellEnergy());
            }
        }
    }

    private double getLowestHorizontalEnergy(int row, int col)
    {
        int colBefore = col - 1;
        double minEnergy;

        if (col == 0)
        {
            minEnergy = 0;
        } else if (row == 0)
        {
            minEnergy = Math.min(startingTable[row + 1][colBefore].getEnergyH(),
                    startingTable[row][colBefore].getEnergyH());
        } else if (row == heightPixels - 1)
        {
            minEnergy = Math.min(startingTable[row - 1][colBefore].getEnergyH(),
                    startingTable[row][colBefore].getEnergyH());
        } else
        {
            minEnergy = Math.min(startingTable[row - 1][colBefore].getEnergyH(),
                    startingTable[row][colBefore].getEnergyH());
            minEnergy = Math.min(minEnergy, startingTable[row + 1][colBefore].getEnergyH());
        }
        return minEnergy;
    }

    private int findLowestIndexLeft(int indexLowest, int col)
    {
        int lowestLeftCol;
        int colBefore = col - 1;
        if (indexLowest == 0)
        {
            lowestLeftCol = startingTable[indexLowest][colBefore].getEnergyH()
                            < startingTable[indexLowest + 1][colBefore].getEnergyH()
                    ? indexLowest
                    : indexLowest + 1;
        } else if (indexLowest == heightPixels - 1)
        {
            lowestLeftCol = startingTable[indexLowest][colBefore].getEnergyH()
                            < startingTable[indexLowest - 1][colBefore].getEnergyH()
                    ? indexLowest
                    : indexLowest - 1;
        } else
        {
            if (startingTable[indexLowest + 1][colBefore].getEnergyH()
                < startingTable[indexLowest][colBefore].getEnergyH()
                && startingTable[indexLowest + 1][colBefore].getEnergyH()
                   < startingTable[indexLowest - 1][colBefore].getEnergyH())
            {
                //then down left is smallest
                lowestLeftCol = indexLowest + 1;
            } else if (startingTable[indexLowest - 1][colBefore].getEnergyH()
                       < startingTable[indexLowest][colBefore].getEnergyH()
                       && startingTable[indexLowest - 1][colBefore].getEnergyH()
                          < startingTable[indexLowest + 1][colBefore].getEnergyH())
            {
                //then up left is smallest
                lowestLeftCol = indexLowest - 1;
            } else
            {
                //then same row is lowest
                lowestLeftCol = indexLowest;
            }
        }
        return lowestLeftCol;
    }

}
