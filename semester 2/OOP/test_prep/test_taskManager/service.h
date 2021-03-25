#pragma once
#include "repository.h"
#include <vector>

using namespace std;

class Service
{
private:
	Repository* current_repository;

public:
	//constructor
	Service(Repository* repository) : current_repository{ repository } {}

	int addTaskService(const std::string description, const int estimatedDuration, const int priority);
	int deleteTaskService(const std::string description);
	int updateTaskService(const std::string description, const int estimatedDuration, const int priority);

	vector<Task> getTasksService();
	int showPriority(const int priority);
};

