package com.web.app.service;
import com.web.app.controller.List;
import com.web.app.domain.Mediationcase;

public interface MediationcaseService {

 int MediationcaseInsert(Mediationcase mediationcase);

 List<Mediationcase> MediationcaseSearch(Mediationcase mediationcase);
} 