/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vukelic.com.data;

import com.pi4j.system.NetworkInfo;
import com.pi4j.system.SystemInfo;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Mitja
 */
public class RpiData {

    static Session session = null;
    static  Date date;
    static long schedule = 1000;
    static SessionFactory sessionFactory = null;
    private static final DecimalFormat df = new DecimalFormat("###.##");
    private static boolean isFirstCommit = true;
    private static Data data = new Data();
   
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
       sessionFactory = new org.hibernate.cfg.Configuration().configure().buildSessionFactory();
   
              try {
            PopulateDB();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(RpiData.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    static void PopulateDB() throws IOException, InterruptedException{
         int respond = 0;
         LocalDateTime localDateTime = LocalDateTime.now();
        String time = "" + localDateTime.getYear() + "-" + localDateTime.getMonthValue() + "-" + localDateTime.getDayOfMonth()
                + "/" + localDateTime.getHour() + ":" + localDateTime.getMinute() + ":" + localDateTime.getSecond();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
        date = Date.from(Instant.now());
        try {
            date = formatter.parse(time);
        } catch (ParseException ex) {
            Logger.getLogger(RpiData.class.getName()).log(Level.SEVERE, null, ex);
        }
         Device raspi = new Device();
        try {
           String serial = SystemInfo.getSerial();
            String ipAddress[] = new String[10];
            int i = 0;
             for (String ipAddres : NetworkInfo.getIPAddresses()){
           ipAddress[i] = ipAddres;
             i++;
            }
            session = sessionFactory.openSession();
            session.beginTransaction();
            String HQL_QUERY = "from Device where serial = '" + serial+"'";
            org.hibernate.Query query = session.createQuery(HQL_QUERY);
        //Prepared statement
            // query.setParameter("name","Meteo");
            try {
               Iterator it = query.iterate();
           
            if (it.hasNext()) {
                 System.out.println("Reading values");
                raspi = (Device) it.next();
                System.out.println(raspi.getSerial());
                System.out.println(raspi.getName());
                System.out.println(raspi.getIp());
                 session.getTransaction().commit();
                 isFirstCommit = false;
            }
            } catch (Exception e) {
                
            }
            session = sessionFactory.openSession();
            session.beginTransaction();
            System.out.println("Adding values");
            raspi.setSerial(serial);
            raspi.setName(NetworkInfo.getHostname());
            raspi.setIp(ipAddress[0]);
            session.saveOrUpdate(raspi);
            
            session.getTransaction().commit();
            
           session = sessionFactory.openSession();
            session.beginTransaction();
            HQL_QUERY = "from Data where serial = '" + serial+"'";
            query = session.createQuery(HQL_QUERY);
             try {
               Iterator it = query.iterate();
           
            if (it.hasNext()) {
                 System.out.println("Reading DATA values");
                data = (Data) it.next();
                System.out.println(data.getSerial());
              
                isFirstCommit = false;
            }
           
            } catch (Exception e) {
                
            }finally{
                
            }
           
         
            System.out.println("Populating the database !");
       
            data.setDevice(raspi);
            data.setTemp(Double.valueOf(df.format(SystemInfo.getCpuTemperature())));
            data.setCoreVolt(Double.valueOf(df.format(SystemInfo.getCpuVoltage())));
            data.setMemTot(SystemInfo.getMemoryTotal()/1048576);
            data.setMemFree(SystemInfo.getMemoryFree()/1048576);
            data.setMemUsd(SystemInfo.getMemoryUsed()/1048576);
            data.setSendTime(date);
            session.saveOrUpdate(data); 
            session.getTransaction().commit();
            
            System.out.println("Done!");
        } catch (HibernateException | IOException | InterruptedException | NumberFormatException e) {
            System.out.println(e);
        } finally {
            System.exit(0);
           // session.flush();
           // session.close();
           
        }
    }
}
