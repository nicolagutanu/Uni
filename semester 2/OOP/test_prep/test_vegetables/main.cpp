#include "test_vegetables.h"
#include <QtWidgets/QApplication>
#include "repository.h"
#include "service.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    
    Repository* repository = new Repository{ "E:\\School\\sem2\\OOP\\test_vegetables\\vegetables.txt" };
    Service service{ repository };
    test_vegetables gui{ service };
    gui.show();

    return a.exec();
}
