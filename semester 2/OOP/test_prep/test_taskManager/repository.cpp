#include "repository.h"
#include <cstring>
#include <sstream>
#include <fstream>

vector<string> Repository::convertLine(string line, char delimiter)
{
	stringstream stringStream(line);
	string subString;
	vector<string> task;
	while (getline(stringStream, subString, delimiter))
		task.push_back(subString);
	return task;
}

vector<Task> Repository::readFromFile()
{
	std::string task;
	ifstream fileIn;
	fileIn.open(this->fileName);
	vector<std::string> Tasks;
	vector<Task> allTasks;
	Task newTask;
	while (fileIn >> task)
	{
		Tasks = this->convertLine(task, ',');
		newTask = Task(Tasks[0], stoi(Tasks[1]), stoi(Tasks[2]));
		allTasks.push_back(newTask);
	}
	fileIn.close();
	return allTasks;
}

void Repository::writeToFile(vector<Task> allTasks)
{
	ofstream fileOut(this->fileName);
	for (Task task : allTasks)
	{
		fileOut << task.get_description() << ',' << std::to_string(task.get_estimatedDuration()) << ',' << std::to_string(task.get_priority()) << '\n';
	}
}

Repository::Repository(const string& fileName)
{
	this->fileName = fileName;
}

void Repository::addTask(const Task& taskToAdd)
{
	vector<Task> allTasks = this->readFromFile();
	allTasks.push_back(taskToAdd);
	this->writeToFile(allTasks);
}

void Repository::deleteTask(const Task& taskToDelete)
{
	vector<Task> allTasks = this->readFromFile();
	vector<Task>::iterator it = find(allTasks.begin(), allTasks.end(), taskToDelete);
	if (it != allTasks.end())
		allTasks.erase(it);
	this->writeToFile(allTasks);
}

void Repository::updateTask(const Task& newTask)
{
	vector<Task> allTasks = this->readFromFile();
	vector<Task>::iterator it = find(allTasks.begin(), allTasks.end(), newTask);
	if (it != allTasks.end())
		*it = newTask;
	this->writeToFile(allTasks);
}

int Repository::taskExistsInList(const Task& task)
{
	vector<Task> allTasks = this->readFromFile();
	vector<Task>::iterator it = find(allTasks.begin(), allTasks.end(), task);
	if (it != allTasks.end())
		return 1;
	return 0;
}

vector<Task> Repository::getTasks()
{
	vector<Task> allTasks = this->readFromFile();
	return allTasks;
}
