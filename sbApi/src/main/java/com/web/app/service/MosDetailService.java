package com.web.app.service;

import com.web.app.domain.MosDetail.WithdrawalReturn;

public interface MosDetailService {

    WithdrawalReturn applyWithdraw(String caseId);

}
