#pragma once
#include <string>
#include <exception>

using namespace std;

class RepoException : public std::exception
{
	std::string message;
	
public:
	RepoException(const std::string& message) : message(message) {}
	virtual const char* what() const noexcept override;
};

class ValidatorException : public std::exception
{
	std::string message;

public:
	ValidatorException(const std::string& message): message(message) {}
	virtual const char* what() const noexcept override;
};