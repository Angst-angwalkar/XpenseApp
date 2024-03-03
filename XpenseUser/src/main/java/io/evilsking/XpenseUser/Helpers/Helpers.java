package io.evilsking.XpenseUser.Helpers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Helpers {

    public Date getCurrentDate(){
        Date in = new Date();
        LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
        return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
    }
}
