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

BLA::Matrix<1,1> kE = {-6.9720};                                           // Matriz de ganho estático
BLA::Matrix<1,1> kI = {-3};                                          // Matriz de ganho dos integradores
BLA::Matrix<1,1> Int;                                                     // Matriz contendo a integral do(s) erro(s)
BLA::Matrix<1,1> x;                                                       // Matriz de estados
BLA::Matrix<1,1> u = {0};                                                       // Sinal de Controle
BLA::Matrix<1,1> ajuste = {382.58};                                           // Sinal de controle inicia a partir de 60 para quebrar a inércia do cooler, alterar de acordo com o projeto


BLA::Matrix<1,1> kE_L = {6.2163};                                           // Matriz de ganho estático
BLA::Matrix<1,1> kI_L = {5.1600};                                          // Matriz de ganho dos integradores
BLA::Matrix<1,1> Int_L;                                                     // Matriz contendo a integral do(s) erro(s)
BLA::Matrix<1,1> u_L = {0};                                                       // Sinal de Controle
BLA::Matrix<1,1> ajuste_L = {150}; 

#define N 100                                                             // Janela do filtro        

const int LM35 = A0;                                                      // Port de leitura do sensor de temperatura
double error, setPoint, aux, Ts, temperatura,sum;                
float val[N];
long cont = 0;
long lastProcess;
unsigned long lastTime = 0;


void setup(){

  Serial.begin(9600);
  setPoint = 30;                                                          // Setpoint inicial
  Ts = 25;                                                                // Tempo de amostragem
  
}

void loop (){

  // Definindo Ts = 25 ms

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

    
    if(cont > N){
      error = (setPoint - temperatura);
      float dT = (millis() - lastProcess) / 1000.0;             // Definindo o intervalo de tempo entre as amostras para o cálculo da integral e da derivada
      lastProcess = millis();
      Int += error*dT;
      Int_L += error*dT;
      x = {temperatura};
      u = kE*x + kI*Int + ajuste;
      u_L = kE_L*x + kI_L*Int_L + ajuste_L;
                                    

      // Windup

      if(error < 0.2 && error > -0.2){
        if((255 - u(0))>255)
          Int = Int/1.2;
        else if((255-u(0))<0)
          Int = Int/1.2;
        }else
          Int = Int;

      if(error < 0.2 && error > -0.2){
        if((255 - u_L(0))>255)
          Int_L = Int_L/1.2;
        else if((255 - u_L(0))<0)
          Int_L = Int_L/1.2;
        }else
          Int_L = Int_L;

      // Delimitando o limite de atuação de acordo com o PWM

      if (u(0) > 255)
        u(0) = 255;
      else if (u(0) < 0)
        u(0) = 0;
      else 
        u = u;

     if(u_L(0) > 255)
      u_L(0) = 255;
     else if (u_L(0) < 0)
        u_L(0) = 0;
      else 
        u_L = u_L;

    }
  

    // Alterando os setpoints ao decorrer da simulação
  
    if (millis() > 400000 && millis() < (400000*2)){
      setPoint = 31;  
    }else if(millis() >= (400000*2) && millis() < (400000*3)){
      setPoint = 29.5;    
    }else if(millis() >= (400000*4)){
      setPoint = 30.5;
    }

    // Escrevendo cálculos para controlar os atuadores
    int output = u(0);
    int output1 = u_L(0);
    //int output1 = 255;
    // Aplicando perturbações no sistema SISO

    if((millis() >= (400000*0.6)) && (millis() <= (400000*0.61)))
      output1 = 128;
    else if(millis() >= (400000*1.6) && (millis() <= (400000*1.62)))
      output1 = 160;
    else if (millis() >= (400000*4.6) && (millis() <= (400000*4.63)))
      output1 = 100;
    else
      output1 = output1;
      //output1 = u_L(0);

    
    
    analogWrite(6,output);
    analogWrite(5,output1);

    // Imprimindo Resultados no Matlab
  
    double results[] = {temperatura, output, output1, setPoint};
    Serial.print(results[0]);
    Serial.print("\t");
    Serial.print(results[1]);  
    Serial.print("\t");
    Serial.print(results[2]);  
    Serial.print("\t");
    Serial.println(results[3]);

    lastTime = now;
  }
}

double getError(double setPoint, double temperatura){

  error = (setPoint - temperatura);

  return error;
}
