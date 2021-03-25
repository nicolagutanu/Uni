#pragma once
#include "domain.h"
#include <vector>

class Repository
{
private:
	std::string fileName;

	//function that receives a line and a delimiter and break the line up in the fileds of an Item
	vector<string> convertLine(string line, char delimiter);
	//function that reads information from file and returnes a list with all Items
	vector<Bill> readFromFile();
	//function that writes all info gathered in repo to file
	void writeToFile(vector<Bill> allBills);

public:
	Repository(const std::string& fileName);

	void addBill(const Bill& billToAdd);
	void deleteBill(const Bill& billToDelete);
	void updateBill(const Bill& newBill);

	int billExistsInList(const Bill& bill);

	vector<Bill> getBills();
};

