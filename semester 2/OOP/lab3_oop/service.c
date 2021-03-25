#include "stdafx.h"
#include "service.h"

Service createService(Repository* listBots)
{
	Service listBotsService;
	listBotsService.repo = *listBots;
	return listBotsService;
}

int addBotService(Service* listBotsService, Bot botToAdd)
{
	return addBot(&listBotsService->repo, botToAdd);
}

int deleteBotService(Service* listBotsService, int serialNumber_of_botToDelete)
{
	return deleteBot(&listBotsService->repo, serialNumber_of_botToDelete);
}

int updateBotService(Service* listBotsService, Bot botToUpdate)
{
	return updateBot(&listBotsService->repo, botToUpdate);
}

int getSize(Service* listBotsService)
{
	return getSizeRepo(&listBotsService->repo);
}

Bot* getList(Service* listBotsService)
{
	return getListBots(&listBotsService->repo);
}