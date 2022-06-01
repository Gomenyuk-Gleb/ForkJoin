import java.lang.reflect.Array;
import java.util.Objects;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        var arr = generate(120_000);
        var task = new Task(arr);
        Objects.requireNonNull(arr);
        var sortedArray = ForkJoinPool.commonPool().invoke(task);

        print(sortedArray);
    }

    static Integer[] generate(int size) {
        return IntStream.generate(() -> ThreadLocalRandom.current().nextInt(120_000_00 * 25))
                .limit(size)
                .boxed()
                .toArray(Integer[]::new);
    }

    static void print(Integer[] arr){
        if (arr.length > 0) {
            System.out.print(arr[0]);
            for (int i = 1; i < arr.length - 1; i++) {
                System.out.print(String.format(" -> %s", arr[i]));
            }
        }
        if (arr.length > 1) {
            System.out.print(arr[arr.length - 1]);
        }
    }
}
