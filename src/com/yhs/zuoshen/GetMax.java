package com.yhs.zuoshen;

import org.junit.Test;

public class GetMax {
    /*
        返回数组的最大值 递归解法
     */
    public int GetMax(int[] nums){
        int res = process(nums,0,nums.length -1);
        return res;
    }

    private int process(int[] nums, int l, int r) {
        if(l==r)
            return nums[l];
        int mid = l+((r-l)>>1); //防止l+r溢出
        int leftMax = process(nums,l,mid);
        int rigthMax = process(nums,mid+1,r);
        return Math.max(leftMax,rigthMax);
    }
    @Test
    public void test(){
        int[] nums={1,2,3,4,5};
        System.out.println(GetMax(nums));
    }
}
