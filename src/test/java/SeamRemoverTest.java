import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class SeamRemoverTest {

    @Test
    void removeVerticalSeam() {
        //given
        Pixel[][] image = new Pixel[3][5];
        Pixel pixel00 = mock(Pixel.class);
        doReturn(1.0).when(pixel00).getCellEnergy();
        image[0][0] = pixel00;
        Pixel pixel01 = mock(Pixel.class);
        doReturn(1.0).when(pixel01).getCellEnergy();
        image[0][1] = pixel01;
        Pixel pixel02 = mock(Pixel.class);
        doReturn(1.0).when(pixel02).getCellEnergy();
        image[0][2] = pixel02;
        Pixel pixel03 = mock(Pixel.class);
        doReturn(1.0).when(pixel03).getCellEnergy();
        image[0][3] = pixel03;
        Pixel pixel04 = mock(Pixel.class);
        doReturn(1.0).when(pixel04).getCellEnergy();
        image[0][4] = pixel04;
        Pixel pixel10 = mock(Pixel.class);
        doReturn(1.0).when(pixel10).getCellEnergy();
        image[1][0] = pixel10;
        Pixel pixel11 = mock(Pixel.class);
        doReturn(1.0).when(pixel11).getCellEnergy();
        image[1][1] = pixel11;
        Pixel pixel12 = mock(Pixel.class);
        doReturn(1.0).when(pixel12).getCellEnergy();
        image[1][2] = pixel12;
        Pixel pixel13 = mock(Pixel.class);
        doReturn(1.0).when(pixel13).getCellEnergy();
        image[1][3] = pixel13;
        Pixel pixel14 = mock(Pixel.class);
        doReturn(1.0).when(pixel14).getCellEnergy();
        image[1][4] = pixel14;
        Pixel pixel20 = mock(Pixel.class);
        doReturn(1.0).when(pixel20).getCellEnergy();
        image[2][0] = pixel20;
        Pixel pixel21 = mock(Pixel.class);
        doReturn(1.0).when(pixel21).getCellEnergy();
        image[2][1] = pixel21;
        Pixel pixel22 = mock(Pixel.class);
        doReturn(1.0).when(pixel22).getCellEnergy();
        image[2][2] = pixel22;
        Pixel pixel23 = mock(Pixel.class);
        doReturn(1.0).when(pixel23).getCellEnergy();
        image[2][3] = pixel23;
        Pixel pixel24 = mock(Pixel.class);
        doReturn(1.0).when(pixel24).getCellEnergy();
        image[2][4] = pixel24;

        //when

        //then
        Pixel[][] ending = new Pixel[3][4];
        ending[0][0] = pixel01;
        ending[0][1] = pixel02;
        ending[0][2] = pixel03;
        ending[0][3] = pixel04;

        ending[1][0] = pixel10;
        ending[1][1] = pixel12;
        ending[1][2] = pixel13;
        ending[1][3] = pixel14;

        ending[2][0] = pixel20;
        ending[2][1] = pixel22;
        ending[2][2] = pixel23;
        ending[2][3] = pixel24;

        ArrayList<Integer> toRemove = new ArrayList<>(Arrays.asList(0, 1, 1));
        SeamRemover seamRemover = new SeamRemover();
        seamRemover.removeVertical(image, toRemove);
        assertTrue(Arrays.deepEquals(ending, seamRemover.getEnding()));
    }
}