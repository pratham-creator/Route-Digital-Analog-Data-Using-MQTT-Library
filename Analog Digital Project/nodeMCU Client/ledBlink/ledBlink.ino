#include<ESP8266WiFi.h>
#include<PubSubClient.h>

#define DigitalLED1 D5
#define AnalogLED1 D3

const char* ssid="realme X";
const char* password="1234567890";
const char* broker="broker.hivemq.com";
const char* clientId="nodemcu_subscriber";

WiFiClient wifi;
PubSubClient mqttClient(wifi);

void setupWifi(){
  delay(10);
  WiFi.begin(ssid,password);

  while(WiFi.status()!=WL_CONNECTED){
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.print("Connected to Wifi");
}

void subscribeReceive(char* topic, byte* payload, unsigned int length)
{
  // Print the topic
  Serial.print("Topic: ");
  Serial.print(topic);
  Serial.print(" ");
  for(int i = 0; i < length; i++){
    Serial.print(char(payload[i]));
  }
  Serial.println("");

  if(topic[0]=='d'){
      if(topic[8]=='0'){
        if(char(payload[1])=='N'){
        digitalWrite(DigitalLED1, HIGH);
      }
      else{
        digitalWrite(DigitalLED1, LOW);
      }
    }
  }
  else{
    if(topic[7]=='0'){
      int intensity=0;
      for(int i = 0; i < length; i++){
        if(payload[i]=='.'){
          break;
        }
       
        intensity=(intensity*10)+(char(payload[i])-'0');
      }
      analogWrite(AnalogLED1,intensity*2);
    }
    //digitalWrite(AnalogLED1, HIGH);
    //Serial.println(intensity);
  }
  
}

void setup() {

  Serial.begin(9600);
  pinMode(DigitalLED1, OUTPUT);
  pinMode(AnalogLED1, OUTPUT);
  
  setupWifi();
  mqttClient.setServer(broker,1883);  
 
  if (mqttClient.connect(clientId)) 
  {
    Serial.println("Connection has been established");
    mqttClient.subscribe("digital/#");
    mqttClient.subscribe("analog/#");
    mqttClient.setCallback(subscribeReceive);
  } 
  else 
  {
    Serial.println("Looks like the server connection failed...");
  }
}

// the loop function runs forever

void loop() {
  mqttClient.loop();

  delay(1000);               // wait for a second

}
