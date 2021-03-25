#include "tests.h"

void showPriority_willFindTasks_willReturnTotalDuration()
{
	Repository* repository = new Repository{ "E:\\School\\sem2\\OOP\\test_taskManager\\tasks_test.txt" };
	Service service{ repository };
	service.addTaskService("Solve_assg", 120, 1);
	service.addTaskService("Respond_to_email", 45, 2);
	service.addTaskService("Eat_sushi", 30, 3);
	service.addTaskService("Visit_parent", 240, 1);
	service.addTaskService("Buy_jacket", 120, 2);
	assert(service.showPriority(2) == 165);
	service.deleteTaskService("Solve_assg");
	service.deleteTaskService("Respond_to_email");
	service.deleteTaskService("Eat_sushi");
	service.deleteTaskService("Visit_parent");
	service.deleteTaskService("Buy_jacket");
}

void showPriority_willNotFindTasks_willReturnZero()
{
	Repository* repository = new Repository{ "E:\\School\\sem2\\OOP\\test_taskManager\\tasks_test.txt" };
	Service service{ repository };
	service.addTaskService("Solve_assg", 120, 1);
	service.addTaskService("Eat_sushi", 30, 3);
	service.addTaskService("Visit_parent", 240, 1);
	assert(service.showPriority(2) == 0);
	service.deleteTaskService("Solve_assg");
	service.deleteTaskService("Eat_sushi");
	service.deleteTaskService("Visit_parent");
}

void runTests()
{
	showPriority_willFindTasks_willReturnTotalDuration();
	showPriority_willNotFindTasks_willReturnZero();
}
