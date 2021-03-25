#pragma once
#include "domain.h"
#include <iostream>

template <typename TypePlant>

class DynamicVector
{
private:

	TypePlant* elements;
	int capacity;
	int length;

	void resize(int factor = 2);

public:
	/* Default constructor */
	DynamicVector(int capacity = 10);

	/* Copy constructor */
	DynamicVector(const DynamicVector& vector_to_copy);

	/* Destructor*/
	~DynamicVector();

	/* Function that adds an element */
	void addPlantToDatabase(const TypePlant& plantToAdd);
	/* Function that deletes an element */
	void deletePlantFromDatabase(const int& position_of_plantToDelete);
	/* Function that updates an element */
	void updatePlantInDatabase(const TypePlant& newPlant, const int& position_of_plantToUpdate);
	/* Function that returns the length of the repo */
	int getLength();
	/* Function that return an element on a given position */
	TypePlant getElement(const int& position_of_plantToFind);
	/* Function that returns all elements in the repo */
	TypePlant* getElements();
};

template<typename TypePlant>
inline void DynamicVector<TypePlant>::resize(int factor)
{
	this->capacity *= factor;
	TypePlant* auxiliary_vector = new TypePlant[this->capacity];
	for (int i = 0; i < this->length; i++)
		auxiliary_vector[i] = this->elements[i];
	delete[] this->elements;
	this->elements = auxiliary_vector;
}

template<typename TypePlant>
inline DynamicVector<TypePlant>::DynamicVector(int capacity)
{
	this->length = 0;
	this->capacity = capacity;
	this->elements = new TypePlant[this->capacity];
}

template<typename TypePlant>
inline DynamicVector<TypePlant>::DynamicVector(const DynamicVector& vector_to_copy)
{
	this->capacity = vector_to_copy.capacity;
	this->length = vector_to_copy.length;
	this->elements = new TypePlant[this->capacity];
	for (int i = 0; i < this->length; i++)
		this->elements[i] = vector_to_copy.elements[i];
}

template<typename TypePlant>
inline DynamicVector<TypePlant>::~DynamicVector()
{
	delete[] this->elements;
}

template<typename TypePlant>
inline void DynamicVector<TypePlant>::addPlantToDatabase(const TypePlant& plantToAdd)
{
	if (this->length == this->capacity)
		this->resize();
	this->elements[this->length] = plantToAdd;
	this->length += 1;
}

template<typename TypePlant>
inline void DynamicVector<TypePlant>::deletePlantFromDatabase(const int& position_of_plantToDelete)
{
	for (int i = position_of_plantToDelete; i < this->length; i++)
		this->elements[i] = this->elements[i + 1];
	this->length -= 1;
}

template<typename TypePlant>
inline void DynamicVector<TypePlant>::updatePlantInDatabase(const TypePlant& newPlant, const int& position_of_plantToUpdate)
{
	this->elements[position_of_plantToUpdate] = newPlant;
}

template<typename TypePlant>
inline int DynamicVector<TypePlant>::getLength()
{
	return this->length;
}

template<typename TypePlant>
inline TypePlant DynamicVector<TypePlant>::getElement(const int& position_of_plantToFind)
{
	return this->elements[position_of_plantToFind];
}

template<typename TypePlant>
inline TypePlant* DynamicVector<TypePlant>::getElements()
{
	return this->elements;
}