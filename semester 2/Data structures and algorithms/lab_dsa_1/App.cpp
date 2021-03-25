#include "Bag.h"
#include "ShortTest.h"
#include "ExtendedTest.h"
#include <iostream>

using namespace std;

int main() {

	testAll();
	cout << "Short tests over" << endl;
	testAllExtended();

	cout << "All test over" << endl;
	Bag b;
	b.add(5);
	b.add(1);
	b.add(10);
	b.add(7);
	b.add(1);
	b.add(11);
	b.add(-3);
	b.add(1);
	b.add(3);
	b.add(5);
	b.add(1);
	b.add(10);
	b.add(7);
	b.add(1);
	b.add(11);
	b.add(-3);
	cout << "Size before remove " << b.size() << endl;
	cout << "Number of occurences of 1 before remove " << b.nrOccurrences(1) << endl;
	cout << "Number of elements removes " << b.removeAllOccurrences(1) << endl;
	cout << "Number of occurences of 1 after remove " << b.nrOccurrences(1) << endl;
	cout << "Size after remove " << b.size() << endl;
}