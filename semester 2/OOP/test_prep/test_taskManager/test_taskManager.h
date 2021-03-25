#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_test_taskManager.h"
#include "service.h"

class test_taskManager : public QMainWindow
{
    Q_OBJECT

public:
    test_taskManager(Service& service, QWidget* parent = Q_NULLPTR);

private:
    Service& service;
    Ui::test_taskManagerClass ui;

    void makeConnection();
    void populateList();

    void listPriorityOne();
    void totalDuration();
    void exitApp();

    //add on functions just in case, you only gotta add buttons and make the connection 
    void addTask();
    void deleteTask();
    void updateTask();
};
