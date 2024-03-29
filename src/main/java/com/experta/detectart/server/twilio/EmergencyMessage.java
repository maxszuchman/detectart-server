package com.experta.detectart.server.twilio;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

import com.experta.detectart.server.model.Contact;
import com.experta.detectart.server.model.Device;
import com.experta.detectart.server.model.User;
import com.experta.detectart.server.model.deviceData.Sensor;

public class EmergencyMessage {

    public static final String FROM_TELEPHONE_NUMBER = System.getenv("FROM_TELEPHONE_NUMBER");

    private static final String GOOGLE_MAPS_API = System.getenv("GOOGLE_MAPS_API");

    private List<Sensor> sensors;
    private Collection<Contact> contacts;
    private Device device;
    private User user;
    private Instant sentTime;

    public EmergencyMessage(final List<Sensor> sensors, final Collection<Contact> contacts, final User user
                            , final Device device) {
        super();
        this.sensors = sensors;
        this.contacts = contacts;
        this.user = user;
        this.device = device;
        sentTime = Instant.now();
    }

    public String getMessage() {
        String message = "Alerta de ";;

        for (Sensor sensor : sensors) {
            message += sensor.getType() + ", ";
        }

        message = message.substring(0, message.length() - 2);
        message += " en el dispositivo " + device.getAlias();
        message += " del usuario " + user.getFullName();
        message += " ubicado en " + GOOGLE_MAPS_API;
        message += device.getLatitude() + "," + device.getLongitude();

        return message;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public Collection<Contact> getContacts() {
        return contacts;
    }

    public User getUser() {
        return user;
    }

    public Device getDevice() {
        return device;
    }

    public Instant getSentTime() {
        return sentTime;
    }
}
