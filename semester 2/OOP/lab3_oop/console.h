#pragma once
#include "service.h"
#include "domain.h"
#include <stdlib.h>

typedef struct
{
	Service serv;
}Console;

Console createConsole(Service* botsService);
void commands(Console* botsConsole);