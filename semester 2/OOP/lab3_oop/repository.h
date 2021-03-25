#pragma once
#include "domain.h"

typedef struct
{
	Bot bots[1000];
	int length;
}Repository;

Repository createRepository();
int addBot(Repository* listBots, Bot botToAdd);
int deleteBot(Repository* listBots, int serialNumber_of_botToDelete);
int updateBot(Repository* listBots, Bot botToUpdate);
int getSizeRepo(Repository* listBots);
Bot* getListBots(Repository* listBots);