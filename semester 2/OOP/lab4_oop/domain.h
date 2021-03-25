#pragma once

typedef struct
{
	int serialNumber;
	char state[40];
	char specialization[40];
	int energyCostToRepair;
}Bot;

/*Constructor for bot type
input: serialNumber is an integer
	   state is an array of characters
	   specialization is an array of characters
	   energyCostToRepair is an integer
output: a bot with those atributes
*/
Bot createBot(int serialNumber, char state[], char specialization[], int energyCostToRepair);

//we test the create function of the bot
void test_CreateBot();