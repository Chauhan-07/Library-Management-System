package com.LibraryManagementSystem.LMS.project.Controller;

import com.LibraryManagementSystem.LMS.project.DTO.UserDTO;
import com.LibraryManagementSystem.LMS.project.Entity.Card;
import com.LibraryManagementSystem.LMS.project.Entity.Customer;
import com.LibraryManagementSystem.LMS.project.Entity.User;
import com.LibraryManagementSystem.LMS.project.Service.Card.CardService;
import com.LibraryManagementSystem.LMS.project.Service.Customer.CustomerService;
import com.LibraryManagementSystem.LMS.project.Service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CardService cardService;


    @Autowired
    public UserController(UserService userService,CustomerService customerService,CardService cardService)
    {
        this.userService=userService;
        this.customerService=customerService;
        this.cardService=cardService;
    }

//    @PostMapping
//    public ResponseEntity<User> createCard(@RequestBody User user) {
//        User savedUser = userService.saveUser(user);
//        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,Object>> register (@RequestBody User u) {
        Map<String,Object>  mp=new HashMap<>();
        String msg="";
        boolean suc=false;

        // finding the user
        User user= userService.getUserByEmail(u.getEmail());
        if(user==null)
        {

            user=userService.saveUser(u);



            Customer customer = new Customer();
            customer.setUser(user);
            customer.setName(user.getName());
            customer.setEmail(user.getEmail());
            customer.setAddress(user.getAddress());
            customer.setContact_no(user.getContact_no());


            //customer.setCard(card); // Set the card for the customer

            Customer dbCustomer = customerService.saveCustomer(customer);
            Card card = new Card();
            //card.setCustomerId(dbCustomer.getId());
            card.setCustomer(dbCustomer);
            // card.setCustomer
            //card.setExpiredDate(LocalDateTime.of(2024, 3, 25, 16, 8, 38, 206000));
            card.setStatus(true);
            cardService.saveCard(card);




            msg = "Registration Successful";
            suc = true;
            mp.put("user",new UserDTO(user.getId(),user.getName(),user.getEmail(),user.getAddress(),user.getContact_no(),user.getRole().getId()));

        }
        else {
            msg="User already exists";
        }

        mp.put("message", msg);
        mp.put("success", suc);


        return new ResponseEntity<>(mp,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,Object>> login (@RequestBody User u)
    {
        Map<String,Object> mp=new HashMap<>();
        String msg="";
        boolean suc=false;

        User user=userService.getUserByEmail(u.getEmail());
        if(user!=null)
        {
            if(user.getPassword().equals(u.getPassword()))
            {
                msg="Authentication Successful";
                suc=true;
                mp.put("User",new UserDTO(user.getId(),user.getName(),user.getEmail(),user.getAddress(),user.getContact_no(),user.getRole().getId()));
            }
            else {
                msg="Invalid Credentials";
            }
        }
        else {
            msg="User Not Found";
        }
        mp.put("message",msg);
        mp.put("sucess",suc);
        return new ResponseEntity<>(mp,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> users = userService.findAllUser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getCardById(@PathVariable int id) {
        Optional<User> user = userService.findById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        User updated = userService.updateUser(id, updatedUser);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        // Invoke the delete method from the service layer
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
