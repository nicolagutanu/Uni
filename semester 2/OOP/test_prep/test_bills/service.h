#pragma once
#include "repository.h"

class Service
{
private:
	Repository* current_repository;

public:
	//constructor
	Service(Repository* repository) : current_repository{ repository } {}

	int addBillService(const std::string companyName, const std::string code, const double sum, const std::string isPaid);
	int deleteBillService(const std::string code);
	int updateBillService(const std::string companyName, const std::string code, const double sum, const std::string isPaid);

	vector<Bill> getBillsService();

	vector<Bill> sortBillsByCompany();
	double unpaidBills(const std::string companyName);
};

