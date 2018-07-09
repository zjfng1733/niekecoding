package niuke.coding.library.jianzhiOffer;

import com.sun.scenario.effect.Merge;
import org.omg.CORBA.TRANSACTION_MODE;

import java.util.*;


public class jichuMethod {


    static int counter = 0;//定义一个静态计数变量

    /**
     * 创建一棵二叉树,按层次结构创建二叉树，可以采用递归方式，也可以采用队列的方式
     *
     * @param root 根节点，数组，计数器
     */
    public static TreeNode createBiTree(int[] a) {

        //不采用递归方式
        List<TreeNode> nodeList = new ArrayList<>();
        //将所有节点装入到nodeList数组中
        for (int i = 0; i < a.length; i++) {
            nodeList.add(new TreeNode(a[i]));
        }
        //遍历nodeList，来构建一颗二叉树,把最后一个父节点留出来 ，
        // 左孩子：2n+1,右孩子：2n+2,父节点为n,则 有 n = (2n+2)/2-1,当最后一个叶子节点为m时，那么父节点则为m/2-1
        for (int parentIndex = 0; parentIndex < nodeList.size() / 2 - 1; parentIndex++) {
            //创建左孩子
            nodeList.get(parentIndex).left = nodeList.get(2 * parentIndex + 1);
            //创建右孩子
            nodeList.get(parentIndex).right = nodeList.get(2 * parentIndex + 2);
        }
        //最后一个父节点需要单独拿来处理
        int lastParentIndex = nodeList.size() / 2 - 1;
        nodeList.get(lastParentIndex).left = nodeList.get(lastParentIndex * 2 + 1);
        //长度为基数，就表明最后一个父节点存在右孩子
        if (nodeList.size() % 2 == 1) {
            nodeList.get(lastParentIndex).right = nodeList.get(lastParentIndex * 2 + 2);
        }
        return nodeList.get(0);

    }

    //采用递归的方式创建二叉树
    public static TreeNode createBiTree1(int[] a) {

        //不采用递归方式
        List<TreeNode> nodeList = new ArrayList<>();

        return nodeList.get(0);

    }


    /**
     * 层次遍历，使用队列的方式
     *
     * @param root
     */
    public static void levelTraverse(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);// 从根节点入队列
        while (!queue.isEmpty()) {// 在队列为空前反复迭代
            TreeNode bitNode = queue.poll();// 取出队列首节点
            visitTNode(bitNode);
            if (bitNode.left != null)
                queue.offer(bitNode.left);// 左孩子入列
            if (bitNode.right != null)
                queue.offer(bitNode.right);// 右孩子入列
        }
    }

    /**
     * 使用中序遍历二叉树，包含递归和非递归,左中右
     *
     * @param root
     */
    public static void orderTraverse1(TreeNode root) {
        if (root == null) {
            return;
        }
        orderTraverse1(root.left);
        visitTNode(root);
        orderTraverse1(root.right);
    }

    //采用栈的方式，即非递归方式
    public static void orderTraverse2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                visitTNode(root);
                root = root.right;
            }
        }
    }

    /**
     * 后续遍历二叉树，包含递归和非递归，左右中
     *
     * @param root
     */
    public static void postTraverse1(TreeNode root) {
        if (root == null) {
            return;
        }
        postTraverse1(root.left);
        postTraverse1(root.right);
        visitTNode(root);

    }

    /**
     * 这个需要重点琢磨
     * 后序遍历递归定义：先左子树，后右子树，再根节点。
     * 后序遍历的难点在于：需要判断上次访问的节点是位于左子树，还是右子树。
     * 若是位于左子树，则需跳过根节点，先进入右子树，再回头访问根节点；
     * 若是位于右子树，则直接访问根节点。
     *
     * @param root
     */

    public static void postTraverse2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastVisitNode = null;
        TreeNode curNode = root;
        while (root != null) {
            stack.push(curNode);
            curNode = curNode.left;
        }
        while (!stack.isEmpty()) {
            curNode = stack.peek();
            //如果是包含左右子树的节点，且左子树被访问过，右子树没有被访问过
            if (curNode.left != null && curNode.right != lastVisitNode) {
                curNode = curNode.right;
                while (curNode != null) {
                    stack.push(curNode);
                    curNode = curNode.left;
                }
            } else {
                //左右子树均被访问过
                stack.pop();
                visitTNode(curNode);
                lastVisitNode = curNode;
            }

        }


    }

    /**
     * 前序遍历二叉树，包含递归和非递归，中左右
     *
     * @param root
     */
    public static void preTraverse1(TreeNode root) {
        if (root == null) {
            return;
        }
        visitTNode(root);
        preTraverse1(root.left);
        preTraverse1(root.right);

    }

    public static void preTraverse2(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                visitTNode(root);//在节点放入栈之前就进行访问
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                root = root.right;
            }
        }


    }

    /**
     * 访问节点
     *
     * @param node
     */
    public static void visitTNode(TreeNode node) {

        System.out.print(node.val + " ");
    }

    /**
     * 冒泡排序，从小到大排序，升序排列
     *
     * @param arr
     */
    public static int[] sortBubble(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序，从小到大排序，升序排列
     *
     * @param arr
     */
    public static int[] sortSelect(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 插入排序，从小到大排序，升序排列
     *
     * @param arr
     */
    public static int[] sortInsert(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] < arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                    break;
                }
            }
        }
        return arr;
    }

    /**
     * 归并排序，从小到大排序，升序排列
     *
     * @param arr
     */
    public static int[] sortMerge(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        if (arr.length == 1) {
            return arr;
        }
        //只针对同一个数组进行处理，不要添加其他的数组，处理的过程只是对数组中的某个阶段进行归并排序处理
        sortM(arr, 0, arr.length - 1);
        return arr;

    }

    public static void sortM(int[] arr, int start, int end) {
        if (start < end) {//当start<end的时候满足这一段数据进行归并排序
            int m = (start + end) / 2;
            if (start != m) {//保证start与m不相等时，则是两个数据，此时不需要进行划分，而是直接合并
                sortM(arr, start, m);
                sortM(arr, m, end);
            }
            Merge(arr, start, m, end);
        }
    }

    //合并
    public static void Merge(int[] arr, int start, int m, int end) {
        if (start == m) {//相当于这一个合并中只包含两个数
            if (arr[m] > arr[end]) {
                int temp = arr[m];
                arr[m] = arr[end];
                arr[end] = temp;
            }
        } else {//当这一段路径中包含大于2个数
            //处理merge过程
            for (int i = m; i < end; i++) {
                for (int j = i - 1; j >= start; j--) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;

                    } else {
                        break;
                    }
                }
            }
        }

    }


    /**
     * 堆排序，从小到大排序，升序排列
     *
     * @param arr
     */
    public static int[] sortHeap(int[] arr) {
        //进行升序排列，就需要构造一个大根堆（根节点大于孩子节点的值），然后将根节点与最后一个节点进行互换，
        // 然后再构造一个大根堆，将根节点与倒数第二个值进行互换，依次类推
        if (arr.length == 0) {
            return null;
        }
        if (arr.length == 1) {
            return arr;
        }
        heapSort(arr, 0, arr.length - 1);
        return arr;
    }

    //将大根堆的第一个值与最后一个值进行互换
    public static void heapSort(int[] arr, int start, int end) {
        if (start < end) {
            heapAdjust(arr, start, end);
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            heapSort(arr, start, end - 1);
        }
    }

    //构造大根堆
    public static void heapAdjust(int[] arr, int start, int end) {
        for (int i = end / 2; i >= 0; i--) {
            if (2 * i + 2 <= end) {
                if (arr[i] < arr[2 * i + 2]) {
                    int temp = arr[i];
                    arr[i] = arr[2 * i + 2];
                    arr[2 * i + 2] = temp;
                }
            }
            if (2 * i + 1 <= end) {
                if (arr[i] < arr[2 * i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[2 * i + 1];
                    arr[2 * i + 1] = temp;
                }
            }

        }

    }

    /**
     * 快速排序，从小到大排序，升序排列
     * 分析时间复杂度：partition部分时间复杂度为o(n),Qsort属于二分搜索阶段，其时间复杂度为logn,
     * 故总的时间复杂度为O（）
     *
     * @param arr
     */
    public static int[] sortQuick(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        if (arr.length == 1) {
            return arr;
        }
        Qsort(arr, 0, arr.length - 1);
        return arr;
    }

    public static void Qsort(int[] arr, int start, int end) {
        if (start < end) {
            int p = Partition(arr, start, end);//代表的是keyValue的位置
            Qsort(arr, start, p - 1);
            Qsort(arr, p + 1, end);
        }
    }

    //找出一个p值得位置，使得位于他前面的数均小于他，位于他后面的数均大于他
    public static int Partition(int[] arr, int start, int end) {
        int keyValue = arr[start];
        while (start < end) {
            while (start < end && keyValue <= arr[end]) {
                end--;
            }
            //进行交换
            arr[start] = arr[end];
            while (start < end && keyValue >= arr[start]) {
                start++;
            }
            arr[end] = arr[start];
        }
        arr[start] = keyValue;
        return start;
    }

    /**
     * 希尔排序，从小到大排序，升序排列
     *
     * @param arr
     */
    public static void sortShell(int[] arr) {


    }

    public static void main(String[] args) {
        //TreeNode1 root = new TreeNode1();
        //int[] a = { 1, 2, 3, 0, 0, 4, 0, 0, 5, 0, 0 };
        int[] a = {364, 637, 341, 406, 747, 995, 234, 971, 571, 219, 993, 407, 416, 366, 315, 301, 601, 650, 418, 355, 460, 505, 360, 965, 516, 648, 727, 667, 465, 849, 455, 181, 486, 149, 588, 233, 144, 174, 557, 67, 746, 550, 474, 162, 268, 142, 463, 221, 882, 576, 604, 739, 288, 569, 256, 936, 275, 401, 497, 82, 935, 983, 583, 523, 697, 478, 147, 795, 380, 973, 958, 115, 773, 870, 259, 655, 446,
                863, 735, 784, 3, 671, 433, 630, 425, 930, 64, 266, 235, 187, 284, 665, 874, 80, 45, 848, 38, 811, 267, 575};
        jichuMethod jm = new jichuMethod();
        jm.sortMerge(a);
        //jm.sortMerge(a);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }

    }

}

