const { default: axios } = require("axios");
const mqtt = require('mqtt')
const express = require("express");

const app = express();

// var digital = new Array(10).fill(false);
// var analog = new Array(10).fill(0);

// setInterval(()=>{
//     for(let id=0;id<10;id++){
//         axios.get("http://localhost:9090/digital/"+id).then((res)=>{
//             if(digital[id]!=res.data){
//                 digital[id]=res.data;
//                 if(res.data===true){
//                     console.log("Switch "+id+" is turned ON");
//                 }
//                 else{
//                     console.log("Switch "+id+" is turned OFF");
//                 }
//             }
//         })
//         //console.log(id);
//     }
    
//     for(let id=0;id<10;id++){
//         axios.get("http://localhost:9090/analog/"+id).then((res)=>{
//             if(analog[id]!=res.data){
//                 console.log("Device "+id+" value has changed from "+analog[id]+" to "+res.data);
//                 analog[id]=res.data;
//             }
//         })
//         //console.log(id);
//     }

// },1000);

const connectUrl = "tcp://broker.hivemq.com:1883"
const digitalClientId="subscriber_digital_client1"
const analogClientId="subscriber_analog_client1"

const digitalClient = mqtt.connect(connectUrl, {
  digitalClientId,
  clean: true,
  connectTimeout: 4000,
  username: 'pratham',
  password: 'pratham',
  reconnectPeriod: 1000,
})

const digitalTopic = 'digital/#'

digitalClient.on('connect', () => {
  console.log('Successfully connected digital client to Broker')
  digitalClient.subscribe([digitalTopic], () => {
    console.log(`Successfully Subscribed to topic '${digitalTopic}'`)
  })
})

digitalClient.on('message', (topic, payload) => {
    console.log(topic+" "+payload.toString())
})

const analogClient = mqtt.connect(connectUrl, {
    analogClientId,
    clean: true,
    connectTimeout: 4000,
    username: 'pratham',
    password: 'pratham',
    reconnectPeriod: 1000,
})

const analogTopic = 'analog/#'

analogClient.on('connect', () => {
  console.log('Successfully connected analog client to Broker')
  analogClient.subscribe([analogTopic], () => {
    console.log(`Successfully Subscribed to topic '${analogTopic}'`)
  })
})

analogClient.on('message', (topic, payload) => {
    console.log(topic+" "+payload.toString())
})

// port
const port = process.env.PORT || 8000;

app.listen(port, () => console.log(`Server is running on port ${port}`));