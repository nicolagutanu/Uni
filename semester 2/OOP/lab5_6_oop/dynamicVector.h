#pragma once
#include "domain.h"
#include <iostream>

class DynamicVector
{
private:

	Plant* elements;
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
	void addPlantToDatabase(const Plant& plantToAdd);
	/* Function that deletes an element */
	void deletePlantFromDatabase(const int& position_of_plantToDelete);
	/* Function that updates an element */
	void updatePlantInDatabase(const Plant& newPlant, const int& position_of_plantToUpdate);
	/* Function that returns the length of the repo */
	int getLength();
	/* Function that return an element on a given position */
	Plant getElement(const int& position_of_plantToFind);
	/* Function that returns all elements in the repo */
	Plant* getElements();
};