# Roadmap
## MiniMax Player
 Implement a player utilising the minimax algorithm.
 
 Currently we're not passing the state of the game to the player, so we'll
 need to do that so that the player can make a decision based on the board.
 We should probably start by refactoring the current code to do this, and encapsulating
 the state of the game into a separate object pulling it out of Connect4.
 
 Once we have that, then we can construct game states and pass them to the
 player and test for the terminal conditions in minimax. The terminal conditions are
 where:
 
 * It is our turn and we have three in a row and can place a fourth and final token
   completing a run.
 * It is the opponents turn and they have three in a row and can place a fourth and
   final token completing their run.
  
 It is worth noting we could also figure out whether we'll win or lose 
 in some circumstances based on some more intelligent guessing although this wouldn't
 be vanilla minimax.
 
 After having completed the functionality to deal with the terminal conditions we
 need to start to triangulate the recursion rules.
 
 * If it is our turn, we want to maximise the score of the move, so we pick the max
   of all the options
 * If it is the opponents turn, we assume they will try to minimize our score, so we
   pick the min of all the options
  
 