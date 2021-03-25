#pragma once
#include <QtWidgets/QMainWindow>
#include "ui_lab10_oop.h"
#include "service.h"
#include "modeB.h"

class lab10_oop : public QMainWindow
{
	Q_OBJECT

public:
	lab10_oop(Service& service, QWidget* parent = Q_NULLPTR);

private:
	Service& service;
	Ui::lab10_oopClass ui;

	//for qt, for buttons
	void makeConnections();

	//ADMIN
	void populateList();
	void addPlant();
	void deletePlant();
	void updatePlant();
	void undo();
	void redo();
	void exitAdmin();

	//ASISSTANT
	void populateMyList();
	void savePlant();
	void nextPlant();
	void filterPlants();
	void openPath();
	//handles the mylist window, opens and populates
	void mylistWindow();
	void exitAsisstant();

public slots:

	void undoKeys();
	void redoKeys();
};