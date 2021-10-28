module infectiontracer.rest {
   requires java.annotation;
   requires spring.boot;
   requires spring.boot.autoconfigure;
   requires spring.context;
   requires spring.web;
   requires infectiontracer.core;

   opens infectiontracer.rest;

}