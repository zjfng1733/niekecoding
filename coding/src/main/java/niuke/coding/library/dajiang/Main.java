package niuke.coding.library.dajiang;

import java.text.DecimalFormat;
import java.util.Scanner;

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
//
//}
public class Main {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 3, 1, 1, 4} ;
        int s = getMin(arr);
}
public    static int getMin(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }
        if (arr.length == 1) {
​​​​​​​return 0;
        }
     int len  =arr.length;
        int s = arr[0];
       for (int i = 0; i < len; i++) {
            if (s < arr[i]) {
                s = arr[i];
            }
          
        }
    }
}