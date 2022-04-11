#include<vector>
#include<iostream>
#include<string>
#include<cmath>

using namespace std;

void error(string s){
    throw runtime_error("ERROR: " + s + "\n");
    
}

void makeVector(int num, vector<string>& words){
    const vector<string> ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    const vector<string> tens = {"", "", "twenty", "thirty", "fourty", "fifty","sixty", "seventy", "eighty", "ninty"};
    const vector<string> hundreds = {"", "", "", "hundred", "thousand", "ten thousand", "one hundred thousand", "million"};
    
    int numDigits = log10(num) + 1;
    for(int i = numDigits; i > 0; i--){
        int highestDigit = pow(10, i-1);    
        int temp = num / highestDigit;
        num = num % highestDigit;
        //printf("Temp is: %d, Num is: %d, Highest Digit is: %d, I is: %d\n", temp, num,highestDigit, i);
        
        switch(i){
            case(1):
                if(ones.at(temp).compare("") != 0){
                words.push_back(ones.at(temp));
                }
                break;
            case(2):
                if(tens.at(temp).compare("") != 0){
                words.push_back(tens.at(temp));
                }
                break;
            case(3):
                if(hundreds.at(i).compare("") != 0){              
                words.push_back(ones.at(temp) + " " + hundreds.at(i));
                }
                break;
            case(4):
                if(hundreds.at(i).compare("") != 0){
                    words.push_back(ones.at(temp) + " " + hundreds.at(i));
                }
                break;
                
        }    
    }
}

int main(int argc, char** argv){
    //int test = 123;
    vector<string> words;
    

    //Need to handle 12, 13, 14, 15 (ex the one in the tens digit).

    if (argc != 2){
        error("Wrong number of arguments");
    }

    int inputNum = atoi(argv[1]);
    if((inputNum / 20 == 0) && (inputNum / 10 != 0)){
        error("The tens digit cannot be a 1");
    }
    makeVector(inputNum, words);
    

    cout << "Number " << inputNum <<" is written as ";
    for(string s : words){
        cout << s  << " ";
    }
    cout << endl;

}
