#include "service.h"
#include "domain.h"
#include <vector>
#include <exception>

using namespace std;

int Service::addItemService(const std::string& category, const std::string& name, const int quantity)
{
	Item itemToAdd = Item(category, name, quantity);
	if (this->current_repository->itemExistsInList(itemToAdd) == true)
		throw exception("Item already exists!\n");
	this->current_repository->addItem(itemToAdd);
}

int Service::deleteItemService(const std::string category, const std::string name, const int quantity)
{
	Item itemToDelete = Item(category, name, quantity);
	if (this->current_repository->itemExistsInList(itemToDelete) == false)
		throw exception("Item doesn't exist!\n");
	this->current_repository->deleteItem(itemToDelete);
}

int Service::updateItemService(const std::string category, const std::string name, const int quantity)
{
	Item newItem = Item(category, name, quantity);
	if (this->current_repository->itemExistsInList(newItem) == false)
		throw exception("Item doesn't exist!\n");
	this->current_repository->updateItem(newItem);
}

vector<Item> Service::filteredPlants(const std::string category)
{
	vector<Item> allItems = this->current_repository->getItems();
	vector<Item> filteredItems;
	for (Item item : allItems)
		if (item.get_category() == category)
			filteredItems.push_back(item);
	return filteredItems;
}

vector<Item> Service::getItemsService() const
{
	return this->current_repository->getItems();
}
