#include "repository.h"
#include <cstring>
#include <sstream>
#include <fstream>

vector<string> Repository::convertLine(string line, char delimiter)
{
	stringstream stringStream(line);
	string subString;
	vector<string> bill;
	while (getline(stringStream, subString, delimiter))
		bill.push_back(subString);
	return bill;
}

vector<Bill> Repository::readFromFile()
{
	std::string bill;
	ifstream fileIn;
	fileIn.open(this->fileName);
	vector<std::string> Bills;
	vector<Bill> allBills;
	Bill newBill;
	while (getline(fileIn, bill))
	{
		Bills = this->convertLine(bill, ';');
		newBill = Bill(Bills[0], Bills[1], stod(Bills[2]), Bills[3]);
		allBills.push_back(newBill);
	}
	fileIn.close();
	return allBills;
}

void Repository::writeToFile(vector<Bill> allBills)
{
	ofstream fileOut(this->fileName);
	for (Bill bill : allBills)
	{
		fileOut << bill.get_companyName() << ';' << bill.get_code() << ';' << to_string(bill.get_sum()) << ';' << bill.get_isPaid() << '\n';
	}
}

Repository::Repository(const std::string& fileName)
{
	this->fileName = fileName;
}

void Repository::addBill(const Bill& billToAdd)
{
	vector<Bill> allBills = this->readFromFile();
	allBills.push_back(billToAdd);
	this->writeToFile(allBills);
}

void Repository::deleteBill(const Bill& billToDelete)
{
	vector<Bill> allBills = this->readFromFile();
	
	vector<Bill>::iterator it = find(allBills.begin(), allBills.end(), billToDelete);
	if (it != allBills.end())
		allBills.erase(it);

	this->writeToFile(allBills);
}

void Repository::updateBill(const Bill& newBill)
{
	vector<Bill> allBills = this->readFromFile();

	vector<Bill>::iterator it = find(allBills.begin(), allBills.end(), newBill);
	if (it != allBills.end())
		*it = newBill;

	this->writeToFile(allBills);
}

int Repository::billExistsInList(const Bill& bill)
{
	vector<Bill> allBills = this->readFromFile();

	vector<Bill>::iterator it = find(allBills.begin(), allBills.end(), bill);
	if (it != allBills.end())
		return 1;
	return 0;
}

vector<Bill> Repository::getBills()
{
	vector<Bill> allBills = this->readFromFile();
	return allBills;
}