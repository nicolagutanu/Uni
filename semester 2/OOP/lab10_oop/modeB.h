#pragma once

#include <QWidget>
#include "ui_modeB.h"

class modeB : public QWidget
{
	Q_OBJECT

public:
	modeB(QWidget *parent = Q_NULLPTR);
	~modeB();

private:
	Ui::modeB ui;
};
