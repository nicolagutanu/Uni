#include "test_lab13.h"
#include <QtWidgets/QApplication>
#include "service.h"
#include "repository.h"
#include "tests.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    runTests();

    Repository* repo = new Repository{ "E:\\School\\sem2\\OOP\\test_lab13\\list.txt" };
    Service service{ repo };
    test_lab13 gui{ service };
    gui.show();

    return a.exec();
}
