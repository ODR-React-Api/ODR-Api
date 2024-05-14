package com.web.app.service;
import java.util.List;

import com.web.app.domain.CouAnswerLogin.CasesByCid;
public interface CasesByCidService {
    List<CasesByCid> casesByCid(String CaseId,String PlatformId);

}
