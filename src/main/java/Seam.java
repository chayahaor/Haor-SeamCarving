import java.util.ArrayList;

public class Seam {

    private final int widthPixels;
    private final int heightPixels;
    private int[][] startingEnergy;


    public Seam(int[][] energyTable) {
        widthPixels = energyTable[0].length;
        heightPixels = energyTable.length;
        startingEnergy = energyTable;
    }

    private ArrayList<Integer> findSmallestSeam() {
        ArrayList<Integer> smallSeam = new ArrayList<>();
        for (int row = 0; row < heightPixels; row++)
        {
            for (int col = 0; col < widthPixels; col++)
            {
                /*if (row == heightPixels - 1)
                {

                } else if (col == 0)
                {

                } else if (col == widthPixels - 1)
                {

                } else
                {

                }
*/
            }
        }
        return smallSeam;
    }
}
