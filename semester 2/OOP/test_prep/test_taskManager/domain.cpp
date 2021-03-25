#include "domain.h"
#include <string>

using namespace std;

Task::Task()
{
	this->description = "";
	this->estimatedDuration = 0;
	this->priority = 0;
}

Task::Task(const std::string description, const int estimatedDuration, const int priority)
{
	this->description = description;
	this->estimatedDuration = estimatedDuration;
	this->priority = priority;
}

bool Task::operator==(const Task& task)
{
	if (this->description == task.get_description())
		return true;
	return false;
}
