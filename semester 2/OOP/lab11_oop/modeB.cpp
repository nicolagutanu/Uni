#include "modeB.h"
#include <QSortFilterProxyModel>
#include "qfont.h"

modeB::modeB(Service& service, QWidget *parent)
	: QWidget(parent), service{service}
{
	ui.setupUi(this);

	QFont font("Thames", 14, 10, true);
	this->ui.mylist_tableView->setFont(font);
	this->ui.mylist_tableView->horizontalHeader()->setSectionResizeMode(QHeaderView::ResizeToContents);

	TableModel* model = new TableModel{ this->service };
	this->ui.mylist_tableView->setModel(model);
}

modeB::~modeB()
{
}