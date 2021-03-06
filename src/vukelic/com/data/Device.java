package vukelic.com.data;
// Generated Oct 14, 2015 1:46:28 PM by Hibernate Tools 4.3.1



/**
 * Device generated by hbm2java
 */
public class Device  implements java.io.Serializable {


     private String serial;
     private String name;
     private String ip;
     private Data data;

    public Device() {
    }

	
    public Device(String serial) {
        this.serial = serial;
    }
    public Device(String serial, String name, String ip, Data data) {
       this.serial = serial;
       this.name = name;
       this.ip = ip;
       this.data = data;
    }
   
    public String getSerial() {
        return this.serial;
    }
    
    public void setSerial(String serial) {
        this.serial = serial;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getIp() {
        return this.ip;
    }
    
    public void setIp(String ip) {
        this.ip = ip;
    }
    public Data getData() {
        return this.data;
    }
    
    public void setData(Data data) {
        this.data = data;
    }




}


