#include "lab10_oop.h"
#include "qmessagebox.h"
#include "modeB.h"
#include <qshortcut.h>

using namespace std;

lab10_oop::lab10_oop(Service& service, QWidget *parent)
	: QMainWindow(parent), service{service}
{
	ui.setupUi(this);

	this->populateList();
	this->makeConnections();
	//this->populateMyList();

	QShortcut* undoK = new QShortcut(QKeySequence(Qt::CTRL + Qt::Key_Z), this, SLOT(undoKeys()));
	QShortcut* redoK = new QShortcut(QKeySequence(Qt::CTRL + Qt::Key_Y), this, SLOT(redoKeys()));
}

void lab10_oop::makeConnections()
{
	//Admin
	QObject::connect(this->ui.deleteButton, &QPushButton::clicked, this, &lab10_oop::deletePlant);
	QObject::connect(this->ui.addButton, &QPushButton::clicked, this, &lab10_oop::addPlant);
	QObject::connect(this->ui.updateButton, &QPushButton::clicked, this, &lab10_oop::updatePlant);
	QObject::connect(this->ui.undo_pushButton, &QPushButton::clicked, this, &lab10_oop::undo);
	QObject::connect(this->ui.redo_pushButton, &QPushButton::clicked, this, &lab10_oop::redo);
	QObject::connect(this->ui.exitButton, &QPushButton::clicked, this, &lab10_oop::exitAdmin);

	//Asisstant
	QObject::connect(this->ui.saveButton, &QPushButton::clicked, this, &lab10_oop::savePlant);
	QObject::connect(this->ui.nextButton, &QPushButton::clicked, this, &lab10_oop::nextPlant);
	QObject::connect(this->ui.filterButton, &QPushButton::clicked, this, &lab10_oop::filterPlants);
	QObject::connect(this->ui.openButton, &QPushButton::clicked, this, &lab10_oop::openPath);
	QObject::connect(this->ui.mylist_pushButton, &QPushButton::clicked, this, &lab10_oop::mylistWindow);
	QObject::connect(this->ui.exitAsisstantButton, &QPushButton::clicked, this, &lab10_oop::exitAsisstant);
}

void lab10_oop::populateList()
{
	this->ui.plantsListWidget->clear();
	vector<Plant> allPlants = this->service.getPlantsService();
	for (Plant& plant : allPlants)
		this->ui.plantsListWidget->addItem(QString::fromStdString(plant.get_codedName() + " - " + plant.get_species() + " " + to_string(plant.get_ageInMonths()) + " " + plant.get_digitizedScan()));
}

void lab10_oop::addPlant()
{
	string codedName = this->ui.codedNameLineEdit->text().toStdString();
	string species = this->ui.speciesLineEdit->text().toStdString();
	int ageInMonths = this->ui.ageInMonthsLineEdit->text().toInt();
	string digitizedScan = this->ui.digitizedScanLineEdit->text().toStdString();

	try 
	{
		this->service.addPlantService(codedName, species, ageInMonths, digitizedScan);
	}
	catch (exception& ex)
	{
		QMessageBox::critical(this, "Error", ex.what());
	}

	this->populateList();

	int lastElement = this->service.getPlantsService().size() - 1;
	this->ui.plantsListWidget->setCurrentRow(lastElement);
}

void lab10_oop::deletePlant()
{
	string codedName = this->ui.codedNameLineEdit->text().toStdString();
	try
	{
		this->service.deletePlantService(codedName);
	}
	catch (exception& ex)
	{
		QMessageBox::critical(this, "Error", ex.what());
	}

	this->populateList();
}

void lab10_oop::updatePlant()
{
	string codedName = this->ui.codedNameLineEdit->text().toStdString();
	string species = this->ui.speciesLineEdit->text().toStdString();
	int ageInMonths = this->ui.ageInMonthsLineEdit->text().toInt();
	string digitizedScan = this->ui.digitizedScanLineEdit->text().toStdString();

	try
	{
		this->service.updatePlantService(codedName, species, ageInMonths, digitizedScan);
	}
	catch (exception& ex)
	{
		QMessageBox::critical(this, "Error", ex.what());
	}

	this->populateList();
}

void lab10_oop::undo()
{
	try
	{
		this->service.undo();
	}
	catch (exception& ex)
	{
		QMessageBox::critical(this, "Error", ex.what());
	}
	this->populateList();
}

void lab10_oop::redo()
{
	try
	{
		this->service.redo();
	}
	catch (exception& ex)
	{
		QMessageBox::critical(this, "Error", ex.what());
	}
	this->populateList();
}

void lab10_oop::exitAdmin()
{
	exit(0);
}

void lab10_oop::populateMyList()
{
	this->ui.mylistListWidget->clear();
	vector<Plant> mylist = this->service.getPlantsToBeDisplacedService();
	for (Plant plant : mylist)
		this->ui.mylistListWidget->addItem(QString::fromStdString(plant.get_codedName() + " - " + plant.get_species() + " " + to_string(plant.get_ageInMonths()) + " " + plant.get_digitizedScan()));
}

void lab10_oop::savePlant()
{
	string codedName = this->ui.codedNameAsisstantlineEdit->text().toStdString();

	try 
	{
		this->service.addPlantDisplacedListService(codedName);
	}
	catch (exception& ex)
	{
		QMessageBox::critical(this, "Error", ex.what());
	}

	//this->populateMyList();
}

void lab10_oop::nextPlant()
{
	Plant plant = this->service.nextPlantService();
	QMessageBox::information(this, "Next plant: ", QString::fromStdString(plant.get_codedName() + " - " + plant.get_species() + " " + to_string(plant.get_ageInMonths()) + " " + plant.get_digitizedScan()));
}

void lab10_oop::filterPlants()
{
	string species = this->ui.speciesAsisstantlineEdit->text().toStdString();
	int ageInMonths = this->ui.ageInMonthsAsisstantlineEdit->text().toInt();

	if (ageInMonths <= 0)
		QMessageBox::critical(this, "Error", "Not a valid age in months!");
	else
	{
		vector<Plant> filteredPlants = this->service.getPlantsService();
		for (Plant plant : filteredPlants)
			if (plant.get_ageInMonths() < ageInMonths && plant.get_species() == species)
				this->ui.filteredListWidget->addItem(QString::fromStdString(plant.get_codedName() + " - " + plant.get_species() + " " + to_string(plant.get_ageInMonths()) + " " + plant.get_digitizedScan()));
	}
}

void lab10_oop::openPath()
{
	this->service.openPath();
}

void lab10_oop::mylistWindow()
{
	modeB* mylist = new modeB{ this->service };
	mylist->show();
}

void lab10_oop::exitAsisstant()
{
	exit(0);
}

void lab10_oop::undoKeys()
{
	this->undo();
}

void lab10_oop::redoKeys()
{
	this->redo();
}