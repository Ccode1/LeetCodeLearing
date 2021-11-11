package com.yhs.twentydays;

import java.util.HashMap;

public class lengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int MAX = 0;
        for(int i = 0; i < s.length();i++){
            StringBuffer sb = new StringBuffer();
            sb.append(s.charAt(i));
            int j = i+1;
            int count = 1;
            while(j<s.length()){
                if(sb.indexOf(String.valueOf(s.charAt(j))) == -1){
                    sb.append(s.charAt(j));
                    count ++;
                }else{
                    break;
                }
                j++;
            }
            MAX = MAX>count?MAX:count;
        }
        return MAX;
    }
    public int lengthOfLongestSubstring2(String s) {
       int n = s.length();
       if(n <= 1) {
           return n;
       }
       int Max = 0;
       int l =0,r=0;
       HashMap<Character,Integer> map = new HashMap<>();
       while(r<n){
           Character rChar = s.charAt(r);
           while(map.containsKey(rChar)){
               map.remove(s.charAt(l));
               l++;
           }
           map.put(rChar,1);
           r++;
           Max = Max>(r-l+1)?Max:(r-l+1);
       }
       return Max;
    }
}
