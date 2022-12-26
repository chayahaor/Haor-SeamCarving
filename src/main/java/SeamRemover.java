import java.util.ArrayList;

public class SeamRemover {
    Pixel[][] ending;

    public SeamRemover() {

    }

    public void removeVertical(Pixel[][] starting, ArrayList<Integer> seamRemoving) {
        ending = new Pixel[starting.length][starting[0].length - 1];

        int cols = starting[0].length - 1;
        int spot;
        for (int row = 0; row < starting.length; row++)
        {
            spot = seamRemoving.get(row);
            System.arraycopy(starting[row], 0, ending[row], 0, spot);
            System.arraycopy(starting[row], spot + 1, ending[row], spot, cols - spot);
        }
    }

    public Pixel[][] getEnding() {
        return ending;
    }
}
