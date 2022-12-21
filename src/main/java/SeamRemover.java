import java.util.ArrayList;

public class SeamRemover {
    Pixel[][] starting;
    Pixel[][] ending;
    ArrayList<Integer> seamRemoving;

    public SeamRemover(Pixel[][] starting, ArrayList<Integer> toRemove) {
        //TODO: this is for vertical seams only
        this.starting = starting;
        ending = new Pixel[starting.length][starting[0].length];
        seamRemoving = toRemove;
        removeVertical();
    }

    private void removeVertical() {
        int cols = starting[0].length - 2;
        int spot;
        for (int row = 0; row < starting.length; row++)
        {
            spot = seamRemoving.get(row);
            System.arraycopy(starting, 0, ending, 0, spot);
            //TODO: fix so that it actually removes it. Now seems to have 0/1 problem
            System.arraycopy(starting, spot + 1, ending, spot, cols - spot - 1);
            //srcPos has a +1 since skipping the element
            // -1 at end because skipping the element when copying
        }
    }

    public Pixel[][] getEnding() {
        return ending;
    }
}
