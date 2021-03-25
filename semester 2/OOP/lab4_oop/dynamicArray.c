#include "dynamicArray.h"
#include <stdlib.h>

DynamicArray* createDynamicArray(int capacity)
{
	DynamicArray* dynamicArray = (DynamicArray*)malloc(sizeof(DynamicArray));
	if (dynamicArray == NULL)
		return;
	dynamicArray->capacity = capacity;
	dynamicArray->length = 0;
	dynamicArray->bots = (Bot*)malloc(capacity * sizeof(Bot));
	if (dynamicArray->bots == NULL)
		return;
	return dynamicArray;
}

void destroyDynamicArray(DynamicArray* dynamicArray)
{
	if (dynamicArray == NULL)
		return;
	free(dynamicArray->bots);
	dynamicArray->bots = NULL;
	free(dynamicArray);
}

int resize(DynamicArray* dynamicArray)
{
	if (dynamicArray == NULL)
		return;
	dynamicArray->capacity *= 2;
	Bot* copyBotsArray = (Bot*)realloc(dynamicArray->bots, dynamicArray->capacity * sizeof(Bot));
	if (copyBotsArray == -1)
		return -1;
	dynamicArray->bots = copyBotsArray;
	return 0;
}

void addElement(DynamicArray* dynamicArray, Bot botToAdd)
{
	if (dynamicArray == NULL)
		return;
	if (dynamicArray->bots == NULL)
		return;
	if (dynamicArray->length == dynamicArray->capacity)
		resize(dynamicArray);
	dynamicArray->bots[dynamicArray->length] = botToAdd;
	dynamicArray->length += 1;
}

void deleteElement(DynamicArray* dynamicArray, int position_of_botToDelete)
{
	if (dynamicArray == NULL)
		return;
	if (dynamicArray->bots == NULL)
		return;
	/*if (dynamicArray->length == 1)
		dynamicArray->bots = NULL;*/
	for (int i = position_of_botToDelete; i < dynamicArray->length - 1; i++)
	{
		dynamicArray->bots[i] = dynamicArray->bots[i + 1];
	}
	dynamicArray->length -= 1;
}

void updateElement(DynamicArray* dynamicArray, Bot newBot, int position_of_botToUpdate)
{
	if (dynamicArray == NULL)
		return;
	if (dynamicArray->bots == NULL)
		return;
	dynamicArray->bots[position_of_botToUpdate] = newBot;
}

int getLength(DynamicArray* dynamicArray)
{
	if (dynamicArray == NULL)
		return;
	return dynamicArray->length;
}

Bot getElement(DynamicArray* dynamicArray, int position)
{
	if (dynamicArray == NULL)
		return;
	if (dynamicArray->bots == NULL)
		return;
	return dynamicArray->bots[position];
}

Bot* getElements(DynamicArray* dynamicArray)
{
	if (dynamicArray == NULL)
		return;
	return dynamicArray->bots;
}