package com.web.app.service;
import com.web.app.controller.List;
import com.web.app.domain.mediationsMake.InsMediationsData;

public interface MediationsMakeService {

 int MediationcaseInsert(InsMediationsData mediationcase);

 List<InsMediationsData> MediationcaseSearch(InsMediationsData mediationcase);
} 