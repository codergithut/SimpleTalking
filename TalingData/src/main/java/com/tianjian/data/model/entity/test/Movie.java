package com.tianjian.data.model.entity.test;

/**
 * @ProjectName: com.tianjian.data.model.entity
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/12
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/12
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
import org.neo4j.ogm.annotation.*;

@NodeEntity(label = "Movie")
public class Movie {
    @Id
    @GeneratedValue
    private Long nodeId;

    @Property(name = "title")
    private String title;

    @Property(name = "released")
    private int released;

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleased(int released) {
        this.released = released;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public String getTitle() {
        return title;
    }

    public int getReleased() {
        return released;
    }


}
