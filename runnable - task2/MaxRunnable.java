import java.util.stream.*;

// BEGIN (write your solution here)
public class MaxRunnable implements Runnable{
    private final int[] target;
    private int result = 0;

    MaxRunnable(final int[] target){
        this.target = target;
    }

    public int getResult(){
        return result;
    }

    @Override
    public void run() {
        for (int i : target){
            if (i > result){
                result = i;
            }
        }
    }
}
// END
