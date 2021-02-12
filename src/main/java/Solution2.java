import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution2 {

    // 0 -1 -9 -3 -32 -1 

    // Complete the maxSubarray function below.
    static int[] maxSubarray(int[] arr) {
        long maxSum=Long.MIN_VALUE , currentSum=0;
        for(int i=0 ;i<arr.length;++i){
            currentSum += arr[i];

            if(maxSum < currentSum){
                maxSum = currentSum;
            }
            if(maxSum<arr[i])
                currentSum = maxSum = arr[i];

        }

        long sum=0 ; int max=0;
        for(int i = 0;i<arr.length;++i){
            if(arr[i]>0)
                sum+=arr[i];
            if(arr[max]<arr[i])
                max = i;
        }
        if(sum == 0)
            sum =arr[max];

        int[] res = new int[2];
        res[0]=(int)maxSum;
        res[1]=(int)sum;

        return res;

    }

    public static void main(String[] args) throws IOException {

        File file =
                new File("C:\\Users\\RAJESH NEGI\\IdeaProjects\\demo\\src\\main\\java\\Input");

        Scanner scanner = new Scanner( file);
        while(scanner.hasNext()){
            System.out.println(scanner.next());
        }
        int t = scanner.nextInt();
       for(int i=0;i<t;++i){
           int n = scanner.nextInt();
           int[] a= new int[n];
           for(int j=0;j<n;++j)
               a[j]= scanner.nextInt();
           int[] res= maxSubarray(a);
           System.out.println("res "+res[0]+" "+res[1]);
       }
    }
}


//1 -1 -1 -1 -1 5