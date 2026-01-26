// LEETCODE - 876 Middle of the Linked List
// You are given a singly linked list containing N nodes. 
// Your task is to find the middle element of the linked list.

// Input Format:
// -------------
// Line 1: An integer N, representing the number of nodes in the linked list.
// Line 2: N space-separated integers representing the elements of the linked list.

// Output Format:
// --------------
// Line-1: Print a single integer, the middle element of the linked list.

// Sample Input-1:
// ---------------
// 1 2 3 4 5

// Sample Output-1:
// ----------------
// 3


// Sample Input-2:
// ---------------
// 1 2 3 4 5 6

// Sample Output-2:
// ----------------
// 4


#include<iostream>
using namespace std;

class Node{
    public:
    int val;
    Node* next;
    Node(int value){
        val = value;
        next = nullptr;
    }
    ~Node(){
        cout << "Deconstructor called.";
    }
};
int main(){
    Node* n1 = new Node(5);
    n1->next = new Node(6);
    n1->next->next = new Node(7);
    n1->next->next->next = new Node(8);
    n1->next->next->next->next = new Node(9);
    Node* head = n1;
    while(head!=nullptr){
        cout << head->val << endl;
        head = head->next;
    }
    Node* slow=n1;
    Node* fast=n1;
    while(fast!=NULL && fast->next!=NULL){
        slow=slow->next;
        fast = fast->next->next;
    }
    cout << slow->val;
    return 0;
}