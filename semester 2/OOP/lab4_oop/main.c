#include "console.h"
#include "stdio.h"
#define _CRTDBG_MAP_ALLOC
#include <stdlib.h>
#include <crtdbg.h>
#include "repository.h"

int main()
{
	Repository* repo = createRepository();
	addElement_validElement_willBeAddedToList(repo);
	addElement_invalidElemnt_willNotBeAddedToList(repo);
	deleteElement_validElemnt_willBeDeletedFromList(repo);
	deleteElement_invalidElemnt_willBeDeletedFromList(repo);
	updateElement_validElement_willBeUpdated(repo);
	updateElement_invalidElement_willNotBeUpdated(repo);
	check_size(repo);
	destroyRepository(repo);
	Repository* repo2 = createRepository();
	OperationStack* undo = createOperationStack();
	OperationStack* redo = createOperationStack();
	Service* serv = createService(repo2, undo, redo);
	Console* cons = createConsole(serv);
	run(cons);
	destroyConsole(cons);
	_CrtDumpMemoryLeaks();
	return 0;
}