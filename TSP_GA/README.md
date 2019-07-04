## Overview
This is a Genetic algoithm that solves the traveling salesman problem.
The traveling salesman problem is defined as below:
 - A salesmen wants to find the shortest path to taken in order to visit x number of cities without visiting the same city twice.
 - The salesman also wants to end in the same city that he started in.
 - The salesman can also go from and city to any city.  

## http://www.math.uwaterloo.ca/tsp/world/countries.html
`TSP.py` was created by Dr. Art White and this program takes in data from the link above and turns the data in to a usable matrix that holds the distance between any two cities.  

The data comes from the tsp files that are also in this folder.  The name of the tsp files is the name of the tour that is going to be taken and then the number of cities that it includes. 
(ex. burma14 is the tour of Burma and it has 14 cities)

`GA_TSP_Tester.py` was created by myself with the purpose of finding the configuration of variables (populationSize, mutationRate, crossoverRate, and tournmentSize) that produces the best results for this problem.  The output of this program is `ConfigResults.txt`

The current `matrix.txt` which is the distance matrix produced by TSP.py is set up for the wi29 tour. Depending on the computer a single configuration should take about a minute to run.

`txtparser.py` is a script that goes through the large output file produced by GA_TSP_Tester.py and finds which configuration on average produces the best results. The output for this program is `Summary.txt`

`GA_TSP` is going to be the single configuration version on `GA_TSP_Tester` where it only runs one set of variables.  WIP.