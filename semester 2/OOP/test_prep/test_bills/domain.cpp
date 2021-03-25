#include "domain.h"

Bill::Bill()
{
	this->companyName = "";
	this->code = "";
	this->sum = 0;
	this->isPaid = "";
}

Bill::Bill(const std::string companyName, const std::string code, const double sum, const std::string isPaid)
{
	this->companyName = companyName;
	this->code = code;
	this->sum = sum;
	this->isPaid = isPaid;
}

bool Bill::operator==(const Bill& bill)
{
	if (this->code == bill.get_code())
		return true;
	return false;
}
