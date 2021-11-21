package com.yhs.zuoshen;

import org.junit.Test;

import javax.annotation.Resource;

@Resource(name = "compareNumplus")
public class compareNumPlus {
    //荷兰国旗问题，给定一个数组和一个目标直指，返回小于目标值的在左侧，等于的在中间，大于的在后面
    public int [] compareNumPlus(int[] nums,int target){
        if(nums.length < 2)
            return nums;
        int l = 0 ;//小于区域下标
        int r = nums.length-1;//大于区域下标
        for(int i = 0; i <= r;i++){
            if(nums[i] < target){
                int temp = nums[l];
                nums[l] = nums[i];
                nums[i] = temp;
                l++;
            }else if(nums[i] == target){
                continue;
            }else{
                int temp = nums[r];
                nums[r] = nums[i];
                nums[i] = temp;
                i--;
                r--;
            }
        }
        return nums;
    }
    @Test
    public void test(){
        int[] nums = {3,5,6,7,5,4,2,6,9,0};
        for(int i :compareNumPlus(nums,5)){
            System.out.print(i+" ");
        }
    }
}
