package com.moneyview.calendercollab.model;

import lombok.ToString;
import java.util.HashMap;
import java.util.Map;

@ToString
public enum ResponseStatus {
    pending("pending"),
    accepted("accepted"),
    rejected("rejected");
    private String name;

    ResponseStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Map<String,ResponseStatus> reverseLookup = new HashMap<>();

    static {
        for (ResponseStatus status:
                ResponseStatus.values()) {
            reverseLookup.put(status.getName(), status);
        }
    }
}
