package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.PoliciesInfo;
import com.web.app.mapper.GetPoliciesInfoMapper;
import com.web.app.service.PoliciesConfirmService;

/**
 * API_利用規約情報取得
 * 
 * @author DUC 閆文静
 * @since 2024/04/19
 * @version 1.0
 */
@Service
public class PoliciesConfirmServiceImpl implements PoliciesConfirmService {
    // セッション情報.ConfirmedVersionNoOfTerms
    private static final String confirmedVersionNoOfTerms = "14";
    // セッション情報.ConfirmedVersionNoOfPolicy
    private static final String confirmedVersionNoOfPolicy = "14";

    @Autowired
    private GetPoliciesInfoMapper getPoliciesInfoMapper;

    @Override
    public List<PoliciesInfo> getPoliciesInfoList() {
        List<PoliciesInfo> policiesInfoList = new ArrayList<>();
        PoliciesInfo policiesInfo = new PoliciesInfo();
        PoliciesInfo privacyPolicyInfo = new PoliciesInfo();
        try {
            // 利用規約情報取得
            policiesInfo = getPoliciesInfoMapper.selectPoliciesInfo();
            policiesInfoList.add(policiesInfo);
        } catch (Exception e) {
            System.out.println("エラー画面(404)へ遷移");
        }
        try {
            // プライバシーポリシー情報取得
            privacyPolicyInfo = getPoliciesInfoMapper.selectPrivacyPolicyInfo();
            policiesInfoList.add(privacyPolicyInfo);
        } catch (Exception e) {
            System.out.println("エラー画面(404)へ遷移");
        }
        String policiesVersionNo = policiesInfo.getVersionNo();
        String privacyPolicyVersionNo = privacyPolicyInfo.getVersionNo();
        // 利用規約のみが更新された場合
        if (!policiesVersionNo.equals(confirmedVersionNoOfTerms)
                && privacyPolicyVersionNo.equals(confirmedVersionNoOfPolicy)) {
            System.out.println("利用規約のみが更新された場合VersionNo:" + policiesInfo.getVersionNo());
            // プライバシーポリシーのみが更新された場合
        } else if (!privacyPolicyVersionNo.equals(confirmedVersionNoOfPolicy)
                && policiesVersionNo.equals(confirmedVersionNoOfTerms)) {
            System.out.println("プライバシーポリシーのみが更新された場合VersionNo:" + privacyPolicyInfo.getVersionNo());
            // 利用規約とプライバシーポリシーが更新された場合
        } else if (!policiesVersionNo.equals(confirmedVersionNoOfTerms)
                && !privacyPolicyVersionNo.equals(confirmedVersionNoOfPolicy)) {
            System.out.println("利用規約とプライバシーポリシーが更新された場合VersionNo:" + policiesInfo.getVersionNo() + ","
                    + privacyPolicyInfo.getVersionNo());
        }
        return policiesInfoList;
    }
}
