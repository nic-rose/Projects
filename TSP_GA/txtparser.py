numberRuns = 10
outfile = open("summary.txt",'w')

def readinFile(fileName):
    infile = open(fileName,"r")
    average = 0
    averagecount = 0
    runList = []
    for line in infile:  
        lineList = line.split()
        # print(lineList)
        if lineList != []:
            if lineList[0] == "Fitness":
                # print(lineList)
                # print(average)
                average += float(lineList[2])
                averagecount += 1
                if averagecount % numberRuns == 0:
                    outfile.write(str(average)+"\n")
                    runList.append(average)
                    average = 0
            if averagecount % numberRuns == 0 and lineList[0] != "Fitness":
                runList.append(lineList[-1])
    return runList

def FindMinFitness(runList):
    minFit = 1000000.0
    params = []
    count = 0
    paramCount = 5
    flag = False
    for i in runList:
        if count % paramCount == 0:
            print(i)
            if float(i) < float(minFit):
                minFit = i
                flag = True
                params = []
            else:
                flag = False
        if flag == True:
            params.append(i)
        count += 1
    return params



def main():

    runList = readinFile("ConfigResults.txt")
    print(runList)
    paramsList = FindMinFitness(runList)
    print(paramsList)
    # outfile.close()

main()