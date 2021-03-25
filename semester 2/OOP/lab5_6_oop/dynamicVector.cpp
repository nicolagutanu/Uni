#include "dynamicVector.h"
#include <stdlib.h>
#include <iostream>

DynamicVector::DynamicVector(int capacity)
{
	this->length = 0;
	this->capacity = capacity;
	this->elements = new Plant[capacity];
}

DynamicVector::DynamicVector(const DynamicVector& vector_to_copy)
{
	this->length = vector_to_copy.length;
	this->capacity = vector_to_copy.capacity;
	this->elements = vector_to_copy.elements;
}

DynamicVector::~DynamicVector()
{
	delete[] this->elements;
}

void DynamicVector::resize(int factor)
{
	this->capacity *= static_cast<int>(factor);
	Plant* copy_elements = new Plant[this->capacity];
	for (int i = 0; i < this->length; i++)
		copy_elements[i] = this->elements[i];
	delete[] this->elements;
	this->elements = copy_elements;
}

void DynamicVector::addPlantToDatabase(const Plant& plantToAdd)
{
	if (this->length == this->capacity)
		this->resize();
	this->elements[this->length] = plantToAdd;
	this->length += 1;
}

void DynamicVector::deletePlantFromDatabase(const int& position_of_plantToDelete)
{
	for (int i = position_of_plantToDelete; i < this->length - 1; i++)
		this->elements[i] = this->elements[i + 1];
	this->length -= 1;
}

void DynamicVector::updatePlantInDatabase(const Plant& newPlant, const int& position_of_plantToUpdate)
{
	this->elements[position_of_plantToUpdate] = newPlant;
}

int DynamicVector::getLength()
{
	return this->length;
}

Plant DynamicVector::getElement(const int& position_of_plantToFind)
{
	return this->elements[position_of_plantToFind];
}

Plant* DynamicVector::getElements()
{
	return this->elements;
}