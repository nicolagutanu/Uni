#pragma once
#include "dynamicArray.h"

typedef struct
{
	DynamicArray* bots;
}Repository;

/*Constructor*/
Repository* createRepository();

/*Destructor*/
void destroyRepository(Repository* repo);

/*Function the returns the position of a bot if found and -1 otherwise*/
int findBot(Repository* repo, Bot botToFind);

int addBotRepo(Repository* repo, Bot botToAdd);

int deleteBotRepo(Repository* repo, Bot botToDelete);

int updateBotRepo(Repository* repo, Bot botToUpdate, Bot newBot);

/*Returns the length of the list of bots*/
int getRepoLength(Repository* repo);

/*Funtion that, given a position, returns the bot on that position in the list*/
Bot getBotByPosition(Repository* repo, int position);

/*Returns the list of all bots*/
Bot* getBots(Repository* repo);

/* Tests */
void addElement_validElement_willBeAddedToList(Repository* repo);
void addElement_invalidElemnt_willNotBeAddedToList(Repository* repo);
void deleteElement_validElemnt_willBeDeletedFromList(Repository* repo);
void deleteElement_invalidElemnt_willBeDeletedFromList(Repository* repo);
void updateElement_validElement_willBeUpdated(Repository* repo);
void updateElement_invalidElement_willNotBeUpdated(Repository* repo);
void check_size(Repository* repo);