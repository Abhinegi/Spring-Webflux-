import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        long countPat=0;
        for(int i =0 ;i<n;++i){
            int c=0;
            while(i<n-1 && s.charAt(i)==s.charAt(i+1)){
                ++c;++i;
                if(i==n-1){
                    ++c;
                }
            }
            countPat+=sameChar(c);
        }

        for(int i =0 ;i<n;++i){
            int c=0;
            while(i<n-1 && s.charAt(i)==s.charAt(i+1)){
                c++; i++;
            }
            if(i!=n-1){
                int low=i;
                int high=i+2;
                char check= s.charAt(i);
                while(low>=0 && high<n && s.charAt(low)==check && s.charAt(high)==check){
                    low--;high++;
                }
               countPat+=midSameChar(high-low-1);
            }
        }

        return countPat+s.length();
    }

    static long sameChar(int n){
        n=n-1;
        if(n<=0) return 0;
        return  n*(n+1)/2;
    }

    static long midSameChar(int n){

        n = (n-1)/2;
        if(n<=0)
            return 0;
        return (2+(n-1)*2)*n/2;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
      int n = scanner.nextInt();
      String s = scanner.next();
        System.out.println(substrCount(n,s));
    }
}
