#include <opencv2/opencv.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <stdio.h>
#include <tchar.h>
#include "serial_test.h"	// Library described above
#include <string>

using namespace cv;

int _tmain(int argc, char** argv) {

	std::string raw_port = std::string("\\\\.\\COM") + std::string(argv[1]);
	char *serial_port = new char[raw_port.length() + 1];
	strcpy(serial_port, raw_port.c_str());
	printf("Welcome to the serial test app!\n\n");
	printf("Connected to serial port %s\n", serial_port);
	Serial* SP = new Serial(serial_port);    // adjust as needed

	if (SP->IsConnected())
		printf("We're connected");

	char incomingData[256] = "";			// don't forget to pre-allocate memory
	//printf("%s\n",incomingData);
	int dataLength = 255;
	int readResult = 0;

	namedWindow("Output", 1);
	Mat output = Mat::zeros(120,350,CV_8UC3);

	while(SP->IsConnected())
	{
		readResult = SP->ReadData(incomingData,dataLength);
		// printf("Bytes read: (0 means no data available) %i\n",readResult);
                incomingData[readResult] = 0;

        std::string outp = incomingData;
        outp = outp.substr(0, 4);

        int offset = 0;
        if(outp.length() == 4) offset = 5000;

        //create wait offset int to be set to 5000 if it gets a code
        //else = 0;

        output.setTo(Scalar(0,0,0));
        putText(output, outp, cvPoint(15,70), FONT_HERSHEY_PLAIN, 3, cvScalar(0, 255, 0) , 4);
        imshow("Output", output);
        waitKey(100 + offset);
	}

	waitKey(0);
	return 0;
}
