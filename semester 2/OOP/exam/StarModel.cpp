#include "StarModel.h"

StarModel::StarModel(Repository& repo, QObject* parent): QAbstractTableModel{ parent }, repo{ repo }
{
}

StarModel::~StarModel()
{
}

int StarModel::rowCount(const QModelIndex& parent) const
{
	int totalRows = this->repo.getStars().size();
	return totalRows + 1;
}

int StarModel::columnCount(const QModelIndex& parent) const
{
	return 5;
}

QVariant StarModel::data(const QModelIndex& index, int role) const
{
	int row = index.row();
	int column = index.column();
	vector<Star> allStars = this->repo.getStars();
	if (row == allStars.size())
		return QVariant();
	Star s = allStars[row];
	if (role == Qt::DisplayRole || role == Qt::EditRole)
	{
		switch (column)
		{
		case 0:
			return QString::fromStdString(s.get_con());
		case 1:
			return QString::fromStdString(s.get_name());
		case 2:
			return QString::number(s.get_ra());
		case 3:
			return QString::number(s.get_dec());
		case 4:
			return QString::number(s.get_diameter());
		default:
			break;
		}
	}
	return QVariant{};
}

QVariant StarModel::headerData(int section, Qt::Orientation orientation, int role) const
{
	if (role == Qt::DisplayRole)
	{
		if (orientation == Qt::Horizontal)
		{
			switch (section)
			{
			case 0:
				return QString{ "Constelation" };
			case 1:
				return QString{ "Name" };
			case 2:
				return QString{ "Ra" };
			case 3:
				return QString{ "Dec" };
			case 4:
				return QString{ "Diameter" };
			default:
				break;
			}
		}
	}
	return QVariant{};
}

bool StarModel::setData(const QModelIndex& index, const QVariant& value, int role)
{
	if (!index.isValid() || role != Qt::EditRole)
		return false;
	int ideaIndex = index.row();
	vector<Star> allStars = this->repo.getStars();
	if (ideaIndex == allStars.size())
	{
		this->beginInsertRows(QModelIndex{}, ideaIndex, ideaIndex);
		switch (index.column())
		{
		case 0:
			this->repo.addStar(Star{ "a", value.toString().toStdString(), 0,0,1 });
		case 1:
			this->repo.addStar(Star{ value.toString().toStdString(), "", 0,0,1 });
		case 2:
			this->repo.addStar(Star{ "a", "",value.toInt(),0,1 });
		case 3:
			this->repo.addStar(Star{ "a", "",0,value.toInt(),1 });
		case 4:
			this->repo.addStar(Star{ "a", "",0, 0, value.toInt() });
		default:
			break;
		}
		this->endInsertRows();
		return true;
	}
	Star& currentS = allStars[ideaIndex];
	switch (index.column())
	{
	case 0:
		currentS.set_name(value.toString().toStdString());
		break;
	case 1:
		currentS.set_ra(value.toInt());
		break;
	case 2:
		currentS.set_dec(value.toInt());
		break;
	case 3:
		currentS.set_diameter(value.toInt());
		break;
	}
	this->repo.updateStar(currentS);
	emit dataChanged(index, index);
	return true;
}

Qt::ItemFlags StarModel::flags(const QModelIndex& index) const
{
	return Qt::ItemIsSelectable | Qt::ItemIsEnabled;
}
