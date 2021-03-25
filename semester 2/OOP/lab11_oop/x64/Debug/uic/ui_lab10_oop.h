/********************************************************************************
** Form generated from reading UI file 'lab10_oop.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_LAB10_OOP_H
#define UI_LAB10_OOP_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QFormLayout>
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
#include <QtWidgets/QTabWidget>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_lab10_oopClass
{
public:
    QWidget *centralWidget;
    QTabWidget *tabWidget;
    QWidget *tab_3;
    QWidget *widget;
    QVBoxLayout *verticalLayout;
    QListWidget *plantsListWidget;
    QFormLayout *formLayout;
    QLabel *label;
    QLineEdit *codedNameLineEdit;
    QLabel *label_2;
    QLineEdit *speciesLineEdit;
    QLabel *label_3;
    QLineEdit *ageInMonthsLineEdit;
    QLabel *label_4;
    QLineEdit *digitizedScanLineEdit;
    QGridLayout *gridLayout;
    QPushButton *addButton;
    QPushButton *deleteButton;
    QPushButton *updateButton;
    QPushButton *exitButton;
    QPushButton *undo_pushButton;
    QPushButton *redo_pushButton;
    QWidget *tab_4;
    QWidget *layoutWidget;
    QVBoxLayout *verticalLayout_6;
    QHBoxLayout *horizontalLayout_3;
    QListWidget *mylistListWidget;
    QListWidget *filteredListWidget;
    QFormLayout *formLayout_2;
    QLabel *label_5;
    QLineEdit *codedNameAsisstantlineEdit;
    QLabel *label_6;
    QLineEdit *speciesAsisstantlineEdit;
    QLabel *label_7;
    QLineEdit *ageInMonthsAsisstantlineEdit;
    QLabel *label_8;
    QLineEdit *digitizedScanAsisstantlineEdit;
    QHBoxLayout *horizontalLayout_2;
    QVBoxLayout *verticalLayout_4;
    QPushButton *saveButton;
    QPushButton *filterButton;
    QPushButton *openButton;
    QVBoxLayout *verticalLayout_5;
    QPushButton *nextButton;
    QPushButton *mylist_pushButton;
    QPushButton *exitAsisstantButton;
    QMenuBar *menuBar;
    QMenu *menuPlant_Clusters;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *lab10_oopClass)
    {
        if (lab10_oopClass->objectName().isEmpty())
            lab10_oopClass->setObjectName(QString::fromUtf8("lab10_oopClass"));
        lab10_oopClass->resize(630, 598);
        centralWidget = new QWidget(lab10_oopClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        tabWidget = new QTabWidget(centralWidget);
        tabWidget->setObjectName(QString::fromUtf8("tabWidget"));
        tabWidget->setGeometry(QRect(10, 10, 601, 531));
        tab_3 = new QWidget();
        tab_3->setObjectName(QString::fromUtf8("tab_3"));
        widget = new QWidget(tab_3);
        widget->setObjectName(QString::fromUtf8("widget"));
        widget->setGeometry(QRect(0, 2, 591, 501));
        verticalLayout = new QVBoxLayout(widget);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        verticalLayout->setContentsMargins(0, 0, 0, 0);
        plantsListWidget = new QListWidget(widget);
        plantsListWidget->setObjectName(QString::fromUtf8("plantsListWidget"));
        QFont font;
        font.setFamily(QString::fromUtf8("Thames"));
        font.setPointSize(14);
        plantsListWidget->setFont(font);

        verticalLayout->addWidget(plantsListWidget);

        formLayout = new QFormLayout();
        formLayout->setSpacing(6);
        formLayout->setObjectName(QString::fromUtf8("formLayout"));
        label = new QLabel(widget);
        label->setObjectName(QString::fromUtf8("label"));
        label->setFont(font);

        formLayout->setWidget(0, QFormLayout::LabelRole, label);

        codedNameLineEdit = new QLineEdit(widget);
        codedNameLineEdit->setObjectName(QString::fromUtf8("codedNameLineEdit"));
        codedNameLineEdit->setFont(font);

        formLayout->setWidget(0, QFormLayout::FieldRole, codedNameLineEdit);

        label_2 = new QLabel(widget);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setFont(font);

        formLayout->setWidget(1, QFormLayout::LabelRole, label_2);

        speciesLineEdit = new QLineEdit(widget);
        speciesLineEdit->setObjectName(QString::fromUtf8("speciesLineEdit"));
        speciesLineEdit->setFont(font);

        formLayout->setWidget(1, QFormLayout::FieldRole, speciesLineEdit);

        label_3 = new QLabel(widget);
        label_3->setObjectName(QString::fromUtf8("label_3"));
        label_3->setFont(font);

        formLayout->setWidget(2, QFormLayout::LabelRole, label_3);

        ageInMonthsLineEdit = new QLineEdit(widget);
        ageInMonthsLineEdit->setObjectName(QString::fromUtf8("ageInMonthsLineEdit"));
        ageInMonthsLineEdit->setFont(font);

        formLayout->setWidget(2, QFormLayout::FieldRole, ageInMonthsLineEdit);

        label_4 = new QLabel(widget);
        label_4->setObjectName(QString::fromUtf8("label_4"));
        label_4->setFont(font);

        formLayout->setWidget(3, QFormLayout::LabelRole, label_4);

        digitizedScanLineEdit = new QLineEdit(widget);
        digitizedScanLineEdit->setObjectName(QString::fromUtf8("digitizedScanLineEdit"));
        digitizedScanLineEdit->setFont(font);

        formLayout->setWidget(3, QFormLayout::FieldRole, digitizedScanLineEdit);


        verticalLayout->addLayout(formLayout);

        gridLayout = new QGridLayout();
        gridLayout->setSpacing(6);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        addButton = new QPushButton(widget);
        addButton->setObjectName(QString::fromUtf8("addButton"));
        addButton->setFont(font);

        gridLayout->addWidget(addButton, 0, 0, 1, 1);

        deleteButton = new QPushButton(widget);
        deleteButton->setObjectName(QString::fromUtf8("deleteButton"));
        deleteButton->setFont(font);

        gridLayout->addWidget(deleteButton, 0, 1, 1, 1);

        updateButton = new QPushButton(widget);
        updateButton->setObjectName(QString::fromUtf8("updateButton"));
        updateButton->setFont(font);

        gridLayout->addWidget(updateButton, 1, 0, 1, 1);

        exitButton = new QPushButton(widget);
        exitButton->setObjectName(QString::fromUtf8("exitButton"));
        exitButton->setFont(font);

        gridLayout->addWidget(exitButton, 1, 1, 1, 1);

        undo_pushButton = new QPushButton(widget);
        undo_pushButton->setObjectName(QString::fromUtf8("undo_pushButton"));
        undo_pushButton->setFont(font);

        gridLayout->addWidget(undo_pushButton, 2, 0, 1, 1);

        redo_pushButton = new QPushButton(widget);
        redo_pushButton->setObjectName(QString::fromUtf8("redo_pushButton"));
        redo_pushButton->setFont(font);

        gridLayout->addWidget(redo_pushButton, 2, 1, 1, 1);


        verticalLayout->addLayout(gridLayout);

        tabWidget->addTab(tab_3, QString());
        tab_4 = new QWidget();
        tab_4->setObjectName(QString::fromUtf8("tab_4"));
        layoutWidget = new QWidget(tab_4);
        layoutWidget->setObjectName(QString::fromUtf8("layoutWidget"));
        layoutWidget->setGeometry(QRect(4, 2, 591, 501));
        verticalLayout_6 = new QVBoxLayout(layoutWidget);
        verticalLayout_6->setSpacing(6);
        verticalLayout_6->setContentsMargins(11, 11, 11, 11);
        verticalLayout_6->setObjectName(QString::fromUtf8("verticalLayout_6"));
        verticalLayout_6->setContentsMargins(0, 0, 0, 0);
        horizontalLayout_3 = new QHBoxLayout();
        horizontalLayout_3->setSpacing(6);
        horizontalLayout_3->setObjectName(QString::fromUtf8("horizontalLayout_3"));
        mylistListWidget = new QListWidget(layoutWidget);
        mylistListWidget->setObjectName(QString::fromUtf8("mylistListWidget"));
        mylistListWidget->setFont(font);

        horizontalLayout_3->addWidget(mylistListWidget);

        filteredListWidget = new QListWidget(layoutWidget);
        filteredListWidget->setObjectName(QString::fromUtf8("filteredListWidget"));
        filteredListWidget->setFont(font);

        horizontalLayout_3->addWidget(filteredListWidget);


        verticalLayout_6->addLayout(horizontalLayout_3);

        formLayout_2 = new QFormLayout();
        formLayout_2->setSpacing(6);
        formLayout_2->setObjectName(QString::fromUtf8("formLayout_2"));
        label_5 = new QLabel(layoutWidget);
        label_5->setObjectName(QString::fromUtf8("label_5"));
        label_5->setFont(font);

        formLayout_2->setWidget(0, QFormLayout::LabelRole, label_5);

        codedNameAsisstantlineEdit = new QLineEdit(layoutWidget);
        codedNameAsisstantlineEdit->setObjectName(QString::fromUtf8("codedNameAsisstantlineEdit"));
        codedNameAsisstantlineEdit->setFont(font);

        formLayout_2->setWidget(0, QFormLayout::FieldRole, codedNameAsisstantlineEdit);

        label_6 = new QLabel(layoutWidget);
        label_6->setObjectName(QString::fromUtf8("label_6"));
        label_6->setFont(font);

        formLayout_2->setWidget(1, QFormLayout::LabelRole, label_6);

        speciesAsisstantlineEdit = new QLineEdit(layoutWidget);
        speciesAsisstantlineEdit->setObjectName(QString::fromUtf8("speciesAsisstantlineEdit"));
        speciesAsisstantlineEdit->setFont(font);

        formLayout_2->setWidget(1, QFormLayout::FieldRole, speciesAsisstantlineEdit);

        label_7 = new QLabel(layoutWidget);
        label_7->setObjectName(QString::fromUtf8("label_7"));
        label_7->setFont(font);

        formLayout_2->setWidget(2, QFormLayout::LabelRole, label_7);

        ageInMonthsAsisstantlineEdit = new QLineEdit(layoutWidget);
        ageInMonthsAsisstantlineEdit->setObjectName(QString::fromUtf8("ageInMonthsAsisstantlineEdit"));
        ageInMonthsAsisstantlineEdit->setFont(font);

        formLayout_2->setWidget(2, QFormLayout::FieldRole, ageInMonthsAsisstantlineEdit);

        label_8 = new QLabel(layoutWidget);
        label_8->setObjectName(QString::fromUtf8("label_8"));
        label_8->setFont(font);

        formLayout_2->setWidget(3, QFormLayout::LabelRole, label_8);

        digitizedScanAsisstantlineEdit = new QLineEdit(layoutWidget);
        digitizedScanAsisstantlineEdit->setObjectName(QString::fromUtf8("digitizedScanAsisstantlineEdit"));
        digitizedScanAsisstantlineEdit->setFont(font);

        formLayout_2->setWidget(3, QFormLayout::FieldRole, digitizedScanAsisstantlineEdit);


        verticalLayout_6->addLayout(formLayout_2);

        horizontalLayout_2 = new QHBoxLayout();
        horizontalLayout_2->setSpacing(6);
        horizontalLayout_2->setObjectName(QString::fromUtf8("horizontalLayout_2"));
        verticalLayout_4 = new QVBoxLayout();
        verticalLayout_4->setSpacing(6);
        verticalLayout_4->setObjectName(QString::fromUtf8("verticalLayout_4"));
        saveButton = new QPushButton(layoutWidget);
        saveButton->setObjectName(QString::fromUtf8("saveButton"));
        saveButton->setFont(font);

        verticalLayout_4->addWidget(saveButton);

        filterButton = new QPushButton(layoutWidget);
        filterButton->setObjectName(QString::fromUtf8("filterButton"));
        filterButton->setFont(font);

        verticalLayout_4->addWidget(filterButton);

        openButton = new QPushButton(layoutWidget);
        openButton->setObjectName(QString::fromUtf8("openButton"));
        openButton->setFont(font);

        verticalLayout_4->addWidget(openButton);


        horizontalLayout_2->addLayout(verticalLayout_4);

        verticalLayout_5 = new QVBoxLayout();
        verticalLayout_5->setSpacing(6);
        verticalLayout_5->setObjectName(QString::fromUtf8("verticalLayout_5"));
        nextButton = new QPushButton(layoutWidget);
        nextButton->setObjectName(QString::fromUtf8("nextButton"));
        nextButton->setFont(font);

        verticalLayout_5->addWidget(nextButton);

        mylist_pushButton = new QPushButton(layoutWidget);
        mylist_pushButton->setObjectName(QString::fromUtf8("mylist_pushButton"));
        mylist_pushButton->setFont(font);

        verticalLayout_5->addWidget(mylist_pushButton);

        exitAsisstantButton = new QPushButton(layoutWidget);
        exitAsisstantButton->setObjectName(QString::fromUtf8("exitAsisstantButton"));
        exitAsisstantButton->setFont(font);

        verticalLayout_5->addWidget(exitAsisstantButton);


        horizontalLayout_2->addLayout(verticalLayout_5);


        verticalLayout_6->addLayout(horizontalLayout_2);

        tabWidget->addTab(tab_4, QString());
        lab10_oopClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(lab10_oopClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 630, 21));
        menuPlant_Clusters = new QMenu(menuBar);
        menuPlant_Clusters->setObjectName(QString::fromUtf8("menuPlant_Clusters"));
        lab10_oopClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(lab10_oopClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        lab10_oopClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(lab10_oopClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        lab10_oopClass->setStatusBar(statusBar);

        menuBar->addAction(menuPlant_Clusters->menuAction());

        retranslateUi(lab10_oopClass);

        tabWidget->setCurrentIndex(0);


        QMetaObject::connectSlotsByName(lab10_oopClass);
    } // setupUi

    void retranslateUi(QMainWindow *lab10_oopClass)
    {
        lab10_oopClass->setWindowTitle(QCoreApplication::translate("lab10_oopClass", "lab10_oop", nullptr));
        label->setText(QCoreApplication::translate("lab10_oopClass", "Coded Name", nullptr));
        label_2->setText(QCoreApplication::translate("lab10_oopClass", "Species", nullptr));
        label_3->setText(QCoreApplication::translate("lab10_oopClass", "Age in Months", nullptr));
        label_4->setText(QCoreApplication::translate("lab10_oopClass", "Digitized Scan", nullptr));
        addButton->setText(QCoreApplication::translate("lab10_oopClass", "Add", nullptr));
        deleteButton->setText(QCoreApplication::translate("lab10_oopClass", "Delete", nullptr));
        updateButton->setText(QCoreApplication::translate("lab10_oopClass", "Update", nullptr));
        exitButton->setText(QCoreApplication::translate("lab10_oopClass", "Exit", nullptr));
        undo_pushButton->setText(QCoreApplication::translate("lab10_oopClass", "Undo", nullptr));
        redo_pushButton->setText(QCoreApplication::translate("lab10_oopClass", "Redo", nullptr));
        tabWidget->setTabText(tabWidget->indexOf(tab_3), QCoreApplication::translate("lab10_oopClass", "mode A", nullptr));
        label_5->setText(QCoreApplication::translate("lab10_oopClass", "Coded Name", nullptr));
        label_6->setText(QCoreApplication::translate("lab10_oopClass", "Species", nullptr));
        label_7->setText(QCoreApplication::translate("lab10_oopClass", "Age in Months", nullptr));
        label_8->setText(QCoreApplication::translate("lab10_oopClass", "Digitized Scan", nullptr));
        saveButton->setText(QCoreApplication::translate("lab10_oopClass", "Save", nullptr));
        filterButton->setText(QCoreApplication::translate("lab10_oopClass", "Filter", nullptr));
        openButton->setText(QCoreApplication::translate("lab10_oopClass", "Open", nullptr));
        nextButton->setText(QCoreApplication::translate("lab10_oopClass", "Next", nullptr));
        mylist_pushButton->setText(QCoreApplication::translate("lab10_oopClass", "My List", nullptr));
        exitAsisstantButton->setText(QCoreApplication::translate("lab10_oopClass", "Exit", nullptr));
        tabWidget->setTabText(tabWidget->indexOf(tab_4), QCoreApplication::translate("lab10_oopClass", "mode B", nullptr));
        menuPlant_Clusters->setTitle(QCoreApplication::translate("lab10_oopClass", "Plant Clusters", nullptr));
    } // retranslateUi

};

namespace Ui {
    class lab10_oopClass: public Ui_lab10_oopClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_LAB10_OOP_H
