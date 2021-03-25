#include "service.h"
#include <iostream>
#include <algorithm>

int Service::addBillService(const std::string companyName, const std::string code, const double sum, const std::string isPaid)
{
	Bill billToAdd = Bill(companyName, code, sum, isPaid);
	if (this->current_repository->billExistsInList(billToAdd) == true)
		throw exception("Bill already exists!");
	this->current_repository->addBill(billToAdd);
}

int Service::deleteBillService(const std::string code)
{
	Bill billToDelete = Bill("", code, 0, "");
	if (this->current_repository->billExistsInList(billToDelete) == false)
		throw exception("Bill doesn't exist!");
	this->current_repository->deleteBill(billToDelete);
}

int Service::updateBillService(const std::string companyName, const std::string code, const double sum, const std::string isPaid)
{
	Bill newBill = Bill(companyName, code, sum, isPaid);
	if (this->current_repository->billExistsInList(newBill) == false)
		throw exception("Bill doesn't exist!");
	this->current_repository->updateBill(newBill);
}

vector<Bill> Service::getBillsService()
{
	return this->current_repository->getBills();
}

vector<Bill> Service::sortBillsByCompany()
{
	vector<Bill> allBills = this->current_repository->getBills();

	auto sortRuleBills = [](Bill const& bill1, Bill const& bill2) -> bool
	{
		return bill1.get_companyName() < bill2.get_companyName();
	};
	std::sort(allBills.begin(), allBills.end(), sortRuleBills);

	return allBills;
}

double Service::unpaidBills(const std::string companyName)
{
	int total = 0;
	vector<Bill> allBills = this->current_repository->getBills();

	for (Bill bill : allBills)
		if (bill.get_companyName() == companyName && bill.get_isPaid() == "false")
			total += bill.get_sum();
	return total;
}
