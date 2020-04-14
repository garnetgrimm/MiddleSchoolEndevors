/*
 * Parsing.h
 *
 *  Created on: Feb 27, 2016
 *      Author: Garnet Grimm
 */

#ifndef PARSING_H_
#define PARSING_H_

#include <sstream>
#include <iostream>
#include <stdlib.h>
double a,b,c;

using namespace std;

string first,second,third;

double StringToInt(string Numbers, int ReturnVal) {
first = Numbers;
second = Numbers;
third = Numbers;

first.erase(first.begin() + 8, first.end());

second.erase(second.begin(), second.begin() + 9);
second.erase(second.end() - 9,second.end());

third.erase(third.begin(), third.begin() + 18);

a = ::atof(first.c_str());
b = ::atof(second.c_str());
c = ::atof(third.c_str());

if(ReturnVal == 1) return a;
if(ReturnVal == 2) return b;
else return c;

}


#endif /* PARSING_H_ */
