# matchingStringInArray
# This line appears in master branch
The purpose of this program is to extract matching strings from an array regardless of the characters order.  

You will write a Java class that accepts an array of strings in the constructor. The class will expose a find function that accepts a string. The function will return all strings from the array that contains the exact same characters as the string passed into it. The order of the characters in the strings is not relevant.  

For example, given the following array:  
["asd", "asdd", "fre", "glk", "lkm"]
and the input "sad", the program should return an array containing the string "asd" (the string which matches "sad") .  

In the case where more than one member of the initialization array matches the key the return array will have more than one member.
