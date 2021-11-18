package com.yhs.zuoshen;

import org.junit.Test;

public class MegerSort {
    public int[]  MegerSort(int[] nums){
        if(nums.length <2)
            return nums;
        process(nums,0,nums.length-1);
        return nums;
    }

    private void process(int[] nums, int l, int r) {
        if(l == r)
            return;
        int mid = l+((r-l)>>1);
        process(nums,0,mid);
        process(nums,mid+1,r);
        meger(nums,l,mid,r);
    }

    private void meger(int[] nums, int l, int mid, int r) {
        int [] temp = new int[r-l+1];
        int p1 = l;
        int p2 = mid+1;
        int index = 0;
        while(p1 <= mid && p2 <= r){
            temp[index++] = nums[p1]<nums[p2]?nums[p1++]:nums[p2++];
        }
        while(p1 <= mid){
            temp[index++] = nums[p1++];
        }
        while (p2 <= r){
            temp[index++] = nums[p2++];
        }
        for(int i = 0;i < temp.length;i++){
            nums[l+i] = temp[i];
        }
    }

    @Test
    public void test(){
        int[] nums = {2,1,4,3,5,2,1};
        MegerSort(nums);
        for (int i:nums
             ) {
            System.out.println(i);
        }
    }
}
