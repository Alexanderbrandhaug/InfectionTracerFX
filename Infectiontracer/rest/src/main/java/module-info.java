module infectiontracer.rest {
   
   requires java.annotation;
   requires spring.boot;
   requires spring.boot.autoconfigure;
   requires spring.context;
   requires spring.web;
   requires spring.beans;
   requires infectiontracer.core;
    requires com.google.gson;


    exports infectiontracer.rest;
    opens infectiontracer.rest to spring.beans, spring.context, spring.web;

}