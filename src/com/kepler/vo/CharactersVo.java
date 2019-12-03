package com.kepler.vo;

import javax.persistence.*;

@Table(name = "characters")
@Entity
public class CharactersVo {//角色管理
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int characterId;
    private String characterName;//角色名称
    private int postId;//关联岗位表,给岗位设置权限

    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    @Override
    public String toString() {
        return "CharactersVo{" +
                "characterId=" + characterId +
                ", characterName='" + characterName + '\'' +
                ", postId=" + postId +
                '}';
    }
}
