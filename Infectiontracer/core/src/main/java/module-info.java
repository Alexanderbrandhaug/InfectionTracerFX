module infectiontracer.core {
    requires com.google.gson;
    requires java.desktop;
    requires lombok;
    exports infectiontracer.core;
    exports infectiontracer.json;

    opens infectiontracer.core;
}