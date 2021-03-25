#pragma once
#include "repository.h"
#include "undoRedo.h"

typedef struct
{
	Repository* repo;
	OperationStack* undo;
	OperationStack* redo;
}Service;

/*Constructor for the service type, receives repo, a pointer to repository*/
Service* createService(Repository* repo, OperationStack* undo, OperationStack* redo);

/*Destructor for the service*/
void destroyService(Service* serv);

int addBotServ(Service* serv, int serialNumber, char* state, char* specialization, int energyCostToRepair);
int addBotUndo(Service* serv, int serialNumber, char* state, char* specialization, int energyCostToRepair);
int deleteBotServ(Service* serv, int serialNumber_of_botToDelete);
int deleteBotUndo(Service* serv, int serialNumber_of_botToDelete);
int updateBotServ(Service* serv, int serialNumber, char* newState, char* newSpecialization, int newEnergyCostToRepair);
int updateBotUndo(Service* serv, int serialNumber, char* newState, char* newSpecialization, int newEnergyCostToRepair);

/*Function that will return the length of the repository*/
int Length(Service* serv);
/*Function that will return the list of bots*/
Bot* getListBotsFromRepo(Service* serv);

void undo(Service* serv);
void redo(Service* serv);