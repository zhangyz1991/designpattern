package com.vick.designpattern.action.template.entity;

/**
 * @author Vick Zhang
 * @create 2020/9/1
 */
public class Score {
    private Integer id;
    private Integer userid;
    private String subject;
    private Double score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score[" + "id=" + id + ","
                + "userid=" + userid + ","
                + "subject=" + subject + ","
                + "score=" + score + "]";
    }
}
