#include "test_shoppingList.h"
#include <QtWidgets/QApplication>
#include "service.h"
#include "repository.h"
#include "test_shoppingList.h"
#include "tests.h"

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    runTests();
   
    FileRepository* repository = new FileRepository{ "E:\\School\\sem2\\OOP\\test_shoppingList\\items.txt" };
    Service service{ repository };
    test_shoppingList gui{ service };
    gui.show();

    return a.exec();
}
