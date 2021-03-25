/********************************************************************************
** Form generated from reading UI file 'test_taskManager.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_TEST_TASKMANAGER_H
#define UI_TEST_TASKMANAGER_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_test_taskManagerClass
{
public:
    QWidget *centralWidget;
    QWidget *layoutWidget;
    QVBoxLayout *verticalLayout;
    QListWidget *task_listWidget;
    QGridLayout *gridLayout;
    QLabel *label;
    QLineEdit *description_lineEdit;
    QLabel *label_2;
    QLineEdit *estimatedDuration_lineEdit;
    QLabel *label_3;
    QLineEdit *priority_lineEdit;
    QLabel *label_4;
    QLineEdit *totalDuration_lineEdit;
    QGridLayout *gridLayout_2;
    QPushButton *list_pushButton;
    QPushButton *totalDuraction_pushButton;
    QPushButton *exit_pushButton;
    QPushButton *priorityOne_pushButton;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *test_taskManagerClass)
    {
        if (test_taskManagerClass->objectName().isEmpty())
            test_taskManagerClass->setObjectName(QString::fromUtf8("test_taskManagerClass"));
        test_taskManagerClass->resize(606, 594);
        centralWidget = new QWidget(test_taskManagerClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        layoutWidget = new QWidget(centralWidget);
        layoutWidget->setObjectName(QString::fromUtf8("layoutWidget"));
        layoutWidget->setGeometry(QRect(10, 0, 581, 541));
        verticalLayout = new QVBoxLayout(layoutWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        task_listWidget = new QListWidget(layoutWidget);
        task_listWidget->setObjectName(QString::fromUtf8("task_listWidget"));
        QFont font;
        font.setPointSize(14);
        task_listWidget->setFont(font);

        verticalLayout->addWidget(task_listWidget);

        gridLayout = new QGridLayout();
        gridLayout->setSpacing(6);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        label = new QLabel(layoutWidget);
        label->setObjectName(QString::fromUtf8("label"));
        label->setFont(font);

        gridLayout->addWidget(label, 0, 0, 1, 1);

        description_lineEdit = new QLineEdit(layoutWidget);
        description_lineEdit->setObjectName(QString::fromUtf8("description_lineEdit"));
        description_lineEdit->setFont(font);

        gridLayout->addWidget(description_lineEdit, 0, 1, 1, 1);

        label_2 = new QLabel(layoutWidget);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setFont(font);

        gridLayout->addWidget(label_2, 1, 0, 1, 1);

        estimatedDuration_lineEdit = new QLineEdit(layoutWidget);
        estimatedDuration_lineEdit->setObjectName(QString::fromUtf8("estimatedDuration_lineEdit"));
        estimatedDuration_lineEdit->setFont(font);

        gridLayout->addWidget(estimatedDuration_lineEdit, 1, 1, 1, 1);

        label_3 = new QLabel(layoutWidget);
        label_3->setObjectName(QString::fromUtf8("label_3"));
        label_3->setFont(font);

        gridLayout->addWidget(label_3, 2, 0, 1, 1);

        priority_lineEdit = new QLineEdit(layoutWidget);
        priority_lineEdit->setObjectName(QString::fromUtf8("priority_lineEdit"));
        priority_lineEdit->setFont(font);

        gridLayout->addWidget(priority_lineEdit, 2, 1, 1, 1);

        label_4 = new QLabel(layoutWidget);
        label_4->setObjectName(QString::fromUtf8("label_4"));
        label_4->setFont(font);

        gridLayout->addWidget(label_4, 3, 0, 1, 1);

        totalDuration_lineEdit = new QLineEdit(layoutWidget);
        totalDuration_lineEdit->setObjectName(QString::fromUtf8("totalDuration_lineEdit"));
        totalDuration_lineEdit->setFont(font);

        gridLayout->addWidget(totalDuration_lineEdit, 3, 1, 1, 1);


        verticalLayout->addLayout(gridLayout);

        gridLayout_2 = new QGridLayout();
        gridLayout_2->setSpacing(6);
        gridLayout_2->setObjectName(QString::fromUtf8("gridLayout_2"));
        list_pushButton = new QPushButton(layoutWidget);
        list_pushButton->setObjectName(QString::fromUtf8("list_pushButton"));
        list_pushButton->setFont(font);

        gridLayout_2->addWidget(list_pushButton, 0, 0, 1, 1);

        totalDuraction_pushButton = new QPushButton(layoutWidget);
        totalDuraction_pushButton->setObjectName(QString::fromUtf8("totalDuraction_pushButton"));
        totalDuraction_pushButton->setFont(font);

        gridLayout_2->addWidget(totalDuraction_pushButton, 0, 1, 1, 1);

        exit_pushButton = new QPushButton(layoutWidget);
        exit_pushButton->setObjectName(QString::fromUtf8("exit_pushButton"));
        exit_pushButton->setFont(font);

        gridLayout_2->addWidget(exit_pushButton, 1, 0, 1, 1);

        priorityOne_pushButton = new QPushButton(layoutWidget);
        priorityOne_pushButton->setObjectName(QString::fromUtf8("priorityOne_pushButton"));
        priorityOne_pushButton->setFont(font);

        gridLayout_2->addWidget(priorityOne_pushButton, 1, 1, 1, 1);


        verticalLayout->addLayout(gridLayout_2);

        test_taskManagerClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(test_taskManagerClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 606, 21));
        test_taskManagerClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(test_taskManagerClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        test_taskManagerClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(test_taskManagerClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        test_taskManagerClass->setStatusBar(statusBar);

        retranslateUi(test_taskManagerClass);

        QMetaObject::connectSlotsByName(test_taskManagerClass);
    } // setupUi

    void retranslateUi(QMainWindow *test_taskManagerClass)
    {
        test_taskManagerClass->setWindowTitle(QCoreApplication::translate("test_taskManagerClass", "test_taskManager", nullptr));
        label->setText(QCoreApplication::translate("test_taskManagerClass", "Description", nullptr));
        label_2->setText(QCoreApplication::translate("test_taskManagerClass", "Estimate duration", nullptr));
        label_3->setText(QCoreApplication::translate("test_taskManagerClass", "Priority", nullptr));
        label_4->setText(QCoreApplication::translate("test_taskManagerClass", "Total duration", nullptr));
        list_pushButton->setText(QCoreApplication::translate("test_taskManagerClass", "List", nullptr));
        totalDuraction_pushButton->setText(QCoreApplication::translate("test_taskManagerClass", "Total Duration", nullptr));
        exit_pushButton->setText(QCoreApplication::translate("test_taskManagerClass", "Exit", nullptr));
        priorityOne_pushButton->setText(QCoreApplication::translate("test_taskManagerClass", "Show priority 1", nullptr));
    } // retranslateUi

};

namespace Ui {
    class test_taskManagerClass: public Ui_test_taskManagerClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_TEST_TASKMANAGER_H
