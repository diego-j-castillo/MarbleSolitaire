# MarbleSolitaire
A text-based adaptation of Marble Solitaire

## Usage
The game must be started by first declaring the desired game version.
There are 3 possible game versions:
- `european`
- `english`
- `triangle`

Further on, the user can specify two more starting aspects of the game:
the location of the starting hole and the size of the board. These are set with the 
commands `-hole row col` and `-size n`, respectively. 

While playing the game, movements are made by entering the coordinates of
the starting marble and the coordinates of the end slot. For example, 
`3 1 3 3` is a jump of the marble at (3, 1) - row 3 and column 1 - 
over another marble to the whole on its right at (3, 3). 

If at any point, the user wants to quit, simply enter `q`.
