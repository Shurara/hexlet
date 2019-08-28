import java.util.Arrays;

public class Main {

    public static void main(String... args) throws Exception {
        final int[] test1 = {1, 2, 3};
        final int expectedResult1 = 3;
        final int[] test2 = {4, 5, 6};
        final int expectedResult2 = 6;
        final int[] test3 = {4, -5, 2, 4};
        final int expectedResult3 = 4;
        validateMaxThread(test1, expectedResult1);
        validateMaxThread(test2, expectedResult2);
        validateMaxThread(test3, expectedResult3);
    }

    private static void validateMaxThread(
            final int[] input,
            final int expectedResult) throws Exception {
        final MaxRunnable maxR = new MaxRunnable(input);
        final Thread t = new Thread(maxR);
        t.start();
        t.join();
        final int actualResult = maxR.getResult();
        if (actualResult != expectedResult) {
            throw new RuntimeException(String.format("MaxRunnable found" +
                            "incorrect max for the input: %s," +
                            " expected result: %d, actual" +
                            " result: %d",
                    Arrays.toString(input),
                    expectedResult,
                    actualResult));
        }
    }

}
