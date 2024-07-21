# Python program to solve 
# Traveling Salesman Problem using 
# Branch and Bound.

import math

# Constants
N = 5
maxsize = float('inf')

# Function to copy the current path to the final path
def copyToFinal(curr_path):
    final_path[:N + 1] = curr_path[:]
    final_path[N] = curr_path[0]

# Function to find the minimum edge cost ending at the vertex i
def firstMin(adj, i):
    min_val = maxsize
    for k in range(N):
        if adj[i][k] < min_val and i != k:
            min_val = adj[i][k]
    return min_val

# Function to find the second minimum edge cost ending at the vertex i
def secondMin(adj, i):
    first, second = maxsize, maxsize
    for j in range(N):
        if i == j:
            continue
        if adj[i][j] <= first:
            second = first
            first = adj[i][j]
        elif adj[i][j] <= second and adj[i][j] != first:
            second = adj[i][j]
    return second

# Recursive function to solve TSP using Branch and Bound
def TSPRec(adj, curr_bound, curr_weight, level, curr_path):
    if level == N:
        if adj[curr_path[level - 1]][curr_path[0]] != 0:
            curr_res = curr_weight + adj[curr_path[level - 1]][curr_path[0]]
            if curr_res < final_res[0]:
                copyToFinal(curr_path)
                final_res[0] = curr_res
        return

    for i in range(N):
        if adj[curr_path[level - 1]][i] != 0 and visited[i] == False:
            temp = curr_bound
            curr_weight += adj[curr_path[level - 1]][i]

            if level == 1:
                curr_bound -= ((firstMin(adj, curr_path[level - 1]) + firstMin(adj, i)) / 2)
            else:
                curr_bound -= ((secondMin(adj, curr_path[level - 1]) + firstMin(adj, i)) / 2)

            if curr_bound + curr_weight < final_res[0]:
                curr_path[level] = i
                visited[i] = True
                TSPRec(adj, curr_bound, curr_weight, level + 1, curr_path)

            curr_weight -= adj[curr_path[level - 1]][i]
            curr_bound = temp

            visited[:] = [False] * len(visited)
            for j in range(level):
                if curr_path[j] != -1:
                    visited[curr_path[j]] = True

# Function to setup and solve TSP
def TSP(adj):
    curr_bound = 0
    curr_path = [-1] * (N + 1)
    visited[:] = [False] * N

    for i in range(N):
        curr_bound += (firstMin(adj, i) + secondMin(adj, i))

    curr_bound = (curr_bound // 2) if (curr_bound % 2) == 0 else (curr_bound // 2 + 1)
    visited[0] = True
    curr_path[0] = 0

    TSPRec(adj, curr_bound, 0, 1, curr_path)

if __name__ == "__main__":
    adj = [
        [0, 29, 20, 21, 16],
        [29, 0, 15, 17, 28],
        [20, 15, 0, 18, 23],
        [21, 17, 18, 0, 12],
        [16, 28, 23, 12, 0]
    ]
    
    final_path = [None] * (N + 1)
    visited = [False] * N
    final_res = [maxsize]

    TSP(adj)

    print("Minimum cost:", final_res[0])
    print("Path Taken:", end=" ")
    for i in range(N + 1):
        print(final_path[i], end=" ")
    print()
