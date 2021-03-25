#include "tests.h"
#include "Repository.h"
#include "Domain.h"
#include <assert.h>
#include <iostream>

void addStar_validStar_willBeAdded()
{
	Repository repo{ "E:\\School\\sem2\\OOP\\exam\\ats.txt", "E:\\School\\sem2\\OOP\\exam\\stars.txt" };
	Star s = Star("hefes", "hefestis", 2, 2, 2);
	try
	{
		repo.addStar(s);
	}
	catch (exception& ex)
	{
		cout << ex.what();
	}
	assert(repo.sExistsInList(s) == true);
	repo.deleteStar(s);
}

void addStar_invalidName_notAdded()
{
	Repository repo{ "E:\\School\\sem2\\OOP\\exam\\ats.txt", "E:\\School\\sem2\\OOP\\exam\\stars.txt" };
	Star s = Star("", "hefestis", 2, 2, 2);
	try
	{
		repo.addStar(s);
	}
	catch (exception& ex)
	{
		cout << ex.what();
	}
	assert(repo.sExistsInList(s) == false);
}

void addStar_invalidDimeter_notAdded()
{
	Repository repo{ "E:\\School\\sem2\\OOP\\exam\\ats.txt", "E:\\School\\sem2\\OOP\\exam\\stars.txt" };
	Star s = Star("hefes", "hefestis", 2, 2, -2);
	try
	{
		repo.addStar(s);
	}
	catch (exception& ex)
	{
		cout << ex.what();
	}
	assert(repo.sExistsInList(s) == false);
}

void addStar_alreadyExists_notAdded()
{
	Repository repo{ "E:\\School\\sem2\\OOP\\exam\\ats.txt", "E:\\School\\sem2\\OOP\\exam\\stars.txt" };
	Star s = Star("huhs", "hefestis", 2, 2, 2);
	try
	{
		repo.addStar(s);
	}
	catch (exception& ex)
	{
		cout << ex.what();
	}
}

void testAll()
{
	addStar_validStar_willBeAdded();
	addStar_invalidName_notAdded();
	addStar_invalidDimeter_notAdded();
	addStar_alreadyExists_notAdded();
}
