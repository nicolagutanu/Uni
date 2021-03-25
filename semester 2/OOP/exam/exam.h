#pragma once

#include <QtWidgets/QMainWindow>
#include "ui_exam.h"
#include "Repository.h"
#include "StarModel.h"

class exam : public QMainWindow
{
    Q_OBJECT

public:
    exam(Astronomer &ast, Repository &repo, QWidget *parent = Q_NULLPTR);

private:
    Astronomer& ast;
    Repository& repo;
    StarModel* tableModel;
    Ui::examClass ui;

    void makeConnection();

    int getSelectedIndex();

    void addStar();

    void isCheck();

    void search();
};
