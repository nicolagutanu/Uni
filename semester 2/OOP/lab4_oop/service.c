#include "service.h"
#include <stdlib.h>

Service* createService(Repository* repo, OperationStack* undo, OperationStack* redo)
{
	Service* serv = (Service*)malloc(sizeof(Service));
	if (serv == NULL)
		return;
	serv->repo = repo;
	serv->undo = undo;
	serv->redo = redo;
	return serv;
}

void destroyService(Service* serv)
{
	destroyRepository(serv->repo);
	destroyOperationStack(serv->undo);
	destroyOperationStack(serv->redo);
	free(serv);
}

int addBotServ(Service* serv, int serialNumber, char* state, char* specialization, int energyCostToRepair)
{
	Bot botToAdd = createBot(serialNumber, state, specialization, energyCostToRepair);
	Operation operation = createOperation("add", botToAdd.serialNumber, botToAdd.state, botToAdd.specialization, botToAdd.energyCostToRepair);
	push(serv->undo, operation);
	makeEmpty(serv->redo);
	return addBotRepo(serv->repo, botToAdd);
}

int addBotUndo(Service* serv, int serialNumber, char* state, char* specialization, int energyCostToRepair)
{
	Bot botToAdd = createBot(serialNumber, state, specialization, energyCostToRepair);
	return addBotRepo(serv->repo, botToAdd);
}

int deleteBotServ(Service* serv, int serialNumber_of_botToDelete)
{
	Bot botToDelete = createBot(serialNumber_of_botToDelete, "", "", 0);
	int position_of_botToDelete = findBot(serv->repo, botToDelete);
	if (position_of_botToDelete != -1)
	{
		Bot botToDeleteWithAtributes = getBotByPosition(serv->repo, position_of_botToDelete);
		Operation operation = createOperation("delete", botToDeleteWithAtributes.serialNumber, botToDeleteWithAtributes.state, botToDeleteWithAtributes.specialization, botToDeleteWithAtributes.energyCostToRepair);
		push(serv->undo, operation);
		makeEmpty(serv->redo);
	}
	return deleteBotRepo(serv->repo, botToDelete);
}

int deleteBotUndo(Service* serv, int serialNumber_of_botToDelete)
{
	Bot botToDelete = createBot(serialNumber_of_botToDelete, "", "", 0);
	return deleteBotRepo(serv->repo, botToDelete);
}

int updateBotServ(Service* serv, int serialNumber, char* newState, char* newSpecialization, int newEnergyCostToRepair)
{
	Bot botBeforeUpdate = createBot(serialNumber, "", "", 0);
	Bot newBot = createBot(serialNumber, newState, newSpecialization, newEnergyCostToRepair);
	int position_of_botBeforeUpdate = findBot(serv->repo, botBeforeUpdate);
	if (position_of_botBeforeUpdate != -1)
	{
		Bot botToUpdateWithAtributes = getBotByPosition(serv->repo, position_of_botBeforeUpdate);
		Operation operation = createOperation("update", botToUpdateWithAtributes.serialNumber, botToUpdateWithAtributes.state, botToUpdateWithAtributes.specialization, botToUpdateWithAtributes.energyCostToRepair);
		push(serv->undo, operation);
		makeEmpty(serv->redo);
	}
	return updateBotRepo(serv->repo, botBeforeUpdate, newBot);
}

int updateBotUndo(Service* serv, int serialNumber, char* newState, char* newSpecialization, int newEnergyCostToRepair)
{
	Bot botBeforeUpdate = createBot(serialNumber, "", "", 0);
	Bot newBot = createBot(serialNumber, newState, newSpecialization, newEnergyCostToRepair);
	int position_of_botBeforeUpdate = findBot(serv->repo, botBeforeUpdate);
	Bot botToUpdateWithAtributes = getBotByPosition(serv->repo, position_of_botBeforeUpdate);
	Operation operation = createOperation("update", botToUpdateWithAtributes.serialNumber, botToUpdateWithAtributes.state, botToUpdateWithAtributes.specialization, botToUpdateWithAtributes.energyCostToRepair);
	push(serv->redo, operation);
	return updateBotRepo(serv->repo, botBeforeUpdate, newBot);
}

int Length(Service* serv)
{
	return getRepoLength(serv->repo);
}

Bot* getListBotsFromRepo(Service* serv)
{
	return getBots(serv->repo);
}

void undo(Service* serv)
{
	if (serv->undo->length > 0)
	{
		Operation operation = pop(serv->undo);
		if (strcmp(operation.command, "add") == 0)
		{
			deleteBotUndo(serv, operation.serialNumber);
			push(serv->redo, operation);
		}
		if (strcmp(operation.command, "delete") == 0)
		{
			addBotUndo(serv, operation.serialNumber, operation.state, operation.specialization, operation.energyCostToRepair);
			push(serv->redo, operation);
		}
		if (strcmp(operation.command, "update") == 0)
		{
			updateBotUndo(serv, operation.serialNumber, operation.state, operation.specialization, operation.energyCostToRepair);
		}
	}
}

void redo(Service* serv)
{
	if (serv->redo->length > 0)
	{
		Operation operation = pop(serv->redo);
		if (strcmp(operation.command, "add") == 0)
		{
			addBotUndo(serv, operation.serialNumber, operation.state, operation.specialization, operation.energyCostToRepair);
			push(serv->undo, operation);
		}
		if (strcmp(operation.command, "delete") == 0)
		{
			deleteBotUndo(serv, operation.serialNumber);
			push(serv->undo, operation);
		}
		if (strcmp(operation.command, "update") == 0)
		{
			updateBotUndo(serv, operation.serialNumber, operation.state, operation.specialization, operation.energyCostToRepair);
			push(serv->undo, operation);
		}
	}
	return 0;
}