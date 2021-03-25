#include "domain.h"

Vegetable::Vegetable()
{
	this->family = "";
	this->name = "";
	this->parts = "";
}

Vegetable::Vegetable(const std::string family, const std::string name, std::string parts)
{
	this->family = family;
	this->name = name;
	this->parts = parts;
}

bool Vegetable::operator==(const Vegetable& veg)
{
	if (this->name == veg.get_name() && this->family == veg.get_family())
		return true;
	return false;
}
