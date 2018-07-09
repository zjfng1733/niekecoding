package niuke.coding.library.jianzhiOffer;

import jdk.management.resource.internal.inst.FileOutputStreamRMHooks;

import java.util.*;

import static niuke.coding.library.jianzhiOffer.jichuMethod.createBiTree;


public class Solution {

    /*//第一题
    在一个二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
    请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public boolean Find(int target, int[][] array) {
        int n = array.length;//代表数组有n行
        int m = array[0].length;//代表数组有m列

        for (int i = 0; i < n && i < m; i++) {
            if (target == array[i][i]) {
                return true;
            } else if (target > array[i][i]) {
                continue;
            } else {
                //比较array[i-1][i]和array[i][i-1]
                for (int j = 0; j < i; j++) {
                    if (target == array[j][i] || target == array[i][j]) {
                        return true;
                    }
                }
            }
        }
        if (n > m) {
            for (int k = m; k < n; k++) {
                for (int j = 0; j < m; j++) {
                    if (target == array[k][j]) {
                        return true;
                    }
                }
            }
        } else if (n < m) {
            for (int k = 0; k < n; k++) {
                for (int j = n; j < m; j++) {
                    if (target == array[k][j]) {
                        return true;
                    }
                }
            }
        } else {
            return false;
        }
        return false;
    }

    //第二题
    /*
    请实现一个函数，将一个字符串中的空格替换成“%20”。
    例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     */
    public String replaceSpace(StringBuffer str) {

        String[] strs = str.toString().split("");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].equals(" ")) {
                sb.append("%20");
            } else {
                sb.append(strs[i]);
            }
        }
        return sb.toString();
    }

    //第三题
    /*
    输入一个链表，从尾到头打印链表每个节点的值。
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> ai = new ArrayList<>();
        while (listNode != null) {
            ai.add(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> aii = new ArrayList<>();
        for (int i = ai.size() - 1; i >= 0; i--) {
            aii.add(ai.get(i));
        }
        return aii;
    }

    //第四题
    /*
    输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
    则重建二叉树并返回。
    针对根的存放顺序来决定
    先序遍历：中左右
    中序遍历：左中右
    后续遍历：左右中
     */

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0)
            return null;
        TreeNode node = new TreeNode(pre[0]);
        for (int i = 0; i < pre.length; i++) {
            if (pre[0] == in[i]) {
                //Arrays.copyOfRange  表示数组复制，左闭右开
                node.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                node.right = reConstructBinaryTree(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return node;
    }

    //第五题
// 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);//实现队列的push操作

    }

    public int pop() {
        int result;
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
            result = stack2.pop();
        } else {
            result = stack2.pop();
        }
        return result;
    }

    //第六题，把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
    // 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
    public int minNumberInRotateArray(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[i]) {
                    int temp = array[j];
                    array[j] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array[0];

    }

    //第七题：大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。n<=39
    public int Fibonacci(int n) {
        int[] F = new int[n + 1];
        if (n == 0) {
            return 0;
        }
        F[0] = 0;
        F[1] = 1;
        for (int i = 2; i < F.length; i++) {
            F[i] = F[i - 1] + F[i - 2];
        }
        return F[n];
    }

    //第8题：一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
    //1  1
    // 2  2
    // 3  3
    // 4  5
    // 5  8
    // 6  13
    public int JumpFloor(int target) {
        if (target == 0) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int[] array = new int[target + 1];
        array[0] = 0;
        array[1] = 1;
        array[2] = 2;
        array[3] = 3;
        for (int i = 4; i < array.length; i++) {
            int x1 = array[i - 2] - array[i - 3];
            int x2 = array[i - 1] - array[i - 2];
            array[i] = array[i - 1] + x1 + x2;//等于上一个值加上斐波那契数列
        }
        return array[target];
    }

    //第九题：一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
    public int JumpFloorII(int target) {
        if (target == 0) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        if (target == 2) {
            return 2;
        }
        int[] array = new int[target + 1];
        array[0] = 0;
        array[1] = 1;
        array[2] = 2;
        array[3] = 4;
        for (int i = 4; i < array.length; i++) {
            array[i] = array[i - 1] + (int) Math.pow(2, i - 2); //等于上一个值加上2的i-2次方
        }
        return array[target];

    }

    //第10题：我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
    //实际上是解一个斐波那契数列的修改版，需要通过分析可得
    public int RectCover(int target) {
        int[] F = new int[target + 1];
        if (target == 0) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        F[0] = 0;
        F[1] = 1;
        F[2] = 2;
        for (int i = 3; i < F.length; i++) {
            F[i] = F[i - 1] + F[i - 2];
        }
        return F[target];
    }

    //第11题：输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
    //解题思路：将10进制转换为二进制
    //补码=反+1;
    //此处可以用到：补码+原码=int型的2的32次方，因为int型占4个字节，一个字节8位，也就是占32位，故补码加原码的和为2的32次方
    public int NumberOf1(int n) {
        StringBuilder sb = new StringBuilder();
        long nn = 0;
        //求正数的原码
        if (n < 0) {//针对负数，求其原码的补码，其补码=原码的反码+1
            nn = (long) Math.pow(2, 32) + n;
        } else {
            nn = n;
        }
        while (nn > 0) {
            if (nn % 2 == 1) {
                nn = nn / 2;
                sb.append(1);
            } else {
                nn = nn / 2;
                sb.append(0);
            }
        }
        int num = 0;
        String[] ss = sb.toString().split("");
        StringBuilder sb1 = new StringBuilder();
        for (int i = ss.length - 1; i >= 0; i--) {
            sb1.append(ss[i]);
            if (ss[i].equals("1")) {
                num++;
            }
        }
        return num;
    }

    //第12题：给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
    public double Power(double base, int exponent) {
        double ss = Math.pow(base, exponent);
        return ss;
    }

    //第13题：输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
    // 所有的偶数位于位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变。
    public void reOrderArray(int[] array) {
        int[] flag = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {//表示偶数
                flag[i] = 1;
            } else {//表示基数
                flag[i] = 0;
            }
        }
        int[] array_new = new int[array.length];
        int k = 0;
        for (int i = 0; i < array.length; i++) {
            if (flag[i] == 0) {
                array_new[k] = array[i];
                k++;
            }
        }
        for (int i = 0; i < array.length; i++) {
            if (flag[i] == 1) {

                array_new[k] = array[i];
                k++;
            }
        }
        for (int i = 0; i < array_new.length; i++) {
            array[i] = array_new[i];
        }
    }

    //第14题：输入一个链表，输出该链表中倒数第k个结点。
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        int sum = 1;
        ListNode l = head;
        while (head.next != null) {
            head = head.next;
            sum = sum + 1;
        }//得到最后一个节点，跳出循环时，head属于最后一个节点,sum表示有多少个节点
        if (sum < k) {
            return null;
        }
        for (int i = 1; i < sum - k + 1; i++) {  //循环到第sum-k个节点，我们需要返回的是sum-k+1个节点，故执行下列语句可以得到
            l = l.next;
        }
        return l;
    }

    //第15题：输入一个链表，反转链表后，输出链表的所有元素。
    public ListNode ReverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode ln = head;
        int sum = 1;
        while (head.next != null) {
            head = head.next;
            sum++;
        }//得到链表的最后一个节点
        int[] sb = new int[sum];
        //将节点中的值存储在一个数组中
        for (int i = 0; i < sum; i++) {
            sb[i] = ln.val;
            ln = ln.next;
        }
        //将数组中的值逆向赋值给一个新的链表
        ListNode no = new ListNode(sb[sb.length - 1]);
        if (sb.length == 1) {
            return no;
        }
        ListNode sk = no;//当后文no的节点被赋予值的时候，sk节点的后文也被赋予值，只不过指向的是首节点
        for (int i = sb.length - 2; i >= 0; i--) {
            no.next = new ListNode(sb[i]);
            no = no.next;
        }
        no.next = null;//此处no指向的是尾节点
        return sk;
    }

    //**第16题：输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode head;
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val < list2.val) {
            head = list1;
            head.next = Merge(list1.next, list2);
        } else {
            head = list2;
            head.next = Merge(list1, list2.next);
        }
        return head;
    }

    //第17题：输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return false;
        }
        if (root2 == null) {
            return false;
        }
        return isSubTree(root1, root2) || isSubTree(root1.left, root2) || isSubTree(root1.right, root2);
    }

    public boolean isSubTree(TreeNode root1, TreeNode root2) {
        if (root2 == null) {
            return true;
        }
        //上下两个顺序不能互换
        if (root1 == null) {
            return false;
        }

        if (root1.val == root2.val) {
            return isSubTree(root1.left, root2.left) && isSubTree(root1.right, root2.right);
        } else {
            return false;
        }
    }

    //第19题：输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，
    // 例如，如果输入如下矩阵： 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16
    // 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        int rows = matrix.length;
        int cols = matrix[0].length;

        int start = 0;
        while (rows > start * 2 && cols > start * 2) {
            PrintMatrix1(matrix, cols, rows, start, list);
            start++;
        }

        return list;
    }

    public void PrintMatrix1(int[][] matrix, int cols, int rows, int start, ArrayList<Integer> list) {
        int endX = cols - 1 - start;//最后一列，代表横坐标
        int endY = rows - 1 - start;//最后一行，代表纵坐标
        int[][] flag = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flag[i][j] = 0;
            }
        }
        //从左向右遍历
        if (start <= endX) {
            for (int i = start; i <= endX; i++) {
                if (flag[start][i] == 0) {
                    list.add(matrix[start][i]);
                    flag[start][i] = 1;
                }

            }
        }

        //从上到下遍历
        if (start < endY) {
            for (int i = start; i < endY; i++) {
                if (flag[i][endX] == 0) {
                    list.add(matrix[i][endX]);
                    flag[i][endX] = 1;
                }

            }
        }

        //从右到左遍历
        if (endX > start) {
            for (int i = endX; i > start; i--) {
                if (flag[endY][i] == 0) {
                    list.add(matrix[endY][i]);
                    flag[endY][i] = 1;
                }

            }
        }

        //从下向上遍历
        if (endY > start) {
            for (int i = endY; i > start; i--) {
                if (flag[i][start] == 0) {
                    list.add(matrix[i][start]);
                    flag[i][start] = 1;
                }

            }
        }

    }

    //第20题：定义栈的数据结构，请在该类型中实现一个能够得到栈最小元素的min函数。
    //peek()方法 查看栈顶对象而不移除它
    Stack<Integer> sk = new Stack<Integer>();
    Stack<Integer> sk_min = new Stack<Integer>();
    Integer temp = null;

    public void push1(int node) {

        sk.push(node);
        if (sk_min.isEmpty()) {
            sk_min.push(node);
            temp = node;
        } else {
            int sk = sk_min.peek();
            if (sk <= node) {//始终保持最小元素处于栈顶
                sk_min.push(sk);
            } else {
                sk_min.push(node);
            }
        }

    }

    public void pop1() {//表示弹出栈顶元素
        sk.pop();
        sk_min.pop();
    }

    public int top() {//表示取栈顶元素，但不弹出
        return sk.peek();
    }

    public int min() {
        return sk_min.peek();
    }

    //第21题：输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
    // 假设压入栈的所有数字均不相等。
    // 例如序列1,2,3,4,5是某栈的压入顺序，序列4，5,3,2,1是该压栈序列对应的一个弹出序列，
    // 但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
    Stack<Integer> stack = new Stack<>();

    public boolean IsPopOrder(int[] pushA, int[] popA) {
        if (pushA.length == 0 || popA.length == 0)
            return false;
        Stack<Integer> s = new Stack<Integer>();
        //用于标识弹出序列的位置
        int popIndex = 0;
        for (int i = 0; i < pushA.length; i++) {
            s.push(pushA[i]);
            //如果栈不为空，且栈顶元素等于弹出序列
            while (!s.empty() && s.peek() == popA[popIndex]) {
                //出栈
                s.pop();
                //弹出序列向后一位
                popIndex++;
            }
        }
        return s.empty();


    }

    //第22题：从上往下打印出二叉树的每个节点，同层节点从左至右打印。
    //属于层次遍历,使用队列的思想，以后层次遍历均需要使用队列的思想,同理，广度优先搜索也可以使用队列的思想来进行解决，
    // 对于深度优先搜索应该是可以使用栈的思想来解决，后期遇到的话可以考虑试试看
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode tn = queue.poll();
            try {
                list.add(tn.val);//此处可能会抛出异常，即有节点，但节点上的值没有，若抛出异常则需要停止，
            } catch (Exception e) {
                break;
            }
            if (tn.left != null) {
                TreeNode left = tn.left;
                queue.add(left);
            }
            if (tn.right != null) {
                TreeNode rigth = tn.right;
                queue.add(rigth);
            }
        }
        return list;

    }

    //第23题：输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
    //二叉查找树（Binary Search Tree），（又：二叉搜索树，二叉排序树）它或者是一棵空树，或者是具有下列性质的二叉树：
    // 若它的左子树不空，则左子树上所有结点的值均小于它的根结点的值； 若它的右子树不空，则右子树上所有结点的值均大于它的根结点的值； 它的左、右子树也分别为二叉排序树。
    //后序遍历：左右根
    //解题思路：最后一个节点值作为value,将数组前面的值分别与其进行比较，大的放在大的集合中，小的放在小的集合中，又分别构造两个数组，进行递归再次比较
    public boolean VerifySquenceOfBST(int[] sequence) {
        int len = sequence.length;
        if (len == 0) {
            return false;
        }
        if (len == 1 || len == 2) {
            return true;
        }
        int value = sequence[len - 1];
        List<Integer> listMax = new ArrayList<>();
        List<Integer> listMin = new ArrayList<>();
        int index = 0;
        while (sequence[index] < value) {
            listMin.add(sequence[index]);
            index++;
        }
        while (sequence[index] > value) {
            listMax.add(sequence[index]);
            index++;
        }
        if (listMax.size() + listMin.size() == len - 1) {
            int[] left = new int[listMin.size()];
            int[] right = new int[listMax.size()];
            for (int i = 0; i < listMin.size(); i++) {
                left[i] = listMin.get(i);
            }
            for (int i = 0; i < listMax.size(); i++) {
                right[i] = listMax.get(i);
            }
            if (listMax.size() > 0 && listMin.size() > 0) {
                return VerifySquenceOfBST(left) && VerifySquenceOfBST(right);
            }
            if (listMax.size() == 0) {
                return VerifySquenceOfBST(left);
            }
            if (listMin.size() == 0) {
                return VerifySquenceOfBST(right);
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * 第24题：输入一颗二叉树和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
     * 路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
     * 解析：有多少个叶子节点就有多少条路径，从这些路径中选择节点值得和与target相同的路径，构成一个list数组
     * 解决思路：1，将每条路径转变为一个list数组，
     * 2,将每个list数组作为一个元素存储在一个大的List数组中；
     * 3,遍历大的List数组中的元素，删除元素不为target的小的list
     * 根据题意，显然是深度搜索DFS，DFS问题，要么用递归，要么用栈进行解决，此处我们先用栈进行解决来试一下
     */


    static ArrayList<ArrayList<Integer>> listAll = new ArrayList<ArrayList<Integer>>();
    static ArrayList<Integer> list = new ArrayList<Integer>();

    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if (root == null) return listAll;
        list.add(root.val);
        target -= root.val;
        if (target == 0 && root.left == null && root.right == null) {
            listAll.add(new ArrayList<>(list));//这里一定要new一下，不然加入的只是引用的地址
        }
        if (target < 0) {
            list.remove(list.size() - 1);
            return listAll;
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        list.remove(list.size() - 1);
        return listAll;
    }

    public ArrayList<ArrayList<Integer>> FindPath1(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        ArrayList<ArrayList<Integer>> listMax = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        Stack<TreeNode> st1 = new Stack<>();
        st.add(root);
        ArrayList<Integer> listBasic = new ArrayList<>();
        int targetValue = root.val;
        listBasic.add(root.val);
        TreeNode parent = root;
        //需要记录一个父节点的位置
        while (!st.isEmpty()) {
            TreeNode treeNode = st.peek();//查询该节点的状况
            if ((treeNode.left == null && treeNode.right == null) && !st1.contains(treeNode)) {//表明此节点为叶子节点。此时需要将栈内的元素全部弹出来，然后将此条路径进行标记
                st.pop();
                if (targetValue == target) {//值相等则加入大的List中，不相等则忽略不加
                    listMax.add(listBasic);
                }

                //弹出栈的同时也将基本的listBasic中该节点的值进行删除，说明该路径已经进行讨论过，重新构造新的listBasic
                ArrayList<Integer> listBasic1 = new ArrayList<>();
                for (int i = 0; i < listBasic.size() - 1; i++) {
                    listBasic1.add(listBasic.get(i));
                }
                listBasic = listBasic1;
                //listBasic.remove(listBasic.get(listBasic.size() - 1));
                //弹出栈后，进行另一条路径的寻址，需要更改targetValue的值，将targetValue=targetValue-treeNode.val
                targetValue = targetValue - treeNode.val;
                // treeNode= null;//对该叶子节点进行处理后，我们需要将其弄为空
                st1.add(treeNode);
            } else if (treeNode.left != null && !st1.contains(treeNode.left)) {
                st.add(treeNode.left);
                listBasic.add(treeNode.left.val);
                targetValue = targetValue + treeNode.left.val;
            } else if (treeNode.right != null && !st1.contains(treeNode.right)) {
                //上下两个节点每次只能执行一个，需要做一个标记，判断之前是走过的路径就不进行遍历
                st.add(treeNode.right);
                listBasic.add(treeNode.right.val);
                targetValue = targetValue + treeNode.right.val;
            } else {
                if (st1.contains(treeNode.right) && st1.contains(treeNode.left)) {//表示左右孩子的路径都走过，此时弹出该节点
                    st.pop();
                    //弹出栈的同时也将基本的listBasic中该节点的值进行删除，说明该路径已经进行讨论过，重新构造新的listBasic
                    ArrayList<Integer> listBasic1 = new ArrayList<>();
                    for (int i = 0; i < listBasic.size() - 1; i++) {
                        listBasic1.add(listBasic.get(i));
                    }
                    listBasic = listBasic1;
                    // listBasic.remove(listBasic.get(listBasic.size() - 1));
                    //弹出栈后，进行另一条路径的寻址，需要更改targetValue的值，将targetValue=targetValue-treeNode.val
                    targetValue = targetValue - treeNode.val;
                    // treeNode= null;//对该叶子节点进行处理后，我们需要将其弄为空
                    st1.add(treeNode);
                }

            }
        }
        return listMax;
    }

    /**
     * 第25题
     * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
     * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
     */
    public RandomListNode Clone(RandomListNode pHead) {

        if (pHead == null) {
            return pHead;
        }
        // RandomListNode node = new RandomListNode(pHead.label);
        // A-B-C   变为：A-a-B-b-C-c
        RandomListNode pCur = pHead;
        while (pCur != null) {
            RandomListNode node = new RandomListNode(pCur.label);
            node.random = pCur.random;
            node.next = pCur.next;
            pCur.next = node;
            pCur = node.next;
        }
        RandomListNode head = pHead.next;
        RandomListNode cur = head;
        //pCur = pHead;
        pCur = pHead;
        //复制random pCur是原来链表的结点 pCur.next是复制pCur的结点
        while (pCur != null) {
            if (pCur.random != null)
                pCur.next.random = pCur.random.next;
            pCur = pCur.next.next;

        }
        //拆分链表

        while (pCur != null) {

            pCur.next = pCur.next.next;
            if (cur.next != null)
                cur.next = cur.next.next;
            cur = cur.next;
            pCur = pCur.next;
        }

        return head;
    }


    /**
     * //26题：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
     * 二叉搜索树也叫二叉排序树，左<中<右
     * 排序双向链表：  小 中 大   采用中序遍历二叉搜索树
     * 1,采用中序遍历打印二叉树
     *
     * @param args
     */
    //非递归版，要使用栈，先用栈来进行中序遍历,层次遍历需要使用队列
    public TreeNode Convert(TreeNode pRootOfTree) {

        if (pRootOfTree == null) {
            return null;
        }
        if (pRootOfTree.left == null && pRootOfTree.right == null) {
            return pRootOfTree;
        }
        ArrayList<TreeNode> list = new ArrayList<>();
        orderTraverseK(pRootOfTree, list);
        TreeNode root = list.get(0);
        root.left = null;
        root.right = list.get(1);

        for (int i = 1; i < list.size() - 1; i++) {
            TreeNode s = list.get(i);
            s.left = list.get(i - 1);
            s.right = list.get(i + 1);
        }
        TreeNode s = list.get(list.size() - 1);
        s.left = list.get(list.size() - 2);
        s.right = null;

        return root;
    }

    public static void orderTraverseK(TreeNode root, ArrayList<TreeNode> list) {
        if (root == null) {
            return;
        }
        orderTraverseK(root.left, list);
        list.add(root);
        orderTraverseK(root.right, list);
    }

    /**
     * 27题：
     * 输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     * 输入一个字符串,长度不超过9(可能有字符重复),字符只包括大小写字母。
     *
     * @param args
     */
    public ArrayList<String> Permutation(String str) {
        //先将全排列全部列出来，再进行去重，再进行排序，再进行输出
        ArrayList<String> list = digui(str);
        HashSet<String> hh = new HashSet<>();
        for (int i = 0; i < list.size(); i++) {
            hh.add(list.get(i));
        }
        String[] s = new String[hh.size()];
        int p = 0;
        for (String ssb : hh) {
            s[p] = ssb;
            p++;
        }
        Arrays.sort(s);
        ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            list1.add(s[i]);
        }

        return list1;
    }

    public ArrayList<String> digui(String str) {
        ArrayList<String> list = new ArrayList<>();
        if (str.length() == 1) {
            list.add(str);
            return list;
        }
        if (str.equals("")) {
            return list;
        }
        //如果字符串的长度大于等于2
        StringBuilder sb = new StringBuilder(str.substring(1, str.length()));//截取子串1到len-1,只保留第一位字符串
        ArrayList<String> list1 = digui(sb.toString());
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list1.get(i).length() + 1; j++) {
                StringBuilder sb1 = new StringBuilder(list1.get(i));
                sb1.insert(j, str.substring(0, 1));
                list.add(sb1.toString());
            }
        }
        return list;
    }

    /**
     * 28题：数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
     * 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
     *
     * @param args
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }
        int maxNum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxNum) {
                maxNum = array[i];
            }
        }
        //hash全部设置为0
        int[] hash = new int[maxNum + 1];
        for (int i = 0; i < hash.length; i++) {
            hash[i] = 0;
        }
        for (int i = 0; i < array.length; i++) {
            hash[array[i]]++;
        }
        int cishuMax = 0;
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > cishuMax) {
                cishuMax = hash[i];
            }
        }
        if (cishuMax > array.length / 2) {
            for (int i = 0; i < hash.length; i++) {
                if (hash[i] == cishuMax) {
                    return i;
                }
            }
        } else {
            return 0;
        }

        return 0;
    }

    /**
     * 29题
     * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4,。
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        //k>len情况
        if (k > input.length) {
            return list;
        }
        //k<=len情况
        Arrays.sort(input);
        for (int i = 0; i < k; i++) {
            list.add(input[i]);
        }
        return list;
    }

    /**
     * 30题
     * HZ偶尔会拿些专业问题来忽悠那些非计算机专业的同学。今天测试组开完会后,他又发话了:在古老的一维模式识别中,
     * 常常需要计算连续子向量的最大和,当向量全为正数的时候,问题很好解决。但是,如果向量中包含负数,是否应该包含某个负数,并期望旁边的正数会弥补它呢？
     * 例如:{6,-3,-2,7,-15,1,2,2},连续子向量的最大和为8(从第0个开始,到第3个为止)。你会不会被他忽悠住？(子向量的长度至少是1)
     *
     * @param array
     * @return
     */
    //求连续子向量的最大值
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array.length == 1) {
            return array[0];
        }
        int[] F = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            F[i] = array[i];
            int maxF = F[i];
            for (int j = i + 1; j < array.length; j++) {
                F[i] = F[i] + array[j];
                maxF = F[i] > maxF ? F[i] : maxF;
            }
            F[i] = maxF;
        }
        int maxValue = F[0];
        for (int i = 1; i < F.length; i++) {
            maxValue = F[i] > maxValue ? F[i] : maxValue;
        }
        return maxValue;
    }

    //求的是不连续的子向量
    public int FindGreatestSumOfSubArray1(int[] array) {
        if (array.length == 1) {
            return array[0];
        }
        int[] F = new int[array.length];
        F[0] = array[0];
        for (int i = 1; i < array.length; i++) {
            F[i] = F[i - 1] + array[i] > F[i - 1] ? F[i - 1] + array[i] : F[i - 1];
        }
        return F[array.length - 1];
    }

    /**
     * 31题
     * 求出1~13的整数中1出现的次数,并算出100~1300的整数中1出现的次数？为此他特别数了一下1~13中包含1的数字有1、10、11、12、13因此共出现6次,但是对于后面问题他就没辙了。
     * ACMer希望你们帮帮他,并把问题更加普遍化,可以很快的求出任意非负整数区间中1出现的次数。
     *
     * @param args
     */
    public int NumberOf1Between1AndN_Solution(int n) {
        int[] num = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            num[i] = 0;
            int j = i;
            while (j > 0) {
                if (j % 10 == 1) {
                    num[i]++;
                }
                j = j / 10;
            }
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum = sum + num[i];
        }
        return sum;
    }

    /**
     * 32题：输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
     * 例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
     * 采用策略：当两个字符串不相等时，将短的那个字符串用该字符串的最后一个字符串进行填充，直到两个比较的字符串长度相等     ---在比较器中进行操作，得到操作次序，但返回的是原字符串的
     *
     * @param args
     */
    public String PrintMinNumber(int[] numbers) {
        String[] strs = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strs[i] = numbers[i] + "";
        }
        //加一个比较器，如果第一位相等则比较第二位，第二位没有
        MyComparator mc = new MyComparator();
        Arrays.sort(strs, mc);
        String sk = new String();
        for (int i = 0; i < strs.length; i++) {
            sk = sk + strs[i];
        }
        return sk;
    }

    /**
     * 33题：把只包含因子2、3和5的数称作丑数（Ugly Number）。
     * 例如6、8都是丑数，但14不是，因为它包含因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
     *
     * @param args
     */
    public int GetUglyNumber_Solution(int index) {
        if (index == 0) {
            return 0;
        }
        if (index == 1) {
            return 1;
        }
        int[] F = new int[index + 1];
        F[0] = 0;
        F[1] = 1;
        for (int i = 2; i < index + 1; i++) {
            int min1 = 0;
            int min2 = 0;
            int min3 = 0;
            for (int j = 1; j < i; j++) {
                int s1 = F[j] * 2;
                if (s1 > F[i - 1]) {
                    min1 = s1;
                    break;
                }
            }
            for (int j = 1; j < i; j++) {
                int s1 = F[j] * 3;
                if (s1 > F[i - 1]) {
                    min2 = s1;
                    break;
                }
            }
            for (int j = 1; j < i; j++) {
                int s1 = F[j] * 5;
                if (s1 > F[i - 1]) {
                    min3 = s1;
                    break;
                }
            }
            F[i] = (min1 < min2 ? min1 : min2) < min3 ? (min1 < min2 ? min1 : min2) : min3;
        }

        return F[index];
    }

    /**
     * 34题：在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置, 如果没有则返回 -1.
     *
     * @param args
     */
    public int FirstNotRepeatingChar(String str) {
        if (str.equals("")) {
            return -1;
        }
        String[] strs = str.split("");
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            if (map.containsKey(strs[i])) {
                map.put(strs[i], map.get(strs[i]) + 1);
            } else {
                map.put(strs[i], 1);
            }
        }
        for (int i = 0; i < strs.length; i++) {
            int s = map.get(strs[i]);
            if (s == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 35题：
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007
     * 输入描述:
     * 题目保证输入的数组中没有的相同的数字
     * <p>
     * 数据范围：
     * 对于%50的数据,size<=10^4
     * 对于%75的数据,size<=10^5
     * 对于%100的数据,size<=2*10^5
     * <p>
     * 示例1
     * 输入
     * 复制
     * 1,2,3,4,5,6,7,0
     * 输出
     * 复制
     * 7
     *
     * @param args
     */
    static int sum = 0;

    public int InversePairs(int[] array) {
        if (array.length == 0 || array.length == 1) {
            return 0;
        }
        sortMerge(array);
        return sum % 1000000007;

    }

    public static void sortMerge(int[] arr) {
        //只针对同一个数组进行处理，不要添加其他的数组，处理的过程只是对数组中的某个阶段进行归并排序处理
        sortM(arr, 0, arr.length - 1);
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
        int[] arrs = new int[arr.length];
        if (start == m) {//相当于这一个合并中只包含两个数
            if (arr[m] > arr[end]) {
                int temp = arr[m];
                arr[m] = arr[end];
                arr[end] = temp;
                sum++;
            }
        } else {//当这一段路径中包含大于2个数
            //处理merge过程
            int p = m-1;
            for (int i =arrs.length-1;i>=0;i--){
                if(p>=0&&end>=m){
                    if (arr[p]>arr[end]){
                        arrs[i] = arr[p];
                        p--;
                    }else{
                        arrs[i]=arr[end];
                        end--;
                    }
                }

            }
            for (int i = m; i < end; i++) {
                for (int j = i - 1; j >= start; j--) {
                    if (arr[j] > arr[j + 1]) {
                        int temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                        sum++;
                    } else {
                        break;
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        Solution sl = new Solution();
        String str = "google";
        int[] arr = {364, 637, 341, 406, 747, 995, 234, 971, 571, 219, 993, 407, 416, 366, 315, 301, 601, 650, 418, 355, 460, 505, 360, 965, 516, 648, 727, 667, 465, 849, 455, 181, 486, 149, 588, 233, 144, 174, 557, 67, 746, 550, 474, 162, 268, 142, 463, 221, 882, 576, 604, 739, 288, 569, 256, 936, 275, 401, 497, 82, 935, 983, 583, 523, 697, 478, 147, 795, 380, 973, 958, 115, 773, 870, 259, 655, 446,
                863, 735, 784, 3, 671, 433, 630, 425, 930, 64, 266, 235, 187, 284, 665, 874, 80, 45, 848, 38, 811, 267, 575};

        int s = sl.InversePairs(arr);
        System.out.println(s);
    }
}

class MyComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        //比较一个一个的字符
        int len1 = o1.length();
        int len2 = o2.length();
        if (len1 == len2) {
            return o1.compareTo(o2);
        }
        int maxLen = len1 > len2 ? len1 : len2;
        if (len1 < maxLen) {
            String s = o1.substring(len1 - 1, len1);
            int num = maxLen - len1;
            while (num > 0) {
                o1 = o1 + s;
                num--;
            }
            return o1.compareTo(o2);
        } else {
            String s = o2.substring(len2 - 1, len2);
            int num = maxLen - len2;
            while (num > 0) {
                o2 = o2 + s;
                num--;
            }
            return o1.compareTo(o2);
        }

    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

class TreeNode {
    int val = 0;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

class TreeNode1 {
    int val;
    TreeNode1 left;
    TreeNode1 right;
}

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
