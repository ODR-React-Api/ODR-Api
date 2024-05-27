package com.web.app.service;

import org.springframework.stereotype.Service;

import com.web.app.domain.Entity.OdrUserUtil;

@Service
public interface UserInfoConfirmService {

     boolean RegisterUserMapper(OdrUserUtil odrUserUtil);

}
