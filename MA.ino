#define N 100                                         // Tamanho da janela do filtro

const int LM35 = A0;                                  // Entrada de Sinal do sensor de temperatura
float val[N];                                         // Vetor para salvar dados do filtro
double sum = 0;                                       // Somatório da média do filtro
unsigned long lastTime = 0, now, timeChange;          // Variáveis para controlar o tempo de amostragem = 25ms


void setup() {
  
  Serial.begin(9600);
  pinMode(LM35,INPUT);
}

void loop() {
  now = millis();
  timeChange = (now - lastTime);

  if (timeChange >= 25){ 

    // Iniciando Atuadores em 50%

    double outputC = 128;
    double outputL = 128;

    // Medição da temperatura
  
    double temperatura = (double(analogRead(LM35))*5/(1023))/0.01;

    // Filtrando as medições com janela móvel

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

    // Modificando os degraus nos atuadores

    if (millis() < 120000){ 
      outputC = 128;
      outputL = 128;
    }else{
      outputC = 255;
      outputL = 128;  
    }

    // Escrevendo no PWM o sinal desejado nos atuadores
  
    analogWrite(5,outputL);
    analogWrite(6,outputC);

    // Imprimindo na serial para o Matlab

    double results[] = {temperatura, outputC, outputL};

    Serial.print(results[0]);
    Serial.print("\t");
    Serial.print(results[1]);  
    Serial.print("\t");
    Serial.println(results[2]);

    lastTime = now;

  } 
}
