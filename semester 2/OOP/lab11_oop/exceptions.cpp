#include "exceptions.h"

using namespace std;

const char* RepoException::what() const noexcept
{
	return this->message.c_str();
}

const char* ValidatorException::what() const noexcept
{
	return this->message.c_str();
}