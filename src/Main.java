import warstwaDanych.OperateOnFiles;
import warstwaLogiki.pl.exceptions.OutOfFrequencyException;
import warstwaLogiki.pl.exceptions.TooFastException;
import warstwaLogiki.pl.lights.*;
import warstwaLogiki.pl.pedals.*;
import warstwaInterfejsu.Radio;
import warstwaDanych.OperateOnDataBase;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws OutOfFrequencyException, SQLException {
//        LightingSystem system = new LightingSystem();
//        Indicator turningLeft = new Indicator(Side.left);
//        Indicator turningRight = new Indicator(Side.right);
//        PassingLights passingLights = new PassingLights();
//        Headlights headlights = new Headlights();
//        system.addToList(turningLeft);
//        system.addToList(turningRight);
//        system.addToList(passingLights);
//        system.addToList(headlights);
//
//        Accelerator acc = new Accelerator();
//        Brake brk  = new Brake();
//        Clutch clt = new Clutch();
//
//        Radio radio = new Radio();
//        try{radio.adjustFrequency(250);}
//        catch(OutOfFrequencyException exc) {System.err.println(exc);}
//
//        try{acc.pressPedal(100);}
//        catch(TooFastException exc) {System.err.println(exc);}
////        acc.pressPedal(20);
////        acc.pressPedal(50);
////        acc.releasePedal(40);
////
//        brk.pressPedal(20);
//
//        clt.pressPedal(null);
//        clt.releasePedal(null);
//
//        turningLeft.turnOn();
//        turningRight.turnOn();
//        passingLights.turnOn();
//        headlights.turnOn();
//        headlights.turnOff();
//        turningLeft.turnOff();
//        turningRight.turnOff();
//        System.out.println(system.info());

        OperateOnDataBase operate = new OperateOnDataBase();
        String out = operate.selectAll();
        System.out.println(out);
        System.out.println();
        //operate.insert("Zupa Romana", "Roman", "Winiary", "0:3:54");
        //operate.delete(6);
        operate.updateTitle(1, "Make me Fade");
        operate.updateAlbum(1, "Tuesdays");
        operate.updatePerformer(1, "Klej");
//        String one = operate.selectOne(1);
//        System.out.println(one);
        //operate.insert("Secrets", "One Republic", "Waking Up", "0:3:45", 11);
        //operate.delete(3);
        //operate.updateIndexes();
        operate.insert("Astronomia", "Vicetone & Tony Igy", "Astronomia", "0:3:18", 2);
        String out2 = operate.selectAll();
        System.out.println(out2);
    }

}
