package niuke.coding.library.Wannafly19;


import java.text.DecimalFormat;
import java.util.*;

import java.util.*;
//public class Main{
//    public static void main(String[] args){
//        Scanner sc = new Scanner(System.in);
//        String str  = sc.next();
//        String str_chuan = sc.next();
//        char[] arr = str.toCharArray();
//        char[] arr1 = str_chuan.toCharArray();
//        int s =0;
//        for(int i =0;i<arr.length;i++){
//            if((arr[i]>='0'&&arr[i]<='9')||(arr[i]>='a'&&arr[i]<='z')||(arr[i]>='A'&&arr[i]<='Z')){
//                arr[i] = '1';
//                if(arr[i]==arr1[i]){
//                    s++;
//                }
//            }else{
//                arr[i] = '0';
//            }
//        }
//        double ff = s*1.00/str.length();
//        DecimalFormat df = new DecimalFormat(".00");
//        System.out.println(df.format(s*1.00/str.length()));
//
//
//    }
//}
public class Main {
    public static void main(String[] args) {
        /**
         * 法一
         */
//        List<Integer> listAll = getMethod1();
//        for (int i = 0; i < listAll.size() - 1; i++) {
//            System.out.print(listAll.get(i) + " ");
//        }
//        System.out.print(listAll.get(listAll.size() - 1));


        /**
         * 法二
         */
//        Queue<Integer> queue = getMethod2();
//        int num = queue.size();
//        for (int i =0;i<num-1;i++){
//            System.out.print(queue.poll()+" ");
//        }
//        System.out.print(queue.poll());
        /**
         * 法3
         */
//        String ss  ="asdsf";
//       char[] s = ss.toCharArray();
//       s.length
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<ListNode> list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ListNode(sc.nextInt()));
        }
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }
        list.get(list.size() - 1).next = null;
        String[] strs = new String[sc.nextInt()];
        int k = 0;
        for (int i = 0; i < strs.length; i++) {
            String value = sc.next();
            int key = sc.nextInt();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).val == key) {
                    if (j == 0) {//在首位置
                        if (value.equals("LAST")) {
                            list.get(list.size() - 1).next = list.get(j);
                            list.get(j).next = null;
                            k = j++;
                            break;
                        } else {
                            k = j;
                            break;
                        }

                    } else if (j == list.size() - 1) {//在末位置
                        if (value.equals("FIRST")) {
                            list.get(list.size() - 2).next = null;
                            list.get(j).next = list.get(0);
                            k = j;
                            break;
                        } else {
                            break;
                        }
                    } else {
                        list.get(j - 1).next = list.get(j + 1);
                        if (value.equals("FIRST")) {
                            list.get(j).next = list.get(0);
                            k = j;
                            break;
                        } else {
                            list.get(list.size() - 1).next = list.get(j);
                            list.get(j).next = null;
                            break;
                        }
                    }


                }
            }


        }
        ListNode pp = list.get(k);
        while (pp.next != null) {
            System.out.print(pp.val + " ");
            pp = pp.next;
        }
        System.out.print(pp.val);

    }

    private static Queue<Integer> getMethod2() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            queue.add(sc.nextInt());
        }
        int p = sc.nextInt();
        String[] strs = new String[p];
        for (int i = 0; i < strs.length; i++) {
            List<Integer> list = new ArrayList<>();
            String value = sc.next();
            int key = sc.nextInt();
            while (!queue.isEmpty()) {
                if (queue.peek().equals(key)) {
                    queue.poll();
                } else {
                    list.add(queue.poll());
                }
            }
            if (value.equals("FIRST")) {//将key元素值移到队头
                queue.add(key);
                for (int j = 0; j < list.size(); j++) {
                    queue.add(list.get(j));
                }
            }
            if (value.equals("LAST")) {///将key元素值移到队尾
                for (int j = 0; j < list.size(); j++) {
                    queue.add(list.get(j));
                }
                queue.add(key);
            }
        }
        return queue;
    }

    private static List<Integer> getMethod1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> listAll = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            listAll.add(sc.nextInt());
        }
        int p = sc.nextInt();
        String[] strs = new String[p];
        for (int i = 0; i < strs.length; i++) {
            String value = sc.next();
            int key = sc.nextInt();
            for (int k = 0; k < listAll.size(); k++) {
                if (key == listAll.get(k)) {
                    listAll.remove(k);
                }
            }
            if (value.equals("FIRST")) {//将key元素值移到队头
                listAll.add(key);
                while (listAll.get(0) != key) {
                    int ss = listAll.get(0);
                    listAll.remove(0);
                    listAll.add(ss);
                }
            }
            if (value.equals("LAST")) {///将key元素值移到队尾
                listAll.add(key);
            }
        }
        return listAll;
    }

}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}
