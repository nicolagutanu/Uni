#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_test_vegetables.h"
#include "service.h"

class test_vegetables : public QMainWindow
{
    Q_OBJECT

public:
    test_vegetables(Service &service, QWidget *parent = Q_NULLPTR);

private:
    Service& service;
    Ui::test_vegetablesClass ui;

    void makeConnections();
    void populateList();

    int getSelectedIndex();

    void displayNameParts();
    void searchVegetables();
    void exitApp();

    //add ons just in case
    void addVegetable();
    void deleteVegetable();
    void updateVegetable();
};
