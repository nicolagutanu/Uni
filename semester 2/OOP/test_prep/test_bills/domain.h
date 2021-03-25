#pragma once
#include <string>

using namespace std;

class Bill
{
private:
	std::string companyName;
	std::string code;
	double sum;
	std::string isPaid;

public:
	//empty constructor
	Bill();

	//constrcutor
	Bill(const std::string companyName, const std::string code, const double sum, const std::string isPaid);

	//getters
	std::string get_companyName() const { return companyName; };
	std::string get_code() const { return code; };
	double get_sum() const { return sum; };
	std::string get_isPaid() const { return isPaid; };

	//equality operator overwrite
	bool operator==(const Bill& bill);
};

