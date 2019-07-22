/*
  Autor: Gustavo Caetano de Souza
  Universidade Federal de Mato Grosso
  Trabalho de Conclusão de Curso
  
  Obs: Para trocar de estratégia de controle, isto é, de PID 2DOF para PID clássico basta setar b e c como 1 
 
 */


class mPID{
public:

  // Definição das variáveis utilizadas no processo
  
  double error, errorP, sample, lastSample, kP, kI, kD, P, I, D, b,c, pid, setPoint, aux = 0;
  long lastProcess;

  // Método Construtor da classe
  
  mPID(double _kP, double _kI, double _kD, double _b, double _c){
    kP = _kP;
    kI = _kI;
    kD = _kD;
    b = _b;
    c = _c;
  }

  // Adiciona uma nova amostra para os cálculos
  
  void addNewSample(double _sample){
    sample = _sample;
  }


  // Define um Setpoint para o sistema
  
  void setSetPoint(double _setPoint){
    setPoint = _setPoint;
  }


  // Efetua o cálculo do PID 2DOF com um Ts de 25 ms
  
  double process(){
            
      errorP = (b*setPoint) - sample;                           // Erro para ação Proporcional
      error = setPoint - sample;                                // Erro para ação Integral
    
      float dT = (millis() - lastProcess) / 1000.0;             // Definindo o intervalo de tempo entre as amostras para o cálculo da integral e da derivada
      lastProcess = millis();
    
      //Ação Proporcional
    
      P = errorP * kP;

      // Ação Derivativa
    
      D = (c*lastSample - sample) * kD / dT;
      lastSample = sample;
  
      // Ação Integral

      I += ((error * kI) * dT);

      // Windup
    
      if(error < 0.2 && error > -0.2){
        if((255 - aux)>255)
          I = I/1.2;
        else if((255-aux)<0)
          I = I/1.2;
      }else
        I=I;
    
    // PID
    
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


#define N 100                                                               // Tamanho da janela do filtro janela móvel
const int LM35 = A0;                                                        // Port onde é coletado os dados do sensor de temperatura
double temperatura, input, output, output1, sum, setPoint = 31;             
float val[N];
float Ts = 1000;                                                                   

mPID MyPID(-38, -3, -0.1, 0.8, 0.8);                                            // Chamada do construtor para o cooler
//mPID MyPID1(38, 20 -3, 0.1, 1, 1);                                             // Chamada do construtor para a lâmpada

void setup() {
  
  Serial.begin(9600);
  pinMode(LM35,INPUT);
  MyPID.setSetPoint(setPoint);                                              // Definição de setpoint para o cooler
  //MyPID1.setSetPoint(setPoint);                                             // Definição de setpoint para a lâmpada

}


void loop() {

  unsigned long lastTime;
  unsigned long now = millis();
  unsigned long timeChange = (now - lastTime);

  Serial.println(timeChange);

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

  // Alterando os setpoints ao decorrer da simulação

  if (millis() > 250000 && millis() < (250000*2)){
    setPoint = 31;
    MyPID.setSetPoint(setPoint);
    //MyPID1.setSetPoint(setPoint);
  
  }else if(millis() >= (250000*2) && millis() < (250000*3)){
    setPoint = 29.8;
    MyPID.setSetPoint(setPoint);
    //MyPID1.setSetPoint(setPoint);
    
  }else if(millis() >= (250000*4)){
    setPoint = 30.5;
    MyPID.setSetPoint(setPoint);
    //MyPID1.setSetPoint(setPoint);
  }

  // Adicionando a amostra de temperatura filtrada para calculos


    MyPID.addNewSample(temperatura);
    //MyPID1.addNewSample(temperatura);
  
    // Realizando cálculos
    output = MyPID.process();
    //output1 = MyPID1.process();
  
    // Aplicação de perturbação
  
  
    
    if((millis() >= (250000*0.6)) && (millis() <= (250000*0.67)))
      output1 = 128;
    else if(millis() >= (250000*1.6) && (millis() <= (250000*1.65)))
      output1 = 160;
    else if (millis() >= (250000*4.4) && (millis() <= (250000*4.8)))
      output1 = 100;
    else
      output1 = 255;
  
    // Escrevendo cálculos para controlar os atuadores
    
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

    
  }

  lastTime = now;
}
