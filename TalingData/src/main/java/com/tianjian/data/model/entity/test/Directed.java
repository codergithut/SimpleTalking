package com.tianjian.data.model.entity.test;
import org.neo4j.ogm.annotation.*;

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

@RelationshipEntity(type = "DIRECTED")
public class Directed {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private Person startNode;

    @EndNode
    private Movie endNode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getStartNode() {
        return startNode;
    }

    public void setStartNode(Person startNode) {
        this.startNode = startNode;
    }

    public Movie getEndNode() {
        return endNode;
    }

    public void setEndNode(Movie endNode) {
        this.endNode = endNode;
    }
}