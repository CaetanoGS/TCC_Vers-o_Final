class mPID{
public:
  
  double error, errorP, sample, lastSample, kP, kI, kD, P, I, D, b,c, pid, setPoint, aW, aux = 0;
  long lastProcess;
  
  mPID(double _kP, double _kI, double _kD, double _b, double _c){
    kP = _kP;
    kI = _kI;
    kD = _kD;
    b = _b;
    c = _c;
  }
  
  void addNewSample(double _sample){
    sample = _sample;
  }
  
  void setSetPoint(double _setPoint){
    setPoint = _setPoint;
  }
  
  double process(){

    // Erro - Se desejar 1 grau de liberdade, b = c = 1
    
    errorP = (b*setPoint) - sample;
    error = setPoint - sample;

    // Diferença de tempo entre as amostras
    
    float dT = (millis() - lastProcess) / 1000.0;
    lastProcess = millis();
    
    //Ação Proporcional
    
    P = errorP * kP;

    // Ação Derivativa
    
    D = (c*lastSample - sample) * kD / dT;
    lastSample = sample;
  
    // Ação Integral - Toda vez que o erro zera, a ação integral
    // também zera para não gerar um acumulo excessivo da variável 

    /*
    if ((pid - aux) > 255 || (pid-aux) < 0){
       I = I;
      //I = I + aW;
    }else
      I += ((error * kI) * dT);

    */

    I += ((error * kI) * dT);
    
    if(error < 0.2 && error > -0.2){
      if((255 - aux)>255)
        I = 0;
      else if((255-aux)<0)
      I = 255;
  }else
      I=I;
   

    
    // Soma tudo
    pid = P + I + D;

    aux = pid;

    // Controle do intervalo de atuação do PID 
    
    if (pid >= 255)
      pid = 255;
    else if (pid <= 0)
      pid = 0;
    else
      pid = pid;
     
    

    
    return pid;
  }
};


#define N 100
const int LM35 = A0; // Define o pino que lera a saída do LM35
double temperatura, input, output, output1, sum, setPoint = 30; // Variável que armazenará a temperatura medida
float val[N];

mPID MyPID(-38, -3, -0.1, 1, 1);
mPID MyPID1(38, 20, 0.1, 1, 1);

void setup() {
  
  Serial.begin(9600); // inicializa a comunicação serial
  pinMode(LM35,INPUT);
  MyPID.setSetPoint(setPoint);
  MyPID1.setSetPoint(setPoint);

}

//Função que será executada continuamente
void loop() {

temperatura = (double(analogRead(LM35))*5/(1023))/0.01;

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


  if (millis() > 350000 && millis() < (350000*2)){
    setPoint = 31;
    MyPID.setSetPoint(setPoint);
    MyPID1.setSetPoint(setPoint);
  
  }else if(millis() >= (350000*2) && millis() < (350000*3)){
    setPoint = 29.5;
    MyPID.setSetPoint(setPoint);
    MyPID1.setSetPoint(setPoint);
  
  }else if(millis() >= (350000*3) && millis() < (350000*4)){
    setPoint = 31.5;
    MyPID.setSetPoint(setPoint);
    MyPID1.setSetPoint(setPoint);
    
  }else if(millis() >= (350000*4)){
    setPoint = 30.5;
    MyPID.setSetPoint(setPoint);
    MyPID1.setSetPoint(setPoint);
  }




MyPID.addNewSample(temperatura);
MyPID1.addNewSample(temperatura);
output = MyPID.process();
output1 = MyPID1.process();
analogWrite(6,output);
analogWrite(5,output1);

double results[] = {temperatura, output, output1, setPoint};

Serial.print(results[0]);
Serial.print("\t");
Serial.print(results[1]);  
Serial.print("\t");
Serial.print(results[2]);  
Serial.print("\t");
Serial.println(results[3]);

delay(1);
}
