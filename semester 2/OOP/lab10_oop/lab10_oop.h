#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_lab10_oop.h"
#include "service.h"

class lab10_oop : public QMainWindow
{
	Q_OBJECT

public:
	lab10_oop(Service &service, QWidget *parent = Q_NULLPTR);

private:
	Service& service;
	Ui::lab10_oopClass ui;

	void makeConnections();
	int getSelectedIndex() const;

	//Admin
	void populateList();
	void addPlant();
	void deletePlant();
	void updatePlant();
	void exitAdmin();

	//Asisstant
	void populateMyList();
	void savePlant();
	void nextPlant();
	void filterPlants();
	void openPath();
	void exitAsisstant();
};
