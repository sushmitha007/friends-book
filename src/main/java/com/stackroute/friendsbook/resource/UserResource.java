package com.stackroute.friendsBook.resource;

import ch.qos.logback.core.pattern.util.RegularEscapeUtil;

import com.stackroute.friendsBook.Exceptions.UserAlreadyExistsException;
import com.stackroute.friendsBook.model.User;
import com.stackroute.friendsBook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/rest/neo4j/user")
public class UserResource {

    @Autowired
    UserService userService;


    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody User user) throws UserAlreadyExistsException { try{

        try{
            userService.addUser(user);
            return new ResponseEntity<String>("user saved", HttpStatus.OK);}

        catch(UserAlreadyExistsException e){

            return new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);

        }
    }
    catch(Exception e){
        e.printStackTrace();
        return new ResponseEntity<String>("user not saved",HttpStatus.CONFLICT);
    }
    }
    @GetMapping("/allUser")
    public ResponseEntity<?> getAllUser(){


        try{


            return new ResponseEntity<List<User>>(userService.getAll(),HttpStatus.OK);


        }
        catch(Exception e){



            return new ResponseEntity<String>("not availaible",HttpStatus.CONFLICT);
        }



    }



    @PostMapping("/addFriend/{idPerson1}/{idPerson2}")
    public ResponseEntity<?> addFriend(@Valid @PathVariable("idPerson1") long idPerson1,@PathVariable("idPerson2") long idPerson2){
        System.out.println(idPerson1+"  "+idPerson2);
        try{


            return new ResponseEntity<String>(userService.addFriend(idPerson1,idPerson2)+"",HttpStatus.OK);
        }
        catch(Exception e){

            return new ResponseEntity<String>(false+"",HttpStatus.CONFLICT);
        }



    }
    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") long id){

        try{

            return new ResponseEntity<User>(userService.getBYId(id),HttpStatus.OK);

        }
        catch(Exception e){

            e.printStackTrace();

            return new ResponseEntity<String>("no id",HttpStatus.CONFLICT);
        }



    }


    @PostMapping("/isUserExists")
    public ResponseEntity<?> vaildateUserLogin(@RequestBody  User user){

        try{
            System.out.println("the maisl os "+user.getEmail());
            return new ResponseEntity<User>(userService.valdiateUserEmail(user.getEmail()),HttpStatus.OK);
        }

        catch(Exception e){


            return new ResponseEntity<String>("Facing Trouble",HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/deleteAllUsers")
    public ResponseEntity<?> deleteAllusers() {


        try {
            return new ResponseEntity<>(userService.deleteAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("error occures", HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/getFirstLevelRecommendations/{id}")
    public  ResponseEntity<?> getFirstLevelRecommendations(@PathVariable("id") long id)
    {

        try{

            return new ResponseEntity<List<User>>(userService.getFirstLevelRecommendation(id),HttpStatus.OK);
        }

        catch (Exception e){


            return new ResponseEntity<Boolean>(false,HttpStatus.CONFLICT);
        }

    }
    @GetMapping("/getSecondLevelRecommendations/{id}")
    public  ResponseEntity<?> getSecondLevelRecommendations(@PathVariable("id") long id)
    {

        try{

            return new ResponseEntity<List<User>>(userService.getSecondeLevelRecommendation(id),HttpStatus.OK);
        }

        catch (Exception e){


            return new ResponseEntity<Boolean>(false,HttpStatus.CONFLICT);
        }

    }

}