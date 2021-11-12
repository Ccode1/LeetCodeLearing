package com.yhs.twentydays;

public class checkInclusion {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(),n2 = s2.length();
        if(n1 > n2)
            return false;
        int [] cnt = new int[26];
        for(char ch:s1.toCharArray()){
            cnt[ch-'a'] -=1;
        }
        int l =0,r = 0;
        for(;r<n2;r++){
            int cur = s2.charAt(r)-'a';
            cnt[cur] +=1;
            while(cnt[cur] > 0){
                cnt[s2.charAt(l)-'a'] -=1;
                l++;
            }
            if(r-l+1 == n1){
                return true;
            }
        }
        return false;
    }
}
