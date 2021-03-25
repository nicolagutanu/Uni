/********************************************************************************
** Form generated from reading UI file 'test_shoppingList.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_TEST_SHOPPINGLIST_H
#define UI_TEST_SHOPPINGLIST_H

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

class Ui_test_shoppingListClass
{
public:
    QWidget *centralWidget;
    QWidget *layoutWidget;
    QVBoxLayout *verticalLayout;
    QListWidget *items_listWidget;
    QGridLayout *gridLayout;
    QLabel *label;
    QLineEdit *category_lineEdit;
    QLabel *label_2;
    QLineEdit *name_lineEdit;
    QLabel *label_3;
    QLineEdit *quantity_lineEdit;
    QGridLayout *gridLayout_2;
    QPushButton *exit_pushButton;
    QPushButton *delete_pushButton;
    QPushButton *filter_pushButton;
    QPushButton *list_pushButton;
    QMenuBar *menuBar;
    QMenu *menuShopping_List;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *test_shoppingListClass)
    {
        if (test_shoppingListClass->objectName().isEmpty())
            test_shoppingListClass->setObjectName(QString::fromUtf8("test_shoppingListClass"));
        test_shoppingListClass->resize(535, 581);
        centralWidget = new QWidget(test_shoppingListClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        layoutWidget = new QWidget(centralWidget);
        layoutWidget->setObjectName(QString::fromUtf8("layoutWidget"));
        layoutWidget->setGeometry(QRect(10, 10, 511, 521));
        verticalLayout = new QVBoxLayout(layoutWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        items_listWidget = new QListWidget(layoutWidget);
        items_listWidget->setObjectName(QString::fromUtf8("items_listWidget"));
        QFont font;
        font.setPointSize(14);
        items_listWidget->setFont(font);

        verticalLayout->addWidget(items_listWidget);

        gridLayout = new QGridLayout();
        gridLayout->setSpacing(6);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        label = new QLabel(layoutWidget);
        label->setObjectName(QString::fromUtf8("label"));
        label->setFont(font);

        gridLayout->addWidget(label, 0, 0, 1, 1);

        category_lineEdit = new QLineEdit(layoutWidget);
        category_lineEdit->setObjectName(QString::fromUtf8("category_lineEdit"));
        category_lineEdit->setFont(font);

        gridLayout->addWidget(category_lineEdit, 0, 1, 1, 1);

        label_2 = new QLabel(layoutWidget);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setFont(font);

        gridLayout->addWidget(label_2, 1, 0, 1, 1);

        name_lineEdit = new QLineEdit(layoutWidget);
        name_lineEdit->setObjectName(QString::fromUtf8("name_lineEdit"));
        name_lineEdit->setFont(font);

        gridLayout->addWidget(name_lineEdit, 1, 1, 1, 1);

        label_3 = new QLabel(layoutWidget);
        label_3->setObjectName(QString::fromUtf8("label_3"));
        label_3->setFont(font);

        gridLayout->addWidget(label_3, 2, 0, 1, 1);

        quantity_lineEdit = new QLineEdit(layoutWidget);
        quantity_lineEdit->setObjectName(QString::fromUtf8("quantity_lineEdit"));
        quantity_lineEdit->setFont(font);

        gridLayout->addWidget(quantity_lineEdit, 2, 1, 1, 1);


        verticalLayout->addLayout(gridLayout);

        gridLayout_2 = new QGridLayout();
        gridLayout_2->setSpacing(6);
        gridLayout_2->setObjectName(QString::fromUtf8("gridLayout_2"));
        exit_pushButton = new QPushButton(layoutWidget);
        exit_pushButton->setObjectName(QString::fromUtf8("exit_pushButton"));
        exit_pushButton->setFont(font);

        gridLayout_2->addWidget(exit_pushButton, 0, 0, 1, 1);

        delete_pushButton = new QPushButton(layoutWidget);
        delete_pushButton->setObjectName(QString::fromUtf8("delete_pushButton"));
        delete_pushButton->setFont(font);

        gridLayout_2->addWidget(delete_pushButton, 0, 1, 1, 1);

        filter_pushButton = new QPushButton(layoutWidget);
        filter_pushButton->setObjectName(QString::fromUtf8("filter_pushButton"));
        filter_pushButton->setFont(font);

        gridLayout_2->addWidget(filter_pushButton, 1, 0, 1, 1);

        list_pushButton = new QPushButton(layoutWidget);
        list_pushButton->setObjectName(QString::fromUtf8("list_pushButton"));
        list_pushButton->setFont(font);

        gridLayout_2->addWidget(list_pushButton, 1, 1, 1, 1);


        verticalLayout->addLayout(gridLayout_2);

        test_shoppingListClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(test_shoppingListClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 535, 21));
        menuShopping_List = new QMenu(menuBar);
        menuShopping_List->setObjectName(QString::fromUtf8("menuShopping_List"));
        test_shoppingListClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(test_shoppingListClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        test_shoppingListClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(test_shoppingListClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        test_shoppingListClass->setStatusBar(statusBar);

        menuBar->addAction(menuShopping_List->menuAction());

        retranslateUi(test_shoppingListClass);

        QMetaObject::connectSlotsByName(test_shoppingListClass);
    } // setupUi

    void retranslateUi(QMainWindow *test_shoppingListClass)
    {
        test_shoppingListClass->setWindowTitle(QCoreApplication::translate("test_shoppingListClass", "test_shoppingList", nullptr));
        label->setText(QCoreApplication::translate("test_shoppingListClass", "Category", nullptr));
        label_2->setText(QCoreApplication::translate("test_shoppingListClass", "Name", nullptr));
        label_3->setText(QCoreApplication::translate("test_shoppingListClass", "Quantity", nullptr));
        exit_pushButton->setText(QCoreApplication::translate("test_shoppingListClass", "Exit", nullptr));
        delete_pushButton->setText(QCoreApplication::translate("test_shoppingListClass", "Delete", nullptr));
        filter_pushButton->setText(QCoreApplication::translate("test_shoppingListClass", "Filter", nullptr));
        list_pushButton->setText(QCoreApplication::translate("test_shoppingListClass", "List", nullptr));
        menuShopping_List->setTitle(QCoreApplication::translate("test_shoppingListClass", "Shopping List", nullptr));
    } // retranslateUi

};

namespace Ui {
    class test_shoppingListClass: public Ui_test_shoppingListClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_TEST_SHOPPINGLIST_H
