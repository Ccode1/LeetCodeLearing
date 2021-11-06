package com.yhs.twentydays;

/**
 * @author winstonyou
 * @date 2021年 11月 06日 4:26 下午
 **/
public class firstBadVersion {
    /**
     isBadVersio用来判断当前版本是否是错误版本 if(true) return ture else false;

    *
    **/
    public int firstBadVersion(int n) {
        int l = 1 ,r = n;
        while(l < r){  // 将正确答案的位置定为循环的出口
            int mid =  l + (r-l)/2;
            if(isBadVersion(mid)){
                r = mid; // 如果当前版本是错误的话，那么第一个错误的版本就可能在【left,mid】
            }else{ //如果当前版本正确的话，那么第一个错误的版本必然在【mid+1,right]中
                l = mid + 1;
            }
        }
        return l;
    }
   //这个函数无意义
    private boolean isBadVersion(int mid) {
        return true;
    }
}
