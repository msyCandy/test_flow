package com.msytools.testflow.backend.common.enums;

public enum DealTypeEnum {
    isBug(1),
    doIt(2),
    canRetest(3),
    stillBug(4);

    private int type;

    DealTypeEnum(int type) {
        this.type = type;
    }

    public static boolean checkType(int type) {
        for (DealTypeEnum value : DealTypeEnum.values()) {
            if (value.type == type) {
                return true;
            }
        }
        return false;
    }

}
