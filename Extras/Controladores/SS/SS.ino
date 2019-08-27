<<<<<<< HEAD
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

BLA::Matrix<1,1> kE = {49.9131};                                           // Matriz de ganho estático
BLA::Matrix<1,1> kI = {4.0562};                                          // Matriz de ganho dos integradores
BLA::Matrix<1,1> Int;                                                     // Matriz contendo a integral do(s) erro(s)
BLA::Matrix<1,1> x;                                                       // Matriz de estados
BLA::Matrix<1,1> u = {0};                                                       // Sinal de Controle
BLA::Matrix<1,1> ajuste = {0};                                           // Sinal de controle inicia a partir de 60 para quebrar a inércia do cooler, alterar de acordo com o projeto

#define N 100                                                             // Janela do filtro        

const int LM35 = A0;                                                      // Port de leitura do sensor de temperatura
double error, setPoint, aux, Ts, y_t,sum;                
float val[N];
long cont = 0;
long lastProcess;
unsigned long lastTime = 0;
=======
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
>>>>>>> a8ebc2d53c3c85ee4d870423295c9820a7ead152


void setup(){

  Serial.begin(9600);
<<<<<<< HEAD
  setPoint = 30;                                                          // Setpoint inicial
  Ts = 25;                                                                // Tempo de amostragem
=======
  setPoint = 30;
  Ts = 25;
>>>>>>> a8ebc2d53c3c85ee4d870423295c9820a7ead152
  
}

void loop (){

  // Definindo Ts = 25 ms

<<<<<<< HEAD
=======
  unsigned long lastTime;
>>>>>>> a8ebc2d53c3c85ee4d870423295c9820a7ead152
  unsigned long now = millis();
  unsigned long timeChange = (now - lastTime);

  if (timeChange>=Ts){

    // Efetua a leitura do sensor

<<<<<<< HEAD
    y_t = (double(analogRead(LM35))*5/(1023))/0.01;
=======
    temperatura = (double(analogRead(LM35))*5/(1023))/0.01;
>>>>>>> a8ebc2d53c3c85ee4d870423295c9820a7ead152

    // Filtra os dados coletados do sensor                

    for(int i = N-1; i>0; i--){
      val[i] = val[i-1];
    }

<<<<<<< HEAD
    val[0] = y_t;
=======
    val[0] = temperatura;
>>>>>>> a8ebc2d53c3c85ee4d870423295c9820a7ead152

    sum = 0;

    for(int i = 0; i < N; i++){
      sum += val[i];
    }

    sum = (sum/N);

<<<<<<< HEAD
    y_t = sum;
    
    
    if(cont > N){
      error = (setPoint - y_t);
      float dT = (millis() - lastProcess) / 1000.0;             // Definindo o intervalo de tempo entre as amostras para o cálculo da integral e da derivada
      lastProcess = millis();
      Int += error*dT;
      
      
      
      x = {y_t};

      //lastSample = y_t;
      
      u = -kE*x - kI*Int - ajuste;

                                    

      // Windup

      if(error < 0.2 && error > -0.2){
        if((255 - u(0))>255)
          Int = Int/1.2;
        else if((255-u(0))<0)
          Int = Int/1.2;
        }else
          Int = Int;

      // Delimitando o limite de atuação de acordo com o PWM

      if (u(0) > 255)
        u(0) = 255;
      else if (u(0) < 60)
        u(0) = 60;
      else 
        u = u;

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
    int output1 = 255;
    // Aplicando perturbações no sistema SISO

    if((millis() >= (400000*0.6)) && (millis() <= (400000*0.61)))
      output1 = 128;
    else if(millis() >= (400000*1.6) && (millis() <= (400000*1.62)))
      output1 = 160;
    else if (millis() >= (400000*4.6) && (millis() <= (400000*4.63)))
      output1 = 100;
    else
      output1 = output1;

    
    
    analogWrite(6,output);
    analogWrite(5,output1);

    // Imprimindo Resultados no Matlab
  
    double results[] = {y_t, output, output1, setPoint};
    Serial.print(results[0]);
    Serial.print("\t");
    Serial.print(results[1]);  
    Serial.print("\t");
    Serial.print(results[2]);  
    Serial.print("\t");
    Serial.println(results[3]);

    cont++;
    lastTime = now;
  }
}

double getError(double setPoint, double temperatura){

  error = (setPoint - temperatura);

  return error;
=======
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
>>>>>>> a8ebc2d53c3c85ee4d870423295c9820a7ead152
}
