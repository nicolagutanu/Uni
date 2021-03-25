#include "console.h"
#include <iostream>
#include <crtdbg.h>
#include <string>
#include <stdlib.h>
#include "stdio.h"
#include "repository.h"
#include "tests.h"
#define _CRTDBG_MAP_ALLOC

using namespace std;

void main()
{
	Repository repo{};
	Service service{ repo };
	Console console{ service };
	console.run();
	_CrtDumpMemoryLeaks();
	return;
}