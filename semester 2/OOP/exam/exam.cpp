#include "exam.h"
#include "qmessagebox.h"
#include <iostream>
#include <string>
#include <qsortfilterproxymodel.h>

exam::exam(Astronomer& ast, Repository& repo, QWidget *parent)
    : QMainWindow(parent), repo{ repo }, ast{ ast }
{
    ui.setupUi(this);
    this->tableModel = new StarModel{ this->repo };
    this->ui.star_tableView->setModel(tableModel);
    this->ui.star_tableView->hideColumn(0);
    this->makeConnection();
}

void exam::makeConnection()
{
    QObject::connect(this->ui.add_pushButton, &QPushButton::clicked, this, &exam::addStar);
    QObject::connect(this->ui.checkBox, &QCheckBox::stateChanged, this, &exam::isCheck);
    QObject::connect(this->ui.name_lineEdit, &QLineEdit::textChanged, this, &exam::search);
}

int exam::getSelectedIndex()
{
    QModelIndexList selectedIndexes = this->ui.star_tableView->selectionModel()->selectedIndexes();
    if (selectedIndexes.size() == 0)
    {
        return -1;
    }
    int selectedIndex = selectedIndexes.at(0).row();
    return selectedIndex;
}

void exam::addStar()
{
    std::string name = this->ui.name_lineEdit->text().toStdString();
    int ra = this->ui.ra_lineEdit->text().toInt();
    int dec = this->ui.dec_lineEdit->text().toInt();
    int di = this->ui.diameter_lineEdit->text().toInt();
    Star s = Star(name, ast.get_con(), ra, dec, di);
    try
    {
        this->repo.addStar(s);
    }
    catch (exception& ex)
    {
        QMessageBox::critical(this, "Error", ex.what());
    }
}

void exam::isCheck()
{
    if (this->ui.checkBox->isChecked())
    {
        QSortFilterProxyModel* filtered_model = new QSortFilterProxyModel{};
        filtered_model->setSourceModel(this->tableModel);
        filtered_model->setFilterRegExp(QRegExp(QString::fromStdString(ast.get_con()), Qt::CaseInsensitive, QRegExp::FixedString));
        filtered_model->setFilterKeyColumn(0);
        this->ui.star_tableView->setModel(filtered_model);
        this->ui.star_tableView->hideColumn(0);
    }
    else
    {
        this->ui.star_tableView->setModel(this->tableModel);
    }
}

void exam::search()
{
    this->ui.listWidget->clear();
    vector<Star> allStars = this->repo.getStars();
    std::string name = this->ui.name_lineEdit->text().toStdString();
    for (Star& s : allStars)
    {
        std::size_t found = name.find(s.get_name());
        if (found !=std::string::npos)
            this->ui.listWidget->addItem(QString::fromStdString(s.get_name() + " " + s.get_con() + " " + to_string(s.get_ra()) + " " + to_string(s.get_dec()) + " " + to_string(s.get_diameter())));
    }
       /* if (s.get_name() == name)
            this->ui.listWidget->addItem(QString::fromStdString(s.get_name() + " " + s.get_con() + " " + to_string(s.get_ra()) + " " + to_string(s.get_dec()) + " " + to_string(s.get_diameter())));*/
}
