/********************************************************************************
** Form generated from reading UI file 'modeB.ui'
**
** Created by: Qt User Interface Compiler version 5.14.2
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MODEB_H
#define UI_MODEB_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QTableView>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_modeB
{
public:
    QVBoxLayout *verticalLayout;
    QTableView *mylist_tableView;

    void setupUi(QWidget *modeB)
    {
        if (modeB->objectName().isEmpty())
            modeB->setObjectName(QString::fromUtf8("modeB"));
        modeB->resize(534, 557);
        verticalLayout = new QVBoxLayout(modeB);
        verticalLayout->setSpacing(6);
        verticalLayout->setContentsMargins(11, 11, 11, 11);
        verticalLayout->setObjectName(QString::fromUtf8("verticalLayout"));
        mylist_tableView = new QTableView(modeB);
        mylist_tableView->setObjectName(QString::fromUtf8("mylist_tableView"));

        verticalLayout->addWidget(mylist_tableView);


        retranslateUi(modeB);

        QMetaObject::connectSlotsByName(modeB);
    } // setupUi

    void retranslateUi(QWidget *modeB)
    {
        modeB->setWindowTitle(QCoreApplication::translate("modeB", "modeB", nullptr));
    } // retranslateUi

};

namespace Ui {
    class modeB: public Ui_modeB {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MODEB_H
