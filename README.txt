Traveling Salesman Problem (TSP) using Branch and Bound Technique

This project includes implementations of the Traveling Salesman Problem (TSP) using the Branch and Bound technique in Java, C++, and Python. The Java version provides two variations: one for entering distances manually and another using an adjacency matrix. The C++ and Python versions use an adjacency matrix.

Branch and Bound Technique:
The Branch and Bound technique is used to solve combinatorial optimization problems. It systematically explores all possible solutions to find the optimal solution. In the context of TSP, it explores different permutations of cities to find the shortest possible route that visits each city exactly once and returns to the starting city.
It focuses on finding the optimal solution by using bounds to eliminate suboptimal solutions early in the process compared to backtracking which 
focuses on finding all solutions or a specific valid solution without necessarily optimizing an objective function.

Files:
- Java Code (Entering Distances Manually):enterdistnces.java
- Java Code (Using Adjacency Matrix):TSPBranchAndBound.java
NOTE: ***If you change the filenames for the java programs, make sure you modify the classes to match your filename.***
- C++ Code (Using Adjacency Matrix): C++code.cpp
- Python Code (Using Adjacency Matrix): code4.py
- README.txt

Requirements:
- For Java: JDK 8 or higher
- For C++: A C++ compiler (e.g., g++)
- For Python: Python 3.x(install in the terminal if you are using linux.)

Instructions:
1. Compile and run the Java code using:
 -Ensure the ava Development Kit (JDK) is installed
 -Set up the enviroment variables.
 -write or open and edit the existing code.
  -open cmd and write the command after navigating to the correct director containing the codes
     ***javac filename.java*** then ***java filename*
2. For python use a text editor like Visual  Code(VScode)/ run it on the cmd
by navigating to the correct   directory and type **python filename.py + ENTER**
3. for c++, install a text editor,(recommended -> CodeBlocks IDE/ Visual studio code)
or in the cmd, ensure you have MINGW installed and path configured and then type
*** g++ -o <nameofyourchoice>  <yourfilename.cpp> + ENTER****
then run it by typing <nameofyourfile>

For the TSP using Branch and Bound, the worst-case time complexity is O(N!), where N is the number of cities.
This is because, in the worst case, the algorithm might have to explore all possible permutations of the cities to find the optimal tour.