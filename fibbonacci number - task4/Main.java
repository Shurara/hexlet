import java.util.Arrays;

public class Main {

    public static void main(String... args) throws Exception {
        testFib(1, 1);
        testFib(2, 1);
        testFib(3, 2);
        testFib(4, 3);
        testFib(5, 5);
        testFib(6, 8);
    }

    private static void testFib(final int fibNumber, final int expectedResult) throws Exception {
        final int actualResult = ThreadHelper.fib(fibNumber);
        if (actualResult != expectedResult) {
            throw new RuntimeException(
                    String.format("result is wrong for the input: %d, expected: %d, got: %d",
                            fibNumber,
                            expectedResult,
                            actualResult));
        }
    }

}
