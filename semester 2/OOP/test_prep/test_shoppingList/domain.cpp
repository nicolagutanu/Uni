#include "domain.h"
#include <string>

using namespace std;

Item::Item()
{
	this->category = "";
	this->name = "";
	this->quantity = 0;
}

Item::Item(const std::string category, const std::string name, const int quantity)
{
	this->category = category;
	this->name = name;
	this->quantity = quantity;
}

bool Item::operator==(const Item& item)
{
	if (this->category == item.get_category() && this->name == item.get_name())
		return true;
	return false;
}

std::string Item::itemToPrint()
{
	return this->get_category() + " | " + this->get_name() + " | " + std::to_string(this->get_quantity()) + "\n";
}
