# Route-Digital-Analog-Data-Using-MQTT-Library
A full stack project to simulate transfer of data from web client to IOT devices using MQTT client library

## About Project
stack = Angular 9 (Front-end) + Spring Boot (Back-end) + NodeJs(Simulator)

Task was to create a angular frontend which will make HTTP request to the spring boot rest 
services to perform write operations of digital and analog data. Further the task was extended 
to create a simulator that simulates the changes in values using nodeJs. Next task was to use 
MQTT client library and create a publisher subscriber architecture, where server will be the 
publisher and nodeJS simulator and nodeMCU will be the subscriber. When the server publishes 
value, the request goes to the hive MQTT broker which forwards the data to all the subscribers 
who are subscribed to the particular topic. In this project I have used two LED's which will blink only when I select switch 0 from dropdown of web frontend.

## Intructions to run the project
### 1) Frontend
Do the required configuration for angular.Move to the angular frontend directory.Run npm install to get the node_modules. Run ng serve for a dev server. Navigate to http://localhost:4200/.
### 2) Spring Boot Server
Do the required configuration for Spring Boot Java. Import the siemensTask named folder into your preferred ide. Start the server by running the main file.
### 3) NodeJs server
Install and do the required configuration for nodeJs.Move to the simulator directory.Run npm install to get the node_modules. On terminal run the command node server.js
### 4) Arduino Program
Download and install arduino ide. Make the hardware connections. Connect D3 and D5 pins of nodeMCU to two leds. Connect laptop/Computer to nodeMCU using USB cable. Open the ledBlink.ino file in arduino ide. Run the code by uploading the code. 

## Major Components of Project
1) Angular Frontend
2) Spring Boot Server
3) NodeJs Server(works as simulator)
4) Arduino Program + Hardware(nodeMCU + 2 LED's + BreadBoard)
5) Hive MQTT broker

## About Components of Project
### 1) Angular Frontend -
The frontend includes a switch and a slider. The switch has two states ON/OFF. The slider has 
value from 1 to 100. Once we submit the digital/analog value a HTTP put request goes from 
browser to the spring boot server. 
### 2) Spring Boot Server -
Spring boot server includes 4 rest api’s, which includes 2 put requests and 2 get requests for 
analog and digital values. Eclipse Paho MQTT library is used to make server as a publisher. 
Once the request comes at analog/# or digital/# the server saves the data and publishes it to 
the broker. Hive MQTT broker forwards the data to subscribers.
### 3) NodeJs Simulator -
First task was to create a javascript server which makes HTTP GET request to server 
simultaneously after a certain time interval to get the changes in the data. If a change occurs 
in the data, it gets reflected in console. Further task was to convert this simultaneous calls to 
MQTT architecture using Eclipse Paho Javascript MQTT client library. The nodeJS simulator 
subscribes and waits for the publisher.
### 4) NodeMCU -
NodeMCU is a open source IOT platform developed by EspressIf Systems. The task was to 
simulate the changes in values of switch and slider using two LED’s. The 1st LED should become 
ON/OFF based on state of the switch. The 2nd LED’s intensity should change based on the value 
of slider. Used ESP8266WiFi library to connect nodeMCU to internet. NodeMCU acts as a 
subscriber, who subscribes to the topics analog/# and digital/#. PubSubClient library is used to
create the MQTT client.
### 5) MQTT broker -
HiveMQ provides a free MQTT broker. The broker is reponsible for fast,efficient and reliable movement of data to and from IOT devices. 
It transfers the data obtained from publisher to all the subscribers.




