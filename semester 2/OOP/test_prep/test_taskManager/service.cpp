#include "service.h"
#include <exception>

using namespace std;

int Service::addTaskService(const std::string description, const int estimatedDuration, const int priority)
{
	Task taskToAdd = Task(description, estimatedDuration, priority);
	if (this->current_repository->taskExistsInList(taskToAdd) == true)
		throw exception("Task already exists!\n");
	this->current_repository->addTask(taskToAdd);
}

int Service::deleteTaskService(const std::string description)
{
	Task taskToDelete = Task(description, 0, 0);
	if (this->current_repository->taskExistsInList(taskToDelete) == false)
		throw exception("Task doesn't exist!\n");
	this->current_repository->deleteTask(taskToDelete);
}

int Service::updateTaskService(const std::string description, const int estimatedDuration, const int priority)
{
	Task newTask = Task(description, estimatedDuration, priority);
	if (this->current_repository->taskExistsInList(newTask) == false)
		throw exception("Task doesn't exist!\n");
	this->current_repository->updateTask(newTask);
}

vector<Task> Service::getTasksService()
{
	return this->current_repository->getTasks();
}

int Service::showPriority(const int priority)
{
	vector<Task> allTasks = this->current_repository->getTasks();
	int totalDuration = 0;
	for (Task task : allTasks)
		if (task.get_priority() == priority)
			totalDuration += task.get_estimatedDuration();
	return totalDuration;
}
