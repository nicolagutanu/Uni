#include "stdafx.h"
#include "console.h"

int main()
{
	Repository repo = createRepository();
	Service serv = createService(&repo);
	Console cons = createConsole(&serv);
	commands(&cons);
	return 0;
}