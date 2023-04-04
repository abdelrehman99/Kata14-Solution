# Kata14-Solution
### My thinking process: 
* First, I thought about the challenge as it said to map two adjacent
words directly to the third. But I soon realised that it 
might go to an infinity loop. like for example when the
input is "there there there" this expression can be said
from an excited person found something he has been looking
for. So I needed to handle that part. Then it popped up in
my mind to handle it by brute force like backtracking, and 
not passing through the same cycle again. I thought about it 
in a way that I can implement it in graph theory DFS algorithm.
where I can make every node a combination of two words index,
that I am reading the book as an array of words after it adding
them to an adjacency list and adding the third word to this list.
And then traversing the list in DFS and marking every node that I
visited as a visited node so, I don't traverse to it again. But,
I found that this solution is not going to be correct because
the question mentioned that we must take its trigram, and
my solution at some point will not take a trigram if there is
any, because they will be visited. 
* Second, I thought about it in a more random way. I decided it will be
best if I choose the trigrams of every two adjacent ways randomly. This
means that the sentence could continue from this trigram or previous or
even the front. It gave me good results, but it could still go in infinity
loop and never end. So I decided that the trigrammed book should not be 
more than the book size.
* Finally, I thought about implementing it and then realized that nothing
better than a hashmap to store the trigrams in. The hashmap stores the key as
String and the value as an arraylist of Strings, So that it has all the trigrams.
So I took in the book words as an arrayList and then everytime when the arraylist has more than one word
I added the new word to the Arraylist of the hashmap of the last two words combined.
When writing the trigram book I started by generating a random integer to choose
the first pair of words. And add it to an Arraylist of strings and everytime this
array has more than 1 word I would get its map and choose an arbitrary value from its
Arraylist of values, if this key has no values i would then stop the program. Then
I write the arraylist of trigrams to the results folder with the name of the book
trigrammed.

### Tests:
* First, I handled the case of going to an infinity loop by ensuring that the
trigrams does not exceed the book size. By giving it input "I I I" which resulted in "I I I" output.
* Second, I handled the case when the size of the book is less than two words. where 
when I gave it input "I" this resulted in a crash, but I handled it so that when there 
is less than two words just print the book again, so now my output is "I".
* Third, I handled when the word is only a symbol, so I decided to remove it at all
to make sure that it's not interfering with the actual results. and I tested it by 
giving the input "I *** am *** Happy ***" which resulted "I am Happy".
* Fourth, I handled in the main menu that when the user gives me a float number for
example I would then ask him to give me a number from one to the number of books I have.
* Fifth, I handled that the trigrammed book should have 120 characters max per line, 
So wrote to the file that when the string builder size + line size exceeds or equals
120 it prints a new line. I also tested that when the line does not exceed 120 characters
I need to print it after the for loop, it was tested by this test "I am Happy", which 
didn't print anything at all.

### Run: 
This was developed on IntelliJ Idea, you can run it by directly compiling the Main.java file. 