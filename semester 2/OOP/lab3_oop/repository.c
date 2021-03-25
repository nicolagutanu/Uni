#include "stdafx.h"
#include "repository.h"

Repository createRepository()
{
	Repository listBots;
	listBots.length = 0;
	return listBots;
}

int addBot(Repository* listBots, Bot botToAdd)
{
	for (int i = 1; i <= listBots->length; i++)
	{
		if (listBots->bots[i].serialNumber == botToAdd.serialNumber)
			return 0;
	}
	listBots->bots[++listBots->length] = botToAdd;
	return 1;
}

int deleteBot(Repository* listBots, int serialNumber_of_botToDelete)
{
	for (int i = 1; i <= listBots->length; i++)
	{
		if (listBots->bots[i].serialNumber == serialNumber_of_botToDelete)
		{
			for (int j = i; j < listBots->length; j++)
			{
				listBots->bots[j] = listBots->bots[j + 1];
			}
			listBots->length--;
			return 1;
		}
	}
	return 0;
}

int updateBot(Repository* listBots, Bot botToUpdate)
{
	for (int i = 1; i <= listBots->length; i++)
	{
		if (listBots->bots[i].serialNumber == botToUpdate.serialNumber)
		{
			listBots->bots[i] = botToUpdate;
			return 1;
		}
	}
	return 0;
}

int getSizeRepo(Repository* listBots)
{
	return listBots->length;
}

Bot* getListBots(Repository* listBots)
{
	return listBots->bots;
}