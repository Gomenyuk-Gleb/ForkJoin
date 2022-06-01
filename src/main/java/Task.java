import java.util.Arrays;
import java.util.concurrent.RecursiveTask;

public class Task extends RecursiveTask<Integer[]> {

    public Integer[] headArr;

    public Task(Integer[] headArr) {
        this.headArr = headArr;
    }

    @Override
    protected Integer[] compute() {
        if(headArr.length <= 1) {
            return headArr;
        }

        var leftArr = Arrays.copyOfRange(headArr, 0, headArr.length / 2);
        var rightArr = Arrays.copyOfRange(headArr, headArr.length / 2, headArr.length);
        var taskLeft = new Task(leftArr);
        var taskRight = new Task(rightArr);
        var leftTask = taskLeft.fork();
        taskRight.compute();
        leftTask.join();
        sort(headArr, rightArr, leftArr);
        return headArr;
    }

    void sort(Integer[] headArr, Integer[] left, Integer[] right) {
        var leftArrSize = left.length;
        var rightArrSize = right.length;
        var leftIndex = 0;
        var rightIndex = 0;
        var headArrIndex = 0;
        for (;leftIndex < leftArrSize
                && rightIndex < rightArrSize;) {
            if (left[leftIndex] < right[rightIndex]) {
                headArr[headArrIndex++] = left[leftIndex++];
            } else {
                headArr[headArrIndex++] = right[rightIndex++];
            }
        }
        System.arraycopy(left, leftIndex, headArr, headArrIndex, left.length - leftIndex);
        System.arraycopy(right, rightIndex, headArr, headArrIndex, right.length - rightIndex);
    }
}
