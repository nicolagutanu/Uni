#pragma once
#include <string>

using namespace std;

class Astronomer
{
private:
	std::string name;
	std::string con;

public:
	Astronomer();
	Astronomer(const std::string name, const std::string con);

	std::string get_name()const { return name; }
	std::string get_con() const { return con; }

	bool operator==(const Astronomer& a);
};


class Star
{
private:
	std::string name;
	std::string con;
	int ra;
	int dec;
	int diameter;

public:
	Star();
	Star(const std::string name, const std::string con, const int ra, const int dec, const int diameter);

	std::string get_name() const { return name; }
	std::string get_con() const { return con; }
	int get_ra() const { return ra; }
	int get_dec() const { return dec; }
	int get_diameter() const { return diameter; }

	void set_name(std::string n) { name = n; }
	void set_ra(int r) { ra = r; }
	void set_dec(int d) { dec = d; }
	void set_diameter(int di) { diameter = di; }

	bool operator==(const Star& s);
};