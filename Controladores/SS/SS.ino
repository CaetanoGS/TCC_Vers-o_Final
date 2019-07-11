class mSS{
public:
  
  double error, sample, lastSample, K, kI, I, x, U, setPoint, aux;
  long lastProcess;
  
  // Método construtor

  mSS(double _K, double _kI){
    K = _K;
    kI = _kI;    
  }

  // Adicionar uma nova amostra
  
  void addNewSample(double _sample){
    sample = _sample;
  }
  
  // Adicionar Setpoint

  void setSetPoint(double _setPoint){
    setPoint = _setPoint;
  }
  
  //Calculo do controle que é dado por u(t) = Ki*integral(Erro) + K*x(t)

  double process(){

    error = setPoint - sample; // Coloca-se sinal invertido por conta da característica do sistema

    // Diferença de tempo entre as amostras

          
    float dT = (millis() - lastProcess) / 1000.0;
    lastProcess = millis();
    
    // Ação integral

    I += (error * dT);            

    if(error < 0.2 && error > -0.2){
      if((255 - aux)>255)
        I = 0;
      else if((255 - aux)<0)
      I = I/1.2;
  }else
      I=I;

    // Ação Derivativa para encontrar os estados x(t)

     x = (lastSample-sample)/dT;
     lastSample = sample;    
    
    // Ação de Controle

    U = 60 -kI*I - K*x;
    aux = U;

    // Controle do intervalo de atuação  de acordo com as limitações do PWM

    if (U > 255)
      U = 255;
    else if (U < 0)
      U = 0;
    else
      U = U;
      
    
    return U;
  
  }
};



// Mapeamento de constantes

#define N 100
#define K   5.2527
#define Ki 3.3512
#define K1 4.6249
#define Ki1 -5.0505

const int LM35 = A0;
int cont = 0;
double temperatura, input, output,output1, sum, setPoint = 30;
float val[N];

mSS MySS(K, Ki);
mSS MySS1(K1, Ki1);

void setup() {
  
  Serial.begin(9600); 
  pinMode(LM35,INPUT);
  MySS.setSetPoint(setPoint);
  MySS1.setSetPoint(setPoint);

}

void loop() {

temperatura = (double(analogRead(LM35))*5/(1023))/0.01; //Leitura da temperatura baseando-se nos calculos do datasheet


// Filtro janela móvel


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

// Inicio do controle

MySS.addNewSample(temperatura);
MySS1.addNewSample(temperatura);

if (cont >= 99){
  
  output = MySS.process();
  output1 = MySS1.process();
  //output1 = 255;

// Controle do atuador

  analogWrite(6,output);
  analogWrite(5,output1);

  if (millis() > 350000 && millis() < (350000*2)){
    setPoint = 31;
    MySS.setSetPoint(setPoint);
    MySS1.setSetPoint(setPoint);
  
  }else if(millis() >= (350000*2) && millis() < (350000*3)){
    setPoint = 29.5;
    MySS.setSetPoint(setPoint);
    MySS1.setSetPoint(setPoint);
  
  }else if(millis() >= (350000*3) && millis() < (350000*4)){
    setPoint = 31.5;
    MySS.setSetPoint(setPoint);
    MySS1.setSetPoint(setPoint);
    
  }else if(millis() >= (350000*4)){
    setPoint = 30.5;
    MySS.setSetPoint(setPoint);
    MySS1.setSetPoint(setPoint);
  }


// Construção de vetores para exportação para o Matlab via serial
}
double results[] = {temperatura, output, output1, setPoint};

Serial.print(results[0]);
Serial.print("\t");
Serial.print(results[1]); 
Serial.print("\t");
Serial.print(results[2]); 
Serial.print("\t"); 
Serial.println(results[3]);

delay(1);
if(cont <= 99)
  cont++;
else
  cont = 100;

}
