package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.constants.Constants;
import com.web.app.domain.MosList.Position;
import com.web.app.domain.MosList.ReturnResult;
import com.web.app.domain.MosList.SelectCondition;
import com.web.app.domain.MosList.TestMos;
import com.web.app.mapper.GetSelectListInfoMapper;
import com.web.app.service.MosListService;

/**
 * S3_申立て一覧画面
 * Service層実現類
 * MosListServiceImpl
 * API_検索用一覧取得
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/18
 * @version 1.0
 */
@Service
public class MosListServiceImpl implements MosListService {

    @Autowired
    private GetSelectListInfoMapper getSelectListInfoMapper;

    // 立場フラグ初期化「1（申立人）、2（相手方）、3（調停人）」
    Integer positionFlg = 0;
    // API「検索用ケース詳細取得」の検索条件の引数
    SelectCondition selectCondition = new SelectCondition();
    // API「検索用ケース詳細取得」から戻ったデータの引数
    ReturnResult returnResult = new ReturnResult();
    // 申立人のケース内容取得List
    List<TestMos> list1 = new ArrayList<>();
    // 相手方のケース内容取得List
    List<TestMos> list2 = new ArrayList<>();
    // 調停人のケース内容取得List
    List<TestMos> list3 = new ArrayList<>();
    // 申立人のケース詳細内容取得List
    List<ReturnResult> PetitionsResults1 = new ArrayList<>();
    // 相手方のケース詳細内容取得List
    List<ReturnResult> PetitionsResults2 = new ArrayList<>();
    // 調停人のケース詳細内容取得List
    List<ReturnResult> PetitionsResults3 = new ArrayList<>();

    /**
     * 検索条件の引数によって、一覧データを取得する。
     *
     * @param position 検索サブ画面で入力の画面項目
     * @return ReturnResult 一覧画面表示用のデータ
     */
    @Override
    public List<ReturnResult> selectMosList(Position position) {

        // 1.TBL「ユーザ情報」を取得する
        String email = getSelectListInfoMapper.testOdrUsersSearch(position.getSessionId());

        // TBL「ユーザ情報」より取得したユーザメールをキーにTBL「案件別個人情報リレーション」より申立人、相手方、調停人となるケース取得
        if (email != null) {
            // 引数.立場申立人Flg=0 かつ 引数.立場相手方Flg=0 かつ 引数.立場調停人Flg=0 の場合、申立人、相手方、調停人の取得処理を全て行う。
            if (position.getPetitionsFlg1() == 0 && position.getTraderFlg2() == 0 && position.getMediatorFlg3() == 0) {

                // ⓵申立人の取得処理を行う。
                list1 = getSelectListInfoMapper.testPetitionsSearch(email);

                // 申立人取得有りの場合、下記処理を行う）
                if (list1.size() > 0) {
                    for (int i = 0; i < list1.size(); i++) {

                        // 立場フラグ＝1（申立人）を設定する
                        positionFlg = Constants.POSITIONFLAG_PETITION;
                        // ⓵取得したCaseId
                        selectCondition.setCaseId(list1.get(i).getCaseId());
                        // ⓵取得したPetitionUserId
                        selectCondition.setPetitionUserId(list1.get(i).getPetitionUserId());
                        // 引数.立場フラグ
                        selectCondition.setPositionFlg(positionFlg);
                        // 引数.申立て番号
                        selectCondition.setCid(position.getCid());
                        // 引数.件名
                        selectCondition.setCaseTitle(position.getCaseTitle());
                        // 引数.登録日付From
                        selectCondition.setPetitionDateStart(position.getPetitionDateStart());
                        // 引数.登録日付To
                        selectCondition.setPetitionDateEnd(position.getPetitionDateEnd());
                        // 引数.ステータス
                        selectCondition.setCaseStatus(position.getCaseStatus());

                        // DOTO API「検索用ケース詳細取得」
                        // ReturnResult resultList1 = SearchDetailCase(selectCondition);
                        ReturnResult resultList1 = new ReturnResult();
                        // 戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                        if (resultList1 != null && resultList1.getCid() != null) {
                            PetitionsResults1.add(resultList1);
                        }
                    }
                }
                // ⓶相手方の取得処理を行う。
                list2 = getSelectListInfoMapper.testTraderFlgSearch(email);

                // 相手方取得有りの場合、下記処理を行う）
                if (list2.size() > 0) {
                    for (int i = 0; i < list2.size(); i++) {

                        // 立場フラグ＝2（相手方）を設定する
                        positionFlg = Constants.POSITIONFLAG_TRADER;
                        // ⓶取得したCaseId
                        selectCondition.setCaseId(list2.get(i).getCaseId());
                        // ⓶取得したPetitionUserId
                        selectCondition.setPetitionUserId(list2.get(i).getPetitionUserId());
                        // 引数.立場フラグ
                        selectCondition.setPositionFlg(positionFlg);
                        // 引数.申立て番号
                        selectCondition.setCid(position.getCid());
                        // 引数.件名
                        selectCondition.setCaseTitle(position.getCaseTitle());
                        // 引数.登録日付From
                        selectCondition.setPetitionDateStart(position.getPetitionDateStart());
                        // 引数.登録日付To
                        selectCondition.setPetitionDateEnd(position.getPetitionDateEnd());
                        // 引数.ステータス
                        selectCondition.setCaseStatus(position.getCaseStatus());

                        // DOTO API「検索用ケース詳細取得」
                        // ReturnResult resultList2 = SearchDetailCase(selectCondition);
                        ReturnResult resultList2 = new ReturnResult();
                        // 戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                        if (resultList2 != null && resultList2.getCid() != null) {
                            PetitionsResults2.add(resultList2);
                        }
                    }
                    ;
                }
                // ⓷調停人の取得処理を行う。
                list3 = getSelectListInfoMapper.testMediatorSearch(email);

                // 調停人取得有りの場合、下記処理を行う）
                if (list3.size() > 0) {
                    for (int i = 0; i < list3.size(); i++) {

                        // 立場フラグ＝3（調停人）を設定する
                        positionFlg = Constants.POSITIONFLAG_MEDIATOR;
                        // ⓷取得したCaseId
                        selectCondition.setCaseId(list3.get(i).getCaseId());
                        // ⓷取得したPetitionUserId
                        selectCondition.setPetitionUserId(list3.get(i).getPetitionUserId());
                        // 引数.立場フラグ
                        selectCondition.setPositionFlg(positionFlg);
                        // 引数.申立て番号
                        selectCondition.setCid(position.getCid());
                        // 引数.件名
                        selectCondition.setCaseTitle(position.getCaseTitle());
                        // 引数.登録日付From
                        selectCondition.setPetitionDateStart(position.getPetitionDateStart());
                        // 引数.登録日付To
                        selectCondition.setPetitionDateEnd(position.getPetitionDateEnd());
                        // 引数.ステータス
                        selectCondition.setCaseStatus(position.getCaseStatus());

                        // DOTO API「検索用ケース詳細取得」
                        // ReturnResult resultList3 = SearchDetailCase(selectCondition);
                        ReturnResult resultList3 = new ReturnResult();
                        // 戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                        if (resultList3 != null && resultList3.getCid() != null) {
                            PetitionsResults3.add(resultList3);
                        }
                    }
                    ;
                }
            }
            // 以外の場合
            else {
                // 引数.立場申立人Flg＝1の場合、申立人の取得処理を行う
                if (position.getPetitionsFlg1() == Constants.NUM_1) {

                    // ⓵申立人の取得
                    list1 = getSelectListInfoMapper.testPetitionsSearch(email);

                    // 申立人取得有りの場合、下記処理を行う）
                    if (list1.size() > 0) {
                        for (int i = 0; i < list1.size(); i++) {

                            // 立場フラグ＝1（申立人）を設定する
                            positionFlg = Constants.POSITIONFLAG_PETITION;
                            // ⓵取得したCaseId
                            selectCondition.setCaseId(list1.get(i).getCaseId());
                            // ⓵取得したPetitionUserId
                            selectCondition.setPetitionUserId(list1.get(i).getPetitionUserId());
                            // 引数.立場フラグ
                            selectCondition.setPositionFlg(positionFlg);
                            // 引数.申立て番号
                            selectCondition.setCid(position.getCid());
                            // 引数.件名
                            selectCondition.setCaseTitle(position.getCaseTitle());
                            // 引数.登録日付From
                            selectCondition.setPetitionDateStart(position.getPetitionDateStart());
                            // 引数.登録日付To
                            selectCondition.setPetitionDateEnd(position.getPetitionDateEnd());
                            // 引数.ステータス
                            selectCondition.setCaseStatus(position.getCaseStatus());
                            System.out.println("selectCondition" + selectCondition);

                            // DOTO API「検索用ケース詳細取得」
                            // ReturnResult resultList1 = SearchDetailCase(selectCondition);
                            ReturnResult resultList1 = new ReturnResult();
                            // 戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                            if (resultList1 != null && resultList1.getCid() != null) {
                                PetitionsResults1.add(resultList1);
                            }
                        }
                        ;
                    }
                }
                // 引数.立場相手方Flg＝1の場合、相手方の取得処理を行う
                if (position.getTraderFlg2() == Constants.NUM_1) {

                    // ⓶相手方取得
                    list2 = getSelectListInfoMapper.testTraderFlgSearch(email);

                    // 相手方取得有りの場合、下記処理を行う）
                    if (list2.size() > 0) {
                        for (int i = 0; i < list2.size(); i++) {

                            // 立場フラグ＝2（相手方）を設定する
                            positionFlg = Constants.POSITIONFLAG_TRADER;
                            // ⓶取得したCaseId
                            selectCondition.setCaseId(list2.get(i).getCaseId());
                            // ⓶取得したPetitionUserId
                            selectCondition.setPetitionUserId(list2.get(i).getPetitionUserId());
                            // 引数.立場フラグ
                            selectCondition.setPositionFlg(positionFlg);
                            // 引数.申立て番号
                            selectCondition.setCid(position.getCid());
                            // 引数.件名
                            selectCondition.setCaseTitle(position.getCaseTitle());
                            // 引数.登録日付From
                            selectCondition.setPetitionDateStart(position.getPetitionDateStart());
                            // 引数.登録日付To
                            selectCondition.setPetitionDateEnd(position.getPetitionDateEnd());
                            // 引数.ステータス
                            selectCondition.setCaseStatus(position.getCaseStatus());
                            System.out.println("selectCondition" + selectCondition);

                            // DOTO API「検索用ケース詳細取得」
                            // ReturnResult resultList2 = SearchDetailCase(selectCondition);
                            ReturnResult resultList2 = new ReturnResult();
                            // 戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                            if (resultList2 != null && resultList2.getCid() != null) {
                                PetitionsResults2.add(resultList2);
                            }
                        }
                        ;
                    }
                }
                // 引数.立場調停人Flg＝1の場合、調停人の取得処理を行う
                if (position.getMediatorFlg3() == Constants.NUM_1) {

                    // ⓷調停人取得
                    list3 = getSelectListInfoMapper.testMediatorSearch(email);

                    // 調停人取得有りの場合、下記処理を行う）
                    if (list3.size() > 0) {
                        for (int i = 0; i < list3.size(); i++) {

                            // 立場フラグ＝3（調停人）を設定する
                            positionFlg = Constants.POSITIONFLAG_MEDIATOR;
                            // ⓷取得したCaseId
                            selectCondition.setCaseId(list3.get(i).getCaseId());
                            // ⓷取得したPetitionUserId
                            selectCondition.setPetitionUserId(list3.get(i).getPetitionUserId());
                            // 引数.立場フラグ
                            selectCondition.setPositionFlg(positionFlg);
                            // 引数.申立て番号
                            selectCondition.setCid(position.getCid());
                            // 引数.件名
                            selectCondition.setCaseTitle(position.getCaseTitle());
                            // 引数.登録日付From
                            selectCondition.setPetitionDateStart(position.getPetitionDateStart());
                            // 引数.登録日付To
                            selectCondition.setPetitionDateEnd(position.getPetitionDateEnd());
                            // 引数.ステータス
                            selectCondition.setCaseStatus(position.getCaseStatus());
                            System.out.println("selectCondition" + selectCondition);

                            // DOTO API「検索用ケース詳細取得」
                            // ReturnResult resultList3 = SearchDetailCase(selectCondition);
                            ReturnResult resultList3 = new ReturnResult();
                            // 戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                            if (resultList3 != null && resultList3.getCid() != null) {
                                PetitionsResults3.add(resultList3);
                            }
                        }
                        ;
                    }
                }
            }
        }

        // 申立人２次元配列（もしくはリスト）、相手方２次元配列（もしくはリスト）、調停人２次元配列（もしくはリスト）を結合する。
        List<ReturnResult> preResult = new ArrayList<>(PetitionsResults1);
        preResult.addAll(PetitionsResults2);
        preResult.addAll(PetitionsResults3);
        // ・引数.メッセージ有無Flgによるデータ抽出結合
        // a.引数.メッセージ有Flgが1の場合、未読メッセージ件数 > 0件のデータを抽出して、結合する。
        // b.引数.メッセージ無Flgが1の場合、未読メッセージ件数 = 0件のデータを抽出して、結合する。
        // c.上記以外の場合、両方のデータとも抽出して結合する
        for (int i = preResult.size() - 1; i >= 0; i--) {
            if (position.getMessageFlag() == "1") {
                if (preResult.get(i).getMsgCount() <= 0) {
                    preResult.remove(i);
                }
            } else if (position.getMessageFlag() == "2") {
                if (preResult.get(i).getMsgCount() != 0) {
                    preResult.remove(i);
                }
            }
        }
        // ・引数.要対応有無Flgによるデータ抽出結合
        // a.引数.要対応有Flgが1の場合、要対応有無が1（要対応有り）のデータを抽出して、結合する。
        // b.引数.要対応無FlgFlgが1の場合、要対応有無が0（要対応なし）のデータを抽出して、結合する。
        // c.上記以外の場合、両方のデータとも抽出して結合する
        for (int i = preResult.size() - 1; i >= 0; i--) {
            if (position.getCorrespondenceFlag() == "1") {
                if (preResult.get(i).getCorrespondence() != "1") {
                    preResult.remove(i);
                }
            } else if (position.getCorrespondenceFlag() == "2") {
                if (preResult.get(i).getCorrespondence() != "0") {
                    preResult.remove(i);
                }
            }
        }
        // 要対応有無の降順 かつ 対応期日の昇順で結合後の２次元配列（もしくはリスト）をソートする
        if (preResult.size() > 0) {
            List<ReturnResult> finalResult = preResult.stream()
                    .sorted(Comparator.comparing(ReturnResult::getCorrespondence)
                            .reversed().thenComparing(ReturnResult::getCorrespondDate))
                    .collect(Collectors.toList());

            return (finalResult);
        } else {
            return (preResult);
        }
    }
}
