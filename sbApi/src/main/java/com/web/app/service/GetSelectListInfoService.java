package com.web.app.service;

import java.util.List;

import com.web.app.domain.Position;
import com.web.app.domain.ReturnResult;

public interface GetSelectListInfoService {

    List<ReturnResult> selectMosList(Position position);

}
