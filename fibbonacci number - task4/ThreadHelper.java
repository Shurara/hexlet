public class ThreadHelper {

    public static int fib(final int numberToCalculate) throws Exception {
        final FibCalculator calculator = new FibCalculator(numberToCalculate);
        calculator.start();
        calculator.join();
        return calculator.getResult();
    }

    private static class FibCalculator extends Thread {
        // BEGIN (write your solution here)
        private final int numberToCalculate;
        private int result  = 0;
        public FibCalculator(final int numberToCalculate){
            this.numberToCalculate = numberToCalculate;
        }
        public int getResult(){
            return result;
        }

        int fibo(int n){
            if (n == 1 || n == 2) {
                return 1;
            } else {
                return fibo(n - 1) + fibo(n - 2);
            }
        }
        @Override
        public void run(){
            result = fibo(numberToCalculate);
        }


        // END
    }

}

/*public class ThreadHelper {

    public static int fib(final int numberToCalculate) throws Exception {
        final FibCalculator calculator = new FibCalculator(numberToCalculate);
        calculator.start();
        calculator.join();
        return calculator.getResult();
    }

    private static class FibCalculator extends Thread {
        // BEGIN
        private final int currentNum;

        private int result;

        public FibCalculator(final int numberToCalculate) {
            this.currentNum = numberToCalculate;
        }

        @Override
        public void run() {
            if (currentNum == 1 || currentNum == 2) {
                result = 1;
                return;
            }
            final FibCalculator left = new FibCalculator(currentNum - 1);
            final FibCalculator right = new FibCalculator(currentNum - 2);
            left.start();
            right.start();
            try {
                left.join();
                right.join();
            } catch (final InterruptedException e) {}
            result = left.getResult() + right.getResult();
        }

        public int getResult() {
            return result;
        }
        // END
    }

}*/
