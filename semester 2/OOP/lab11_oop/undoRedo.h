#pragma once
#include "domain.h"
#include "repository.h"

//VIRTUAL UNDO
class UndoAction
{
public:
	virtual void doUndo() = 0;
	virtual void doRedo() = 0;
	UndoAction()noexcept {}
	virtual ~UndoAction() = default;
};

// UNDO/REDO FOR ADD
class UndoAdd : public UndoAction
{
	Plant addedPlant;
	Repository* repository;

public:
	UndoAdd(Repository* repository, const Plant& addedPlant) : repository{ repository }, addedPlant{ addedPlant }{}
	
	void doUndo() override
	{
		repository->deletePlant(addedPlant);
	}
	void doRedo() override
	{
		repository->addPlant(addedPlant);
	}
};

// UNDO/REDO FOR DELETE
class UndoDelete : public UndoAction
{
	Plant deletedPlant;
	Repository* repository;

public:
	UndoDelete(Repository* repository, const Plant& deletedPlant) : repository{ repository }, deletedPlant{ deletedPlant }{}

	void doUndo() override
	{
		repository->addPlant(deletedPlant);
	}
	void doRedo() override
	{
		repository->deletePlant(deletedPlant);
	}
};

// UNDO/REDO FOR UPDATE
class UndoUpdate : public UndoAction
{
	Plant oldPlant;
	Plant newPlant;
	Repository* repository;

public:
	UndoUpdate(Repository* repository, const Plant& newPlant, const Plant& oldPlant) : repository{ repository }, newPlant{ newPlant }, oldPlant{ oldPlant }{}

	void doUndo() override
	{
		repository->updatePlant(oldPlant);
	}
	void doRedo() override
	{
		repository->updatePlant(newPlant);
	}
};