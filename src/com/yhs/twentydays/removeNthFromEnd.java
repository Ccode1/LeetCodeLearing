package com.yhs.twentydays;

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
