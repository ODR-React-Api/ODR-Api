package com.web.app.service;

import com.web.app.domain.Entity.CaseNegotiations;
import com.web.app.domain.NegotiatAgree.NegotiatAgree;

public interface NegotiatAgreeService {
    CaseNegotiations selCaseNegotiations(NegotiatAgree negotiatAgree);
}
