# 2048Fibonaccimod
Modified version of 2048 game

Mod - merging is done, if the corresponding cells are adjacent fibonacci numbers in the series
(0,1,1,2,3,5,8,13.. ) where (3,5,8) and (5,8,13) are adjacent fibonacci numbers.

A user can choose between four moves -

up - pulls up the numbers from the bottom cells to the upper empty cells | merges(Adds) if they are adjacent fibo numbers.
down - pushes down the numbers to the bottom empty cells from the upper cells | merges(Adds) if they are adjacent fibo numbers. 
Right - similar
Left - similar

Game is over - 
IF - all the cells are filled, no further moves are left || any of the cell equals the index of its correspoding fibonacci number in the fibonacci series.

Yet-todo - 
bug fixes (right | left merge)
Frontend
