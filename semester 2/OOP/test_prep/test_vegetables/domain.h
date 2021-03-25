#pragma once
#include <string>
#include <vector>

using namespace std;

class Vegetable
{
private:
	std::string family;
	std::string name;
	std::string parts;

public:
	//empty constructor
	Vegetable();

	//constructor
	Vegetable(const std::string family, const std::string name, const std::string parts);

	//getters
	std::string get_family() const { return family; };
	std::string get_name() const { return name; };
	std::string get_parts() const { return parts; };

	//equality operator overwrite
	bool operator==(const Vegetable& veg);
};

