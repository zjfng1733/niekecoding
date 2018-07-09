package niuke.coding.library.zuochengyun;

public class ArrStack {

    /**
     * 用数组结构实现大小固定的栈,栈是实现先进后出
     */
    Integer[] a;
    Integer index;

    public ArrStack(int initSize) {//初始化栈
        if (initSize < 0) {
            throw new IllegalArgumentException("The init size is less than 0");
        }
        a = new Integer[initSize];
        index = 0;
    }

    void push(Integer obj) {//将数据加入栈中
        if (index == a.length) {
            throw new ArrayIndexOutOfBoundsException("The stack is full");
        }
        a[index] = obj;
        index++;
    }

    Integer pop() {//将数据弹出栈中
        if (index == 0) {
            throw new ArrayIndexOutOfBoundsException("The stack is null");
        }
        index--;
        return a[index];
    }

    Integer peek() {//查询栈顶元素
        if (index == 0) {
            throw new ArrayIndexOutOfBoundsException("The stack is null");
        }
        return a[index - 1];
    }
}
