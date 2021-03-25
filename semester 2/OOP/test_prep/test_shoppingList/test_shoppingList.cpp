#include "test_shoppingList.h"
#include "domain.h"
#include <vector>
#include "qmessagebox.h"
#include <string>

using namespace std;

test_shoppingList::test_shoppingList(Service& service, QWidget* parent)
    : QMainWindow(parent), service{service}
{
    ui.setupUi(this);

    this->populateList();
    this->makeConnections();
}

void test_shoppingList::makeConnections()
{
    QObject::connect(this->ui.list_pushButton, &QPushButton::clicked, this, &test_shoppingList::populateList);
    QObject::connect(this->ui.delete_pushButton, &QPushButton::clicked, this, &test_shoppingList::deleteItem);
    QObject::connect(this->ui.filter_pushButton, &QPushButton::clicked, this, &test_shoppingList::filterItems);
    QObject::connect(this->ui.exit_pushButton, &QPushButton::clicked, this, &test_shoppingList::exitApp);
}

void test_shoppingList::populateList()
{
    this->ui.items_listWidget->clear();
    vector<Item> allItems = this->service.getItemsService();
    for (Item& item : allItems)
        this->ui.items_listWidget->addItem(QString::fromStdString(item.get_category() + " | " + item.get_name() + " | " + to_string(item.get_quantity())));
}

void test_shoppingList::deleteItem()
{
    string category = this->ui.category_lineEdit->text().toStdString();
    string name = this->ui.name_lineEdit->text().toStdString();
    int quantity = this->ui.quantity_lineEdit->text().toInt();

    try
    {
        this->service.deleteItemService(category, name, quantity);
    }
     catch (exception& ex)
    {
        QMessageBox::critical(this, "Error", ex.what());
    }

    this->populateList();
}

void test_shoppingList::filterItems()
{
    string category = this->ui.category_lineEdit->text().toStdString();
    if (category == "")
    {
        QMessageBox::critical(this, "Error", "Choose a category!");
        return;
    }
    this->ui.items_listWidget->clear();
    vector<Item> filteredItems = this->service.filteredPlants(category);
    for (Item& item : filteredItems)
        this->ui.items_listWidget->addItem(QString::fromStdString(item.get_category() + " | " + item.get_name() + " | " + to_string(item.get_quantity())));
}

void test_shoppingList::exitApp()
{
    exit(0);
}

void test_shoppingList::addItemList()
{
    string category = this->ui.category_lineEdit->text().toStdString();
    string name = this->ui.name_lineEdit->text().toStdString();
    int quantity = this->ui.quantity_lineEdit->text().toInt();

    try
    {
        this->service.addItemService(category, name, quantity);
    }
    catch (exception& ex)
    {
        QMessageBox::critical(this, "Error", ex.what());
    }

    this->populateList();

    int lastElement = this->service.getItemsService().size() - 1;
    this->ui.items_listWidget->setCurrentRow(lastElement);
}

void test_shoppingList::updateItem()
{
    string category = this->ui.category_lineEdit->text().toStdString();
    string name = this->ui.name_lineEdit->text().toStdString();
    int quantity = this->ui.quantity_lineEdit->text().toInt();

    try
    {
        this->service.updateItemService(category, name, quantity);
    }
    catch (exception& ex)
    {
        QMessageBox::critical(this, "Error", ex.what());
    }

    this->populateList();
}