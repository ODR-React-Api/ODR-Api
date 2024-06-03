package com.web.app.service;

import org.springframework.stereotype.Service;

import com.web.app.domain.OdrUserUtil;

@Service
public interface UserInfoConfirmService {

    boolean registerUser(OdrUserUtil odrUserUtil);

}
