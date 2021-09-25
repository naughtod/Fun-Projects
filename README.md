## About the Project
The game of Twenty Four is a card game played against others. 
In each round four cards are dealt from a deck and each player must try 
to combine the values of the cards using the operators (+, -, *, /) to get to 24. 
Ace has value 1 and other Picture cards can be removed or take value 10. 
All four cards must be used to get to 24. Frequently there is no solution for a given four cards (i.e. 2, 2, 2, 2).

This program starts a shell, then you put in the 4 cards separated by a space and it will tell you either that there is no solution or it will give you the final step in its calculation of the solution. However, this might not be the only possible final operation to get to 24. For example, if the cards were 4, 2, 3, 6 then the last step might be 8 * 3, but it could also be 18 + 6, 20 + 4 etc.

Code hacked together quickly because we play this game and needed it to be sure a hand has no solution. 
So, apologies for poor commenting and code structure.

## Libraries used
    import java.util.*
    import java.lang.*

## Getting Started
Compile with `javac TwentyFourSolver.java`  
Run with `java TwentyFourSolver`

## Example input / output
    $ 3 4 6 8
    12*2
    $ 9 9 9 9
    no solution


