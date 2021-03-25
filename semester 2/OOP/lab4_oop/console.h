#pragma once
#include "service.h"

typedef struct
{
	Service* serv;
}Console;

/*Constructor for the console type, receives the service as a pointer*/
Console* createConsole(Service* serv);

/*Destructor of the console type*/
void destroyConsole(Console* cons);

/*Function that runs the show*/
void run(Console* cons);