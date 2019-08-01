## Overview
Sieve is a simple, ancient algorithm that is used for finding every prime number up to any limit.
This is done by iteratively finding the multiples of each prime because these numbers can not be prime (typically starts are the first prime number 2). 

The Sieve algorithm takes advantage of Haskells lazy lists because even though the programm can run for forever the program with allows run because it is done iterativley and not 
recursivly. 

## In Depth Analysis
- prods

    Prods returns an infinite list where the first index of the list is the square of the number that is passed in.
- mix

    Mix takes in two infinite lists that as in assending order and merges them in to a single lists that is in assending order with no duplicates.
- sieve
 
    Sieve takes two lists the first one being a list of potential primes and the second being a list of numbers we know that are not prime.  It then returns a list of primes numbers. For example if the input is [2..] [] then sieve returns every prime number. 
- firstn

    Firstn returns the first n primes where n is the number passed in to the function by using the sieve function above.  
- primesto

    primesto returns all of the prime numbers up to p where p is inclusive.  
- mergesort

    Mergesort takes in a single list of any size and sorts it using the mergesort algorithm.
