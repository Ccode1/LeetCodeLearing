package com.yhs.zuoshen;

public class printOddTimesNUum {
    //找出数组中出现奇数次的两个数ab
    public int[] printOddTimesNum(int[] nums){
        int eor = 0;
        for(int i:nums){
            eor ^= nums[i];
        }
        //假如某个数a,二进制位为10010 所以 ~a == 01101  ~a+1 == 01110   a&(~a+1) == (10010)&(01110) == 00010 即为a最右侧为1的位置
        int LastEorOne = eor &(~eor + 1); //取出某个数二进制位最后侧为1的位数
        int a = 0;
        for(int i:nums){
            if((i& LastEorOne) == 0){
                a ^= i;
            }
        }
        int [] res = {a,eor^a}; // 因为eor= a^b ,所以 eor^a = a^b^a  == b
        return res;
    }
}
