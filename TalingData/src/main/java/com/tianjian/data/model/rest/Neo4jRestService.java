package com.tianjian.data.model.rest;

import com.tianjian.data.model.entity.relation.TalkingUser;
import com.tianjian.data.model.entity.test.ActedIn;
import com.tianjian.data.model.entity.test.Directed;
import com.tianjian.data.model.entity.test.Movie;
import com.tianjian.data.model.entity.test.Person;
import com.tianjian.data.model.rep.FriendRelationRepository;
import com.tianjian.data.model.rep.GroupRelationRepository;
import com.tianjian.data.model.rep.TalkingGroupRepository;
import com.tianjian.data.model.rep.TalkingUserRepository;
import com.tianjian.data.model.rep.test.ActedInRepository;
import com.tianjian.data.model.rep.test.DirectedRepository;
import com.tianjian.data.model.rep.test.MovieRepository;
import com.tianjian.data.model.rep.test.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ProjectName: com.tianjian.data.model.rest
 * @Description: 一句话描述该类的功能
 * @Author: tianjian
 * @CreateDate: 2019/3/12
 * @UpdateUser: tianjian
 * @UpdateDate: 2019/3/12
 * @UpdateRemark: 跟新说明
 * @Version: [v1.0]
 */
@RestController
@RequestMapping("/neo4j")
public class Neo4jRestService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    DirectedRepository directedRepository;

    @Autowired
    ActedInRepository actedInRepository;

    @GetMapping("/insert")
    public void insertIntoDataBase() {
        Person person = new Person();

        person.setName("tj");
        person.setBorn(11);

        Person ins_person = personRepository.save(person);

        Movie movie = new Movie();
        movie.setReleased(11);
        movie.setTitle("无间道");

        Movie ins_movie = movieRepository.save(movie);

        ActedIn actedIn = new ActedIn();

        actedIn.setEndNode(ins_movie);
        actedIn.setRoles("主演");
        actedIn.setStartNode(ins_person);

        actedInRepository.save(actedIn);

        Directed directed = new Directed();

        directed.setEndNode(ins_movie);
        directed.setStartNode(ins_person);

        directedRepository.save(directed);

    }

    @Autowired
    TalkingUserRepository talkingUserRepository;

    @Autowired
    TalkingGroupRepository talkingGroupRepository;

    @Autowired
    FriendRelationRepository friendRelationRepository;

    @Autowired
    GroupRelationRepository groupRelationRepository;

    @GetMapping("/init")
    public void insertTalkingObject() {
//        TalkingUser person1 = new TalkingUser();
//        person1.setTag("person");
//        person1.setUserName("tj");
//        person1 = talkingUserRepository.save(person1);
//        TalkingUser person2 = new TalkingUser();
//        person2.setUserName("cl");
//        person2.setTag("person");
//        person2 = talkingUserRepository.save(person2);
//
//        TalkingGroup talkingGroup = new TalkingGroup();
//        talkingGroup.setGroupName("游戏工作群");
//        talkingGroup.setDescription("game players");
//        talkingGroup.setTag("测试");
//
//        FriendRelation friendRelation = new FriendRelation();
//        friendRelation.setFrom(person1);
//        friendRelation.setTo(person2);
//        friendRelationRepository.save(friendRelation);
//
//        FriendRelation friendRelation1 = new FriendRelation();
//        friendRelation1.setFrom(person2);
//        friendRelation1.setTo(person1);
//        friendRelationRepository.save(friendRelation1);
//
//        GroupRelation groupRelation = new GroupRelation();
//        groupRelation.setTalkingUser(person1);
//        groupRelation.setTalkingGroup(talkingGroup);
//        groupRelationRepository.save(groupRelation);
//
//        GroupRelation groupRelation1 = new GroupRelation();
//        groupRelation1.setTalkingUser(person2);
//        groupRelation1.setTalkingGroup(talkingGroup);
//        groupRelationRepository.save(groupRelation1);
//
//        List<TalkingUser> data = talkingUserRepository.getTalkingUserFriend("tj");
//
//        System.out.println("=====================" + data.get(0).getUserName() + "============================");

        List<TalkingUser> datas = talkingUserRepository.getTalkingUserByGroup("游戏工作群");

        System.out.println("======================begin===========================");
        for(TalkingUser user : datas ) {
            System.out.println("=====================" + user.getUserName() + "========================");
        }

    }

}
