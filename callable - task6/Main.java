import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.Callable;

public class Main {

    public static void main(String... args) {
        testArray(new Integer[0], 0);
        testArray(new Integer[]{1, 4, 10, 2}, 10);
        testArray(new Integer[]{1, 4, -10, 2}, 4);
        testArray(new Integer[]{1}, 1);
    }

    private static void testArray(final Integer[] inputArray, final int expectedResult) {
        final int actualResult = new MaxFinder(inputArray).call();
        if (actualResult != expectedResult) {
            throw new RuntimeException(
                    String.format(
                            "Actual max: %d, expected max: %d",
                            actualResult,
                            expectedResult));
        }
    }

    // BEGIN
    private static class MaxFinder implements Callable<Integer> {

        private final Integer[] array;

        MaxFinder(final Integer[] inputArray) {
            this.array = inputArray;
        }

        @Override
        public Integer call() {
            if (array.length == 0) {
                return 0;
            }
            return Collections.max(Arrays.asList(array));
        }
    }
// END