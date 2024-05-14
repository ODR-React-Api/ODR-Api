package com.web.app.service;

import java.util.ArrayList;

import com.web.app.domain.mediationsMake.InsMediationsData;

public interface MediationsMakeService {

    ArrayList<InsMediationsData> dataSearch(InsMediationsData mediationcase);

    InsMediationsData mediationDataCount (InsMediationsData mediationcase);

    int insMediationsData2(InsMediationsData mediationcase);

}