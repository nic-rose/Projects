import random
numberGens = 3000
# populationSize = 1000
# mutationRate = .045
# crossoverRate = .7
# tournmentSize = 2

startingCity = "0"
outfile = open("ConfigResults.txt",'w')
def readinFile(fileName):
    infile = open(fileName,"r")
    cityList = []
    numberCity = 0
    for line in infile:
        numberCity += 1
        rowList = []
        newline = line.strip(' ,\n')
        splitLine = newline.split(",")
        for i in splitLine:
            if i[0] == "[":
                i = i[1:]
            if i[-1] == "]":
                i = i[:-1]

            rowList.append(float(i))
        cityList.append(rowList)
    return cityList, numberCity

cityList, numberCity = readinFile("matrix.txt")

def gen0(): 
    populationList = []
    for x in range(populationSize):
        inOrderList = []
        for i in range(1,numberCity):
           inOrderList.append(i)
        random.shuffle(inOrderList)
        inOrderList.insert(0,int(startingCity))
        inOrderList.append(int(startingCity))
        populationList.append(inOrderList)
    return populationList

def fitnessFunction(individual): 
    fitness = 0
    adds = 0
    for i in range(len(individual)):
        if i < numberCity:
            fitness += cityList[individual[i]][individual[i+1]]
        if i + 1 == len(individual)-1:
            fitness += cityList[individual[i]][individual[i+1]]
    return fitness

def tournment(populationList): 
    newPopulationList = []
    for i in range(populationSize):
        tournmentList = []
        tournmentFit = []
        for j in range(tournmentSize):
            x = random.randint(0,len(populationList)-1)
            while x in tournmentList:
                x = random.randint(0,len(populationList)-1)
            tournmentList.append(x)
            tournmentFit.append(fitnessFunction(populationList[x]))
        winneridx = tournmentFit.index(min(tournmentFit))
        newPopulationList.append(populationList[tournmentList[winneridx]])
    return newPopulationList

def crossoverSetUp(populationList): 
    for individual in range(populationSize):
        if random.random() < crossoverRate:
            pop1 = random.randint(0,populationSize-1) 
            pop2 = random.randint(0,populationSize-1)
            while pop1 == pop2:
                pop1 = random.randint(0,populationSize-1)
            populationList[pop1], populationList[pop2] = crossover(populationList[pop1],populationList[pop2])
    return populationList

def crossover(pop1,pop2): 
    firstIdx = random.randint(1,len(pop1)-2)
    secondIdx = random.randint(1,len(pop1)-2)
    while firstIdx == secondIdx:
        firstIdx = random.randint(1,len(pop1)-2)
    if secondIdx < firstIdx:
        swap = secondIdx 
        secondIdx = firstIdx
        firstIdx = swap
    temp = []
    for i in pop1:
        temp.append(i)
    temp[firstIdx:secondIdx] = sorted(pop1[firstIdx:secondIdx], key=lambda x: pop2.index(x))
    pop2[firstIdx:secondIdx] = sorted(pop2[firstIdx:secondIdx], key=lambda x: pop1.index(x))
    pop1 = temp
    return pop1,pop2

def mutation(individual): 
    first = random.randint(1,len(individual)-2)
    second = random.randint(1,len(individual)-2)
    while first == second:
        first = random.randint(1,len(individual)-2)
    swap = individual[first]
    individual[first] = individual[second]
    individual[second] = swap
    return individual

def converge(populationList, numGens):
    convergeList = []
    lst = []
    for a in range(len(populationList)):
        # print(populationList[a])
        if populationList[a] not in convergeList:
            convergeList.append(populationList[a])
            lst.append(1)
        else:
            idx = convergeList.index(populationList[a])
            lst[idx] += 1 
        # print(lst)
    for b in range(len(lst)):
        if (float(lst[b])/float(populationSize)) > .95:
            outfile.write("Fitness = "+ str(fitnessFunction(populationList[populationList.index(convergeList[b])]))+"\n")
            print("Fitness = "+ str(fitnessFunction(populationList[populationList.index(convergeList[b])])))
            print("converged in", numGens)
            return True
    return False

def main():

    # creation of the first generation
    global populationSize
    global mutationRate
    global crossoverRate
    global tournmentSize
    for populationSize in [1000,1500,2000,3000,5000,8000]:
        for mutationRate in [.05,.03,.02,.01]:
            for crossoverRate in [.5,.6,.7,.8]:
                for tournmentSize in [2,3]:
                    for x in range(10):
                        populationList = gen0()
                        for i in range(numberGens):
                            # print(i)
                            if converge(populationList, i):
                                break
                            else:
                                populationList = tournment(populationList)
                                # CrossoverionList
                                populationList = crossoverSetUp(populationList)
                                # Mutation
                                for individual in range(populationSize):
                                    if (random.random() < mutationRate):
                                        populationList[individual] = mutation(populationList[individual])
                            if i == numberGens-1:
                                # Generate a Random Indvidual to produce a elevated fitness to skew the average higher
                                # because the configuration did not converge.  
                                newIndvidual = []
                                for i in range(1,numberCity):
                                    newIndvidual.append(i)
                                random.shuffle(newIndvidual)
                                newIndvidual.insert(0,int(startingCity))
                                newIndvidual.append(int(startingCity))

                                outfile.write("Fitness = "+ fitnessFunction(newIndvidual) +"\n")
                                print("did not converge")

                        outfile.write("PopulationSize = "+str(populationSize)+"\n")
                        outfile.write("mutationRate = "+str(mutationRate)+"\n")
                        outfile.write("crossoverRate = "+str(crossoverRate)+"\n")
                        outfile.write("tournmentSize = "+str(tournmentSize)+"\n")
                        # outfile.write("number of inters = "+str(i)+"\n")
                        outfile.write("\n")
                        print(populationSize,mutationRate,crossoverRate,tournmentSize)
main()
outfile.close()