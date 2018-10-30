package com.stackroute.friendsBook.service;

import com.stackroute.friendsBook.Exceptions.UserAlreadyExistsException;
import com.stackroute.friendsBook.model.User;
import com.stackroute.friendsBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.getAllUsers();
    }

    public User addUser(User user) throws UserAlreadyExistsException {

        if (!userRepository.getAllUserEmails().contains(user.getEmail().trim().toLowerCase())) {
            return userRepository.save(user);

        } else {

            throw new UserAlreadyExistsException("This user with this email already exists");

        }

    }

    public List<User> addFriend(long person1Id, long person2Id) {
        try {
            //User person1Obj = userRepository.findOne(person1Id);
            //User person2Obj = userRepository.findOne(person2Id);
            User person1Obj = userRepository.getByid(person1Id);
            User person2Obj = userRepository.getByid(person2Id);
            person1Obj.getFriends().add(person2Obj.getId());
            person2Obj.getFriends().add(person1Obj.getId());
            userRepository.save(person1Obj);
            userRepository.save(person2Obj);
            //return userRepository.createRelationship(userRepository.findOne(person1Id).getEmail(), userRepository.findOne(person2Id).getEmail());
            return userRepository.createRelationship(userRepository.getByid(person1Id).getEmail(), userRepository.getByid(person2Id).getEmail());

        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }
    }

    public User getBYId(long id) {


        try {
            return userRepository.getByid(id);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }


    public User valdiateUserEmail(String emailToBeValidated) {

        boolean isFound = false;
        try {
            System.out.println(Arrays.toString(userRepository.getAllUserEmails().toArray()));
            isFound = userRepository.getAllUserEmails().contains(emailToBeValidated);
            if(isFound){

                return userRepository.getUserByValidLogin(emailToBeValidated);
            }

            else{


                return null;
            }

        } catch (Exception e) {
            return null;
        }
    }

    public boolean deleteAllUsers() {

        try {
            userRepository.deleteAllUsers();
            return true;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }



    public List<User> getFirstLevelRecommendation(long id) {

        try {
            //User user = userRepository.findOne(id);
            User user = userRepository.getByid(id);
            return userRepository.getFirstLevelRecommendation(user.getName(), user.getEmail());
        } catch (Exception e) {


            return null;
        }
    }
    public List<User> getSecondeLevelRecommendation(long id) {

        try {
            //User user = userRepository.findOne(id);
            User user = userRepository.getByid(id);
            return userRepository.getSecondeLevelRecommendations(user.getName(), user.getEmail());
        } catch (Exception e) {


            return null;
        }
    }



}

