#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_test_lab13.h"
#include "service.h"

class test_lab13 : public QMainWindow
{
    Q_OBJECT

public:
    test_lab13(Service& service, QWidget* parent = Q_NULLPTR);

private:
    Service& service;
    Ui::test_lab13Class ui;

    void makeConnection();
    void populateList();

    void showSymp();

    void search();

    void exitApp();
};
