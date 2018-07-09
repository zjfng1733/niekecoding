package niuke.coding.library.zuochengyun;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 如何仅用队列结构实现栈结构?
 * 如何仅用栈结构实现队列结构?
 * 此处会考到关于深度优先搜索(DFS)和宽度优先搜索(BFS)
 * 图的深度优先搜索(DFS):一般采用栈进行解决
 * 图的宽度优先搜索(BFS)：一般采用队列进行解决
 * 一般可能问：如何用队列实现图的深度优先搜索(DFS)，想到的应该是用两个队列来实现栈结构，之后再用栈来进行图的深度优先搜索(DFS)
 */
public class QueueAndStack {
    public static class GetStackByQueue {
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();

        void push(Integer obj) {
            queue1.add(obj);
        }

        Integer pop() {
            int n = queue1.size();
            for (int i = 0; i < n - 1; i++) {
                queue2.add(queue1.poll());
            }
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;
            return queue2.poll();
        }
        //针对双队列变为栈
        boolean isEmpty() {
            if (queue1.isEmpty() && queue2.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }

    }

    public static class GetQueueByStack {
        // 往stack1中加入数据前要保证stack1为空，往stack2中加入数据前要保证stack2为空
        Stack<Integer> stack1 = new Stack<>();//用来装源数据
        Stack<Integer> stack2 = new Stack<>();//用来装从第一个栈弹出的数据

        void push(Integer obj) {
            stack1.push(obj);
        }

        Integer pop() {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            } else {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            }

        }
        //针对双队列变为栈
        boolean isEmpty() {
            if (stack1.isEmpty() && stack2.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
//        GetQueueByStack qs = new GetQueueByStack();
//        qs.push(1);
//        qs.push(2);
//        qs.push(3);
//        qs.push(4);
//        qs.push(5);
//        while (!qs.isEmpty()) {
//            System.out.println(qs.pop());
//        }

        GetStackByQueue sq = new GetStackByQueue();
        sq.push(1);
        sq.push(2);
        sq.push(3);
        sq.push(4);
        sq.push(5);
        while(!sq.isEmpty()){
            System.out.println(sq.pop());
        }


    }
}
