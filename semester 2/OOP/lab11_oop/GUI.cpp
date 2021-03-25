#include "GUI.h"
#include "qlayout.h"
#include "qformlayout.h"
#include "qgridlayout.h"
#include "service.h"
#include <string>
#include "qmessagebox.h"

using namespace std;

GUI::GUI(Service& service) : service{service}
{
	this->initGUI();
	this->populateList();
	this->makeConnections();
}

void GUI::initGUI()
{
	this->plantsListWidget = new QListWidget{};
	this->codedNameLine = new QLineEdit{};
	this->speciesLine = new QLineEdit{};
	this->ageInMonthsLine = new QLineEdit{};
	this->digitizedScanLine = new QLineEdit{};
	this->addButton = new QPushButton{ "Add" };
	this->deleteButton = new QPushButton{ "Delete" };

	QVBoxLayout* mainLayout = new QVBoxLayout{ this };
	mainLayout->addWidget(this->plantsListWidget);

	QFormLayout* plantDetailsLayout = new QFormLayout{};
	plantDetailsLayout->addRow("Coded Name", this->codedNameLine);
	plantDetailsLayout->addRow("Species", this->speciesLine);
	plantDetailsLayout->addRow("Age In Months", this->ageInMonthsLine);
	plantDetailsLayout->addRow("Digitized Scan", this->digitizedScanLine);
	mainLayout->addLayout(plantDetailsLayout);

	QGridLayout* buttonsLayout = new QGridLayout{};
	buttonsLayout->addWidget(this->addButton, 0, 0);
	buttonsLayout->addWidget(this->deleteButton, 0, 1);
	mainLayout->addLayout(buttonsLayout);
}

void GUI::populateList()
{
	this->plantsListWidget->clear();
	vector<Plant> allPlants = this->service.getPlantsService();
	for (Plant& plant : allPlants)
		this->plantsListWidget->addItem(QString::fromStdString(plant.get_codedName() + " - " + plant.get_species() + " " + to_string(plant.get_ageInMonths()) + " " + plant.get_digitizedScan()));
	
}

void GUI::makeConnections()
{
	QObject::connect(this->plantsListWidget, &QListWidget::itemSelectionChanged, [this]() 
		{
		int selectedIndex = this->getSelectedIndex();
		if (selectedIndex < 0)
			return;
		Plant plant = this->service.getPlantsService()[selectedIndex];
		this->codedNameLine->setText(QString::fromStdString(plant.get_codedName()));
		this->speciesLine->setText(QString::fromStdString(plant.get_species()));
		this->ageInMonthsLine->setText(QString::fromStdString(to_string(plant.get_ageInMonths())));
		this->digitizedScanLine->setText(QString::fromStdString(plant.get_digitizedScan()));
		}
	);

	QObject::connect(this->addButton, &QPushButton::clicked, this, &GUI::addPlant);
	QObject::connect(this->deleteButton, &QPushButton::clicked, this, &GUI::deletePlant);
}

int GUI::getSelectedIndex() const
{
	QModelIndexList selectedIndexes = this->plantsListWidget->selectionModel()->selectedIndexes();
	if (selectedIndexes.size() == 0)
	{
		this->codedNameLine->clear();
		this->speciesLine->clear();
		this->ageInMonthsLine->clear();
		this->digitizedScanLine->clear();
		return -1;
	}
	int selectedIndex = selectedIndexes.at(0).row();
	return selectedIndex;
}

void GUI::addPlant()
{
	string codedName = this->codedNameLine->text().toStdString();
	string species = this->speciesLine->text().toStdString();
	int ageInMonths = this->ageInMonthsLine->text().toInt();
	string digitizedScan = this->digitizedScanLine->text().toStdString(); 

	this->service.addPlantService(codedName, species, ageInMonths, digitizedScan);

	this->populateList();

	int lastElement = this->service.getPlantsService().size() - 1;
	this->plantsListWidget->setCurrentRow(lastElement);
}

void GUI::deletePlant()
{
	int selectedIndex = this->getSelectedIndex();
	if (selectedIndex < 0)
	{
		QMessageBox::critical(this, "Error", "No plant was selected!");
		return;
	}
	Plant plant = this->service.getPlantsService()[selectedIndex];
	this->service.deletePlantService(plant.get_codedName());

	this->populateList();
}