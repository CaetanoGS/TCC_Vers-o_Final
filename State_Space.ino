/*
  Autor: Gustavo Caetano de Souza
  Universidade Federal de Mato Grosso
  Trabalho de Conclusão de Curso
  
  Obs: Para um sistema MIMO basta alterar as dimensões das matrizes de acordo com a necessidade e preenche-las
  com os valores resultantes da função place do Matlab
*/

#include <BasicLinearAlgebra.h>

using namespace BLA;

// Criando as Matrizes necessárias para o cálculo

BLA::Matrix<1,1> kE = {4.6249};
BLA::Matrix<1,1> kI = {-5.0505};
BLA::Matrix<1,1> Int;
BLA::Matrix<1,1> x;
BLA::Matrix<1,1> u;
BLA::Matrix<1,1> ajuste = {60};


#define N 100  

const int LM35 = A0;   
double error, setPoint, aux, Ts, temperatura,sum, output1;
long dT, lastProcess = 0;
float val[N];


void setup(){

  Serial.begin(9600);
  setPoint = 30;
  Ts = 25;
  
}

void loop (){

  // Definindo Ts = 25 ms

  unsigned long lastTime;
  unsigned long now = millis();
  unsigned long timeChange = (now - lastTime);

  if (timeChange>=Ts){

    // Efetua a leitura do sensor

    temperatura = (double(analogRead(LM35))*5/(1023))/0.01;

    // Filtra os dados coletados do sensor                

    for(int i = N-1; i>0; i--){
      val[i] = val[i-1];
    }

    val[0] = temperatura;

    sum = 0;

    for(int i = 0; i < N; i++){
      sum += val[i];
    }

    sum = (sum/N);

    temperatura = sum;

    error = getError(setPoint, temperatura);
    dT = getDT(lastProcess);

    Int += error*dT;

    x = {temperatura};

    u = ajuste - kE*x - kI*Int;

    // Windup

    if(error < 0.2 && error > -0.2){
      if((255 - u(0))>255)
        Int = Int/1.3;
      else if((255-u(0))<0)
        Int = Int/1.3;
      }else
        Int = Int;

    if (u(0) > 255)
      u = {255};
    else if (u(0) < 0)
      u = {0};
    else 
      u = u;

    if((millis() >= (350000*0.6)) && (millis() <= (350000*0.67)))
      output1 = 128;
    else if(millis() >= (350000*1.6) && (millis() <= (350000*1.65)))
      output1 = 160;
    else if (millis() >= (350000*4.4) && (millis() <= (350000*4.8)))
      output1 = 100;
    else
      output1 = 255;

    // Escrevendo cálculos para controlar os atuadores
  
    analogWrite(6,u(0));
    analogWrite(5,output1);

    // Imprimindo Resultados no Matlab
  
    double results[] = {temperatura, u(0), output1, setPoint};
    Serial.print(results[0]);
    Serial.print("\t");
    Serial.print(results[1]);  
    Serial.print("\t");
    Serial.print(results[2]);  
    Serial.print("\t");
    Serial.println(results[3]);
  
  }
}

double getError(double setPoint, double temperatura){

  error = -(setPoint - temperatura);

  return error;
}

long getDT(long lastProcess){

  dT = (millis()-lastProcess)/1000;
  lastProcess = millis();
  
  return dT;
}
