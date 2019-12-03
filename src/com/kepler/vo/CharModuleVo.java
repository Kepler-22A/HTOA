package com.kepler.vo;

import javax.persistence.*;

@Table(name = "charModule")
@Entity
public class CharModuleVo {//角色权限管理
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int charModuleId;//
    private int moduleId;//管理系统权限,关联系统权限表的id
    private int characterId;//关联角色表

    public int getCharModuleId() {
        return charModuleId;
    }

    public void setCharModuleId(int charModuleId) {
        this.charModuleId = charModuleId;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    @Override
    public String toString() {
        return "CharModuleVo{" +
                "charModuleId=" + charModuleId +
                ", moduleId=" + moduleId +
                ", characterId=" + characterId +
                '}';
    }
}
