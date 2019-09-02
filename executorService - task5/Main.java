public class Main {

    public static void main(String... args) throws Exception {
        final Thread testThread = new TesterThread();
        testThread.start();
        Thread.sleep(3_000);
        if (testThread.isAlive()) {
            ThreadHelper.EXECUTOR_SERVICE.shutdownNow();
            testThread.interrupt();
            throw new RuntimeException("Test was not completed in 3 sec, possible dead lock");
        }
        ThreadHelper.EXECUTOR_SERVICE.shutdown();
    }

    private static class TesterThread extends Thread {

        @Override
        public void run() {
            try {
                testFib(1, 1);
                testFib(2, 1);
                testFib(3, 2);
                testFib(4, 3);
                testFib(5, 5);
                testFib(6, 8);
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
        }
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