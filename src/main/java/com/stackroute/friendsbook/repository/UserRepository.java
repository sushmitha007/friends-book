package com.stackroute.friendsBook.repository;

import com.stackroute.friendsBook.model.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends Neo4jRepository<User,Long> {

    @Query("MATCH (n) RETURN n")
    List<User> getAllUsers();
    @Query("MATCH (s) WHERE ID(s)={id} RETURN s")
    User getByid(@Param("id") long id);
    @Query("MATCH (user:User) RETURN user.email")
    List<String>getAllUserEmails();
    @Query("MATCH (n) detach delete n")
    List<User> deleteAllUsers();
    @Query("MATCH (a:User), (b:User) WHERE a.email ={userEmail1} AND b.email ={userEmail2} CREATE (a)-[r: friend]->(b) CREATE (a)<-[f: friend]-(b) RETURN a,b ")
    List<User> createRelationship(@Param("userEmail1") String emailUser1,@Param("userEmail2") String emailUser2 );
    @Query("match (user:User) where user.email={userEmail} return user")
    User getUserByValidLogin(@Param("userEmail") String userEmail);
    @Query("  MATCH (me:User)-[:friend]->(myFriend:User)-[:friend]->(friendOfFriend:User) WHERE NOT (me)-[:FRIEND]->(friendOfFriend:User) AND me.name = {userName} AND NOT friendOfFriend.email ={userEmail} RETURN friendOfFriend")
    public List<User> getFirstLevelRecommendation(@Param("userName") String name,@Param("userEmail") String userEmail);
    @Query("MATCH (me:User)-[:friend]->(myFriend:User)-[:friend]->(friendOfFriend:User)-[:friend]->(fofof:User) WHERE NOT (me)-[:friend]->(fofof:User) AND me.name = {userName} AND NOT fofof.email = {userEmail} RETURN fofof")
    public List<User> getSecondeLevelRecommendations(@Param("userName") String userName,@Param("userEmail") String userEmail);

}