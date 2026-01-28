/*
You are developing an application for a garden management system where each tree 
in the garden is represented as a binary tree structure. The system needs to 
allow users to plant new trees in a systematic way, ensuring that each tree is 
filled level by level.

A gardener wants to:
 - Plant trees based on user input.
 - Ensure trees grow in a balanced way by filling nodes level by level.
 - Inspect the garden layout by performing an in-order traversal, which helps 
   analyze the natural arrangement of trees.

Your task is to implement a program that:
    - Accepts a list of N tree species (as integers).
    - Builds a binary tree using level-order insertion.
    - Displays the in-order traversal of the tree.

Input Format:
-------------
- An integer N representing the number of tree plants.
- A space-separated list of N integers representing tree species.

Output Format:
--------------
A list of integers, in-order traversal of tree.


Sample Input:
-------------
7
1 2 3 4 5 6 7

Sample Output:
--------------
4 2 5 1 6 3 7


Explanation:
------------
The tree looks like this:

        1
       / \
      2   3
     / \  / \
    4   5 6  7
The in order is : 4 2 5 1 6 3 7



NODE Reference:

class Node {
    int data;
    Node left, right;
    
    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}
/*


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
    Node root=null;
    BT(){
        root=null;
    }
    Node insert(int arr[]){
        root=new Node(arr[0]);
        Queue<Node> q=new LinkedList<>();
        q.offer(root);
        int i=1;
        while(!q.isEmpty() && i<arr.length){
            Node temp=q.poll();
            if(i<arr.length&& arr[i]!=0){
                temp.left=new Node(arr[i]);
                q.add(temp.left);
            }i++;
            if(i<arr.length && arr[i]!=0){
                temp.right=new Node(arr[i]);
                q.add(temp.right);
            }i++;
        }
        return root;
    }
    void insert(Node root,int n){
        if(root==null){ root=new Node(n);return;}
        if(root.left==null)insert(root.left,n);
        if(root.right==null)insert(root.right,n);
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
        int n=sc.nextInt();
        // int arr[]=new int[n];
        BT t=new BT();
        for(int i=0;i<n;i++){
            t.insert(t.root,sc.nextInt());
        }
        t.inorder(t.root);
    }
}