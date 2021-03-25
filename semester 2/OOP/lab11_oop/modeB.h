#pragma once

#include <QWidget>
#include "ui_modeB.h"
#include "service.h"
#include "tableModel.h"

class modeB : public QWidget
{
	Q_OBJECT

public:
	//constructor
	modeB(Service &service, QWidget *parent = Q_NULLPTR);
	
	//destructor
	~modeB();

private:
	Service& service;
	Ui::modeB ui;
};