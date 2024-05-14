package com.web.app.service;

import java.util.ArrayList;

import com.web.app.controller.List;
import com.web.app.domain.mediationsMake.InsMediationsData;

public interface MediationsMakeService {

    ArrayList<InsMediationsData> mediationsDataSearch(InsMediationsData mediationcase);

    int MediationcaseInsert(InsMediationsData mediationcase);

}