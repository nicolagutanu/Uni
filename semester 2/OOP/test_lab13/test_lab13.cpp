#include "test_lab13.h"

test_lab13::test_lab13(Service& service, QWidget *parent)
    : QMainWindow(parent), service{service}
{
    ui.setupUi(this);
    this->makeConnection();
    this->populateList();
}

void test_lab13::makeConnection()
{
    QObject::connect(this->ui.list_pushButton, &QPushButton::clicked, this, &test_lab13::showSymp);
    QObject::connect(this->ui.search_lineEdit, &QLineEdit::textChanged, this, &test_lab13::search);
    QObject::connect(this->ui.exit_pushButton, &QPushButton::clicked, this, &test_lab13::populateList);
}

void test_lab13::populateList()
{
    this->ui.items_listWidget->clear();
    vector<Disorder> allDisorders = this->service.getDisordersService();
    for (Disorder& dis : allDisorders)
        this->ui.items_listWidget->addItem(QString::fromStdString(dis.get_category() + "|" + dis.get_name()));
}

void test_lab13::showSymp()
{
    this->ui.symp_listWidget->clear();
    std::string name = this->ui.name_lineEdit->text().toStdString();
    vector<Disorder> allDisorders = this->service.getDisordersService();
    vector<std::string> symp = this->service.searchDisorders(name);
    for (auto s : symp)
        this->ui.symp_listWidget->addItem(QString::fromStdString(s));
}

void test_lab13::search()
{
    std::string s = this->ui.search_lineEdit->text().toStdString();
    this->ui.items_listWidget->clear();
    vector<Disorder> allDisorders = this->service.getDisordersService();
    for (Disorder& dis : allDisorders)
        if (dis.get_category() == s || dis.get_name() == s)
            this->ui.items_listWidget->addItem(QString::fromStdString(dis.get_category() + "|" + dis.get_name()));
}

void test_lab13::exitApp()
{
    exit(0);
}
