#pragma warning(disable:4996)
#include "domain.h"
#include <string.h>
#include <assert.h>

Bot createBot(int serialNumber, char state[], char specialization[], int energyCostToRepair)
{
	Bot bot;
	bot.serialNumber = serialNumber;
	strcpy(bot.state, state);
	strcpy(bot.specialization, specialization);
	bot.energyCostToRepair = energyCostToRepair;
	return bot;
}

void test_CreateBot()
{
	Bot bot = createBot(123, "abc", "def", 456);
	assert(bot.serialNumber == 123);
	assert(strcmp(bot.state, "abc") == 0);
	assert(strcmp(bot.specialization, "def") == 0);
	assert(bot.energyCostToRepair == 456);
}