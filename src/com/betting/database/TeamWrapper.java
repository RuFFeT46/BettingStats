package com.betting.database;

import com.betting.entity.Team;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="team")
public class TeamWrapper {

    private int teamId;
    private String name;

    public TeamWrapper(){

    }

    public TeamWrapper(Team team){
        this.teamId = team.getTeamId();
        this.name = team.getName();
        //Wrapper.getInstance().getTeams().add(this);
    }

    @Id
    @Column(name="team_id")
    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
