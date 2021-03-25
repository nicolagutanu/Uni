/********************************************************************************
** Form generated from reading UI file 'exam.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_EXAM_H
#define UI_EXAM_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QCheckBox>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTableView>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_examClass
{
public:
    QWidget *centralWidget;
    QWidget *widget;
    QHBoxLayout *horizontalLayout_2;
    QVBoxLayout *verticalLayout_2;
    QTableView *star_tableView;
    QGridLayout *gridLayout;
    QLabel *label;
    QLineEdit *name_lineEdit;
    QLabel *label_2;
    QLineEdit *ra_lineEdit;
    QLabel *label_3;
    QLineEdit *dec_lineEdit;
    QLabel *label_4;
    QLineEdit *diameter_lineEdit;
    QHBoxLayout *horizontalLayout;
    QPushButton *add_pushButton;
    QCheckBox *checkBox;
    QVBoxLayout *verticalLayout_3;
    QListWidget *listWidget;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *examClass)
    {
        if (examClass->objectName().isEmpty())
            examClass->setObjectName(QString::fromUtf8("examClass"));
        examClass->resize(727, 573);
        centralWidget = new QWidget(examClass);
        centralWidget->setObjectName(QString::fromUtf8("centralWidget"));
        widget = new QWidget(centralWidget);
        widget->setObjectName(QString::fromUtf8("widget"));
        widget->setGeometry(QRect(13, 10, 701, 501));
        horizontalLayout_2 = new QHBoxLayout(widget);
        horizontalLayout_2->setSpacing(6);
        horizontalLayout_2->setContentsMargins(11, 11, 11, 11);
        horizontalLayout_2->setObjectName(QString::fromUtf8("horizontalLayout_2"));
        horizontalLayout_2->setContentsMargins(0, 0, 0, 0);
        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setSpacing(6);
        verticalLayout_2->setObjectName(QString::fromUtf8("verticalLayout_2"));
        star_tableView = new QTableView(widget);
        star_tableView->setObjectName(QString::fromUtf8("star_tableView"));

        verticalLayout_2->addWidget(star_tableView);

        gridLayout = new QGridLayout();
        gridLayout->setSpacing(6);
        gridLayout->setObjectName(QString::fromUtf8("gridLayout"));
        label = new QLabel(widget);
        label->setObjectName(QString::fromUtf8("label"));

        gridLayout->addWidget(label, 0, 0, 1, 1);

        name_lineEdit = new QLineEdit(widget);
        name_lineEdit->setObjectName(QString::fromUtf8("name_lineEdit"));

        gridLayout->addWidget(name_lineEdit, 0, 1, 1, 1);

        label_2 = new QLabel(widget);
        label_2->setObjectName(QString::fromUtf8("label_2"));

        gridLayout->addWidget(label_2, 1, 0, 1, 1);

        ra_lineEdit = new QLineEdit(widget);
        ra_lineEdit->setObjectName(QString::fromUtf8("ra_lineEdit"));

        gridLayout->addWidget(ra_lineEdit, 1, 1, 1, 1);

        label_3 = new QLabel(widget);
        label_3->setObjectName(QString::fromUtf8("label_3"));

        gridLayout->addWidget(label_3, 2, 0, 1, 1);

        dec_lineEdit = new QLineEdit(widget);
        dec_lineEdit->setObjectName(QString::fromUtf8("dec_lineEdit"));

        gridLayout->addWidget(dec_lineEdit, 2, 1, 1, 1);

        label_4 = new QLabel(widget);
        label_4->setObjectName(QString::fromUtf8("label_4"));

        gridLayout->addWidget(label_4, 3, 0, 1, 1);

        diameter_lineEdit = new QLineEdit(widget);
        diameter_lineEdit->setObjectName(QString::fromUtf8("diameter_lineEdit"));

        gridLayout->addWidget(diameter_lineEdit, 3, 1, 1, 1);


        verticalLayout_2->addLayout(gridLayout);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout->setSpacing(6);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        add_pushButton = new QPushButton(widget);
        add_pushButton->setObjectName(QString::fromUtf8("add_pushButton"));

        horizontalLayout->addWidget(add_pushButton);

        checkBox = new QCheckBox(widget);
        checkBox->setObjectName(QString::fromUtf8("checkBox"));

        horizontalLayout->addWidget(checkBox);


        verticalLayout_2->addLayout(horizontalLayout);


        horizontalLayout_2->addLayout(verticalLayout_2);

        verticalLayout_3 = new QVBoxLayout();
        verticalLayout_3->setSpacing(6);
        verticalLayout_3->setObjectName(QString::fromUtf8("verticalLayout_3"));
        listWidget = new QListWidget(widget);
        listWidget->setObjectName(QString::fromUtf8("listWidget"));

        verticalLayout_3->addWidget(listWidget);


        horizontalLayout_2->addLayout(verticalLayout_3);

        examClass->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(examClass);
        menuBar->setObjectName(QString::fromUtf8("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 727, 21));
        examClass->setMenuBar(menuBar);
        mainToolBar = new QToolBar(examClass);
        mainToolBar->setObjectName(QString::fromUtf8("mainToolBar"));
        examClass->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(examClass);
        statusBar->setObjectName(QString::fromUtf8("statusBar"));
        examClass->setStatusBar(statusBar);

        retranslateUi(examClass);

        QMetaObject::connectSlotsByName(examClass);
    } // setupUi

    void retranslateUi(QMainWindow *examClass)
    {
        examClass->setWindowTitle(QCoreApplication::translate("examClass", "exam", nullptr));
        label->setText(QCoreApplication::translate("examClass", "Name", nullptr));
        label_2->setText(QCoreApplication::translate("examClass", "Ra", nullptr));
        label_3->setText(QCoreApplication::translate("examClass", "Dec", nullptr));
        label_4->setText(QCoreApplication::translate("examClass", "Diameter", nullptr));
        add_pushButton->setText(QCoreApplication::translate("examClass", "Add", nullptr));
        checkBox->setText(QCoreApplication::translate("examClass", "Stars", nullptr));
    } // retranslateUi

};

namespace Ui {
    class examClass: public Ui_examClass {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_EXAM_H
