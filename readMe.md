# LeeCode 刷题之路
## 20 天「算法」刷题计划
https://leetcode-cn.com/study-plan/algorithms/?progress=pg93lem
### 第一天 （二分查找）
#### 1. 二分查找 704 简单

题目：
给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。

事例1：
```
输入: nums = [-1,0,3,5,9,12], target = 9
输出: 4
解释: 9 出现在 nums 中并且下标为 4
```
事例2：
```
输入: nums = [-1,0,3,5,9,12], target = 2
输出: -1
解释: 2 不存在 nums 中因此返回 -1
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

事例1：
```
输入：n = 5, bad = 4
输出：4
解释：
调用 isBadVersion(3) -> false 
调用 isBadVersion(5) -> true 
调用 isBadVersion(4) -> true
所以，4 是第一个错误的版本。
```
事例2：
```
输入：n = 1, bad = 1
输出：1
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
```txt
    二分查找时，临界点一直是一个难点，通过对比leetcode704、278题可以看到，
    r的更新策略跟循环的判断条件息息相关，当r = mid 循环条件位 l < r
    r = mid - 1  循环条件为  l <= r
    可以这样帮助理解 r = mid 时，当 l== r ,那么 l == r == mid 如果此时循环条件为l <= r ,那么就会一直死循环.
```
