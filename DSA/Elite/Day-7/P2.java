/*
In the world of secret codes and cryptography, you are entrusted with deciphering 
a hidden message. You have two encoded keys, key1 and key2, both of equal length. 
Each character in key1 is paired with the corresponding character in key2. 

This relationship follows the standard rules of an equivalence cipher:
• Self-Mapping: Every character inherently maps to itself.  
• Mutual Mapping: If a character from key1 maps to one in key2, then that 
  character in key2 maps back to the one in key1.  
• Chain Mapping: If character A maps to B, and B maps to C, then A, B, and C 
  are all interchangeable in this cipher.

Using this mapping, you must decode a cipherText, by replacing every character 
with the smallest equivalent character from its equivalence group. 
The result should be the relatively smallest possible decoded message.


Input Format:
-------------
Three space separated strings, key1 , key2 and cipherText

Output Format:
--------------
Print a string, decoded message which is relatively smallest string of cipherText.

Sample Input-1:
---------------
attitude progress apriori

Sample Output-1:
----------------
aaogoog

Explanation: 
-------------
The mapping pairs form groups: [a, p], [o, r, t], [g, i], [e, u], 
[d, e, s]. By substituting each character in cipherText with the smallest from 
its group, you decode the message to "aaogoog".


Constraints:  
• 1 <= key1.length, key2.length, cipherText.length <= 1000  
• key1.length == key2.length  
• key1, key2, and cipherText consist solely of lowercase English letters.

*/
import java.util.*;
class DSU {
    char[] parent;
    DSU() {
        parent = new char[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = (char)('a' + i);
        }
    }
    char find(char x) {
        int i = x - 'a';
        if (parent[i] != x) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }
    void union(char a, char b) {
        char pa = find(a);
        char pb = find(b);
        if (pa == pb) return;
        if (pa < pb) {
            parent[pb - 'a'] = pa;
        } else {
            parent[pa - 'a'] = pb;
        }
    }
}


public class P2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        String cipher = sc.next();
        DSU d = new DSU();
        int j = 0;
        for (int i = 0; i < s1.length(); i++) {
            d.union(s1.charAt(i), s2.charAt(j));
            j++;
        }
        String ans = "";
        for (char c: cipher.toCharArray()) {
            ans += d.find(c);
        }
        System.out.println(ans);
    }
}