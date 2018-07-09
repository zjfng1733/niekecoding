package niuke.coding.library.personalAlgorithm;


/*
*
*
* KMP算法解决包含问题：str1字符串是否包含str2字符串
* str1:ababcababtsababcababtkabf
* str2:ababcababtk
*
* 求解步骤：1）求解str2的next数组
*          2）根据str2的next数组求解str1字符串是否包含str2字符串
*          3）最终结果返回str2字符串的首字符在str1中的编号
* */
public class KMP {
    public static void main(String[] args) {
//        String str1 = "ababcababtsababcababtk abf";
//        String str2 = "ababcababtk";
//        String str1 = "ababxababc";
//        String str2 = "ababc";
        String str1 = "sbaxababc";
        String str2 = "ababc";
         int index = getIndexOfString(str1, str2);
//        int[] next = getNextArrays(str2);
//        System.out.println("haaaaaa");
//        for (int i = 0; i < next.length; i++) {
//            System.out.println(next[i] + " ");
//        }

         System.out.println(index);
    }

    // String str1 = "abab c ababts ababcababtk abf";
    //                       cababcababtk
    // String str2 = " abab c ababtk";

    public static int getIndexOfString(String str1, String str2) {
        int next[] = getNextArrays(str2);
        char[] str1s = str1.toCharArray();
        char[] str2s = str2.toCharArray();
        int j = 0;//代表str1的索引
        int i = 0;//代表str2的索引
        while (i < str2.length() && j < str1.length()) {
            if (str2s[i] != str1s[j]&&j>-1) {//满足j>=0的要求
                //当next[i]=0时，即i前面并没有最长前缀与最长后缀的匹配长度,此时从当前j位置开始比较起
                //此处当next[i]=-1,j=0时，自动满足j=j+1。
                j = j - next[i];//后退next[i]个字符,，
                i = 0;//重新开始比较
            } else {
                i++;
                j++;
            }
        }
        if (i == str2.length()) {//说明全部比较完，且在str1中找到str2的字符串
            return j - i;
        } else {
            return -1;
        }
    }

    //构造str2的next数组,规定next[0]=-1,next[1]=0;
    //ababcababtk  ==> ababc ababtk,经过验证，以下代码为正确
    public static int[] getNextArrays(String str) {
        char[] strs = str.toCharArray();
        int[] next = new int[str.length()];
        int cn = 0;//为前跳位置
        next[0] = -1;
        next[1] = 0;
        int i = 2;
        while (i < next.length) {
            if (strs[i - 1] == strs[cn]) {
                next[i] = cn+1;
                i++;
                cn++;
            } else if (cn > 0) {
                cn = next[cn];//往前跳
            } else {
                next[i] = 0;
                i++;
            }
        }
        return next;
    }

}
