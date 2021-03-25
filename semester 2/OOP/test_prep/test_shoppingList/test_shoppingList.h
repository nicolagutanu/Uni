#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_test_shoppingList.h"
#include "service.h"

class test_shoppingList : public QMainWindow
{
    Q_OBJECT

public:
    test_shoppingList(Service& service, QWidget* parent = Q_NULLPTR);

private:
    Service& service;
    Ui::test_shoppingListClass ui;

    void makeConnections();
    
    void populateList();
    void deleteItem();
    void filterItems();
    void exitApp();

    //add ons that can be deleted
    void addItemList();
    void updateItem();
    
};
