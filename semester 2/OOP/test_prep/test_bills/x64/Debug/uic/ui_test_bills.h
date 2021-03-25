/********************************************************************************
** Form generated from reading UI file 'test_bills.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_TEST_BILLS_H
#define UI_TEST_BILLS_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenu>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_test_billsClass
{
public:
    QWidget *centralWidget;
    QWidget *widget;
    QVBoxLayout *verticalLayout;
    QListWidget *bills_listWidget;
    QGridLayout *gridLayout;
    QLabel *label;
    QLineEdit *companyName_lineEdit;
    QLabel *label_2;
    QLineEdit *code_lineEdit;
    QLabel *label_3;
    QLineEdit *sum_lineEdit;
    QLabel *label_4;
    QLineEdit *isPaid_lineEdit;
    QLabel *label_5;
    QLineEdit *total_lineEdit;
    QGridLayout *gridLayout_2;
    QPushButton *list_pushButton;
    QPushButton *sort_pushButton;
    QPushButton *total_pushButton;
    QPushButton *exit_pushButton;
    QMenuBar *menuBar;
    QMenu *menuBills_bills_bills;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *test_billsClass)
    {
        if (test_billsClass->objectName().isEmpty())
            test_billsClass->setObjectName(QString::fromUtf8("test_billsClass"));
        test_billsClass->resize(600, 586);
        centralWidget = new QWidget(test_billsClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        widget = new QWidget(centralWidget);
        widget->setObjectName(QString::fromUtf8("widget"));
        widget->setGeometry(QRect(10, 10, 581, 521));
        verticalLayout = new QVBoxLayout(widget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        bills_listWidget = new QListWidget(widget);
        bills_listWidget->setObjectName(QString::fromUtf8("bills_listWidget"));
        QFont font;
        font.setPointSize(14);
        bills_listWidget->setFont(font);

        verticalLayout->addWidget(bills_listWidget);

        gridLayout = new QGridLayout();
        gridLayout->setSpacing(6);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        label = new QLabel(widget);
        label->setObjectName(QString::fromUtf8("label"));
        label->setFont(font);

        gridLayout->addWidget(label, 0, 0, 1, 1);

        companyName_lineEdit = new QLineEdit(widget);
        companyName_lineEdit->setObjectName(QString::fromUtf8("companyName_lineEdit"));
        companyName_lineEdit->setFont(font);

        gridLayout->addWidget(companyName_lineEdit, 0, 1, 1, 1);

        label_2 = new QLabel(widget);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setFont(font);

        gridLayout->addWidget(label_2, 1, 0, 1, 1);

        code_lineEdit = new QLineEdit(widget);
        code_lineEdit->setObjectName(QString::fromUtf8("code_lineEdit"));
        code_lineEdit->setFont(font);

        gridLayout->addWidget(code_lineEdit, 1, 1, 1, 1);

        label_3 = new QLabel(widget);
        label_3->setObjectName(QString::fromUtf8("label_3"));
        label_3->setFont(font);

        gridLayout->addWidget(label_3, 2, 0, 1, 1);

        sum_lineEdit = new QLineEdit(widget);
        sum_lineEdit->setObjectName(QString::fromUtf8("sum_lineEdit"));
        sum_lineEdit->setFont(font);

        gridLayout->addWidget(sum_lineEdit, 2, 1, 1, 1);

        label_4 = new QLabel(widget);
        label_4->setObjectName(QString::fromUtf8("label_4"));
        label_4->setFont(font);

        gridLayout->addWidget(label_4, 3, 0, 1, 1);

        isPaid_lineEdit = new QLineEdit(widget);
        isPaid_lineEdit->setObjectName(QString::fromUtf8("isPaid_lineEdit"));
        isPaid_lineEdit->setFont(font);

        gridLayout->addWidget(isPaid_lineEdit, 3, 1, 1, 1);

        label_5 = new QLabel(widget);
        label_5->setObjectName(QString::fromUtf8("label_5"));
        label_5->setFont(font);

        gridLayout->addWidget(label_5, 4, 0, 1, 1);

        total_lineEdit = new QLineEdit(widget);
        total_lineEdit->setObjectName(QString::fromUtf8("total_lineEdit"));
        total_lineEdit->setFont(font);

        gridLayout->addWidget(total_lineEdit, 4, 1, 1, 1);


        verticalLayout->addLayout(gridLayout);

        gridLayout_2 = new QGridLayout();
        gridLayout_2->setSpacing(6);
        gridLayout_2->setObjectName(QString::fromUtf8("gridLayout_2"));
        list_pushButton = new QPushButton(widget);
        list_pushButton->setObjectName(QString::fromUtf8("list_pushButton"));
        list_pushButton->setFont(font);

        gridLayout_2->addWidget(list_pushButton, 0, 0, 1, 1);

        sort_pushButton = new QPushButton(widget);
        sort_pushButton->setObjectName(QString::fromUtf8("sort_pushButton"));
        sort_pushButton->setFont(font);

        gridLayout_2->addWidget(sort_pushButton, 0, 1, 1, 1);

        total_pushButton = new QPushButton(widget);
        total_pushButton->setObjectName(QString::fromUtf8("total_pushButton"));
        total_pushButton->setFont(font);

        gridLayout_2->addWidget(total_pushButton, 1, 0, 1, 1);

        exit_pushButton = new QPushButton(widget);
        exit_pushButton->setObjectName(QString::fromUtf8("exit_pushButton"));
        exit_pushButton->setFont(font);

        gridLayout_2->addWidget(exit_pushButton, 1, 1, 1, 1);


        verticalLayout->addLayout(gridLayout_2);

        test_billsClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(test_billsClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 600, 21));
        menuBills_bills_bills = new QMenu(menuBar);
        menuBills_bills_bills->setObjectName(QString::fromUtf8("menuBills_bills_bills"));
        test_billsClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(test_billsClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        test_billsClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(test_billsClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        test_billsClass->setStatusBar(statusBar);

        menuBar->addAction(menuBills_bills_bills->menuAction());

        retranslateUi(test_billsClass);

        QMetaObject::connectSlotsByName(test_billsClass);
    } // setupUi

    void retranslateUi(QMainWindow *test_billsClass)
    {
        test_billsClass->setWindowTitle(QCoreApplication::translate("test_billsClass", "test_bills", nullptr));
        label->setText(QCoreApplication::translate("test_billsClass", "Company name", nullptr));
        label_2->setText(QCoreApplication::translate("test_billsClass", "Code", nullptr));
        label_3->setText(QCoreApplication::translate("test_billsClass", "Sum", nullptr));
        label_4->setText(QCoreApplication::translate("test_billsClass", "Paid", nullptr));
        label_5->setText(QCoreApplication::translate("test_billsClass", "Total of unpaid bills from a company", nullptr));
        list_pushButton->setText(QCoreApplication::translate("test_billsClass", "List", nullptr));
        sort_pushButton->setText(QCoreApplication::translate("test_billsClass", "Sort", nullptr));
        total_pushButton->setText(QCoreApplication::translate("test_billsClass", "Total", nullptr));
        exit_pushButton->setText(QCoreApplication::translate("test_billsClass", "Exit", nullptr));
        menuBills_bills_bills->setTitle(QCoreApplication::translate("test_billsClass", "Bills, bills, bills", nullptr));
    } // retranslateUi

};

namespace Ui {
    class test_billsClass: public Ui_test_billsClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_TEST_BILLS_H
