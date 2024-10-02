// g++ catByte.cpp
// ./a.out 42 A5 7E

#include <iostream>
#include <iomanip>      // std::setw
#include <stdio.h>
#include <stdlib.h>
#include <algorithm>    // std::fill

using namespace std;

void
cpyFile2Data (unsigned char *dataDst, unsigned char *fileSrc, int dataLength) {
  for (int i = 0; i < dataLength; i++) {
    dataDst [i] = (fileSrc[i] << 4) & 0xF0;
    dataDst [i] |= (fileSrc [i+1] >> 4) & 0x0F;
  }
}

void
cpyData2File (unsigned char *fileDst, unsigned char *dataSrc, int dataLength) {
  for (int i = 0; i < dataLength; i++) {
    fileDst [i] |= (dataSrc[i] >> 4) & 0x0F;
    fileDst [i+1] |= (dataSrc [i] << 4) & 0xF0;
  }
}

void printTab (unsigned char *data, int length) {
  cout << hex;
  for (int i (0); i < length; ++i) {
    if (! (i % 16))
      cout << endl;
    cout << setw (2) << setfill ('0') << (unsigned int) data[i] << " ";
  }
  cout << dec;
}

int
main (int argc, char** argv, char** envp) {

  int length (argc-1);
  unsigned char tab [length];
  for (int i = 1; i < argc; i++) {
    unsigned int adresse;
    sscanf (argv[i], "%x", &adresse);
    tab [i-1] = adresse & 0xFF;
  }

  cout << "original: ";
  printTab (tab, length);
  cout << endl;

  int reduceLength (length-1);
  unsigned char reduce [reduceLength];
  fill (reduce, reduce+reduceLength, 0);

  cpyFile2Data (reduce, tab, reduceLength);
  cout << "reduce: ";
  printTab (reduce, reduceLength);
  cout << endl;

  int extendLength (length+1);
  unsigned char extend [extendLength];
  fill (extend, extend+extendLength, 0);

  cpyData2File (extend, tab, length);
  cout << "extend: ";
  printTab (extend, extendLength);
  cout << endl;
    
  return 0;
}
