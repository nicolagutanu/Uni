#pragma once
#include "domain.h"
#include <string>
#include <vector>

using namespace std;

class Repository
{
private:
	string fileName;

	//function that receives a line and a delimiter and break the line up in the fileds of an Item
	vector<string> convertLine(string line, char delimiter);
	//function that reads information from file and returnes a list with all Items
	vector<Task> readFromFile();
	//function that writes all info gathered in repo to file
	void writeToFile(vector<Task> allTasks);

public:
	//constructor
	Repository(const string& fileName);

	void addTask(const Task& taskToAdd);
	void deleteTask(const Task& taskToDelete);
	void updateTask(const Task& newTask);

	//function that checks that a task exists
	int taskExistsInList(const Task& task);

	vector<Task> getTasks();
};

