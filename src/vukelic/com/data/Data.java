package vukelic.com.data;
// Generated Oct 14, 2015 1:46:28 PM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Data generated by hbm2java
 */
public class Data  implements java.io.Serializable {


     private String serial;
     private Device device;
     private Date sendTime;
     private double temp;
     private double coreVolt;
     private double memTot;
     private double memUsd;
     private double memFree;

    public Data() {
    }

    public Data(Device device, Date sendTime, double temp, double coreVolt, double memTot, double memUsd, double memFree) {
       this.device = device;
       this.sendTime = sendTime;
       this.temp = temp;
       this.coreVolt = coreVolt;
       this.memTot = memTot;
       this.memUsd = memUsd;
       this.memFree = memFree;
    }
   
    public String getSerial() {
        return this.serial;
    }
    
    public void setSerial(String serial) {
        this.serial = serial;
    }
    public Device getDevice() {
        return this.device;
    }
    
    public void setDevice(Device device) {
        this.device = device;
    }
    public Date getSendTime() {
        return this.sendTime;
    }
    
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    public double getTemp() {
        return this.temp;
    }
    
    public void setTemp(double temp) {
        this.temp = temp;
    }
    public double getCoreVolt() {
        return this.coreVolt;
    }
    
    public void setCoreVolt(double coreVolt) {
        this.coreVolt = coreVolt;
    }
    public double getMemTot() {
        return this.memTot;
    }
    
    public void setMemTot(double memTot) {
        this.memTot = memTot;
    }
    public double getMemUsd() {
        return this.memUsd;
    }
    
    public void setMemUsd(double memUsd) {
        this.memUsd = memUsd;
    }
    public double getMemFree() {
        return this.memFree;
    }
    
    public void setMemFree(double memFree) {
        this.memFree = memFree;
    }




}

