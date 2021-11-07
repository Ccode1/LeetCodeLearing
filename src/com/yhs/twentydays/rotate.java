package com.yhs.twentydays;

/**
 * @author winstonyou
 * @date 2021年 11月 07日 9:54 下午
 **/
public class rotate {
    //方法一 辅助数组法：
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int [] res = new int[n];
        for(int i = 0 ;i < n;i++){
            res[(i+k)%n] = nums[i];
        }
        for(int i = 0; i <n;i++){
            nums[i] = res[i];
        }
    }

    //方法二：翻转法
    public void rotate2(int[] nums, int k) {
        k = k % nums.length;
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }
    public void reverse(int[] nums,int i,int j){
        while(i<j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

}
