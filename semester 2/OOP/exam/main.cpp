#include "exam.h"
#include <QtWidgets/QApplication>
#include "Repository.h"
#include "exam.h"
#include "tests.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    testAll();
    Repository repo{"E:\\School\\sem2\\OOP\\exam\\ats.txt", "E:\\School\\sem2\\OOP\\exam\\stars.txt"};
    auto allA = repo.getAstronomers();
    for (int i = 0; i < allA.size(); i++)
    {
        exam* e = new exam{ allA[i], repo };
        e->setWindowTitle(QString::fromStdString(allA[i].get_name()));
        e->show();
    }
    return a.exec();
}
