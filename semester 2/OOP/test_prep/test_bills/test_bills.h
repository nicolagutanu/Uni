#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_test_bills.h"
#include "service.h"

class test_bills : public QMainWindow
{
    Q_OBJECT

public:
    test_bills(Service& service, QWidget* parent = Q_NULLPTR);

private:
    Service& service;
    Ui::test_billsClass ui;

    void makeConnections();
    void populateList();

    void sortBills();
    void totalBills();
    void exitApp();

    //add ons just in case
    void addBill();
    void deleteBill();
    void updateBill();
};