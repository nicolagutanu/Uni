#include "test_bills.h"
#include <QtWidgets/QApplication>
#include "tests.h"
#include "service.h"
#include "repository.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);

    runTests();
    
    Repository* repo = new Repository{ "E:\\School\\sem2\\OOP\\test_bills\\bills.txt" };
    Service service{ repo };
    test_bills gui{ service };
    gui.show();
    
    return a.exec();
}
