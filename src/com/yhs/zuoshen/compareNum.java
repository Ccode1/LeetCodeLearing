package com.yhs.zuoshen;

import org.junit.Test;

public class compareNum {
    //给定一个数组，和一个目标数，返回数组小组等于目标数的在左侧，大于在右侧
    public int[] compareNum(int[] nums,int target){
        if(nums.length <2)
            return nums;
        int small = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i] <= target){
                int temp = nums[small];
                nums[small++] = nums[i];
                nums[i] = temp;
            }
        }
        return nums;
    }
    @Test
    public void test(){
        int [] nums = {1,3,5,6,7,9,3,2,6};
        for(int i : compareNum(nums,5)){
            System.out.print(i+" ");
        }
    }
}
