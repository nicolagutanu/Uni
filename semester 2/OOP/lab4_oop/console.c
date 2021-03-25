#pragma warning(disable:4996)
#include "console.h"
#include <string.h>
#include <stdlib.h>
#include <stdio.h>
#include <crtdbg.h>


Console* createConsole(Service* serv)
{
	Console* cons = (Console*)malloc(sizeof(Console));
	if (cons == NULL)
		return NULL;
	cons->serv = serv;
	return cons;
}

void destroyConsole(Console* cons)
{
	destroyService(cons->serv);
	free(cons);
}

void list(Console* cons)
{
	int length = Length(cons->serv);
	Bot* listOfAllBots = getListBotsFromRepo(cons->serv);
	for (int i = 0; i < length; i++)
		printf("Bot %d is in the state %s, has the specialization %s and the energy cost to repair %d\n", listOfAllBots[i].serialNumber, listOfAllBots[i].state, listOfAllBots[i].specialization, listOfAllBots[i].energyCostToRepair);
}

void list_maximum_energyCostToRepair(Console* cons, int given_energCostToRepair)
{
	int length = Length(cons->serv);
	Bot* listOfAllBots = getListBotsFromRepo(cons->serv);
	Bot list_by_filter[200], auxiliar_bot;
	int length_new_list = 0;
	for (int i = 0; i < length; i++)
		if (listOfAllBots[i].energyCostToRepair < given_energCostToRepair)
		{
			list_by_filter[length_new_list] = listOfAllBots[i];
			length_new_list += 1;
		}
	for (int i = 0; i < length_new_list - 1; i++)
		for (int j = i + 1; j < length_new_list; j++)
			if (strcmp(list_by_filter[i].state, list_by_filter[j].state) > 0)
			{
				auxiliar_bot = list_by_filter[i];
				list_by_filter[i] = list_by_filter[j];
				list_by_filter[j] = auxiliar_bot;
			}
	for (int i = 0; i < length_new_list; i++)
		printf("Bot %d is in the state %s, has the specialization %s and the energy cost to repair %d\n", list_by_filter[i].serialNumber, list_by_filter[i].state, list_by_filter[i].specialization, list_by_filter[i].energyCostToRepair);
}

void run(Console* cons)
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
			char state[40];
			strcpy(state, split_command);
			split_command = strtok(NULL, ", ");
			char specialization[40];
			strcpy(specialization, split_command);
			split_command = strtok(NULL, ", ");
			int energyCostToRepair = atoi(split_command);
			if (addBotServ(cons->serv, serialNumber, state, specialization, energyCostToRepair) == 1)
			{
				printf("No! \n");
			}
		}
		else
			if (strcmp(split_command, "delete") == 0)
			{
				split_command = strtok(NULL, ", ");
				int serialNumber_of_botToDelete = atoi(split_command);
				if (deleteBotServ(cons->serv, serialNumber_of_botToDelete) == 1)
				{
					printf("No! \n");
				}
			}
			else
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
					if (updateBotServ(cons->serv, serialNumber, newState, newSpecialization, newEnergyCostToRepair) == 1)
					{
						printf("No! \n");
					}
				}
				else
					if (strcmp(split_command, "list") == 0)
					{
						split_command = strtok(NULL, ", ");
						if (split_command != NULL)
						{
							int given_value;
							given_value = atoi(split_command);
							list_maximum_energyCostToRepair(cons, given_value);
						}
						else
						{
							list(cons);
						}
					}
					else
						if (strcmp(split_command, "undo") == 0)
						{
							undo(cons->serv);
						}
						else
							if (strcmp(split_command, "redo") == 0)
								redo(cons->serv);
							else
								if (strcmp(split_command, "exit") == 0)
									break;
	}
}