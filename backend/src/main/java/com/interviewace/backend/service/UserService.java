//package com.interviewace.backend.service;
//
////import com.interviewace.backend.dto.UserResponse;
//
//public interface UserService {
//
//    com.interviewace.backend.dto.UserResponse getCurrentUser(String email);
//
//}

package com.interviewace.backend.service;

import com.interviewace.backend.dto.UserResponse;

public interface UserService {

    UserResponse getCurrentUser(String email);

}