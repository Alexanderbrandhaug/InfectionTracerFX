module infectiontracer.core {
    requires com.google.gson;
    requires java.desktop;
    requires lombok;
    requires java.mail;
    exports infectiontracer.core;
    exports infectiontracer.json;
    opens infectiontracer.core;
}