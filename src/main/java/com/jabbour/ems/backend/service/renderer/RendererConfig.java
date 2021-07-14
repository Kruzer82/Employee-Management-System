package com.jabbour.ems.backend.service.renderer;

import org.springframework.security.core.userdetails.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class RendererConfig {

    static HashMap<Class, Set<ClassConfig>> classMap = new HashMap<>();

    public RendererConfig() {
    }

    public static HashMap<Class, Set<ClassConfig>>  getUserClassMap() {
        classMap.computeIfAbsent(User.class, x -> new HashSet<>()).add(new ClassConfig(User.class,"username", "Login", false, true, 1L));
        classMap.computeIfAbsent(User.class, x -> new HashSet<>()).add(new ClassConfig(User.class,"password", "Password", false, true, 2L));
        return classMap;
    }

    public static Set<ClassConfig>  getUserClass() {
        HashSet set = new HashSet();
        set.add(new ClassConfig(User.class,"username", "Login", false, true, 1L));
        set.add(new ClassConfig(User.class,"password", "Password", false, true, 2L));
        return set;
    }
}

