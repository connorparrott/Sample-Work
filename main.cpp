#include<string>
#include<iostream>
#include<vector>

using namespace std;

class Term{
  private:
    int coefficient; 
    int exponent;
  public:
    Term(){
        //initialize coefficient and exponent to 0 if nothing is entered (technically unneccessary but hey
        coefficient = 0;
        exponent = 0;
    }
    Term(int c, int e){
        //initialize coefficient and exponent to their designated values.
        coefficient = c;
        exponent = e;
    }
    //standard getters and setters for coefficient and exponent.
    void set_coefficient(int c){coefficient = c;}
    void set_exponent(int e){exponent = e;}
    int get_coefficient(){return coefficient;}
    int get_exponent(){return exponent;}
    
    void printTerm(){
        if(coefficient == 0){
        //if coefficient is zero dont print anything
        } else if (exponent == 0){
        //if exponent is 0 dont print the x, just print the coefficient
            cout << coefficient; 
        } else if(exponent == 1){
        //if exponent is 1 dont print the 1 for a power.
            cout << coefficient << "x";
        } else if(coefficient == 1){
        //if coefficient is 1, dont print the coefficient
            cout << "x" << exponent;
        } else{
        //standard print
            cout << coefficient << "x" << exponent;
        }
    }
};

class Equation{
  private:
    vector<Term> equation;
  
  public:
    void setVector(vector<Term> temp){}
    vector<Term> getEquation(){return equation;}

    //Adds all like exponent terms.
    void add(){
        vector<Term> newEquation; //new Vector to hold sorted and added terms.
        int highestExponent = 0;

        //used to find the highest exponent
        for(Term s : equation){
            if(s.get_exponent() > highestExponent){
                highestExponent = s.get_exponent();
                //cout << "The new highest exponent is: " << highestExponent << endl;
            }
        }
        
        //iterate from highest exponent to 0 grabbing and adding the highest exponents and putting them in the new array first (thus sorting them)
        for(int i = highestExponent; i >= 0; i--){
            int c = 0; //coefficient
            int e = 0; //exponent
            for(Term it : equation){
                if(it.get_exponent() == i){
                    //sum coefficient
                    c += it.get_coefficient();
                    //exponenet doesnt change
                    e = i;
                }
            }
            
            //doesnt add terms that dont exist to the vector (ex: wont print 0x2 if there are no x2 terms
            if(c != 0){
                Term temp = Term(c, e);
                newEquation.push_back(temp);
            }
        }
        cout << "Here is the simplified polynomial\n";
        //clear equation and then updates equation with the summed equation
        equation.clear();

        for(Term s : newEquation){
            equation.push_back(s);
        } 
    }

    void read_in(string temp)
    {
        int coefficient;
        int exponent;
            //process temp and add it to the polynomial vector.
            //doesnt account for multi digit constants (ex: 12 or 52)         
            //checks if the length of the term is 3 (inferring it is a standard CxE, with C being Coefficient and E being exponent
            if(temp.length() == 3)
            {
                coefficient = temp.at(0) - 48;
                exponent = temp.at(2) - 48; //convert number on asci table to an actual int
            }
            
            //checks if the length of the term is 2
            else if(temp.length() == 2)
            {
                //checks if the first is an X, implies xE
                if(temp.at(0) == 'x')
                {
                    coefficient = 1;
                    exponent = temp.at(1) -48;
                } 
                //checks if the last is an X, implies Cx1
                else if(temp.at(1) == 'x')
                {
                    coefficient = temp.at(0) -48;
                    exponent = 1;
                }
            } 
            //assumes constant
            else if(temp.length() == 1)
            {
                coefficient = temp.at(0) - 48;
                exponent = 0;
            }
            //create term and add it to the vector.
            Term new_term = Term(coefficient, exponent);
            equation.push_back(new_term);
       
    }
};

int main(){
    
    Equation eq1; //equation (holds vector)
    string temp; //holds the term
    string op;  //holds the operator (is discarded)
    
    cout << "Please enter a polynomial to simplify or type percent d to exit\n";
    while(cin >> temp){
        //read past operator since we are only adding.
        cin >> op;
        //adds all terms to vector
        eq1.read_in(temp);
    }
    cout << "Adding...\n";
    eq1.add(); //sums the terms
    for(int i = 0; i < (int) eq1.getEquation().size(); i++){
        eq1.getEquation()[i].printTerm();
        if(i != (int) eq1.getEquation().size() - 1){
            cout << " + ";
        }
    }
    cout << endl;
    return(1);
}

