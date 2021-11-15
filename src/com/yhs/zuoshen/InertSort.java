package com.yhs.zuoshen;

public class InertSort {
    //插入排序
    public void InsertSort(int nums[]){
        if(nums.length < 2)
            return;
        for(int i = 1;i < nums.length;i++){
            for(int j = i-1 ;j > 0 && nums[j] > nums[i];j--){
                nums[j] = nums[j]^nums[i];
                nums[i] = nums[j]^nums[i];
                nums[j] = nums[j]^nums[i];
            }
        }
    }
}
