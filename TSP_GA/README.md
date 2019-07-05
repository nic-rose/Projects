## Overview
This is a Genetic algoithm that solves the traveling salesman problem.
The traveling salesman problem is defined as below:
 - A salesmen wants to find the shortest path to taken in order to visit x number of cities without visiting the same city twice.
 - The salesman also wants to end in the same city that he started in.
 - The salesman can also go from and city to any city.  

http://www.math.uwaterloo.ca/tsp/world/countries.html

`TSP.py` was created by Dr. Art White and this program takes in data from the link above and turns the data in to a usable matrix that holds the distance between any two cities.  

The data comes from the tsp files that are also in this folder.  The name of the tsp files is the name of the tour that is going to be taken and then the number of cities that it includes. 
(ex. burma14 is the tour of Burma and it has 14 cities)

`GA_TSP_Tester.py` was created by myself with the purpose of finding the configuration of variables (populationSize, mutationRate, crossoverRate, and tournmentSize) that produces the best results for this problem.  The output of this program is `ConfigResults.txt`

The current `matrix.txt` which is the distance matrix produced by TSP.py is set up for the wi29 tour. Depending on the computer a single configuration should take about a minute to run.

`txtparser.py` is a script that goes through the large output file produced by GA_TSP_Tester.py and finds which configuration on average produces the best results. The output for this program is `Summary.txt`

`GA_TSP` is going to be the single configuration version on `GA_TSP_Tester` where it only runs one set of variables.  WIP.

## Setup/Run
1. Open `TSP.py` and give it the .tsp file that you would like to try.  (`wi29.tsp` is currently being used).

2. python3 `TSP.py`

3. python3 `GA_TSP_Tester.py` (also can be run with pypy).

4. python3 `txtparser.py`

5. Open `summary.txt`, this should contain the best configuration for the tour that was given.

6. Open `GA_TSP.py`, and change the global varibles to the configuration that was in `summary.txt`.

6. python3 `GA_TSP.py`

## In Depth Analysis
- Gen0
    A individual is created by making a list from 0 to the number of cities in the tour and then those numbers are suffled so the list is randomized. This is repeated until the entire population is created. 
- fitnessfunction 
    This function measures the number of miles it would take to complete the tour. This is done by the iterating through the list of cities and finding the distanced between the two cities until the end point is reached. 
- tournment
    The weakest individuls are removed from the population by a tournment style approach. The size of the tournment is determinded by a global varible, which can be any number between 2 and the population size.  The individuls are pulled from the old population and the individual with the best fitness will be placed new population.  This process is continued until the new population is created, this process is called a generation.  This approach's only guarantee is that the worst individul does not continue to the next population. 
- crossover
    Crossover is used to find the parts of an individul that makes that individul more fit and give them to other individuls in the population. Crossover is this program is implemented by randomly taking a segment of one individul and reordering the numbers in second individul to match the order of the numbers of the segment extracted from the first individul. This approach is simple and still produces good results.  
- mutation
    Mutation is used to help increase the search space of the algorithm to help find a better solution.  This is done by randomly swapping two cities in the individul (ex. 1,2,3,4,5 - 1,3,2,4,5).
- converge
    Converage searches through the entire population checking to see if 95% of the population is the same.  Once 95% of the population is the same this is the best solution that the algoithm found.  