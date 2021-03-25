#pragma warning(disable:4996)
#include "undoRedo.h"
#include <stdlib.h>
#include <string.h>

Operation createOperation(char* command, int serialNumber, char* state, char* specialization, int energyCostToRepair)
{
	Operation operation;
	strcpy(operation.command, command);
	operation.serialNumber = serialNumber;
	strcpy(operation.state, state);
	strcpy(operation.specialization, specialization);
	operation.energyCostToRepair = energyCostToRepair;
	return operation;
}

OperationStack* createOperationStack()
{
	OperationStack* stack = (OperationStack*)malloc(sizeof(OperationStack));
	stack->length = 0;
	return stack;
}

void destroyOperationStack(OperationStack* stack)
{
	if (stack == NULL)
		return;
	free(stack);
}

void push(OperationStack* stack, Operation operation)
{
	if (stack == NULL)
		return;
	stack->operations[stack->length] = operation;
	stack->length += 1;
}

Operation pop(OperationStack* stack)
{
	if (stack == NULL)
		return;
	Operation operation = stack->operations[stack->length-1];
	stack->length -= 1;
	return operation;
}

void makeEmpty(OperationStack* stack)
{
	if (stack == NULL)
		return;
	stack->length = 0;
}