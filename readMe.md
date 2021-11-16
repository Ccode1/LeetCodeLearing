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
#### 1.有序数组的平方 977 简单

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
思路2：
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
#### 2.旋转数组 189 中等难度

题目：
给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

条件提示:

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_4.png)

事例1：
```text
输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
```
事例2：
```text
输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释: 
向右旋转 1 步: [99,-1,-100,3]
向右旋转 2 步: [3,99,-1,-100]
```

思路：
```text
方法一：利用辅助数组，将每个数组元素移动后的位置，填到辅助数组当中，然后再将辅助数组给赋值给原数组
因为k的值可能大于nums.length,所以移动前要对k取模
```
代码：
```java
public class rotate {
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
}
```
思路2：
```text
方法二：翻转法：
原数组旋转k个位置，可以看成将原数组整个翻转，然后将翻转后的数组分成两部分翻转【0，k-1】【k,nums.length-1]
[1,2,3,4,5,6] 3
第一步：【6，5，4，3，2，1】
第二步：【4，5，6，3，2，1】
第三步：【4，5，6，1，2，3】
```
代码：
```java
class Soulution{
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
```
### 第三天（双指针）
#### 1. 移动零 238 简单

题目：
给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。

条件提示:

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_5.png)

事例1：
```text
输入: [0,1,0,3,12]
输出: [1,3,12,0,0]
```

思路：
```text
方法一：遍历数组，记录数组中0出现的次数，在遍历的过程中，当前元素非0，当前值覆盖到数组首部，更新指针向后移动
当前元素为0，count++,遍历跳过当前元素，最后非0元素都在数组头部，再根据count的大小给数组尾部赋值0
```
代码：
```java

//方法一：
public class moveZeroes {

    public void moveZeroes(int[] nums) {
        int count = 0;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            } else {
                nums[index++] = nums[i];
            }
        }
        while (count > 0) {
            nums[index++] = 0;
            count--;
        }
    }
}
```
思路2：
```text
方法二：双指针法（空间优化）：
定义两个指针i,j在数组初始位置，i用来遍历数组，j用来记录下一个非0元素将要交换的位置
当前元素nums[i] ==0 ，跳过
当前元素nums[i] != 0,交换nums[i],nums[j]数值，更新指针位置i++,j++;

```
代码：
```java
class Soulution{
    //双指针法
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
```
#### 2. 两数之和 II - 输入有序数组 167 简单

题目：
给定一个已按照 非递减顺序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。

条件提示:

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_6.png)

事例1：
```text
输入：numbers = [2,7,11,15], target = 9
输出：[1,2]
解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 
```
事例2：
```text
输入：numbers = [2,3,4], target = 6
输出：[1,3]
```
事例3：
```text
输入：numbers = [-1,0], target = -1
输出：[1,2]
```
思路：
```text
方法一：hash表法
首先遍历下数组，将每个元素对应的达成目标值的另一个元素存入hash表中作为key，value值则为对应输出的索引
然后再一次遍历数组，遍历到当前元素作为key值，如果在map中存在，则说明对应的数组中可能存在另一个元素能与之达成目标值，
但不排除相同的元素使用两次，所以对索引值再进行判断，当两个元素索引值不一致时，则为正确答案
```
代码：
```java

//方法一：
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
}
```
思路2：
```text
方法二：双指针法：
因为数组为非递减数组，所以可以考虑双指针法，定义两个指针分别在首尾，每次根据首尾指针的和的大小与目标值对比移动指针
当满足条件存入结果数组，跳出循环（一定记得跳出循环，否则可能超时），循环判断条件为l<r 因为不能重复元素

```
代码：
```java
class twoSum{
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
```
### 第四天（双指针）
#### 1. 反转字符串 344 简单

题目：
编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。

不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。


条件提示:

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_7.png)

事例1：
```text
输入：s = ["h","e","l","l","o"]
输出：["o","l","l","e","h"]
```
事例2：
```text
输入：s = ["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]
```
思路：
```text
方法一：反转字符串，分为数组的奇偶性两种情况：
奇数个元素： 每次交换首尾，然后向中间缩进，直到最后中间肯定剩一项不用交换
偶数个：交换到最后，元素没有剩余
从中可以看出，l，r始终没有碰面，所以循环结束条件应该是l<r
```
代码：
```java

//方法一：
public class reverseString {
    //双指针法
    public void reverseString(char[] s) {
        int l =0,r= s.length-1;
        while(l<r){
            swap(s,l++,r--);
        }
    }
    public void swap(char[] s,int l,int r){
        char temp = s[l];
        s[l] = s[r];
        s[r] = temp;
    }
}
```
#### 2. 反转字符串中的单词 III 557 简单

题目：
给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。


条件提示:

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_8.png)

事例1：
```text
输入："Let's take LeetCode contest"
输出："s'teL ekat edoCteeL tsetnoc"
```
思路：
```text
方法一：
这一题与上一题比较类似，入参为一个以空格切分的一个字符串，出参要求将每个空格两边的字符串字符反转
这里我们可以先将字符转化成字符数组，然后分别对该数组中的每个子元素字符串进行单词反转，局部的处理遇上一题一样，最后用一个StringBuffer将结果拼接
```
代码：
```java
public class reverseWords {
    //化整为零，双指针交换，StringBuffer拼接结果返回
    public String reverseWords(String s) {
        if(s==null|| s.length() == 0){
            return s;
        }
        String[] srr = s.split(" "); //根据空格将字符串切分成n个字符串型数组
        StringBuffer sb = new StringBuffer(); //定义一个StringBuffer接受结果，并在下面的逻辑进行拼装
        for(String ss:srr){
            int l = 0,r = ss.length()-1;//反转每个字符串数组中的单词
            char[] crr = ss.toCharArray();
            while(l<r){
                char c = crr[l];
                crr[l] = crr[r];
                crr[r] = c;
                l++;
                r--;
            }
            sb.append(String.valueOf(crr)).append(" ");
        }
        return sb.toString().trim();
    }
}
```
### 第五天（双指针）
#### 1.  链表的中间结点 876 简单

题目：
给定一个头结点为 head 的非空单链表，返回链表的中间结点。

如果有两个中间结点，则返回第二个中间结点


条件提示:

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_9.png)

事例1：
```text
输入：[1,2,3,4,5]
输出：此列表中的结点 3 (序列化形式：[3,4,5])
返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
注意，我们返回了一个 ListNode 类型的对象 ans，这样：
ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
```
事例2：
```text
输入：[1,2,3,4,5,6]
输出：此列表中的结点 4 (序列化形式：[4,5,6])
由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
```
思路：
```text
方法一：给定一个链表，返回中间节点，首先我会想到快慢指针的方式
慢指针每次走一步，快指针每次走两步，这样当快指针走到终点的时候慢指针的位置恰好在中间位置附近，因为要考虑到链表元素的奇偶性
当链表元素为奇数时，当快指针指向最后一个元素的时候，慢指针的位置恰好为中点1 2 3 4 5  快指针： 1->3  3->5 慢指针：1->2 2->3
当链表元素为偶数时,快指针必定先走到终点，并且最后一次指向null  1 2 3 4 5 6快指针： 1->3 3->5 5->null 慢指针：1->2 2—>3 3->4
所以综上while()的终止条件应该为fast != null && fast.next ！= null才会继续往下赋值 当fast == null时对应偶数情况，当fast.next ==null对应奇数情况

```
代码：
```java

//方法一：
public class middleNode {
    //定义一个listNode内部类
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    //快慢指针方式
    public ListNode middleNode(ListNode head) {
        ListNode low =  head;
        ListNode fast = head;
        while(fast != null && fast.next != null){
            low = low.next;
            fast = fast.next.next;
        }
        return low;
    }
}
```
#### 1.  删除链表的倒数第 N 个结点 19 中等

题目：
给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 
条件提示:

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_10.png)

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_11.png)

事例2：
```text
输入：head = [1], n = 1
输出：[]
```
事例3：
```text
输入：head = [1,2], n = 1
输出：[1]
```
思路：
```text
方法一：
因为要删除的元素可能是第一个元素，所以要构建一个0节点，考虑到这种情况
采用快慢指针的方式，目的是为了让快指针跳出循环的时候，慢指针正好指向要删除元素的前一个位置，那么删除元素的方法即位：low.next = low.next.next
通过观察，fast指针从head的第一个元素开始，慢指针从新构建的0个元素开始
fast先走n步，然后fast和low以同步长向后走，当fast走完，指向null时，low正好指向要删除节点的前一个位置 ，low.next = low.next.next删除节点
因为temp的next是head,此时返回temp.next时即位结果
考虑到特殊情况，当删除节点为头部节点时，对于low指针，head的前面是0，也就是返回0->head.next ([1] ->[])([1 2 3]->[2  3])

```
代码：
```java

//方法一：
public class removeNthFromEnd {
    //定义一个listNode内部类
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = new ListNode(0,head);
        ListNode fast = head;
        ListNode low = temp;
        //先让fast指针走n步，然后low指针和fast指针走一个步长，当fast指针指向最后一个元素时，low指针正好走到要删除的元素的前一个
        for(int i = 0; i < n;i++){
            fast = fast.next;
        }
        while(fast != null){
            fast = fast.next;
            low = low.next;
        }
        low.next = low.next.next;
        ListNode res = temp.next;
        return res;
    }
}
```
### 第六天（滑动窗口）
#### 1.  无重复字符的最长子串 3 简单

题目：
给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。

条件提示:

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_12.png)

事例1：
```text
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```
事例2：
```text
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```
思路1：
```text
方法一：
题目要求返回一个字符串的最大不重复子串的长度，可以每次从字符串的一个位置开始往后进行遍历，不重复就计数加1
出现重复的话就结束当前位置的遍历，比较此时的不重复元素计数大小跟MAX进行比较，MAX = MAX>count?MAX:count
当遍历完所有位置，返回MAX的值即为最大不重复子串的长度

```
代码：
```java

//方法一：
public class lengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        int MAX = 0;
        for(int i = 0; i < s.length();i++){
            StringBuffer sb = new StringBuffer();
            sb.append(s.charAt(i));
            int j = i+1;
            int count = 1;
            while(j<s.length()){
                if(sb.indexOf(String.valueOf(s.charAt(j))) == -1){
                    sb.append(s.charAt(j));
                    count ++;
                }else{
                    break;
                }
                j++;
            }
            MAX = MAX>count?MAX:count;
        }
        return MAX;
    }
}
```
思路2：
```text
方法二：滑动窗口
定义两个变量l,r初始位置为0，先移动r指针，当前元素不在窗口中，将r装入，r++
当前元素在窗口中，将窗口的首位（l的指向位置）移除，l++。
定义一个map用来判定当前窗口是否有重复值
每次窗口更新之后（l的指向改变）更新下Max大小，记录上一个窗口的最大不重复子串的长度
例如：“abcad"
l= 0,r=0
当前元素r的位置为0，所以，判断a是否在map当中，不在，所以跳出while（map.containsKey(s.charAt(r))）
窗口为（0，0） 更新下Max = 1 , map{a} r++ r==1 进入下一层循环
当前元素r位置为1，判断b是否在map当中，不在，所以跳出while（map.containsKey(s.charAt(r))）
窗口大小为（0，1）跟新下Max = 2 map{a,b} r++ r==2 进入下一层循环
当前元素r位置为2，判断c是否在map当中，不在，所以跳出while（map.containsKey(s.charAt(r))）
窗口大小为（0，2）跟新下Max = 3 map{a,b,c} r++ r==3 进入下一层循环
当前元素r位置为3，判断a是否在map当中，在，进入while（map.containsKey(s.charAt(r))），map中移除重复元素map{b,c} l++ l==1 进入下一层循环  判断a是否在map当中，不在，所以跳出while（map.containsKey(s.charAt(r))）
窗口大小为（1，3）跟新下Max = 3 map{b,c,a} r++ r==4 进入下一层循环
当前元素r位置为4，判断d是否在map当中，不在，所以跳出while（map.containsKey(s.charAt(r))）
窗口大小为（1，4）跟新下Max = 4 map{a,b,c,d} r ==5 r== n，跳出外层循环 ，所以最大值为4


```
代码：
```java

//方法二：
public class lengthOfLongestSubstring {
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        if(n <= 1) {
            return n;
        }
        int Max = 0;
        int l =0,r=0;
        HashMap<Character,Integer> map = new HashMap<>();
        while(r<n){
            Character rChar = s.charAt(r);
            while(map.containsKey(rChar)){
                map.remove(s.charAt(l));
                l++;
            }
            Max = Max>(r-l+1)?Max:(r-l+1);
            map.put(rChar,1);
            r++;
        }
        return Max;
    }
}
```
#### 2.  字符串的排列 567 中等

题目：
给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的排列。如果是，返回 true ；否则，返回 false 。
换句话说，s1 的排列之一是 s2 的 子串 。

条件提示:

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_12.png)

事例1：
```text
输入：s1 = "ab" s2 = "eidbaooo"
输出：true
解释：s2 包含 s1 的排列之一 ("ba").
```
事例2：
```text
输入：s1= "ab" s2 = "eidboaoo"
输出：false
```
思路1：
```text
方法：滑动窗口
本题的意思是找出s2是否包含s1的某种排序，最直观的方法就是将s1全排列，然后把所有的结果比较s2是否包含，但是较为复杂，重复很多计算
可以采用滑动窗口的思想，先遍历一遍s1,记录s1中的所有字符的数量分别的个数，在s2中控制一个窗口，通过判断不同的情况，移动窗口的左右指针
这里记录一个数组ctn[s1.charAt(i)-'a'] -=1   遍历一遍s1，得到各个字符的数量，滑动窗口的两个指针初始位置为0
开始以r指针的位置为条件遍历，每遍历一个s2的位置，ctn[s2.charAt(r)-'a'] += 1 ,这一步意味着，每次遍历无论是否是s1中的元素，都会把该元素放到窗口中
判断ctn[cur]的值是否大于0，如果大于0，说明当前滑动窗口的数元素，有些是不需要的，剔除左侧多余的元素，更新l的位置，
然后判断当前元素的个数跟s1中此元素的数值等价的时候，窗口的大小是否等于s1的长度，如果是的话，返回结果
如果ctn[cur-'a'] <= 0 说明当前元素在s1当中 ，进行结果判断，或者继续往后遍历 
以为每次遍历都会增加r，所以会不断的滑动窗口右移，而当进入里层while循环的时候，说明出现多余的元素，会将窗口左侧移动，剔除多于元素
s1:ab  s2 bbiefba
cur   b(0)   b(1)        i          e         f          b              a
窗口： [b]    [bb]->[b]  [bi]->[i]  [ie]->[e] [ef]->[f]   [fb]->[b]    [ba]->[ba]
l      0      1          2          3         4          5              5
r      0      1          2          3         4          5              6 
r-l+1  0      0          1          1         1          1              2 => true
2
从上面过程可以看出，当遍历s2的当前元素不为s1中的元素，或者超过s1中当前元素的个数时，说明左侧的元素多余，移除，寻找下一个窗口
```
代码：
```java

//方法一：
public class checkInclusion {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(),n2 = s2.length();
        if(n1 > n2)
            return false;
        int [] cnt = new int[26];
        for(char ch:s1.toCharArray()){
            cnt[ch-'a'] -=1;
        }
        int l =0,r = 0;
        for(;r<n2;r++){
            int cur = s2.charAt(r)-'a';
            cnt[cur] +=1;
            while(cnt[cur] > 0){
                cnt[s2.charAt(l)-'a'] -=1;
                l++;
            }
            if(r-l+1 == n1){
                return true;
            }
        }
        return false;
    }
}
```

### 第七天（深度优先搜索）
#### 1.  图像渲染 733 简单

题目：
有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。

给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。

为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。

最后返回经过上色渲染后的图像。


条件提示:

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_13.png)

事例1：
```text
输入: 
image = [[1,1,1],[1,1,0],[1,0,1]]
sr = 1, sc = 1, newColor = 2
输出: [[2,2,2],[2,2,0],[2,0,1]]
解析: 
在图像的正中间，(坐标(sr,sc)=(1,1)),
在路径上所有符合条件的像素点的颜色都被更改成2。
注意，右下角的像素没有更改为2，
因为它不是在上下左右四个方向上与初始点相连的像素点
```
思路1：
```text
方法一：
如果相邻元素只要有一个是与其实元素的值相同，那么替换成新的像素大小
遍历当前元素的上下左右位置，如果当前元素的不满足上色条件，那么这条递归分支断开，这也是为什么实例中右下角的1为什么没有被染色的原因

```
代码：
```java

//dfs：
public class floodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int target = image[sr][sc];
        int row = image.length;
        int clos = image[0].length;
        dfs(image,sr,sc,target,newColor,row,clos);
        return image;
    }

    private void dfs(int[][] image,int i, int j, int target, int newColor, int row, int clos) {
        if(i < 0 || j < 0 || i >= row || j >= clos || image[i][j] != target ||image[i][j] == newColor){
            return ;
        }else{
            image[i][j] = newColor;
            dfs(image,i-1,j,target,newColor,row,clos);
            dfs(image,i+1,j,target,newColor,row,clos);
            dfs(image,i,j-1,target,newColor,row,clos);
            dfs(image,i,j+1,target,newColor,row,clos);
        }
    }
}
```
#### 2.  岛屿的最大面积 659 中等

题目：
给你一个大小为 m x n 的二进制矩阵 grid 。

岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都被 0（代表水）包围着。

岛屿的面积是岛上值为 1 的单元格的数目。

计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。


条件提示:

![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_14.png)

事例1：
![img.png](https://raw.githubusercontent.com/Ccode1/LeetCodeLearing/master/img/img_15.png)
事例2：
```text
输入：grid = [[0,0,0,0,0,0,0,0]]
输出：0
```
思路1：
```text
方法：dfs
返回最大的岛屿，分别统计每个岛屿的大小，然后通过一个值记录历史岛屿的最大值，比较返回最大值
每次通过遍历寻找出岛屿的初始位置，以该位置为起点统计该岛屿的面积
```
代码：
```java

//方法一：
public class maxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        int row = grid.length;
        int clo = grid[0].length;
        if(row == 0 || clo == 0)
            return 0;
        int res = 0;
        for(int i =0 ;i < row;i++){
            for(int j = 0;j <clo;j++){
                if(grid[i][j] == 1){
                    res = Math.max(dfs(grid,i,j,row,clo),res);
                }
            }
        }
        return res;
    }
    public int dfs(int[][]nums,int i,int j,int row,int clo){
        if(i<0 || j<0 ||i >= row||j >= clo ||nums[i][j] == 0){
            return 0;
        }
        //遍历到此位置，说明该位置符合岛屿面积增加的条件，加一，然后继续往深处遍历
        nums[i][j] = 0;
        return 1+ dfs(nums,i-1,j,row,clo)+dfs(nums,i+1,j,row,clo)+dfs(nums,i,j-1,row,clo)+dfs(nums,i,j+1,row,clo);
    }

}
```