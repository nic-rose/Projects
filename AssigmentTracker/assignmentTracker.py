## Assigment Tracker

def options():
    print("1. Add an assignment")
    print("2. Show assignments")
    print("3. Delete assignment")
    print("4. Edit assignmment")
    print("5. Complete assignment")
    print("Exit. To stop the program")
    option = input("choose the number that needs done ")
    return option

def addAssignment(assignments):
    info = input("Give info about assignment: day its due, when it is due, hours to complete, class, Name (ex. 9/2/19 1 Cos120 L01) ")
    infolst = info.split()
    assignments.append(infolst)
    
def sortAssignments(assignments):
    flag = input("What would you like to sort by? ")
    if flag in ["Date", "date", "d"]:
        assignments.sort(key=lambda x: x[0])
    if flag in ["s", "short"]:
        assignments.sort(key=lambda x: x[2])
    if flag in ["l", "long"]:
        assignments.sort(key=lambda x: x[2], reverse = True)
    if flag in ["class", "c"]:
        assignments.sort(key=lambda x: x[3])
    if flag in ["name", "n", "assignment", "a"]:
        assignments.sort(key=lambda x: x[4])
        
def showAssignments(assignments):
    sortAssignments(assignments)
    print()
    for i in assignments:
        aStr = ""
        for j in i:
            aStr = aStr + j + " "
        print(aStr)
    print()

def deleteAssignment(assignments):
    removeA = input("What is the name of the assignment you would like to delete? ")
    count = 0
    for i in assignments:
        if i[3] == removeA:
            assignments.pop(count)
        count +=1

def editAssignment(assignments):
    deleteAssignment(assignments)
    addAssignment(assignments)

def main(): 
    infile = open("assignments.txt", "r")
    assignments = []
    for i in infile:
        linelst = i.split()
        assignments.append(linelst)
    infile.close()

    option = options()
    while 1:
        if option == "1":
            addAssignment(assignments)
        if option == "2":
            showAssignments(assignments)
        if option == "3":
            deleteAssignment(assignments)
        if option == "4":
            editAssignment(assignments)
        if option == "5":
            completeAssignment(assignments)
        if option in ["exit", "Exit", "e", "quit", "Quit", "q"]:
            print("Exiting")
            break

        option = options()


    outfile = open("assignments.txt", "w")
    for i in assignments:
        for j in i:
            outfile.write(j + " ")
        outfile.write("\n")
main()