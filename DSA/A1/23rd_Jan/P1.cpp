/*
LEETCODE 2379. Minimum Recolors to Get K Consecutive Black Blocks

You are given a 0-indexed string blocks of length n, 
where blocks[i] is either 'W' or 'B', representing the color of the ith block. 
The characters 'W' and 'B' denote the colors white and black, respectively.

You are also given an integer k, which is the desired number of consecutive 
black blocks.

In one operation, you can recolor a white block such that it becomes a black 
block.

Return the minimum number of operations needed such that there is at least one 
occurrence of k consecutive black blocks.

input format: string an integer(k)
output format : an Integer
 
Example 1:

Input: WBBWWBBWBW
7
Output: 3
Explanation:
One way to achieve 7 consecutive black blocks is to recolor the 0th, 3rd, 
and 4th blocks
so that blocks = "BBBBBBBWBW". 
It can be shown that there is no way to achieve 7 consecutive black blocks in 
less than 3 operations.
Therefore, we return 3.

Example 2:
Input:WBWBBBW
2
Output: 0

Explanation:
No changes need to be made, since 2 consecutive black blocks already exist.
Therefore, we return 0.
 

Constraints:

n == blocks.length
1 <= n <= 100
blocks[i] is either 'W' or 'B'.
1 <= k <= n
*/

/*
You are given a 0-indexed string blocks of length n, 
where blocks[i] is either 'W' or 'B', representing the color of the ith block. 
The characters 'W' and 'B' denote the colors white and black, respectively.

You are also given an integer k, which is the desired number of consecutive 
black blocks.

In one operation, you can recolor a white block such that it becomes a black 
block.

Return the minimum number of operations needed such that there is at least one 
occurrence of k consecutive black blocks.

input format: string an integer(k)
output format : an Integer
 
Example 1:

Input: WBBWWBBWBW
7
Output: 3
Explanation:
One way to achieve 7 consecutive black blocks is to recolor the 0th, 3rd, 
and 4th blocks
so that blocks = "BBBBBBBWBW". 
It can be shown that there is no way to achieve 7 consecutive black blocks in 
less than 3 operations.
Therefore, we return 3.

Example 2:
Input:WBWBBBW
2
Output: 0

Explanation:
No changes need to be made, since 2 consecutive black blocks already exist.
Therefore, we return 0.
 

Constraints:

n == blocks.length
1 <= n <= 100
blocks[i] is either 'W' or 'B'.
1 <= k <= n
*/

#include<iostream>
#include<string>
using namespace std;

int main(){
    string s;
    getline(cin,s);
    int k;
    cin >> k;
    int whitecount=0;
    for(int i=0;i<k;i++){
        if(s[i]=='W') whitecount++;
    }
    // Implement sliding window
    int ans= whitecount;
    for(int i=k;i<s.size();i++){
        if(s[i-k]=='W') whitecount--;
        if(s[i]=='W') whitecount++;
        ans = min(ans,whitecount);
    }
    cout << ans;
    return 0;
}