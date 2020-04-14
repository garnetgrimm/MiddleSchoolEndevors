
#include <sstream>
#include <iostream>
#include <stdlib.h>

using namespace std;

int main() {

	int hex = 0x009E;

	string k = "009E";
	string full = "0x" + k;
	//string full = "100";
	int a = ::atof(full.c_str());
	cout << (hex - 1) << " " << a - 1 << endl;


	return 0;
}
