#pragma once
#include <QAbstractTableModel>
#include "service.h"

class TableModel: public QAbstractTableModel
{
private:
	Service& service;

public:
	//constructor
	TableModel(Service& service, QObject* parent = NULL);
	
	//destructor
	~TableModel();

	//returns number of rows
	int rowCount(const QModelIndex& parent = QModelIndex{}) const override;

	//return number of columns
	int columnCount(const QModelIndex& parent = QModelIndex{}) const override;

	//adds data to the table view
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override;

	//sets the hearders
	QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const override;
};