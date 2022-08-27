package com.raven.munin.enumeration;

import org.junit.Test;

public enum MemberAuthority {
    SYSTEM_ADMIN, BOARD_MANAGER, NORMAL_MEMBER, BANNED_MEMBER;



    MemberAuthority() {
    }

    public static MemberAuthority getEnum(String value) {
        if (value == null || value.length() < 1) {
            return null;
        }
        for (MemberAuthority auth : values()) {
            if (auth.name().equalsIgnoreCase(value)) {
                return auth;
            }
        }
        return null;
    }

}
