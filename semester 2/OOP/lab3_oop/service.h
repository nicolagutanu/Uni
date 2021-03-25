#pragma once
#include "repository.h"

typedef struct
{
	Repository repo;
}Service;

Service createService(Repository* listBots);
int addBotService(Service* botsService, Bot botToAdd);
int deleteBotService(Service* botsService, int serialNumber_of_botToDelete);
int updateBotService(Service* botsService, Bot botToUpdate);
int getSize(Service* botsService);
Bot* getList(Service* botsService);