package com.task.siemens.service;

import com.task.siemens.model.AnalogData;
import com.task.siemens.model.DigitalData;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AnalogDigitalService {
    float[] analog=new float[10];
    boolean[] digital=new boolean[10];
    MqttClient client;
    public AnalogDigitalService() throws MqttException {
        String broker="tcp://broker.hivemq.com:1883";
        String clientId="client1";
        MemoryPersistence persistence = new MemoryPersistence();
        client = new MqttClient(broker, clientId, persistence);

        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setUserName("pratham");
        connOpts.setPassword("pratham".toCharArray());
        // retain session
        connOpts.setCleanSession(true);

        // establish a connection
        System.out.println("Connecting to broker: " + broker);
        client.connect(connOpts);

        System.out.println("Connected");
    }

    public ResponseEntity<?> updateAnalog(int id,AnalogData d) {
        try
        {
            if(id<0 || id>9)
                return new ResponseEntity<>("id should be between 0 to 9", HttpStatus.BAD_REQUEST);

            if(analog[id]!=d.getValue()){
                MqttMessage message = new MqttMessage((d.getValue()+"").getBytes());
                message.setQos(1);
                client.publish("analog/"+id, message);
            }

            analog[id]=d.getValue();

            return new ResponseEntity<>("SuccessFully Updated",HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public float getAnalog(int id){
        return analog[id];
    }

    public ResponseEntity<String> updateDigital(int id,DigitalData d) {
        try
        {
            if(id<0 || id>9)
                return new ResponseEntity<>("id should be between 0 to 9",HttpStatus.BAD_REQUEST);

            if(digital[id]!=d.getValue()){
                if(d.getValue()==true){
                    MqttMessage message = new MqttMessage(("ON").getBytes());
                    message.setQos(1);
                    client.publish("digital/"+id, message);
                }
                else{
                    MqttMessage message = new MqttMessage(("OFF").getBytes());
                    message.setQos(1);
                    client.publish("digital/"+id, message);
                }
            }

            digital[id]=d.getValue();

            return new ResponseEntity<>("Successfully Updated",HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public boolean getDigital(int id){
        return digital[id];
    }

}
