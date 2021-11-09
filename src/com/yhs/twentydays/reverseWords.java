package com.yhs.twentydays;

public class reverseWords {
    //化整为零，双指针交换，StringBuffer拼接结果返回
    public String reverseWords(String s) {
        if(s==null|| s.length() == 0){
            return s;
        }
        String[] srr = s.split(" "); //根据空格将字符串切分成n个字符串型数组
        StringBuffer sb = new StringBuffer(); //定义一个StringBuffer接受结果，并在下面的逻辑进行拼装
        for(String ss:srr){
            int l = 0,r = ss.length()-1;//反转每个字符串数组中的单词
            char[] crr = ss.toCharArray();
            while(l<r){
                char c = crr[l];
                crr[l] = crr[r];
                crr[r] = c;
                l++;
                r--;
            }
            sb.append(String.valueOf(crr)).append(" ");
        }
        return sb.toString().trim();
    }
}
