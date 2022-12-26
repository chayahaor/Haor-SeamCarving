import java.io.IOException;

public class Driver {

    public static void main(String[] args) throws IOException {
        Image image = new Image("/Seam.jpg");
        Energy energy = new Energy();
        energy.updateCellEnergy(image.getPixelMatrix());
        Seam seam = new Seam();
        seam.calculateLowestVerticalSeam(image.getPixelMatrix());
        SeamRemover seamRemover = new SeamRemover();
        seamRemover.removeVertical(image.getPixelMatrix(), seam.getLowestVerticalSeam());
        //Then repeat until number of seams to remove have been removed
    }
}
