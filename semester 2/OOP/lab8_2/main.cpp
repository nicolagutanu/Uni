#include "console.h"
#include <sstream>
#include <crtdbg.h>
#include <string>
#include <stdlib.h>
#include "stdio.h"
#include "fileRepository.h"
#include "tests.h"
#define _CRTDBG_MAP_ALLOC

using namespace std;

int main()
{
	{
		stringstream path;
		string fileLocation;
		std::cin >> fileLocation;
		cin.get();
		getline(std::cin, fileLocation);
		FileRepository repository{ fileLocation };
		Service service{ repository };
		Console console{ service };
		console.run();
		runTests();
	}
	_CrtDumpMemoryLeaks();
	return 0;
}