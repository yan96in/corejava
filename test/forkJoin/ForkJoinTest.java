package forkJoin;

import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * Created by yan96in on 2017/10/24.
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        final int SISE=10000000;
        double[] numbers=new double[SISE];
        for (int i = 0; i < SISE; i++) {
            numbers[i]=Math.random();
        }
    }


}

class Counter extends RecursiveTask<Integer>{
    public static final int THRESHOLD=1000;
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate filter;

    public Counter(double[] values, int from, int to, DoublePredicate filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    @Override
    protected Integer compute() {
        if(to-from<THRESHOLD){
            int count=0;
            for (int i = from; i < to; i++) {
                if(filter.test(values[i]))count++;
            }
            return count;
        }else {
            int mid=(from+to)/2;
            Counter first=new Counter(values,from,mid,filter);
            Counter second=new Counter(values,mid,to,filter);
            /**
             * Forks the given tasks, returning when {@code isDone} holds for
             * each task or an (unchecked) exception is encountered, in which
             * case the exception is rethrown. If more than one task
             * encounters an exception, then this method throws any one of
             * these exceptions. If any task encounters an exception, the
             * other may be cancelled. However, the execution status of
             * individual tasks is not guaranteed upon exceptional return. The
             * status of each task may be obtained using {@link
             * #getException()} and related methods to check if they have been
             * cancelled, completed normally or exceptionally, or left
             * unprocessed.
             *
             * @param t1 the first task
             * @param t2 the second task
             * @throws NullPointerException if any task is null
             */
            invokeAll(first,second);
            return first.join()+second.join();
        }
    }
}
