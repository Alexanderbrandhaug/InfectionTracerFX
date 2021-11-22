module infectiontracer.core {
    requires com.google.gson;
    requires java.desktop;
    requires lombok;
    requires jakarta.mail;
    exports infectiontracer.core;
    exports infectiontracer.json;
    requires jakarta.activation;
    opens infectiontracer.core;
}