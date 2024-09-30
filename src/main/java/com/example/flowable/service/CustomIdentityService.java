// package com.example.flowable.service;

// import org.flowable.idm.api.Group;
// import org.flowable.idm.api.User;
// import org.flowable.idm.api.IdentityService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// @Service
// public class CustomIdentityService {

//     @Autowired
//     private org.flowable.idm.api.IdentityService identityService;

//     public User createUser(String id, String firstName, String lastName, String email, String password) {
//         User user = identityService.newUser(id);
//         user.setFirstName(firstName);
//         user.setEmail(email);
//         user.setPassword(password);
//         identityService.saveUser(user);
//         return user;
//     }

//     public Group createGroup(String id, String name, String type) {
//         Group group = identityService.newGroup(id);
//         group.setName(name);
//         group.setType(type);
//         identityService.saveGroup(group);
//         return group;
//     }

//     public void addUserToGroup(String userId, String groupId) {
//         identityService.createMembership(userId, groupId);
//     }

//     public User getUser(String userId) {
//         return identityService.createUserQuery().userId(userId).singleResult();
//     }

//     public Group getGroup(String groupId) {
//         return identityService.createGroupQuery().groupId(groupId).singleResult();
//     }
// }
