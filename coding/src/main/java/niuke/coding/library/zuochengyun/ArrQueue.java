package niuke.coding.library.zuochengyun;

/**
 * /**
 * 用数组结构实现大小固定的队列,队列是先进先出
 */
public class ArrQueue {
    Integer[] arr;
    Integer start;//用来数据出队列的时候用的
    Integer end;//用来数据进队列的时候用的
    Integer size;//表示队列空间的大小，也代表数组的长度

    public ArrQueue(Integer initSize) {//初始化队列
        if (initSize < 0) {
            throw new IllegalArgumentException("The init size is less than 0");
        }
        arr = new Integer[initSize];
        size = 0;//用来表示队列的长度
        start = 0;
        end = 0;
    }

    void push(Integer obj) {
        if (size == arr.length) {
            throw new ArrayIndexOutOfBoundsException("The queue is full");
        }
        size++;
        arr[end] = obj;
        end = end == arr.length - 1 ? 0 : end + 1;//判断end是否指向arr.length-1,如果是则下一个指向0，否则指向end+1

    }

    Integer poll() {
        if (size == 0) {//表明队列中并没有元素，此时，无法出数据
            throw new ArrayIndexOutOfBoundsException("The queue is null");
        }
        size--;
        int temp = start;
        start = start == arr.length - 1 ? 0 : start + 1;
        return arr[temp];
    }

    Integer peek() {
        if (size == 0) {
            return null;
        }
        return arr[start];
    }

}
