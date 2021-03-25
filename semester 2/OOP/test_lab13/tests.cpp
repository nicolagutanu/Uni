#include "tests.h"
#include "service.h"
#include "repository.h"
#include <assert.h>

void searchDisorders_findsDisorders_willReturnSymptomps()
{
	Repository* repo = new Repository{ "E:\\School\\sem2\\OOP\\test_lab13\\list.txt" };
	Service service{ repo };
	assert(service.searchDisorders("Stollanite").size() == 2);
}

void searchDisorders_DoesntFindDisorders_willReturnZero()
{
	Repository* repo = new Repository{ "E:\\School\\sem2\\OOP\\test_lab13\\list.txt" };
	Service service{ repo };
	assert(service.searchDisorders("blac").size() == 0);
}

void runTests()
{
	searchDisorders_findsDisorders_willReturnSymptomps();
	searchDisorders_DoesntFindDisorders_willReturnZero();
}
