import java.util.stream.*;
// BEGIN (write your solution here)
public class SumThread extends Thread{
    private final int []toSum;
    private int result = 0;
    SumThread(final int[] toSum){
        this.toSum = toSum;
    }
    public int getResult(){
      return result;
    }

@Override
    public void run() {
       Thread myThread = new SumThread(toSum);
       for(int i : toSum){
            result+=i;
        }
    }
}

// END
