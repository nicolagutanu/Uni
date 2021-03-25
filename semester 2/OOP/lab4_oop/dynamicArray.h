#pragma once
#include "domain.h"

typedef struct
{
	Bot* bots;
	int length;
	int capacity;
}DynamicArray;

/*Constructor of a dynamic array of bot elems*/
DynamicArray* createDynamicArray(int capacity);

/*Destructor of the dynamic array of bot elems*/
void destroyDynamicArray(DynamicArray* dynamicArray);

/*Rezise function in case of overflow of capacity*/
int resize(DynamicArray* dynamicArray);

/*Function that adds an elem to the dynamic array*/
void addElement(DynamicArray* dynamicArray, Bot botToAdd);

/*Function that removes an elem from the dynamic array*/
void deleteElement(DynamicArray* dynamicArray, int position_of_botToDelete);

/*Function that updates the atributes of an elem from the dynamic array*/
void updateElement(DynamicArray* dynamicArray, Bot newBot, int position_of_botToUpdate);

/*Function that returns the length of the dynamic array*/
int getLength(DynamicArray* dynamicArray);

/*Function that returns an elem from the dynamic array given its serialNumber*/
Bot getElement(DynamicArray* dynamicArray, int position);

/*Function that returns all elems of the dynamic array*/
Bot* getElements(DynamicArray* dynamicArray);