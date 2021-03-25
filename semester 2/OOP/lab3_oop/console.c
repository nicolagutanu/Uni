#include "stdafx.h"
#include "console.h"

Console createConsole(Service* botsService)
{
	Console botsConsole;
	botsConsole.serv = *botsService;
	return botsConsole;
}

void commands(Console* botsConsole)
{
	while (1)
	{
		printf("\n");
		printf(">>>");
		char command_read[40];
		gets(command_read);
		char* split_command = strtok(command_read, ", ");

		if (strcmp(split_command, "add") == 0)
		{
			split_command = strtok(NULL, ", ");
			int serialNumber = atoi(split_command);
			split_command = strtok(NULL, ", ");
			char state[100];
			strcpy(state, split_command);
			split_command = strtok(NULL, ", ");
			char specialization[100];
			strcpy(specialization, split_command);
			split_command = strtok(NULL, ", ");
			int energyCostToRepair = atoi(split_command);
			Bot botToAdd = createBot(serialNumber, state, specialization, energyCostToRepair);
			if (addBotService(&botsConsole->serv, botToAdd) == 0)
				printf("No!\n");
		}
		else
		{
			if (strcmp(split_command, "delete") == 0)
			{
				split_command = strtok(NULL, ", ");
				int serialNumber_of_botToDelete = atoi(split_command);
				if (deleteBotService(&botsConsole->serv, serialNumber_of_botToDelete) == 0)
				{
					printf("No!\n");
				}
			}
			else
			{
				if (strcmp(split_command, "update") == 0)
				{
					split_command = strtok(NULL, ", ");
					int serialNumber = atoi(split_command);
					split_command = strtok(NULL, ", ");
					char newState[100];
					strcpy(newState, split_command);
					split_command = strtok(NULL, ", ");
					char newSpecialization[100];
					strcpy(newSpecialization, split_command);
					split_command = strtok(NULL, ", ");
					int newEnergyCostToRepair = atoi(split_command);
					Bot botToUpdate = createBot(serialNumber, newState, newSpecialization, newEnergyCostToRepair);
					if (updateBotService(&botsConsole->serv, botToUpdate) == 0)
					{
						printf("No!\n");
					}
				}
				else
				{
					if (strcmp(split_command, "list") == 0)
					{
						split_command = strtok(NULL, ", ");
						if (split_command != NULL)
						{
							char filter[20];
							strcpy(filter, split_command);
							int length = getSize(&botsConsole->serv);
							Bot* list_bots = getList(&botsConsole->serv);
							for (int i = 1; i < length + 1; i++)
							{
								if (strcmp(botsConsole->serv.repo.bots[i].specialization, filter) == 0)
									printf("Bot %d is in the state %s, has the specialization %s and the energy cost to repair %d\n", list_bots[i].serialNumber, list_bots[i].state, list_bots[i].specialization, list_bots[i].energyCostToRepair);
							}
						}
						else
						{
							int length = getSize(&botsConsole->serv);
							Bot* list_bots = getList(&botsConsole->serv);
							for (int i = 1; i < length + 1; i++)
							{
								printf("Bot %d is in the state %s, has the specialization %s and the energy cost to repair %d\n", list_bots[i].serialNumber, list_bots[i].state, list_bots[i].specialization, list_bots[i].energyCostToRepair);
							}
						}
					}
					else
					{
						if (strcmp(split_command, "exit") == 0)
						{
							break;
						}
						else
						{
							printf("This command doesn't exist! \n");
						}
					}
				}
			}
		}
	}
}