#define N 100
const int LM35 = A0; // Define o pino que lera a saída do LM35
double temperatura, input, output, sum, setPoint = 0; // Variável que armazenará a temperatura medida
float val[N];

void setup() {
Serial.begin(9600); // inicializa a comunicação serial
pinMode(LM35,INPUT);
pinMode(6, OUTPUT);
}
 
//Função que será executada continuamente
void loop() 
{

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
output = 100;
/*
if (millis() > 30000 && millis() < (350000*2)){
  output = 250;
}*/

if (millis() > 15000)
  output = 255;

analogWrite(6,output);
double results[] = {temperatura, output, setPoint};

Serial.print(results[0]);
Serial.print("\t");
Serial.print(results[1]);  
Serial.print("\t");
Serial.println(results[2]);

}
