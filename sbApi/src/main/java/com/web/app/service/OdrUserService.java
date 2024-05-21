package com.web.app.service;

import org.springframework.stereotype.Service;

import com.web.app.domain.Response;
import com.web.app.domain.User;
import com.web.app.domain.Entity.OdrUserUtil;
import com.web.app.domain.Entity.OdrUsers;

@Service
public interface OdrUserService {

     Response addUser(OdrUserUtil odrUserUtil);


}
