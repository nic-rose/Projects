## Assigment Tracker

def options():
    print("1. Add an assignment")
    print("2. Show assignments")
    print("3. Delete assignment")
    option = input("choose the number that needs done ")
    return option

def addAssignment(assignments):
    info = input("Give info about assignment: day its due, hours to complete, class, Name (ex. 9/2/19 1 Cos120 L01) ")
    infolst = info.split()
    assignments.append(infolst)
    
def showAssignments(assignments):
    for i in assignments:
        aStr = ""
        for j in i:
            aStr = aStr + j + " "
        print(aStr)

def deleteAssignment(assignments):
    removeA = input("What is the name of the assignment you would like to delete? ")
    count = 0
    for i in assignments:
        if i[3] == removeA:
            assignments.pop(count)

        count +=1

def main():
    infile = open("assignments.txt", "r")
    assignments = []
    for i in infile:
        linelst = i.split()
        assignments.append(linelst)

    infile.close()
    option = options()

    if option == "1":
        addAssignment(assignments)
    if option == "2":
        showAssignments(assignments)
    if option == "3":
        deleteAssignment(assignments)

    outfile = open("assignments.txt", "w")
    for i in assignments:
        for j in i:
            outfile.write(j + " ")
        outfile.write("\n")
main()