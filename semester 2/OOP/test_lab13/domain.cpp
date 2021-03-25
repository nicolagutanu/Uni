#include "domain.h"

Disorder::Disorder()
{
	this->category = "";
	this->name = "";
}

Disorder::Disorder(const std::string category, const std::string name, const vector<std::string> symptoms)
{
	this->category = category;
	this->name = name;
	this->symptoms = symptoms;
}