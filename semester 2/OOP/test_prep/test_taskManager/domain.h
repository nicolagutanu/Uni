#pragma once
#include <string>

using namespace std;

class Task
{
private:
	std::string description;
	int estimatedDuration;
	int priority;

public:
	//e,pty constructor
	Task();

	//constructor
	Task(const std::string description, const int estimatedDuration, const int priority);

	//getters
	std::string get_description() const { return description; };
	int get_estimatedDuration() const { return estimatedDuration; };
	int get_priority() const { return priority; };

	//equality operator overwrite
	bool operator==(const Task& task);
};

