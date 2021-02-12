package br.com.standard.domains.standard;

import java.util.HashMap;
import java.util.Map;

public enum StandardType {
    INDUSTRIAL(1, "Normas Industriais"),
    ENVIRONMENTAL(2, "Normas Ambientas"),
    DEFAULT(3, "Outras");

    private static final Map<Integer, StandardType> map = new HashMap<>();

    static {
        for (StandardType type : values()) {
            map.put(type.code, type);
        }
    }

    private final int code;
    private final String description;

    StandardType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static StandardType valueOfCode(int code) {
        return map.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
