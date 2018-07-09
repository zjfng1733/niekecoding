package niuke.coding.library.zuochengyun;

import java.util.Stack;

/**
 * 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返
 回栈中最小元素的操作。
 【要求】:
 1.pop、push、getMin操作的时间复杂度都是O(1)。
 2.设计的栈类型可以使用现成的栈结构。
 */
public class GetStackMin {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> stackMin = new Stack<>();
    //两个栈同时push数据，且保持stackMin的栈顶元素为本栈中的最小值
    void push(int obj){
        stack.push(obj);
        if (!stackMin.isEmpty()){
                stackMin.push(stackMin.peek()>obj?obj:stackMin.peek());
        }
    }
    Integer pop(){
        return stack.pop();
    }
    Integer getMin(){
        return stackMin.pop();
    }
}
