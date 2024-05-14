package com.web.app.service;

import com.web.app.controller.List;
import com.web.app.domain.mediationsMake.InsMediationsData;

public interface MediationsMakeService {

    List<InsMediationsData> mediationsDataSearch(InsMediationsData mediationcase);

    int MediationcaseInsert(InsMediationsData mediationcase);

}