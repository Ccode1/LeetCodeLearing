package com.yhs.twentydays;

import java.util.HashMap;

public class twoSum {
    //hashMap法
    public int[] twoSum(int[] numbers, int target) {
        if(numbers.length < 2)
            return new int[2];
        HashMap<Integer,Integer> map = new HashMap();
        for(int i = 0; i< numbers.length;i++){
            map.put(target-numbers[i],i+1);
        }
        for(int i = 0;i<numbers.length;i++){
            if(map.containsKey(numbers[i])){
                if(i != map.get(numbers[i])){
                    int [] res = new int[2];
                    res[0] = i+1;
                    res[1] = map.get(numbers[i]);
                    return res;
                }
            }
        }
        return new int[2];
    }
// 双指针法
    public int[] twoSum2(int[] numbers, int target) {
        int l = 0,r = numbers.length-1;
        int[] res = new int [2];
        while(l<r){
            int nums = numbers[l]+numbers[r];
            if(nums < target){
                l++;
            }else if(nums>target){
                r--;
            }else{
                res[0] = l+1;
                res[1] = r+1;
                break;
            }
        }
        return res;
    }

}
