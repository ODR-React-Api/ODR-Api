package com.web.app.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.getSelectListInfo.CaseRelations;
import com.web.app.domain.getSelectListInfo.MosList;
import com.web.app.domain.getSelectListInfo.ParameterData;
import com.web.app.domain.getSelectListInfo.ReturnComparator;
import com.web.app.domain.getSelectListInfo.ReturnData;
import com.web.app.mapper.GetSelectListInfoMapper;
import com.web.app.service.MosListService;

/**
 * S3_申立て一覧画面
 * 
 * @author DUC 郝建润
 * @since 2024/06/04
 * @version 1.0
 */
@Service
public class MosListServiceImpl implements MosListService {
    @Autowired
    private GetSelectListInfoMapper getSelectListInfoMapper;

    /**
     * 検索条件の引数によって、一覧データを取得する。
     * 
     * @param mosList 検索サブ画面で入力の画面項目
     * @return ReturnData 一覧画面表示用のデータ
     */
    @Override
    public List<ReturnData> findCasePetitionUserId(MosList mosList) {
        // 申立人CaseRelations
        List<CaseRelations> flgApplicant = new ArrayList<CaseRelations>();
        // 相手方CaseRelations
        List<CaseRelations> flgOther = new ArrayList<CaseRelations>();
        // 調停人CaseRelations
        List<CaseRelations> flgMediator = new ArrayList<CaseRelations>();
        // 一覧画面表示用のデータ
        List<ReturnData> allReturnData = new ArrayList<ReturnData>();
        // 申立人flg
        int applicantFlg = mosList.getApplicantFlg();
        // 相手方flg
        int otherFlg = mosList.getOtherFlg();
        // 調停人flg
        int mediatorFlg = mosList.getMediatorFlg();
        // メール
        String email = getSelectListInfoMapper.testEmail(mosList.getSessionId());
        // [ユーザ情報] ない,nullを返す
        if (email == null) {
            return null;
        }
        // 引数.立場申立人Flg=0 かつ 引数.立場相手方Flg=0 かつ 引数.立場調停人Flg=0 の場合、全ての処理を行う
        if (applicantFlg == 0 && otherFlg == 0 && mediatorFlg == 0) {
            // 申立人のケース取得
            flgApplicant = getSelectListInfoMapper.selectCasePetitionUserId1(email);
            // 相手方のケース取得
            flgOther = getSelectListInfoMapper.selectCasePetitionUserId2(email);
            // 調停人のケース取得
            flgMediator = getSelectListInfoMapper.selectCasePetitionUserId3(email);
        }

        if (applicantFlg == 1) {
            // 申立人のケース取得
            flgApplicant = getSelectListInfoMapper.selectCasePetitionUserId1(email);
        }
        if (otherFlg == 1) {
            // 相手方のケース取得
            flgOther = getSelectListInfoMapper.selectCasePetitionUserId2(email);
        }
        if (mediatorFlg == 1) {
            // 調停人のケース取得
            flgMediator = getSelectListInfoMapper.selectCasePetitionUserId3(email);
        }
        // 取得したユーザが申立人、相手方、調停人となる全ケースの合併
        allReturnData.addAll(getDetailedContent(flgApplicant, 1, mosList));
        allReturnData.addAll(getDetailedContent(flgOther, 2, mosList));
        allReturnData.addAll(getDetailedContent(flgMediator, 3, mosList));
        // 余分なデータを削除
        allReturnData = combine(allReturnData, mosList);
        // 要対応有無の降順 かつ 対応期日の昇順で結合後の２次元配列（もしくはリスト）をソートして、戻す。
        Collections.sort(allReturnData, new ReturnComparator());
        // 一覧画面表示用のデータを返す
        return allReturnData;
    }

    /**
     * ユーザが申立人(相手方/調停人)となるケースの取得
     * 
     * @param flg        取得したCaseIdとPetitionUserId立場フラグ，入力パラメータ)
     * @param statusFlag 立場フラグ
     * @param mosList    検索サブ画面で入力の画面項目
     * @return 一覧画面表示用の申立人(相手方/調停人)のデータ
     */
    @Override
    public List<ReturnData> getDetailedContent(List<CaseRelations> flg, int statusFlag, MosList mosList) {
        // 入力されたデータ
        List<ParameterData> flgParameter = new ArrayList<ParameterData>();
        // API「検索用キース詳細取得」で返されるデータ
        List<ReturnData> returnedValue = new ArrayList<ReturnData>();
        // testデータ
        LocalDate time = LocalDate.now();

        for (int i = 0; i < flg.size(); i++) {
            // 取得したCaseIdとPetitionUserIdを引数として、取得したレコードがなくなるまで繰り返して下記APIを呼び出して、ケース詳細内容を取得する。
            CaseRelations caseRelations = flg.get(i);
            // 入力されたデータ
            ParameterData parameterData = new ParameterData(caseRelations.getCaseId(),
                    caseRelations.getPetitionUserId(), statusFlag, mosList.getCid(),
                    mosList.getCaseTitle(), mosList.getPetitionDateForm(), mosList.getPetitionDateTo(),
                    mosList.getCaseStatus());
            flgParameter.add(parameterData);
            // API「検索用ケース詳細取得」からのデータ受信
            ReturnData returnedValues = new ReturnData();
            // API「検索用ケース詳細取得」

            // test
            // Random random = new Random();
            // int numberOfUnreadMessages = random.nextInt(2);
            // int equiredSupport = random.nextInt(2);
            // returnedValues = new ReturnData(statusFlag, "a", "s", "d", "f", time,
            // numberOfUnreadMessages,
            // equiredSupport);
            // time = time.minusDays(1);

            // API「検索用キース詳細取得」で返されるデータ
            returnedValue.add(returnedValues);

        }
        // API「検索用キース詳細取得」で返されるデータ
        return returnedValue;

    }

    @Override
    /**
     * 引数.メッセージ有無Flgと引数.要対応有無Flgによるデータ抽出結合
     * 
     * @param flg     一覧画面表示用のデータ
     * @param mosList ①取得したCaseIdとPetitionUserId
     * @return 戻り値の説明内容
     * @throws Exception 一覧画面表示用のデータ(並べ替え後)
     */
    public List<ReturnData> combine(List<ReturnData> flg, MosList mosList) {
        // 引数.メッセージ有無Flgによるデータ抽出結合
        // a.引数.メッセージ有Flgが1の場合、未読メッセージ件数 > 0件のデータを抽出して、結合する。
        // b.引数.メッセージ無Flgが1の場合、未読メッセージ件数 = 0件のデータを抽出して、結合する。
        // c.上記以外の場合、両方のデータとも抽出して結合する
        if (mosList.getMessage() == "1") {
            for (int i = flg.size() - 1; i >= 0; i--) {
                ReturnData returnData = flg.get(i);
                if (returnData.getNumberOfUnreadMessages() <= 0) {
                    flg.remove(i);
                }
            }
        } else if (mosList.getMessage() == "0") {
            for (int i = flg.size() - 1; i >= 0; i--) {
                ReturnData returnData = flg.get(i);
                if (returnData.getNumberOfUnreadMessages() != 0) {
                    flg.remove(i);
                }
            }
        }
        // 引数.要対応有無Flgによるデータ抽出結合
        // a.引数.要対応有Flgが1の場合、要対応有無が1（要対応有り）のデータを抽出して、結合する。
        // b.引数.要対応無FlgFlgが1の場合、要対応有無が0（要対応なし）のデータを抽出して、結合する。
        // c.上記以外の場合、両方のデータとも抽出して結合する
        if (mosList.getApproval() == "1") {
            for (int i = flg.size() - 1; i >= 0; i--) {
                ReturnData returnData = flg.get(i);
                if (returnData.getRequiredSupport() == 0) {
                    flg.remove(i);
                }
            }
        } else if (mosList.getApproval() == "0") {
            for (int i = flg.size() - 1; i >= 0; i--) {
                ReturnData returnData = flg.get(i);
                if (returnData.getRequiredSupport() == 1) {
                    flg.remove(i);
                }
            }
        }
        // 引数.メッセージ有無Flgと引数.要対応有無Flgによって抽出され結合されたデータ
        return flg;
    }

}
