package com.jabbour.ems.backend.service.renderer.annotations;

public @interface Renderer {
    String fieldName();
    String label();
    boolean hidden();
    boolean editable();
    long position();
    Class clazz();
}
