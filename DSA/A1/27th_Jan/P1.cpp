// Write a Program to perform bitwise OR operation on two integers and print the result
// Program to count the number of set bits of an integer. 
// check if the number is even or odd

#include<iostream>
using namespace std;

int main(){
    int a,b;
    cin >> a >> b;
    // OR operator
    cout << (a | b);
    // count set bits
    int count=0;
    while(a>0) {
        count += (a&1);
        a = a >> 1;
    }
    // even or odd
    int num;
    cin >> num;
    if((num&1)==0) cout << "True";
    else cout << "False";
    return 0;
}