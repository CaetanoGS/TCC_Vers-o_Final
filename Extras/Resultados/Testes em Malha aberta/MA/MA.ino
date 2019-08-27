#define N 50
const int LM35 = A0; // Define o pino que lera a saída do LM35
float val[N];
int count = 0;
int output = 0;

void setup() {
  // put your setup code here, to run once:

  Serial.begin(9600); // inicializa a comunicação serial
  pinMode(LM35,INPUT);

}

void loop() {
  double temperatura = (double(analogRead(LM35))*5/(1023))/0.01;

for(int i = N-1; i>0; i--){
  val[i] = val[i-1];
}

val[0] = temperatura;

double sum = 0;

for(int i = 0; i < N; i++){
  sum += val[i];
}

sum = (sum/N);

temperatura = sum;

if (millis() >= 10000)
  output = 255;    
else
  output = 0;

analogWrite(6,output);

double results[] = {temperatura, output};

Serial.print(results[0]);
Serial.print("\t");
Serial.println(results[1]);

delay(1);

}
