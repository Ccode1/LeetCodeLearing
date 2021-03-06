package com.yhs.zuoshen;

public class BubbleSort {
    public void BubbleSort(int[] nums){
        if(nums == null || nums.length <2)
            return;
        for(int i = nums.length-1;i>0;i--){
            for(int j = 0;j <i;j++){
                if(nums[j] > nums[j+1]){
                    swap(nums,j,j+1);
                }
            }
        }
    }

    private void swap(int[] nums, int j, int i) {
        nums[j] = nums[j]^nums[i];
        nums[i] = nums[j]^nums[i];
        nums[j] = nums[j]^nums[i];
    }
}
