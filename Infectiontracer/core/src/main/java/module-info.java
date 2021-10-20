module infectiontracer.core {
    requires com.google.gson;
    exports infectiontracer.core;
    exports infectiontracer.json;

    opens infectiontracer.core;
}