package com.yhs.twentydays;

/**
 * @author winstonyou
 * @date 2021年 11月 07日 6:57 下午
 **/
public class sortedSquares {
    //方法一：正负划分，归并思想
    public int[] sortedSquares(int[] nums) {
        int split = -1; //记录数组正负元素分割线位置
        for(int i = 0; i < nums.length;i++){
            if(nums[i] < 0){
                split = i;
            }else{
                break;
            }
        }
        int[] res = new int [nums.length];
        int index = 0;
        int i = split; //因为负数越大，他的平方就越小，所以从小到大来进行归并 i为数组中负数的平方值最小的位置
        int j = split+1;//数组中第一个为正数的位置
        while(i>=0 || j < nums.length){
            if(i<0){
                res[index] = nums[j]*nums[j];
                j++;
            }else if(j == nums.length){
                res[index] = nums[i]*nums[i];
                i--;
            }else if(nums[i]*nums[i]< nums[j]*nums[j]){
                res[index] = nums[i]*nums[i];
                i--;
            }else{
                res[index] = nums[j]*nums[j];
                j++;
            }
            index++;
        }
        return res;
    }
    //双指针法
    public int[] sortedSquares2(int[] nums) {
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
