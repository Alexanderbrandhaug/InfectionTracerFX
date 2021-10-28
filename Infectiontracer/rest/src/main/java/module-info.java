module infectiontracer.rest {
   requires infectiontracer.core;
   requires java.annotation;
   requires spring.beans;
   requires spring.context;
   requires spring.web;
   requires spring.boot;
   requires spring.boot.autoconfigure;
  
   exports infectiontracer.restserver;


}