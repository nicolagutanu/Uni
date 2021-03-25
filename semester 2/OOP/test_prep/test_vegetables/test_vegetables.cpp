#include "test_vegetables.h"
#include "qmessagebox.h"

test_vegetables::test_vegetables(Service& service, QWidget *parent)
    : QMainWindow(parent), service{service}
{
    ui.setupUi(this);
    this->makeConnections();
    this->populateList();
    this->displayNameParts();
}

void test_vegetables::makeConnections()
{
    QObject::connect(this->ui.list_pushButton, &QPushButton::clicked, this, &test_vegetables::populateList);
    QObject::connect(this->ui.search_pushButton, &QPushButton::clicked, this, &test_vegetables::searchVegetables);
    QObject::connect(this->ui.exit_pushButton, &QPushButton::clicked, this, &test_vegetables::exitApp);
}

void test_vegetables::populateList()
{
    this->ui.family_listWidget->clear();

    vector<Vegetable> allVegetables = this->service.getVegetablesService();
    string family;

    auto sortRuleVegetables = [](Vegetable const& veg1, Vegetable const& veg2) -> bool
    {
        return veg1.get_family() < veg2.get_family();
    };
    std::sort(allVegetables.begin(), allVegetables.end(), sortRuleVegetables);

    for (int i = 0; i < allVegetables.size(); i++)
    {
        if (allVegetables[i].get_family() != family)
            this->ui.family_listWidget->addItem(QString::fromStdString(allVegetables[i].get_family()));
        family = allVegetables[i].get_family();
    }
}

int test_vegetables::getSelectedIndex() 
{
    QModelIndexList selectedIndexes = this->ui.family_listWidget->selectionModel()->selectedIndexes();
    int selectedIndex = selectedIndexes.at(0).row();
    return selectedIndex;
}

void test_vegetables::displayNameParts()
{
    QObject::connect(this->ui.family_listWidget, &QListWidget::itemSelectionChanged, [this] 
        {
            int selectedIndex = this->getSelectedIndex();

            if (selectedIndex < 0)
                QMessageBox::critical(this, "Error", "Please select a family!");

            vector<Vegetable> allVegetables = this->service.getVegetablesService();

            auto sortRuleVegetables = [](Vegetable const& veg1, Vegetable const& veg2) -> bool
            {
                return veg1.get_family() < veg2.get_family();
            };
            std::sort(allVegetables.begin(), allVegetables.end(), sortRuleVegetables);

            Vegetable selectedVeg = allVegetables[selectedIndex];

            this->ui.vegetable_listWidget->clear();

            for (Vegetable& veg : allVegetables)
                if (veg.get_family() == selectedVeg.get_family())
                    this->ui.vegetable_listWidget->addItem(QString::fromStdString(veg.get_name() + " | " + veg.get_parts()));
        });
}

void test_vegetables::searchVegetables()
{
    std::string name = this->ui.name_lineEdit->text().toStdString();

    vector<Vegetable> allVegetables = this->service.getVegetablesService();
    string family;

    auto sortRuleVegetables = [](Vegetable const& veg1, Vegetable const& veg2) -> bool
    {
        return veg1.get_family() < veg2.get_family();
    };
    std::sort(allVegetables.begin(), allVegetables.end(), sortRuleVegetables);

    for (Vegetable& veg : allVegetables)
        if (veg.get_name() == name)
        {
            this->ui.partsPrint_lineEdit->setText(QString::fromStdString(veg.get_parts()));
            family = veg.get_family();
        }

    for (int i = 0; i < this->ui.family_listWidget->count(); i++)
    {
        QListWidgetItem* item = this->ui.family_listWidget->item(i);
        if (item->text().toStdString() == family)
            this->ui.family_listWidget->setCurrentRow(i);
    }
}

void test_vegetables::exitApp()
{
    exit(0);
}

void test_vegetables::addVegetable()
{
    std::string family = this->ui.family_lineEdit->text().toStdString();
    std::string name = this->ui.name_lineEdit->text().toStdString();
    std::string parts = this->ui.parts_lineEdit->text().toStdString();

    try
    {
        this->service.addVegetableService(family, name, parts);
    }
    catch (exception& ex)
    {
        QMessageBox::critical(this, "Error", ex.what());
    }

    this->populateList();
}

void test_vegetables::deleteVegetable()
{
    std::string family = this->ui.family_lineEdit->text().toStdString();
    std::string name = this->ui.name_lineEdit->text().toStdString();

    try
    {
        this->service.deleteVegetableService(family, name);
    }
    catch (exception& ex)
    {
        QMessageBox::critical(this, "Error", ex.what());
    }

    this->populateList();
}

void test_vegetables::updateVegetable()
{
    std::string family = this->ui.family_lineEdit->text().toStdString();
    std::string name = this->ui.name_lineEdit->text().toStdString();
    std::string parts = this->ui.parts_lineEdit->text().toStdString();

    try
    {
        this->service.updateVegetableService(family, name, parts);
    }
    catch (exception& ex)
    {
        QMessageBox::critical(this, "Error", ex.what());
    }

    this->populateList();
}
