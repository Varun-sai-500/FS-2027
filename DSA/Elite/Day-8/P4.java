/*
Write a program to construct a binary tree from level-order input, while treating -1 
as a placeholder for missing nodes. The program reads input, constructs the tree, 
and provides an in-order traversal to verify correctness.

Input Format:
---------------
Space separated integers, level order data (where -1 indiactes null node).

Output Format:
-----------------
Print the in-order data of the tree.

NODE REFERENCE
--------------
class Node {
    int value;
    Node left, right;

    public Node(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}


Sample Input:
----------------
1 2 3 -1 -1 4 5

Sample Output:
----------------
2 1 4 3 5

Explanation:
--------------
    1
   / \
  2   3
     / \
    4   5


Sample Input:
----------------
1 2 3 4 5 6 7

Sample Output:
----------------
4 2 5 1 6 3 7

Explanation:
--------------
        1
       / \
      2   3
     / \  / \
    4  5 6  7
*/


import java.util.*;

class Node{
    int val;
    Node left,right;
    Node(int data){
        val=data;
        left=right=null;
    }
}
class BT{
    Node insert(int arr[]){
        Node root=new Node(arr[0]);
        Queue<Node> q=new LinkedList<>();
        q.offer(root);
        int i=1;
        while(!q.isEmpty() && i<arr.length){
            Node temp=q.poll();
            if(i<arr.length&& arr[i]!=-1){
                temp.left=new Node(arr[i]);
                q.add(temp.left);
            }i++;
            if(i<arr.length && arr[i]!=-1){
                temp.right=new Node(arr[i]);
                q.add(temp.right);
            }i++;
        }
        return root;

    }
    void inorder(Node root){
        if(root==null)return;
        inorder(root.left);
        System.out.print(root.val+" ");
        inorder(root.right);
    }

}
class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String str[]=sc.nextLine().split(" ");;
        int arr[]=new int[str.length];
        for(int i=0;i<arr.length;i++){
            arr[i]=Integer.parseInt(str[i]);
        }
        BT t=new BT();
        t.inorder(t.insert(arr));

    }
}