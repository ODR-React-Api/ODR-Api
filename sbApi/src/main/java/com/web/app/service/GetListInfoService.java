package com.web.app.service;
import java.util.List;
import com.web.app.domain.Entity.ReturnResult;

public interface GetListInfoService {
  // Service接口
  List<ReturnResult> getListInfo(String uid);
}
