#include "stdafx.h"
#include "domain.h"

Bot createBot(int serialNumber, char state[], char specialization[], int energyCostToRepair)
{
	Bot bot;
	bot.serialNumber = serialNumber;
	strcpy(bot.state, state);
	strcpy(bot.specialization, specialization);
	bot.energyCostToRepair = energyCostToRepair;
	return bot;
}

int get_serialNumber(Bot* bot)
{
	return bot->serialNumber;
}

char* get_state(Bot* bot)
{
	return bot->state;
}

char* get_specialization(Bot* bot)
{
	return bot->specialization;
}

int get_energyCostToRepair(Bot* bot)
{
	return bot->energyCostToRepair;
}