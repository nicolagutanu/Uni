#pragma once
#include <string.h>
#include <stdio.h>

typedef struct
{
	int serialNumber;
	char state[40];
	char specialization[40];
	int energyCostToRepair;
}Bot;

Bot createBot(int serialNumber, char state[], char specialization[], int energyCostToRepair);
int get_serialNumber(Bot* bot);
char* get_state(Bot* bot);
char* get_specialization(Bot* bot);
int get_energyCostToRepair(Bot* bot);