#include "test_taskManager.h"
#include <QtWidgets/QApplication>
#include "repository.h"
#include "service.h"
#include "tests.h"

int main(int argc, char* argv[])
{
    QApplication a(argc, argv);
    runTests();
    Repository* repository = new Repository{ "E:\\School\\sem2\\OOP\\test_taskManager\\task.txt" };
    Service service{ repository };
    test_taskManager gui{ service };
    gui.show();
    return a.exec();
}
