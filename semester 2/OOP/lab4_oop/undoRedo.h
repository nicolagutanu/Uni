#pragma once

typedef struct
{
	char command[20];
	int serialNumber;
	char state[40];
	char specialization[40];
	int energyCostToRepair;
}Operation;

/*Constructor for the operation type*/
Operation createOperation(char* command, int serialNumber, char* state, char* specialization, int energyCostToRepair);


typedef struct
{
	Operation operations[100];
	int length;
}OperationStack;

/*Constructor for the operation stack type*/
OperationStack* createOperationStack();

/*Destructor for the operation stack type*/
void destroyOperationStack(OperationStack* stack);

/*Fuction that push onto the stack a new operation*/
void push(OperationStack* stack, Operation operation);

/*Function that pops an operation from the stack*/
Operation pop(OperationStack* stack);

/*Function that makes the stack empty*/
void makeEmpty(OperationStack* stack);