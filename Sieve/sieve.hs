prods    ::  Int  -> [Int]              -- gen inf list of products
prods x = [(x*x),((x*x)+x)..]

mix      :: [Int] -> [Int] -> [Int]     -- mix two inf lists
mix x y = 
    if (head x) > (head y) then
        (head y) : mix x (tail y)
    else if (head x) < (head y) then
        (head x) : mix (tail x) y
    else
        (head y) : mix (tail x) (tail y)

sieve    :: [Int] -> [Int] -> [Int]     -- sieve of eratosthenes
sieve x y =
    if y == [] then
        (head x) : sieve (tail x) (prods (head x))
    else if x == [] then
        []
    else if (head x) /= (head y) then
        (head x) : sieve (tail x) (mix (y) (prods (head x)))
    else
        sieve (tail x) (mix (tail y) (prods (head x)))

firstn   ::  Int  -> [Int]              -- returns first n primes
firstn x = 
    take x (sieve [2 ..] [])

primesto ::  Int  -> [Int]              -- returns primes up to p
primesto x =
    sieve [2..x] []

secondMix :: [Int]  -> [Int] -> [Int]
secondMix x y= 
        if  x == [] then
            y
        else if y == [] then
            x
        else if (head x) > (head y) then
            (head y) : secondMix x (tail y)
        else 
            (head x) : secondMix (tail x) y

mergesort :: [Int]  -> [Int]            -- sorts list using merge sort alg
mergesort x =
    if (length x) > 1 then
        secondMix (mergesort (take (div (length x) 2) x)) (mergesort [x!!y | y <- [(div (length x) 2) .. ((length x) - 1)]])
    else 
        x
