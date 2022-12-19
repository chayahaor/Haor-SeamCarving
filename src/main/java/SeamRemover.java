import java.util.ArrayList;

public class SeamRemover {
    Pixel[][] starting;
    Pixel[][] ending;
    ArrayList<Integer> seamRemoving;

    public SeamRemover(Pixel[][] starting, ArrayList<Integer> toRemove) {
        this.starting = starting;
        ending = new Pixel[starting.length][starting[0].length];
        seamRemoving = toRemove;
    }

    public void removeVertical() {
        int cols = starting[0].length;
        int spot;
        for (int row = 0; row < starting.length; row++)
        {
            spot = seamRemoving.get(row);
            System.arraycopy(starting, 0, ending, 0, spot);
            System.arraycopy(starting, seamRemoving.get(row), ending, ending.length, cols - spot);
        }
    }


}
