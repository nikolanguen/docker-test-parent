package test.parent;

import org.junit.Assert;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import test.annotations.Points;
import test.annotations.test_base.ArraySource;
import test.annotations.test_base.ArraySources;
import test.extensions.CustomTestExtension;

@ExtendWith(CustomTestExtension.class)
public class DivisionTaskTest {

    @Points(value = 2)
    @ParameterizedTest
    @ArraySources(arrays = {
            @ArraySource(array = {15, 3, 5}),
            @ArraySource(array = {20, 5, 4}),
            @ArraySource(array = {355, 5, 71}),
            @ArraySource(array = {25, 5, 5}),
            @ArraySource(array = {67, -1, -67})
    })
    void divisionTest(int[] args) {
        //Arrange
        int expected = args[2];
        //Act
        int actual = DivisionTask.divide(args[0], args[1]);
        //Assert
        Assert.assertEquals(expected, actual);
    }
}