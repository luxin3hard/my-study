package com.study.luxin.deginpattern.factory;

public class MappingSelector {




    interface Car{
        String carIdentify();
        void run();
    }


    class BWM implements Car{

        @Override
        public String carIdentify() {
            return null;
        }

        @Override

        public void run() {

        }
    }

    class Benz implements Car{

        @Override
        public String carIdentify() {
            return null;
        }

        @Override
        public void run() {

        }
    }







}
