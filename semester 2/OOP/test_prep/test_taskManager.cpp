#include "test_taskManager.h"
#include <qmessagebox.h>
#include "qfont.h"
#include <iostream>

test_taskManager::test_taskManager(Service& service, QWidget *parent)
    : QMainWindow(parent), service{service}
{
    ui.setupUi(this);

    this->makeConnection();
    this->populateList();
}

void test_taskManager::makeConnection()
{
    QObject::connect(this->ui.list_pushButton, &QPushButton::clicked, this, &test_taskManager::populateList);
    QObject::connect(this->ui.priorityOne_pushButton, &QPushButton::clicked, this, &test_taskManager::listPriorityOne);
    QObject::connect(this->ui.totalDuraction_pushButton, &QPushButton::clicked, this, &test_taskManager::totalDuration);
    QObject::connect(this->ui.exit_pushButton, &QPushButton::clicked, this, &test_taskManager::exitApp);
}

void test_taskManager::populateList()
{
    this->ui.task_listWidget->clear();
    vector<Task> allTasks = this->service.getTasksService();
    auto sortRuleTasks = [](Task const& task1, Task const& task2) -> bool
    {
        return task1.get_priority() < task2.get_priority();
    };
    std::sort(allTasks.begin(), allTasks.end(), sortRuleTasks);
    for (Task& task : allTasks)
        this->ui.task_listWidget->addItem(QString::fromStdString(task.get_description() + " | " + to_string(task.get_estimatedDuration()) + " | " + to_string(task.get_priority())));
}

void test_taskManager::listPriorityOne()
{
    this->ui.task_listWidget->clear();
    vector<Task> allTasks = this->service.getTasksService();
    auto sortRuleTasks = [](Task const& task1, Task const& task2) -> bool
    {
        return task1.get_priority() < task2.get_priority();
    };
    std::sort(allTasks.begin(), allTasks.end(), sortRuleTasks);
    for (Task& task : allTasks)
        if (task.get_priority() == 1)
        {
            QFont font("Times", 14, QFont::Bold);
            QListWidgetItem* item = new QListWidgetItem;
            item->setText(QString::fromStdString(task.get_description() + " | " + to_string(task.get_estimatedDuration()) + " | " + to_string(task.get_priority())));
            item->setFont(font);
            this->ui.task_listWidget->addItem(item);
        }   
        else
            this->ui.task_listWidget->addItem(QString::fromStdString(task.get_description() + " | " + to_string(task.get_estimatedDuration()) + " | " + to_string(task.get_priority())));
}

void test_taskManager::totalDuration()
{
    this->ui.totalDuration_lineEdit->clear();
    int priority = this->ui.priority_lineEdit->text().toInt();
    int totalDuration = this->service.showPriority(priority);
    this->ui.totalDuration_lineEdit->insert(QString::fromStdString(to_string(totalDuration)));
}

void test_taskManager::exitApp()
{
    exit(0);
}

void test_taskManager::addTask()
{
    string description = this->ui.description_lineEdit->text().toStdString();
    int estimatedDuration = this->ui.estimatedDuration_lineEdit->text().toInt();
    int priority = this->ui.priority_lineEdit->text().toInt();

    try
    {
        this->service.addTaskService(description, estimatedDuration, priority);
    }
    catch (exception& ex)
    {
        QMessageBox::critical(this, "Error", ex.what());
    }

    this->populateList();

    int lastElement = this->service.getTasksService().size() - 1;
    this->ui.task_listWidget->setCurrentRow(lastElement);
}

void test_taskManager::deleteTask()
{
    string description = this->ui.description_lineEdit->text().toStdString();

    try
    {
        this->service.deleteTaskService(description);
    }
    catch (exception& ex)
    {
        QMessageBox::critical(this, "Error", ex.what());
    }

    this->populateList();
}

void test_taskManager::updateTask()
{
    string description = this->ui.description_lineEdit->text().toStdString();
    int estimatedDuration = this->ui.estimatedDuration_lineEdit->text().toInt();
    int priority = this->ui.priority_lineEdit->text().toInt();

    try
    {
        this->service.updateTaskService(description, estimatedDuration, priority);
    }
    catch (exception& ex)
    {
        QMessageBox::critical(this, "Error", ex.what());
    }

    this->populateList();
}
