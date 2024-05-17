package com.web.app.service;

import com.web.app.domain.NegotiatMake.NegotiationsFile;

public interface NegotiatMakeService {

    int addNegotiationsEdit(NegotiationsFile negotiationsFile)throws Exception;

    int updateNegotiationsEdit(NegotiationsFile negotiationsFile)throws Exception;

}
