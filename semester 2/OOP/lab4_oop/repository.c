#include "repository.h"
#include <stdlib.h>
#include <assert.h>

Repository* createRepository()
{
	Repository* repo = (Repository*)malloc(sizeof(Repository));
	if (repo == NULL)
		return;
	repo->bots = createDynamicArray(50);
	return repo;
}

void destroyRepository(Repository* repo)
{
	if (repo == NULL)
		return;
	destroyDynamicArray(repo->bots);
	free(repo);
}

int findBot(Repository* repo, Bot botToFind)
{
	int length = getLength(repo->bots);
	for (int i = 0; i < length; i++)
	{
		if (getElement(repo->bots, i).serialNumber == botToFind.serialNumber)
			return i;
	}
	return -1;
}

int addBotRepo(Repository* repo, Bot botToAdd)
{
	if (repo == NULL)
		return 1;
	if (findBot(repo, botToAdd) != -1)
		return 1;
	addElement(repo->bots, botToAdd);
	return 0;
}

int deleteBotRepo(Repository* repo, Bot botToDelete)
{
	if (repo == NULL)
		return 1;
	int positon_of_botToDelete = findBot(repo, botToDelete);
	if (positon_of_botToDelete == -1)
		return 1;
	deleteElement(repo->bots, positon_of_botToDelete);
	return 0;
}

int updateBotRepo(Repository* repo, Bot botToUpdate, Bot newBot)
{
	if (repo == NULL)
		return;
	int position_of_botToUpdate = findBot(repo, botToUpdate);
	if (position_of_botToUpdate == -1)
		return 1;
	updateElement(repo->bots, newBot, position_of_botToUpdate);
	return 0;
}

int getRepoLength(Repository* repo)
{
	if (repo == NULL)
		return;
	return getLength(repo->bots);
}

Bot getBotByPosition(Repository* repo, int position)
{
	if (repo == NULL)
		return;
	return getElement(repo->bots, position);
}

Bot* getBots(Repository* repo)
{
	if (repo == NULL)
		return;
	return getElements(repo->bots);
}

void addElement_validElement_willBeAddedToList(Repository* repo)
{
	Bot bot = createBot(123, "abc", "def", 456);
	assert(addBotRepo(repo, bot) == 0);
}

void addElement_invalidElemnt_willNotBeAddedToList(Repository* repo)
{
	Bot bot1 = createBot(123, "abc", "def", 456);
	addBotRepo(repo, bot1);
	Bot bot2 = createBot(123, "def", "abc", 654);
	assert(addBotRepo(repo, bot2) == 1);
}

void deleteElement_validElemnt_willBeDeletedFromList(Repository* repo)
{
	Bot bot1 = createBot(121, "abc", "df", 451);
	Bot bot2 = createBot(122, "ac", "def", 452);
	Bot bot3 = createBot(123, "abc", "def", 453);
	Bot bot4 = createBot(124, "ab", "def", 454);
	addBotRepo(repo, bot1);
	addBotRepo(repo, bot2);
	addBotRepo(repo, bot3);
	addBotRepo(repo, bot4);
	assert(deleteBotRepo(repo, bot3) == 0);
}

void deleteElement_invalidElemnt_willBeDeletedFromList(Repository* repo)
{
	Bot bot1 = createBot(121, "abc", "df", 451);
	Bot bot2 = createBot(122, "ac", "def", 452);
	Bot bot3 = createBot(123, "abc", "def", 453);
	Bot bot4 = createBot(124, "ab", "def", 454);
	Bot bot5 = createBot(125, "def", "abc", 455);
	addBotRepo(repo, bot1);
	addBotRepo(repo, bot2);
	addBotRepo(repo, bot3);
	addBotRepo(repo, bot4);
	assert(deleteBotRepo(repo, bot5) == 1);
}

void updateElement_validElement_willBeUpdated(Repository* repo)
{
	Bot bot1 = createBot(121, "abc", "df", 451);
	Bot bot2 = createBot(121, "abc", "def", 675);
	addBotRepo(repo, bot1);
	assert(updateBotRepo(repo, bot1, bot2) == 0);
}

void updateElement_invalidElement_willNotBeUpdated(Repository* repo)
{
	Bot bot1 = createBot(125, "abc", "df", 451);
	Bot bot2 = createBot(121, "abc", "def", 675);
	assert(updateBotRepo(repo, bot1, bot2) == 1);
}

void check_size(Repository* repo)
{
	Bot bot1 = createBot(121, "abc", "df", 451);
	Bot bot2 = createBot(122, "ac", "def", 452);
	Bot bot3 = createBot(123, "abc", "def", 453);
	Bot bot4 = createBot(124, "ab", "def", 454);
	Bot bot5 = createBot(125, "def", "abc", 455);
	addBotRepo(repo, bot1);
	addBotRepo(repo, bot2);
	addBotRepo(repo, bot3);
	addBotRepo(repo, bot4);
	assert(getRepoLength(repo) == 4);
}