package com.yhs.twentydays;

/**
 * @author winstonyou
 * @date 2021年 11月 07日 8:59 下午
 **/
public class sortedSquares2 {
    //双指针法
    public int[] sortedSquares(int[] nums) {
            int[] res = new int[nums.length];
            int i = 0,j = nums.length-1 , index = j;
            while(i <= j){
                if(nums[i]*nums[i]> nums[j]*nums[j]){
                    res[index] = nums[i]*nums[i];
                    i++;
                }else{
                    res[index] = nums[j]*nums[j];
                    j--;
                }
                index --;
            }
            return res;
    }
}
