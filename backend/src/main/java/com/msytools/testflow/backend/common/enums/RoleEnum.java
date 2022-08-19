package com.msytools.testflow.backend.common.enums;

public enum RoleEnum {
    ADMIN(1, "管理员"),
    DEV(2, "开发"),
    TEST(3, "测试"),
    PRO(4, "产品"),
    VIEWER(5, "观察员");

    private Integer roleId;
    private String roleName;

    RoleEnum(Integer roleId, String roleName) {
        this.roleId = roleId;
        this.roleName = roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    /**
     * 检查roleId合法性
     * @param roleId
     * @return
     */
    public static boolean checkRoleId(int roleId) {
        for (RoleEnum value : RoleEnum.values()) {
            if (value.getRoleId().equals(roleId)) {
                return true;
            }
        }
        return false;
    }
}
