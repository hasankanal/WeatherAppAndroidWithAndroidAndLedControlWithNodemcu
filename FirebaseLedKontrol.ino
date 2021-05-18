
#include<ESP8266WiFi.h>
#include<FirebaseArduino.h>

#define FIREBASE_HOST ""
#define FIREBASE_AUTH ""
#define WIFI_SSID ""
#define WIFI_PASSWORD ""

void setup() {
Serial.begin(9600);
pinMode(D7,OUTPUT);
//pinMode(D6,OUTPUT);
//pinMode(D5,OUTPUT);
WiFi.begin(WIFI_SSID,WIFI_PASSWORD);
Serial.println("CONNECTİNG");
while(WiFi.status() != WL_CONNECTED){
  Serial.print(".");
  delay(500);
}
Serial.println();
Serial.print("Connected:");
Serial.println(WiFi.localIP());

Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH);

}

void loop() {
  String lampStatus = Firebase.getString("isLightning");
  Serial.print("lampStatus"); Serial.println(lampStatus);

  if(lampStatus == "1"){
    digitalWrite(D7,HIGH);
    delay(500);
//    digitalWrite(D6,HIGH);
//    delay(500);
  //  digitalWrite(D5,HIGH);
    String led_status ="LED AÇIK";
    Firebase.setString("Led_Status",led_status);
  }else if(lampStatus == "0"){
    digitalWrite(D7,LOW);
    delay(500);
//    digitalWrite(D6,LOW);
//    delay(500);
 //   digitalWrite(D5,LOW);
    String led_status = "LED KAPALI";
    Firebase.setString("Led_Status",led_status);
  }
  delay(500);

}
