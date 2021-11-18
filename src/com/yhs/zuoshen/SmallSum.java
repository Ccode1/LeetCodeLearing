package com.yhs.zuoshen;

import org.junit.Test;

public class SmallSum {
    public int SmallSum(int [] nums){
        if(nums.length  == 0)
            return 0;
        if(nums.length < 2)
            return nums[0];
        int res = 0;
        for(int i = 0;i<nums.length;i++){
            int j = i-1;
            int temp = nums[i];
            while(j>=0){
                if(nums[j] < temp)
                    res += nums[j];
                j--;
            }
        }
        return res;
    }

    public int megerSort(int[] nums){
        if(nums.length  == 0)
            return 0;
        if(nums.length < 2)
            return nums[0];
        return process(nums,0,nums.length-1);
    }

    private int process(int[] nums, int l, int r) {
        if(l == r)
            return 0;
        int mid = l +((r-l)>>1);
        return process(nums,l,mid)+
        process(nums,mid+1,r)+
        meger(nums,l,r,mid);
    }

    private int meger(int[] nums, int l, int r, int mid) {
        int[] temp = new int[r-l+1];
        int p1 = l;
        int p2 = mid+1;
        int res = 0;
        int i = 0;
        while(p1<=mid && p2<=r){
            res += nums[p1] <nums[p2]?(r-p2+1)*(nums[p1]):0;
            temp[i++] = nums[p1]<nums[p2]?nums[p1++]:nums[p2++];

        }
        while(p1<=mid){
            temp[i++] = nums[p1++];
        }
        while(p2 <= r){
            temp[i++] = nums[p2++];
        }
        for(int j = 0;j<temp.length;j++){
            nums[l+j] = temp[j];
        }
        return res;

    }

    @Test
    public void test(){
        int[] nums ={1,3,4,2,5};
        System.out.println(SmallSum(nums));
        System.out.println(megerSort(nums));
    }
}
