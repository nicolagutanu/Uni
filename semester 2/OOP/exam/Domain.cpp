#include "Domain.h"

Star::Star()
{
	this->name = "";
	this->con = "";
	this->ra = 0;
	this->dec = 0;
	this->diameter = 0;
}

Star::Star(const std::string name, const std::string con, const int ra, const int dec, const int diameter)
{
	this->name = name;
	this->con = con;
	this->ra = ra;
	this->dec = dec;
	this->diameter = diameter;
}

bool Star::operator==(const Star& s)
{
	if (this->name == s.get_name())
		return true;
	return false;
}

Astronomer::Astronomer()
{
	this->name = "";
	this->con = "";
}

Astronomer::Astronomer(const std::string name, const std::string con)
{
	this->name = name;
	this->con = con;
}

bool Astronomer::operator==(const Astronomer& a)
{
	if (this->name == a.get_name())
		return true;
	return false;
}
