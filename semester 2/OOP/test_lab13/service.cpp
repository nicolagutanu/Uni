#include "service.h"
#include <iostream>
#include <algorithm>

vector<Disorder> Service::getDisordersService()
{
	return this->current_repository->getDisorders();
}

vector<std::string> Service::searchDisorders(const std::string name)
{
	//the list to be returned;
	vector<std::string> symptomps;

	vector<Disorder> allDisorders = this->current_repository->getDisorders();
	//searching through all disorders to find the one with the given name
	for (Disorder dis : allDisorders)
		if (dis.get_name() == name)
			for (int i = 0; i < dis.get_symptoms().size(); i++)
				symptomps.push_back(dis.get_symptoms()[i]);
				//adding the symptomps to the list

	return symptomps;
}