package com.aharryhughes;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by ahhughes8 on 8/7/17.
 */
public class Creator {


    public void createWorkOrders() {
        // read input, create work orders and write as json files
            WorkOrder order = new WorkOrder();
            File file = new File("./" + order.id + ".json");

            Scanner scanner = new Scanner(System.in);
        System.out.println("id:");
        order.id = scanner.nextInt();
        System.out.println("descript:");
            order.description = scanner.nextLine();
        System.out.println("name:");
            order.senderName = scanner.nextLine();

            //write to file the order info
            try {
                FileWriter fileWriter = new FileWriter(file);
                ObjectMapper mapper = new ObjectMapper();
                String json = mapper.writeValueAsString(order);
                fileWriter.write(json);
                fileWriter.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    public static void main(String args[]) {
        Creator creator = new Creator();
        while(true) {
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            creator.createWorkOrders();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

