package com.yhs.zuoshen;

public class SelectionSort {
    public void SelectionSort(int[] nums){
        if(nums == null || nums.length < 2){
            return;
        }
        for(int i = 0; i < nums.length ;i++){
            int minIndex = i;
            for(int j = i+1;j < nums.length;j++){
                minIndex = nums[j] < nums[minIndex]?j:minIndex;
            }
            swap(nums,i,minIndex);
        }
    }

    private void swap(int[] nums, int i, int minIndex) {
        int temp = nums[i];
        nums[i] = nums[minIndex];
        nums[minIndex] = temp;
    }
}
