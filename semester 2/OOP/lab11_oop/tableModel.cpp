#include "tableModel.h"
#include "qfont.h"

TableModel::TableModel(Service& service, QObject* parent): QAbstractTableModel{parent}, service{service}
{
}

TableModel::~TableModel()
{
}

int TableModel::rowCount(const QModelIndex& parent) const
{
	int totalRows = this->service.getPlantsToBeDisplacedService().size();
	return totalRows + 1;
}

int TableModel::columnCount(const QModelIndex& parent) const
{
	return 4;
}

QVariant TableModel::data(const QModelIndex& index, int role) const
{
	int row = index.row();
	int column = index.column();

	std::vector<Plant> displacedPlants = this->service.getPlantsToBeDisplacedService();

	if (row == displacedPlants.size())
		return QVariant();

	Plant plant = displacedPlants[row];
	if (role == Qt::DisplayRole || role == Qt::EditRole)
	{
		switch (column)
		{
		case 0:
			return QString::fromStdString(plant.get_codedName());
		case 1:
			return QString::fromStdString(plant.get_species());
		case 2:
			return QString::fromStdString(to_string(plant.get_ageInMonths()));
		case 3:
			return QString::fromStdString(plant.get_digitizedScan());
		default:
			break;
		}
	}

	if (role == Qt::FontRole)
	{
		QFont font("Thames", 14, 10, true);
		font.setItalic(false);
		return font;
	}

	return QVariant{};
}

QVariant TableModel::headerData(int section, Qt::Orientation orientation, int role) const
{
	if (role == Qt::DisplayRole)
	{
		if (orientation == Qt::Horizontal)
		{
			switch (section)
			{
			case 0:
				return QString{ "Coded Name" };
			case 1:
				return QString{ "Species" };
			case 2:
				return QString{ "Age In Months" };
			case 3:
				return QString{ "Digitized Scan" };
			default:
				break;
			}
		}
	}

	if (role == Qt::FontRole)
	{
		QFont font("Thames", 14, 10, true);
		font.setBold(true);
		font.setItalic(false);
		return font;
	}

	return QVariant{};
}