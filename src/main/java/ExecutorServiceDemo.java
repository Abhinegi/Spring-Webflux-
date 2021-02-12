import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class ExecutorServiceDemo {

    // Complete the larrysArray function below.
    static String larrysArray(int[] A) {

        int b[] = A.clone();
        Arrays.sort(b);

        for(int i=0;i<A.length;++i){
            if(A[i]!=b[i]){
                int pos=-1;
                for(int j=i+1;j<A.length;++j){
                    if(b[i]==A[j])
                    {
                        pos = j;
                        break;
                    }
                }

                while(pos!=i && pos!=-1){
                    if(pos+1<A.length) {
                        int t = A[pos - 1];
                        A[pos - 1] = A[pos];
                        A[pos] = A[pos + 1];
                        A[pos + 1] = t;
                        pos--;
                    }else if(pos+1 == A.length && pos-2>=i)
                    {
                        int t=A[pos-2];
                        A[pos-2]=A[pos];
                        A[pos]=A[pos-1];
                        A[pos-1]=t;
                        pos-=2;
                    }
                    else{
                        break;
                    }
                }
            }
        }
        if(A[A.length-1]!=b[A.length-1])
            return "NO";
        return "YES";
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
       Scanner sc = new Scanner( System.in);
       int t = sc.nextInt();

       while(t!=0){
           --t;
           int n= sc.nextInt();
           int a[] = new int[n];
           for(int i=0;i<n;++i)
               a[i]= sc.nextInt();
           System.out.println(larrysArray(a));
       }

    }
}
