import java.util.ArrayList;

public class SeamRemover
{
    Pixel[][] ending;

    public SeamRemover()
    {

    }

    public Pixel[][] removeVertical(Pixel[][] starting, ArrayList<Integer> seamRemoving)
    {
        ending = new Pixel[starting.length][starting[0].length - 1];

        int cols = starting[0].length - 1;
        int rows = starting.length - 1;
        int spot;
        for (int row = starting.length - 1; row >= 0; row--)
        {
            spot = seamRemoving.get(row);
            System.arraycopy(starting[rows - row], 0, ending[rows - row], 0, spot);
            System.arraycopy(starting[rows - row], spot + 1, ending[rows - row], spot, cols - spot);
        }
        return ending;
    }

    //TODO: check that this works. Write tests
    public Pixel[][] removeHorizontal(Pixel[][] starting, ArrayList<Integer> seamRemoving)
    {
        ending = new Pixel[starting.length - 1][starting[0].length];
        int rows = starting.length - 1;
        int spot;
        for (int col = starting[0].length - 1; col >= 0; col--)
        {
            spot = seamRemoving.get(col);
            System.arraycopy(starting, 0, ending, 0, spot);
            System.arraycopy(starting, spot + 1, ending, spot, rows - spot);
        }
        return ending;
    }

}
