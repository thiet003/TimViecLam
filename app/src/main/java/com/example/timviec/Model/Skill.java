package com.example.timviec.Model;

public class Skill {
    private String name_skill;

    public String getName_skill() {
        return name_skill;
    }

    public void setName_skill(String name_skill) {
        this.name_skill = name_skill;
    }

    public String getDesc_skill() {
        return desc_skill;
    }

    public void setDesc_skill(String desc_skill) {
        this.desc_skill = desc_skill;
    }

    private String desc_skill;

    public Skill(String name_skill, String desc_skill) {
        this.name_skill = name_skill;
        this.desc_skill = desc_skill;
    }
}
