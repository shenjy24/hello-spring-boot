package com.jonas.jpa.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shenjy
 * @createTime 2023/5/30 16:37
 * @description
 */
public enum CourseLocaleKey {
    CHINESE("chinese", "course.chinese"),
    MATH("math", "course.math"),
    ;

    private final String course;
    private final String localeKey;

    CourseLocaleKey(java.lang.String course, java.lang.String localeKey) {
        this.course = course;
        this.localeKey = localeKey;
    }

    public String getCourse() {
        return course;
    }

    public String getLocaleKey() {
        return localeKey;
    }

    public static final Map<String, String> map = new HashMap<>(CourseLocaleKey.values().length);

    static {
        for (CourseLocaleKey key : CourseLocaleKey.values()) {
            map.put(key.getCourse(), key.getLocaleKey());
        }
    }

    public static String getLocalKey(String course) {
        return map.getOrDefault(course, "");
    }
}
