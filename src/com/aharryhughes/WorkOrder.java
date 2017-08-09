package com.aharryhughes;

import static com.aharryhughes.Status.*;

/**
 * Created by ahhughes8 on 8/7/17.
 */
public class WorkOrder {

    public int id;
    public String description;
    public String senderName;
    public Status status;

    public WorkOrder() {
        this.status = INITIAL;
    }

    @Override
    public String toString() {
        return senderName + ": " + description;
    }


}
