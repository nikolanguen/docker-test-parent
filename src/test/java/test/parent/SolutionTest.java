package test.parent;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SolutionTest {

    @Test
    public void multiply_Should_ExecuteWithSuccess() {
        int expected = 15;

        int actual = Solution.multiply(3, 5);

        Assertions.assertEquals(expected, actual);
    }
}