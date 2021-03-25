#include "test_bills.h"
#include "qmessagebox.h"

test_bills::test_bills(Service& service, QWidget *parent)
    : QMainWindow(parent), service{service}
{
    ui.setupUi(this);
    this->makeConnections();
    this->populateList();
}

void test_bills::makeConnections()
{
    QObject::connect(this->ui.list_pushButton, &QPushButton::clicked, this, &test_bills::populateList);
    QObject::connect(this->ui.sort_pushButton, &QPushButton::clicked, this, &test_bills::sortBills);
    QObject::connect(this->ui.total_pushButton, &QPushButton::clicked, this, &test_bills::totalBills);
    QObject::connect(this->ui.exit_pushButton, &QPushButton::clicked, this, &test_bills::exitApp);
}

void test_bills::populateList()
{
    this->ui.bills_listWidget->clear();
    vector<Bill> allBills = this->service.getBillsService();
    for (Bill& bill : allBills)
        this->ui.bills_listWidget->addItem(QString::fromStdString(bill.get_companyName() + " " + to_string(bill.get_sum())));
}

void test_bills::sortBills()
{
    this->ui.bills_listWidget->clear();
    vector<Bill> sortedBills = this->service.sortBillsByCompany();
    for (Bill& bill : sortedBills)
        this->ui.bills_listWidget->addItem(QString::fromStdString(bill.get_companyName() + " " + to_string(bill.get_sum())));
}

void test_bills::totalBills()
{
    std::string companyName = this->ui.companyName_lineEdit->text().toStdString();
    if (companyName == "")
        QMessageBox::critical(this, "Error", "Please input an existing company");
    double total = this->service.unpaidBills(companyName);
    this->ui.total_lineEdit->setText(QString::fromStdString(to_string(total)));
}

void test_bills::exitApp()
{
    exit(0);
}

void test_bills::addBill()
{
    std::string companyName = this->ui.companyName_lineEdit->text().toStdString();
    std::string code = this->ui.code_lineEdit->text().toStdString();
    double sum = this->ui.sum_lineEdit->text().toDouble();
    std::string isPaid = this->ui.isPaid_lineEdit->text().toStdString();

    try
    {
        this->service.addBillService(companyName, code, sum, isPaid);
    }
    catch (exception& ex)
    {
        QMessageBox::critical(this, "Error", ex.what());
    }

    this->populateList();
}

void test_bills::deleteBill()
{
    std::string code = this->ui.code_lineEdit->text().toStdString();
 
    try
    {
        this->service.deleteBillService(code);
    }
    catch (exception& ex)
    {
        QMessageBox::critical(this, "Error", ex.what());
    }

    this->populateList();
}

void test_bills::updateBill()
{
    std::string companyName = this->ui.companyName_lineEdit->text().toStdString();
    std::string code = this->ui.code_lineEdit->text().toStdString();
    double sum = this->ui.sum_lineEdit->text().toDouble();
    std::string isPaid = this->ui.isPaid_lineEdit->text().toStdString();

    try
    {
        this->service.updateBillService(companyName, code, sum, isPaid);
    }
    catch (exception& ex)
    {
        QMessageBox::critical(this, "Error", ex.what());
    }

    this->populateList();
}
