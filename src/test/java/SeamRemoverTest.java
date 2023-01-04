import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class SeamRemoverTest
{

    @Test
    void removeVerticalSeam()
    {
        //given
        Pixel pixel00 = mock(Pixel.class);
        doReturn(1.0).when(pixel00).getCellEnergy();
        Pixel pixel01 = mock(Pixel.class);
        doReturn(4.0).when(pixel01).getCellEnergy();
        Pixel pixel02 = mock(Pixel.class);
        doReturn(3.0).when(pixel02).getCellEnergy();
        Pixel pixel03 = mock(Pixel.class);
        doReturn(5.0).when(pixel03).getCellEnergy();
        Pixel pixel04 = mock(Pixel.class);
        doReturn(2.0).when(pixel04).getCellEnergy();
        Pixel pixel10 = mock(Pixel.class);
        doReturn(3.0).when(pixel10).getCellEnergy();
        Pixel pixel11 = mock(Pixel.class);
        doReturn(2.0).when(pixel11).getCellEnergy();
        Pixel pixel12 = mock(Pixel.class);
        doReturn(5.0).when(pixel12).getCellEnergy();
        Pixel pixel13 = mock(Pixel.class);
        doReturn(12.0).when(pixel13).getCellEnergy();
        Pixel pixel14 = mock(Pixel.class);
        doReturn(3.0).when(pixel14).getCellEnergy();
        Pixel pixel20 = mock(Pixel.class);
        doReturn(5.0).when(pixel20).getCellEnergy();
        Pixel pixel21 = mock(Pixel.class);
        doReturn(2.0).when(pixel21).getCellEnergy();
        Pixel pixel22 = mock(Pixel.class);
        doReturn(4.0).when(pixel22).getCellEnergy();
        Pixel pixel23 = mock(Pixel.class);
        doReturn(2.0).when(pixel23).getCellEnergy();
        Pixel pixel24 = mock(Pixel.class);
        doReturn(1.0).when(pixel24).getCellEnergy();

        Pixel[][] image = new Pixel[][]{
                {pixel00, pixel01, pixel02, pixel03, pixel04},
                {pixel10, pixel11, pixel12, pixel13, pixel14},
                {pixel20, pixel21, pixel22, pixel23, pixel24}
        };

        //when

        //then
        Pixel[][] ending = new Pixel[][]{
                {pixel01, pixel02, pixel03, pixel04},
                {pixel10, pixel12, pixel13, pixel14},
                {pixel20, pixel22, pixel23, pixel24}
        };
        ArrayList<Integer> toRemove = new ArrayList<>(Arrays.asList(1, 1, 0));
        SeamRemover seamRemover = new SeamRemover();
        assertTrue(Arrays.deepEquals(ending, seamRemover.removeVertical(image, toRemove)));
    }

    @Test
    void removeHorizontalSeam(){

    }
}