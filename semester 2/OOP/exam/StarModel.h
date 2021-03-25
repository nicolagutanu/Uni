#pragma once
#include "Repository.h"
#include <QAbstractTableModel>

class StarModel : public QAbstractTableModel
{
private:
	Repository& repo;

public:
	StarModel(Repository& repo, QObject* parent = NULL);
	~StarModel();
	int rowCount(const QModelIndex& parent = QModelIndex{}) const override;
	int columnCount(const QModelIndex& parent = QModelIndex{}) const override;
	QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override;
	QVariant headerData(int section, Qt::Orientation orientation, int role = Qt::DisplayRole) const override;
	bool setData(const QModelIndex& index, const QVariant& value, int role = Qt::EditRole) override;
	Qt::ItemFlags flags(const QModelIndex& index) const override;
};

