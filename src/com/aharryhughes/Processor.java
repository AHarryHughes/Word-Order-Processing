package com.aharryhughes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ahhughes8 on 8/7/17.
 */
public class Processor {

    private HashMap<Status, Set<WorkOrder>> orderMap;

    public Processor() {

        orderMap = new HashMap<>();

        orderMap.put( Status.INITIAL, new HashSet<>());
        orderMap.put( Status.ASSIGNED, new HashSet<>());
        orderMap.put( Status.IN_PROGRESS, new HashSet<>());
        orderMap.put( Status.DONE, new HashSet<>());

    }

    public void processWorkOrders() {
        while(true) {
            readIt();
            moveIt();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void moveIt() {
        // move work orders in map from one state to another

        orderMap.get(Status.DONE).addAll(orderMap.get(Status.IN_PROGRESS));
        orderMap.get(Status.IN_PROGRESS).clear();
        orderMap.get(Status.IN_PROGRESS).addAll(orderMap.get(Status.ASSIGNED));
        orderMap.get(Status.ASSIGNED).clear();
        orderMap.get(Status.ASSIGNED).addAll(orderMap.get(Status.INITIAL));
        orderMap.get(Status.INITIAL).clear();

        this.print(orderMap);

    }

    private void readIt() {
        // read the json files into WorkOrders and put in map
        //find all the json files and add to an arraylist


        try {
            File thisFile = new File(".");
            for (File f : thisFile.listFiles()) {
                if (f.getName().endsWith(".json")) {
                    // Now you have a File object named "f". You can use this in the FileReader constructor
                    String json = null;
                    json = new String(Files.readAllBytes(Paths.get(f.getPath())));
                    ObjectMapper mapper = new ObjectMapper();
                    WorkOrder tempOrder = mapper.readValue(json, WorkOrder.class);
                    orderMap.get(tempOrder.status).add(tempOrder);
                    f.delete();
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        this.print(orderMap);

    }

    private void print(HashMap<Status, Set<WorkOrder>> orderMap){
        Object[] orders;

        System.out.println("===================================================================================================================================================");

        System.out.println("Initial::::");
        orders = orderMap.get(Status.INITIAL).toArray();
        for(int i = 0; i < orders.length; i++){
            System.out.println(orders[i].toString());
        }
        System.out.println();

        System.out.println("Assigned::::");
        orders = orderMap.get(Status.ASSIGNED).toArray();
        for(int i = 0; i < orders.length; i++){
            System.out.println(orders[i].toString());
        }
        System.out.println();

        System.out.println("In Progress::::");
        orders = orderMap.get(Status.IN_PROGRESS).toArray();
        for(int i = 0; i < orders.length; i++){
            System.out.println(orders[i].toString());
        }
        System.out.println();

        System.out.println("Done::::");
        orders = orderMap.get(Status.DONE).toArray();
        for(int i = 0; i < orders.length; i++){
            System.out.println(orders[i].toString());
        }
        System.out.println();
    }


    public static void main(String args[]) {
        Processor processor = new Processor();
        processor.processWorkOrders();
    }
}

