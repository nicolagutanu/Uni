#pragma once
#include <string>
#include <vector>

using namespace std;

class Disorder
{
private:
	std::string category;
	std::string name;
	vector<std::string> symptoms;

public:
	Disorder();

	Disorder(const std::string category, const std::string name, const vector<std::string> symptoms);

	std::string get_category() const { return category; };
	std::string get_name() const { return name; };
	vector<std::string> get_symptoms() const { return symptoms; };
};