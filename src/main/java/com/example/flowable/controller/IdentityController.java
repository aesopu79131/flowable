// package com.example.flowable.controller;

// import com.example.flowable.service.CustomIdentityService;
// import org.flowable.idm.api.Group;
// import org.flowable.idm.api.User;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/identity")
// public class IdentityController {

//     @Autowired
//     private CustomIdentityService identityService;

//     @PostMapping("/user")
//     public User createUser(@RequestParam String id, @RequestParam String firstName,
//                            @RequestParam String lastName, @RequestParam String email,
//                            @RequestParam String password) {
//         return identityService.createUser(id, firstName, lastName, email, password);
//     }

//     @PostMapping("/group")
//     public Group createGroup(@RequestParam String id, @RequestParam String name,
//                              @RequestParam String type) {
//         return identityService.createGroup(id, name, type);
//     }

//     @PostMapping("/user/{userId}/group/{groupId}")
//     public void addUserToGroup(@PathVariable String userId, @PathVariable String groupId) {
//         identityService.addUserToGroup(userId, groupId);
//     }

//     @GetMapping("/user/{userId}")
//     public User getUser(@PathVariable String userId) {
//         return identityService.getUser(userId);
//     }

//     @GetMapping("/group/{groupId}")
//     public Group getGroup(@PathVariable String groupId) {
//         return identityService.getGroup(groupId);
//     }
// }