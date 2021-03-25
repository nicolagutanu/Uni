/********************************************************************************
** Form generated from reading UI file 'test_vegetables.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_TEST_VEGETABLES_H
#define UI_TEST_VEGETABLES_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
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

class Ui_test_vegetablesClass
{
public:
    QWidget *centralWidget;
    QWidget *widget;
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout;
    QListWidget *family_listWidget;
    QListWidget *vegetable_listWidget;
    QGridLayout *gridLayout;
    QLabel *label;
    QLineEdit *family_lineEdit;
    QLabel *label_2;
    QLineEdit *name_lineEdit;
    QLabel *label_3;
    QLineEdit *parts_lineEdit;
    QLabel *label_4;
    QLineEdit *partsPrint_lineEdit;
    QGridLayout *gridLayout_2;
    QPushButton *list_pushButton;
    QPushButton *exit_pushButton;
    QPushButton *search_pushButton;
    QMenuBar *menuBar;
    QMenu *menuVegetable;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *test_vegetablesClass)
    {
        if (test_vegetablesClass->objectName().isEmpty())
            test_vegetablesClass->setObjectName(QString::fromUtf8("test_vegetablesClass"));
        test_vegetablesClass->resize(544, 592);
        centralWidget = new QWidget(test_vegetablesClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        widget = new QWidget(centralWidget);
        widget->setObjectName(QString::fromUtf8("widget"));
        widget->setGeometry(QRect(10, 10, 522, 531));
        verticalLayout = new QVBoxLayout(widget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        family_listWidget = new QListWidget(widget);
        family_listWidget->setObjectName(QString::fromUtf8("family_listWidget"));
        QFont font;
        font.setPointSize(14);
        family_listWidget->setFont(font);

        horizontalLayout->addWidget(family_listWidget);

        vegetable_listWidget = new QListWidget(widget);
        vegetable_listWidget->setObjectName(QString::fromUtf8("vegetable_listWidget"));
        vegetable_listWidget->setFont(font);

        horizontalLayout->addWidget(vegetable_listWidget);


        verticalLayout->addLayout(horizontalLayout);

        gridLayout = new QGridLayout();
        gridLayout->setSpacing(6);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        label = new QLabel(widget);
        label->setObjectName(QString::fromUtf8("label"));
        label->setFont(font);

        gridLayout->addWidget(label, 0, 0, 1, 1);

        family_lineEdit = new QLineEdit(widget);
        family_lineEdit->setObjectName(QString::fromUtf8("family_lineEdit"));
        family_lineEdit->setFont(font);

        gridLayout->addWidget(family_lineEdit, 0, 1, 1, 1);

        label_2 = new QLabel(widget);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setFont(font);

        gridLayout->addWidget(label_2, 1, 0, 1, 1);

        name_lineEdit = new QLineEdit(widget);
        name_lineEdit->setObjectName(QString::fromUtf8("name_lineEdit"));
        name_lineEdit->setFont(font);

        gridLayout->addWidget(name_lineEdit, 1, 1, 1, 1);

        label_3 = new QLabel(widget);
        label_3->setObjectName(QString::fromUtf8("label_3"));
        label_3->setFont(font);

        gridLayout->addWidget(label_3, 2, 0, 1, 1);

        parts_lineEdit = new QLineEdit(widget);
        parts_lineEdit->setObjectName(QString::fromUtf8("parts_lineEdit"));
        parts_lineEdit->setFont(font);

        gridLayout->addWidget(parts_lineEdit, 2, 1, 1, 1);

        label_4 = new QLabel(widget);
        label_4->setObjectName(QString::fromUtf8("label_4"));
        label_4->setFont(font);

        gridLayout->addWidget(label_4, 3, 0, 1, 1);

        partsPrint_lineEdit = new QLineEdit(widget);
        partsPrint_lineEdit->setObjectName(QString::fromUtf8("partsPrint_lineEdit"));
        partsPrint_lineEdit->setFont(font);

        gridLayout->addWidget(partsPrint_lineEdit, 3, 1, 1, 1);


        verticalLayout->addLayout(gridLayout);

        gridLayout_2 = new QGridLayout();
        gridLayout_2->setSpacing(6);
        gridLayout_2->setObjectName(QString::fromUtf8("gridLayout_2"));
        list_pushButton = new QPushButton(widget);
        list_pushButton->setObjectName(QString::fromUtf8("list_pushButton"));
        list_pushButton->setFont(font);

        gridLayout_2->addWidget(list_pushButton, 0, 0, 1, 1);

        exit_pushButton = new QPushButton(widget);
        exit_pushButton->setObjectName(QString::fromUtf8("exit_pushButton"));
        exit_pushButton->setFont(font);

        gridLayout_2->addWidget(exit_pushButton, 0, 1, 1, 1);

        search_pushButton = new QPushButton(widget);
        search_pushButton->setObjectName(QString::fromUtf8("search_pushButton"));
        search_pushButton->setFont(font);

        gridLayout_2->addWidget(search_pushButton, 1, 0, 1, 1);


        verticalLayout->addLayout(gridLayout_2);

        test_vegetablesClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(test_vegetablesClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 544, 21));
        menuVegetable = new QMenu(menuBar);
        menuVegetable->setObjectName(QString::fromUtf8("menuVegetable"));
        test_vegetablesClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(test_vegetablesClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        test_vegetablesClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(test_vegetablesClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        test_vegetablesClass->setStatusBar(statusBar);

        menuBar->addAction(menuVegetable->menuAction());

        retranslateUi(test_vegetablesClass);

        QMetaObject::connectSlotsByName(test_vegetablesClass);
    } // setupUi

    void retranslateUi(QMainWindow *test_vegetablesClass)
    {
        test_vegetablesClass->setWindowTitle(QCoreApplication::translate("test_vegetablesClass", "test_vegetables", nullptr));
        label->setText(QCoreApplication::translate("test_vegetablesClass", "Family", nullptr));
        label_2->setText(QCoreApplication::translate("test_vegetablesClass", "Name", nullptr));
        label_3->setText(QCoreApplication::translate("test_vegetablesClass", "Parts", nullptr));
        label_4->setText(QCoreApplication::translate("test_vegetablesClass", "Parts that can be eaten", nullptr));
        list_pushButton->setText(QCoreApplication::translate("test_vegetablesClass", "List", nullptr));
        exit_pushButton->setText(QCoreApplication::translate("test_vegetablesClass", "Exit", nullptr));
        search_pushButton->setText(QCoreApplication::translate("test_vegetablesClass", "Search", nullptr));
        menuVegetable->setTitle(QCoreApplication::translate("test_vegetablesClass", "Vegetable", nullptr));
    } // retranslateUi

};

namespace Ui {
    class test_vegetablesClass: public Ui_test_vegetablesClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_TEST_VEGETABLES_H
