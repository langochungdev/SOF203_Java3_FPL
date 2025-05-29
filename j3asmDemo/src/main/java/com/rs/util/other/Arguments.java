/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rs.util.other;

/**
 * Used for Academics switch case function
 *
 * @author ndhlt
 */
public class Arguments {
        
        public boolean condition;
        public Runnable callback;

        public Arguments(boolean condition, Runnable callback) {
            this.condition = condition;
            this.callback = callback;
        }

        public Arguments() {
        }
        
        public void execute(){
            callback.run();
        }
}
