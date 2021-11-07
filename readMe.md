# LeeCode 刷题之路
## 20 天「算法」刷题计划
https://leetcode-cn.com/study-plan/algorithms/?progress=pg93lem
### 第一天 （二分查找）
#### 1. 二分查找 704 简单

题目：
给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。

条件提示：

![img_1.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_1.png)

事例1：
```text
输入: nums = [-1,0,3,5,9,12], target = 9
输出: 4
解释: 9 出现在 nums 中并且下标为 4
```
事例2：
```text
输入: nums = [-1,0,3,5,9,12], target = 2
输出: -1
解释: 2 不存在 nums 中因此返回 -1
```
思路：
```text
有序数组里面寻找目标值，首选我们会根据有序想到二分查找，然后就是有两个点，l、r的更新策略
循环内，首先定位中位数，根据nums[mid]大小更新l,r.因为数组升序，
所以当nums[mid] < target时，mid+1的位置开始往右才可能找到target,更新l = mid+1;
nums[mid] > target时，mid -1 位置开始往左才可能找到target的值，直到l > r循环跳出
循环条件：l <= r. 
```
代码：
```java
public class search {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length -1;
        while(l <= r){
            int mid = l + (r-l)/2;
            if(nums[mid]< target){
                l = mid + 1;
            }else if(nums[mid] > target){
                r = mid - 1;
            }else{
                return mid;
            }
        }
        return -1;
    }

}
```
#### 2.第一个错误的版本 278 简单

题目：
你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。

条件提示：

![img_2.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_2.png)

事例1：
```text
输入：n = 5, bad = 4
输出：4
解释：
调用 isBadVersion(3) -> false 
调用 isBadVersion(5) -> true 
调用 isBadVersion(4) -> true
所以，4 是第一个错误的版本。
```
事例2：
```text
输入：n = 1, bad = 1
输出：1
```
思路：
```text
根据题意，错误版本之后全是错误版本，这种属于临界点问题，同样可以采用二分查找的方法
我们先定义l、r更新策略再谈循环条件，当isBadVersion(mid) == true时，说明mid位置开始往左都有可能出现第一次错误的版本
r= mid, isBadVersion(mid) == false时，表明第一次出现错误版本的位置只可能在mid + 1到r之间
所以，l = mid +1,直到l == r 找到临界点，跳出循环，所以循环条件是 l < r
```
```text
在求mid时,两种写法：
1. int mid = (l+r) >> 1;
2. int mid = l +（(r-l) >> 1）;
这样写主要是因为，r和l相加可能造成数据类型溢出，根据题目提示判断哪种写法ok
```
代码：
```java
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1 ,r = n;
        while(l < r){  // 将正确答案的位置定为循环的出口
            int mid =  l + (r-l)/2;
            if(isBadVersion(mid)){
                r = mid; // 如果当前版本是错误的话，那么第一个错误的版本就可能在【left,mid】
            }else{ //如果当前版本正确的话，那么第一个错误的版本必然在【mid+1,right]中
                l = mid + 1;
            }
        }
        return l;
    }
}
```
小注：
```text
    二分查找时，临界点一直是一个难点，通过对比leetcode704、278题可以看到，
    r的更新策略跟循环的判断条件息息相关，当r == mid 循环条件位 l < r
    r = mid - 1  循环条件为  l <= r
    可以这样帮助理解 r = mid 时，当 l== r ,那么 l == r == mid 如果此时循环条件为l <= r ,那么就会一直死循环.
```

#### 3.搜索插入位置 35 简单

题目：
给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
请必须使用时间复杂度为 O(log n) 的算法。

条件提示:

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img.png)

事例1：
```text
输入: nums = [1,3,5,6], target = 5
输出: 2
```
事例2：
```text
输入: nums = [1,3,5,6], target = 2
输出: 1
```
事例3：
```text
输入: nums = [1,3,5,6], target = 7
输出: 4
```
事例4：
```text
输入: nums = [1,3,5,6], target = 0
输出: 0
```

思路：
```text
题目分为两个点：1.找目标值 2.找不到按照target的大小插入数组
数组是个有序数组，可想到二分查找，循环体中，l = mid +1 , r = mid -1,所以循环条件为：l <= r ,跳出条件为 l = r+1
二分查找之后，如果没有找到目标值，那么达到临界条件，l == r+1, 此时，l左侧都小于target,所以插入点为l的位置
```

代码：
```java
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
```
### 第二天 （双指针）
#### 3.有序数组的平方 977 简单

题目：
给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。

条件提示:

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_3.png)

事例1：
```text
输入：nums = [-4,-1,0,3,10]
输出：[0,1,9,16,100]
解释：平方后，数组变为 [16,1,0,9,100]
排序后，数组变为 [0,1,9,16,100]
```
事例2：
```text
输入：nums = [-7,-3,2,3,11]
输出：[4,9,9,49,121]
```

思路：
```text
方法一：利用原数组是一个非递减的有序数组的特性，假设原数组所有元素非负，那么结果就是所有元素原地乘方返回就行
假设所有元素为负数，负数越大，其数值越小（-3 > -4 >-5)，那么返回结果即为元素乘方的倒序
那么原数组有正有负的情况即为主要讨论的地方，必然有一个正负元素的分割点，分割点往左，乘方递增，往右乘方也是递增
就可以采用归并排序的思想来解决问题了，分割点为i（i位置的元素为最大的负数），那么从i+1位置往右都是正数
依次比较两个方向的最小平方值，较小的放入新的位置。
```
代码：
```java
public class sortedSquares {
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
}
```
```text
方法二：双指针法
无论上面哪种情况，元素乘方较大值只可能在数组两端，我们可以左侧从0开始，右侧从nums.length-1开始，
比较nums[i]*nums[i]  nums[j]*nums[j]大小，将较大的放入结果数组的最后面，然后移动游标进行比较
```
代码：
```java
public class sortedSquares2 {
    //双指针法
    public int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        int i = 0,j = nums.length-1 , index = j;
        while(i <= j){ //这里跳出条件为 <= 是因为最后左右要碰头，不然少了最后一种情况
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
```