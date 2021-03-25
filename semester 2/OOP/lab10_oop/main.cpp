#include "lab10_oop.h"
#include <QtWidgets/QApplication>
#include "validator.h"
#include "repository.h"
#include "fileRepository.h"
#include "service.h"
#include "csvRepository.h"
#include <memory>
#include "lab10_oop.h"
#include <sstream>
#include <cstring>
#include <fstream>
#include <regex>
#include "GUI.h"
#include "htmlRepository.h"

using namespace std;

int main(int argc, char *argv[])
{
	QApplication a(argc, argv);

	string config;
	ifstream fileIn;
	fileIn.open("E:\\School\\sem2\\OOP\\lab10_oop\\configuration.txt");
	fileIn >> config;
	Repository *mylist = nullptr;
	Repository *repository = nullptr;
	if (!config.compare(0, 4, "file"))
	{
		string typeFile;
		fileIn >> typeFile;
		/*smatch matchFound;
		regex extension(".csv");*/
		if (typeFile == "csv")
		{
			mylist = new CsvRepository{ "E:\\School\\sem2\\OOP\\lab10_oop\\mylist.csv" };
		}
		else
		{
			mylist = new HtmlRepository{ "E:\\School\\sem2\\OOP\\lab10_oop\\mylist.html" };
		}
		repository = new FileRepository{ "E:\\School\\sem2\\OOP\\lab10_oop\\plants.txt" };
	}
	else
	{
		mylist = new MemoryRepository{};
		repository = new MemoryRepository{};
	}
	fileIn.close();
	Service service{ repository, mylist };
	lab10_oop gui{service};
	gui.show();
	/*GUI gui{ service };
	gui.show();*/
	
	return a.exec();
}
