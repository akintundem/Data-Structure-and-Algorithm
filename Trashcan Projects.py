# AkintundeMayokunMosesQ4.py
# 
# Course:      COMP 1012 
# Instructor:  Amir Hosseinmemar
# Assignement: 4 Question 1
# Author:      Mayokun Moses Akintunde
# Version:     2019/07/31
# 
# Purpose:     The purpose of this question is to write a complete Python program
                #that represents some trash cans using a two dimensional array.
import numpy
from time import ctime
def getPosInt(prompt):
    while True:
        value = input(prompt).strip()
        if value != '':
            try:
                number = eval(value,{}, {})
            except:
                print(value , " is not valid")
            else:
                if type(number) is int:
                    if number > 0:
                        print(number , "is valid")
                        return number
                    else:
                        print(number, "is not greater than 0")
                else:
                    print(number, "is not an integer")
        else:
            print("Missing Input!")
            
            
def getBoundedInt(prompt, lowerBound, upperBound):
    while True:
        value = input(prompt).strip()
        if value != '':
            try:
                number = eval(value,{}, {})
            except:
                print(value, "is not valid")
            else:
                if type(number) is int:
                    if number >= lowerBound and number < upperBound:
                        print(number , "is between interval")
                        return number
                        break
                    else:
                        print("%i is not between %i and %i!" %(number,lowerBound,upperBound))
                else:
                    print(number, "is not an integer")
        else:
            print("Missing Input")

def addTrashToCan(trashCans, can, counters):
    TF = ""
    trashCans[trashCans.shape[0]-counters[can],can] = counters[can]
    for columns in range(trashCans.shape[1]):
        if trashCans[0,columns] ==trashCans.shape[0]:
            TF = "Trash can %d is full! Time to have the trash picked up." %(can)
    return trashCans , TF

def displayTrashCans(TrashCans,TF):
    print(TF)
    for row in TrashCans:
        for column in row :
            if column != 0:
                print(column,end=" ")
            else:
                print(" " , end = " ")
            print("|" , end = " ")
        print()
    print("-"*TrashCans.shape[1]*4)


def DisplayTerminationMessage():
    print("""
          Programmed by : Akintunde Mayokun Moses
          Date : %s
          End of processing...  
          """ %(ctime()))
        
def main():
    print("*" *50)
    number_of_trash_cans= getPosInt("Enter number of trash cans(>0): ")
    cans = getPosInt("Enter number of bags per trash can(>0): ")
    trashCans = numpy.zeros((cans,number_of_trash_cans),"int") 
    counter = numpy.zeros(number_of_trash_cans,"int")#holds number of cans  in each trash
    can = getBoundedInt("Enter a trash can number between 0 and %d: " %(number_of_trash_cans-1), 0 , number_of_trash_cans)
    counter[can] = counter[can]+1
    TrashCans,TF = addTrashToCan(trashCans, can, counter)
    displayTrashCans(TrashCans,TF)
    while counter[can] < cans:
        can = getBoundedInt("Enter a trash can number between 0 and %d: " %(number_of_trash_cans-1), 0 , number_of_trash_cans)
        counter[can] = counter[can]+1
        TrashCans,TF = addTrashToCan(trashCans, can, counter)
        displayTrashCans(TrashCans,TF)
    DisplayTerminationMessage()        
main()