package test.parent;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class SolutionTest {

    @Test
    public void multiply_Should_ExecuteWithSuccess() {
        int expected = 15;

        int actual = Solution.multiply(3, 5);

        Assert.assertEquals(expected, actual);
    }
}