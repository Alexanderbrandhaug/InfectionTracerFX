module infectiontracer.rest {
   
   requires java.annotation;
   requires spring.boot;
   requires spring.boot.autoconfigure;
   requires spring.context;
   requires spring.web;
   requires infectiontracer.core;
    requires com.google.gson;


    exports infectiontracer.rest;
   opens infectiontracer.rest;

}