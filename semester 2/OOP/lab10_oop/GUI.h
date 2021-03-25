#pragma once
#include <qwidget.h>
#include "service.h"
#include "qlistwidget.h"
#include "qlineedit.h"
#include "qpushbutton.h"

class GUI :
	public QWidget
{
private:
	Service& service;
	QListWidget* plantsListWidget;
	QLineEdit* codedNameLine, * speciesLine, * ageInMonthsLine, * digitizedScanLine;
	QPushButton* addButton, * deleteButton;

public:
	GUI(Service& service);

private:
	void initGUI();
	void populateList();
	void makeConnections();
	int getSelectedIndex() const;
	void addPlant();
	void deletePlant();
};

