package com.yhs.twentydays;

/**
 * @author winstonyou
 * @date 2021年 11月 07日 12:01 上午
 **/
public class searchInsert {
    public int searchInsert(int[] nums, int target) {
        int l = 0,r = nums.length-1;
        while(l<=r){
            int mid = l + (r-l)/2;
            if(nums[mid] < target){
                l = mid+1;
            }else if(nums[mid] > target){
                r = mid-1;
            }else{
                return mid;
            }
        }
        return l;
    }

}
