package com.web.app.service;

import com.web.app.domain.Entity.S09ScreenIntelligence;

public interface RegistrationInformationRegistrationService {
  // Service接口
  Integer LoginIntelligence(S09ScreenIntelligence s09ScreenIntelligence, String uid, String platformId);
}
