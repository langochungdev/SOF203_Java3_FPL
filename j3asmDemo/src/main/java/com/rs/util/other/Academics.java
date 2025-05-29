/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rs.util.other;

/**
 * Inspired by JavaScript switch case
 *
 * @author ndhlt
 */
public class Academics {

    /**
     * Execute all arguments if the arguments condition meet the given condition
     * 
     * @param condition Determine the condition to execute an argument
     * @param defaultCall execute at the end (can be null if not needed)
     * @param args arguments that are waiting to execute
     */
    public static void switchCaseAll(boolean condition, Runnable defaultCall, Arguments... args) {
        for (Arguments arg : args) {
            if (condition == arg.condition) {
                arg.execute();
            }
        }
        if(defaultCall!=null){
            defaultCall.run();
        }
    }
    
    /**
     * Execute the first argument if the argument's condition meet the given condition
     * @param condition Determine the condition to execute an argument
     * @param defaultCall execute if no arguments meet
     * @param args arguments that are waiting to execute
     */
    public static void switchCaseFirst(boolean condition, Runnable defaultCall, Arguments... args) {
        for (Arguments arg : args) {
            if (condition == arg.condition) {
                arg.execute();
                return;
            }
        }
        if(defaultCall!=null){
            defaultCall.run();
        }
    }

}