#include "tests.h"
#include "service.h"
#include <assert.h>

void unpaidBills_findsUnpaidBills_willReturnTotalSum()
{
	Repository* repo = new Repository{ "E:\\School\\sem2\\OOP\\test_bills\\bills.txt" };
	Service service{ repo };
	assert(service.unpaidBills("Orange") == 56.00);
}

void unpaidBills_doesntFindUnpaidBills_willReturnZero()
{
	Repository* repo = new Repository{ "E:\\School\\sem2\\OOP\\test_bills\\bills.txt" };
	Service service{ repo };
	assert(service.unpaidBills("Vodafone") == 0);
}

void runTests()
{
	unpaidBills_findsUnpaidBills_willReturnTotalSum();
	unpaidBills_doesntFindUnpaidBills_willReturnZero();
}
