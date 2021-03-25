/********************************************************************************
** Form generated from reading UI file 'test_lab13.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_TEST_LAB13_H
#define UI_TEST_LAB13_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
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

class Ui_test_lab13Class
{
public:
    QWidget *centralWidget;
    QWidget *layoutWidget;
    QVBoxLayout *verticalLayout;
    QHBoxLayout *horizontalLayout;
    QListWidget *items_listWidget;
    QListWidget *symp_listWidget;
    QGridLayout *gridLayout;
    QLabel *label;
    QLineEdit *category_lineEdit;
    QLabel *label_2;
    QLineEdit *name_lineEdit;
    QLabel *label_3;
    QLineEdit *symp_lineEdit;
    QLabel *label_4;
    QLineEdit *search_lineEdit;
    QHBoxLayout *horizontalLayout_2;
    QPushButton *list_pushButton;
    QPushButton *exit_pushButton;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *test_lab13Class)
    {
        if (test_lab13Class->objectName().isEmpty())
            test_lab13Class->setObjectName(QString::fromUtf8("test_lab13Class"));
        test_lab13Class->resize(550, 594);
        centralWidget = new QWidget(test_lab13Class);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        layoutWidget = new QWidget(centralWidget);
        layoutWidget->setObjectName(QString::fromUtf8("layoutWidget"));
        layoutWidget->setGeometry(QRect(1, 10, 541, 531));
        verticalLayout = new QVBoxLayout(layoutWidget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        items_listWidget = new QListWidget(layoutWidget);
        items_listWidget->setObjectName(QString::fromUtf8("items_listWidget"));
        QFont font;
        font.setPointSize(14);
        items_listWidget->setFont(font);

        horizontalLayout->addWidget(items_listWidget);

        symp_listWidget = new QListWidget(layoutWidget);
        symp_listWidget->setObjectName(QString::fromUtf8("symp_listWidget"));
        symp_listWidget->setFont(font);

        horizontalLayout->addWidget(symp_listWidget);


        verticalLayout->addLayout(horizontalLayout);

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

        symp_lineEdit = new QLineEdit(layoutWidget);
        symp_lineEdit->setObjectName(QString::fromUtf8("symp_lineEdit"));
        symp_lineEdit->setFont(font);

        gridLayout->addWidget(symp_lineEdit, 2, 1, 1, 1);

        label_4 = new QLabel(layoutWidget);
        label_4->setObjectName(QString::fromUtf8("label_4"));
        label_4->setFont(font);

        gridLayout->addWidget(label_4, 3, 0, 1, 1);

        search_lineEdit = new QLineEdit(layoutWidget);
        search_lineEdit->setObjectName(QString::fromUtf8("search_lineEdit"));
        search_lineEdit->setFont(font);

        gridLayout->addWidget(search_lineEdit, 3, 1, 1, 1);


        verticalLayout->addLayout(gridLayout);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setSpacing(6);
        horizontalLayout_2->setObjectName(QString::fromUtf8("horizontalLayout_2"));
        list_pushButton = new QPushButton(layoutWidget);
        list_pushButton->setObjectName(QString::fromUtf8("list_pushButton"));
        list_pushButton->setFont(font);

        horizontalLayout_2->addWidget(list_pushButton);

        exit_pushButton = new QPushButton(layoutWidget);
        exit_pushButton->setObjectName(QString::fromUtf8("exit_pushButton"));
        exit_pushButton->setFont(font);

        horizontalLayout_2->addWidget(exit_pushButton);


        verticalLayout->addLayout(horizontalLayout_2);

        test_lab13Class->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(test_lab13Class);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 550, 21));
        test_lab13Class->setMenuBar(menuBar);
        mainToolBar = new QToolBar(test_lab13Class);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        test_lab13Class->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(test_lab13Class);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        test_lab13Class->setStatusBar(statusBar);

        retranslateUi(test_lab13Class);

        QMetaObject::connectSlotsByName(test_lab13Class);
    } // setupUi

    void retranslateUi(QMainWindow *test_lab13Class)
    {
        test_lab13Class->setWindowTitle(QCoreApplication::translate("test_lab13Class", "test_lab13", nullptr));
        label->setText(QCoreApplication::translate("test_lab13Class", "Category", nullptr));
        label_2->setText(QCoreApplication::translate("test_lab13Class", "Name", nullptr));
        label_3->setText(QCoreApplication::translate("test_lab13Class", "Symptoms", nullptr));
        label_4->setText(QCoreApplication::translate("test_lab13Class", "Search", nullptr));
        list_pushButton->setText(QCoreApplication::translate("test_lab13Class", "Show symptomps", nullptr));
        exit_pushButton->setText(QCoreApplication::translate("test_lab13Class", "List", nullptr));
    } // retranslateUi

};

namespace Ui {
    class test_lab13Class: public Ui_test_lab13Class {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_TEST_LAB13_H
