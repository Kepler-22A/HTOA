package com.kepler.vo;

import javax.persistence.*;

@Table(name = "module")
@Entity
public class ModuleVo {//系统权限管理
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int moduleId;
    private String moduleName;//权限名称
    private int root;//根节点
    private String controller;//访问控制器

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    @Override
    public String toString() {
        return "ModuleVo{" +
                "moduleId=" + moduleId +
                ", moduleName='" + moduleName + '\'' +
                ", root=" + root +
                ", controller='" + controller + '\'' +
                '}';
    }
}
