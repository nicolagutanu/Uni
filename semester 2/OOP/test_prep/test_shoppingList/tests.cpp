#include "tests.h"
#include "service.h"
#include "repository.h"
#include <assert.h>

void deleteItem_itemExists_willBeDeleted()
{
	FileRepository* repository = new FileRepository{ "E:\\School\\sem2\\OOP\\test_shoppingList\\items.txt" };
	Service service{ repository };
	service.deleteItemService("dairy", "milk", 2);
	Item item = Item("dairy", "milk", 2);
	assert(repository->itemExistsInList(item) == false);
	service.addItemService("dairy", "milk", 2);
}

void deleteItem_itemDoesntExist_willThrowError()
{
	FileRepository* repository = new FileRepository{ "E:\\School\\sem2\\OOP\\test_shoppingList\\items.txt" };
	Service service{ repository };
	Item item = Item("dairy", "juice", 2);
	assert(repository->itemExistsInList(item) == false);
}

void runTests()
{
	deleteItem_itemExists_willBeDeleted();
	deleteItem_itemDoesntExist_willThrowError();
}
