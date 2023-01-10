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

    public Pixel[][] removeHorizontal(Pixel[][] starting, ArrayList<Integer> seamRemoving)
    {
        ending = new Pixel[starting.length - 1][starting[0].length];
        int height = starting.length;
        int width = starting[0].length;
        int spot;
        boolean passedSkipped;
        int indexToRemove = 0;
        for (int col = width - 1; col >= 0; col--)
        {
            passedSkipped = false;
            spot = seamRemoving.get(indexToRemove++);
            for (int row = 0; row < height; row++)
            {
                if (row != spot)
                {
                    if (passedSkipped)
                    {
                        ending[row - 1][col] = starting[row][col];
                    } else
                    {
                        ending[row][col] = starting[row][col];
                    }
                } else
                {
                    passedSkipped = true;
                }
            }
        }


        return ending;
    }

}
