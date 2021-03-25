#include "console.h"
#include <sstream>
#include <crtdbg.h>
#include <string>
#include <stdlib.h>
#include "stdio.h"
#include "fileRepository.h"
#include <regex>
#include "repository.h"
#include "htmlRepository.h"
#include "csvRepository.h"
#include "validator.h"
#define _CRTDBG_MAP_ALLOC

using namespace std;

int main()
{
	{
		Validator validator;
		string path;
		string pathMyList;
		string fileLocation;
		Repository* mylist = nullptr;
		cin >> fileLocation;
		cin.get();
		getline(std::cin, path);
		cin >> fileLocation;
		cin.get();
		getline(std::cin, pathMyList);
		smatch matchFound;
		regex extension(".csv");
		if (regex_search(pathMyList, matchFound, extension))
			mylist = new CsvRepository{ pathMyList };
		else
			mylist = new HtmlRepository{ pathMyList };
		FileRepository repository{ path };
		Service service{ &repository, mylist };
		Console console{ &service, validator };
		console.run();
	}
	_CrtDumpMemoryLeaks();
	return 0;
}