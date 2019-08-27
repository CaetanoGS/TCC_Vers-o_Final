/* Arduino Matlab Serial Connection */
/////////////////////////////////////////////////
const int LM35 = A0;   // Analog input pin that the potentiometer 1 is wired to
const int Led2 = 13;   // Digital pin that the LED 2 is wired to
 
double Pot1_row_value = 0;  // raw value read from the pot1

 
void setup() {
  // initialize digital pins for led as an output.
  pinMode(Led2, OUTPUT);
  // initialize serial communications at 9600 bps:
  Serial.begin(9600);
}
 
void loop() { 
  // read the pot values:
  Pot1_row_value = ((analogRead(LM35))*5/(1023))/0.01;
  
  // Convert the row values (which goes from 0 - 1023) to a voltage (0 - 5V):
  float voltage_Pot1 = Pot1_row_value;
  Serial.println(Pot1_row_value);

  
  // wait 20 milliseconds before the next loop
  // for the analog-to-digital converter to settle
  // after the last reading:
  delay(20);
 
  // read serial data from remote device
  char data = Serial.read();
  //wait available data
  while(Serial.read()&&Serial.available()); 
  
  // Turn the Leds on
  if(data == 'b'){
  digitalWrite(Led2, HIGH);  //green led
  }
  else if(data == 'a'){
  digitalWrite(Led2, LOW);
  } 
}
