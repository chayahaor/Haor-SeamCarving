import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SeamTest {
    Pixel[][] image;

    @BeforeEach
    void fillImage() {
        image = new Pixel[3][5];
        for (int row = 0; row < 3; row++)
        {
            for (int col = 0; col < 5; col++)
            {
                image[row][col] = new Pixel(0);
            }
        }
        image[0][0].setCellEnergy(1);
        image[0][1].setCellEnergy(4);
        image[0][2].setCellEnergy(3);
        image[0][3].setCellEnergy(5);
        image[0][4].setCellEnergy(2);

        image[1][0].setCellEnergy(3);
        image[1][1].setCellEnergy(2);
        image[1][2].setCellEnergy(5);
        image[1][3].setCellEnergy(2);
        image[1][4].setCellEnergy(3);

        image[2][0].setCellEnergy(5);
        image[2][1].setCellEnergy(2);
        image[2][2].setCellEnergy(4);
        image[2][3].setCellEnergy(2);
        image[2][4].setCellEnergy(1);
    }

    @Test
    void lowestVerticalSeam() {
        //given

        //when
        Seam seam = new Seam();
        seam.calculateLowestVerticalSeam(image);
        //then
        //expected goes bottom up
        assertEquals(new ArrayList<>(Arrays.asList(1, 1, 0)), seam.getLowestVerticalSeam());
    }

    @Test
    void lowestHorizontalSeam() {
        //given

        //when
        Seam seam = new Seam();
        seam.calculateLowestHorizontalSeam(image);
        //then
        //expected goes from right to left
        assertEquals(new ArrayList<>(Arrays.asList(2, 1, 0, 1, 0)), seam.getLowestHorizontalSeam());
    }
}