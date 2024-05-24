package com.web.app.service;

import org.springframework.stereotype.Service;

import com.web.app.domain.Response;
import com.web.app.domain.Entity.OdrUserUtil;

@Service
public interface UserInfoConfirmService {

     Response addUser(OdrUserUtil odrUserUtil);

}
