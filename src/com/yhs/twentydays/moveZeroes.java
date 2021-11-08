package com.yhs.twentydays;

/**
 * @author winstonyou
 * @date 2021年 11月 08日 10:22 下午
 **/

//方法一：
public class moveZeroes {
    public void moveZeroes(int[] nums) {
        int count = 0;
        int index = 0;
        for(int i = 0; i < nums.length;i++){
            if(nums[i] == 0){
                count++;
            }else{
                nums[index++] = nums[i];
            }
        }
        while(count>0){
            nums[index++] = 0;
            count --;
        }
    }

    public void moveZeroes2(int[] nums) {
        int i = 0;//用于遍历数组
        int j = 0;//用于记录下一个非0元素需要交换的位置
        for(int num:nums){
            if(num == 0){
                i++;
            }else{
                swap(nums,i,j);
                i++;
                j++;
            }
        }
    }
    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
