#pragma once
#include <string>

using namespace std;

class Item
{
private:
	std::string category;
	std::string name;
	int quantity;

public:
	//empty constructor
	Item();

	//constructor
	Item(const std::string category, const std::string name, const int quantity);

	//getters
	std::string get_category() const { return category; };
	std::string get_name() const { return name; };
	int get_quantity() const { return quantity; };

	//equality overwrite
	bool operator==(const Item& item);

	//function to print the string 
	std::string itemToPrint();
};

