package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.web.app.domain.constants.Constants;
import com.web.app.domain.MosList.Position;
import com.web.app.domain.MosList.SelectListInfoResult;
import com.web.app.domain.MosList.SelectCondition;
import com.web.app.domain.MosList.TestMos;
import com.web.app.mapper.GetSelectListInfoMapper;
import com.web.app.service.MosListService;

/**
 * S3_申立て一覧画面
 * Service層実現類
 * MosListServiceImpl
 * 
 * @author DUC 馮淑慧
 * @since 2024/04/18
 * @version 1.0
 */
@Service
public class MosListServiceImpl implements MosListService {

    @Autowired
    private GetSelectListInfoMapper getSelectListInfoMapper;

    /**
     * 検索条件の引数によって、一覧データを取得する。
     *
     * @param position 検索サブ画面で入力の画面項目
     * @return SelectListInfoResult 一覧画面表示用のデータ
     */
    @Override
    public List<SelectListInfoResult> getSelectListInfo(Position position) {

        // 共通関数「申立人のケース取得」の戻り内容
        List<SelectListInfoResult> petitionsResult = new ArrayList<>();
        // 共通関数「相手方のケース取得」の戻り内容
        List<SelectListInfoResult> traderFlgResult = new ArrayList<>();
        // 共通関数「調停人のケース取得」の戻り内容
        List<SelectListInfoResult> mediatorResult = new ArrayList<>();

        // 1.TBL「ユーザ情報」を取得する
        String email = getSelectListInfoMapper.testOdrUsersSearch(position.getSessionId());

        // TBL「ユーザ情報」取得なしの場合、nullが返される。
        // 以外の場合、TBL「ユーザ情報」より取得したユーザメールをキーにTBL「案件別個人情報リレーション」より申立人、相手方、調停人となるケース取得
        if (email == null) {
            return null;
        }
        // 引数.立場申立人Flg=0 かつ 引数.立場相手方Flg=0 かつ 引数.立場調停人Flg=0 の場合、申立人、相手方、調停人の取得処理を全て行う。
        if (position.getPetitionsFlg1() == 0 && position.getTraderFlg2() == 0 && position.getMediatorFlg3() == 0) {

            // 共通関数「申立人のケース取得」
            petitionsResult = getPetitions(email, position);

            // 共通関数「相手方のケース取得」
            traderFlgResult = getTraderFlg(email, position);

            // 共通関数「調停人のケース取得」
            mediatorResult = getMediator(email, position);
        }
        // 以外の場合
        else {
            // 引数.立場申立人Flg＝1の場合、申立人の取得処理を行う
            if (position.getPetitionsFlg1() == Constants.NUM_1) {
                // 共通関数「申立人のケース取得」
                petitionsResult = getPetitions(email, position);
            }
            // 引数.立場相手方Flg＝1の場合、相手方の取得処理を行う
            if (position.getTraderFlg2() == Constants.NUM_1) {
                // 共通関数「相手方のケース取得」
                traderFlgResult = getTraderFlg(email, position);
            }
            // 引数.立場調停人Flg＝1の場合、調停人の取得処理を行う
            if (position.getMediatorFlg3() == Constants.NUM_1) {
                // 共通関数「調停人のケース取得」
                mediatorResult = getMediator(email, position);
            }
        }

        // 申立人２次元配列（もしくはリスト）、相手方２次元配列（もしくはリスト）、調停人２次元配列（もしくはリスト）を結合する。
        List<SelectListInfoResult> preResult = new ArrayList<>(petitionsResult);
        preResult.addAll(traderFlgResult);
        preResult.addAll(mediatorResult);

        for (int i = preResult.size() - 1; i >= 0; i--) {
            // ・引数.メッセージ有無Flgによるデータ抽出結合
            // a.引数.メッセージ有Flgが1の場合、未読メッセージ件数 > 0件のデータを抽出して、結合する。
            // b.引数.メッセージ無Flgが1の場合、未読メッセージ件数 = 0件のデータを抽出して、結合する。
            // c.上記以外の場合、両方のデータとも抽出して結合する
            if (position.getMessageFlag() == "1") {
                if (preResult.get(i).getMsgCount() <= 0) {
                    preResult.remove(i);
                }
            } else if (position.getMessageFlag() == "2") {
                if (preResult.get(i).getMsgCount() != 0) {
                    preResult.remove(i);
                }
            }
            // ・引数.要対応有無Flgによるデータ抽出結合
            // a.引数.要対応有Flgが1の場合、要対応有無が1（要対応有り）のデータを抽出して、結合する。
            // b.引数.要対応無FlgFlgが1の場合、要対応有無が0（要対応なし）のデータを抽出して、結合する。
            // c.上記以外の場合、両方のデータとも抽出して結合する
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
            List<SelectListInfoResult> finalResult = preResult.stream()
                    .sorted(Comparator.comparing(SelectListInfoResult::getCorrespondence)
                            .reversed().thenComparing(SelectListInfoResult::getCorrespondDate))
                    .collect(Collectors.toList());

            return (finalResult);
        } else {
            return (preResult);
        }
    }

    /**
     * 共通関数「申立人のケース取得」
     *
     * @param email    ユーザ情報.Email
     * @param position 検索サブ画面で入力の画面項目
     * @return SelectListInfoResult 一覧画面表示用の申立人のデータ
     */
    private List<SelectListInfoResult> getPetitions(String email, Position position) {

        // 申立人のケース内容取得List
        List<TestMos> list1 = new ArrayList<>();
        // 申立人のケース詳細内容取得List
        List<SelectListInfoResult> petitionsResults = new ArrayList<>();
        // API「検索用ケース詳細取得」の検索条件の引数
        SelectCondition selectCondition1 = new SelectCondition();

        // ⓵申立人の取得処理を行う。
        list1 = getSelectListInfoMapper.testPetitionsSearch(email);

        // 申立人取得有りの場合、下記処理を行う）
        if (list1.size() > 0) {
            for (int i = 0; i < list1.size(); i++) {

                // ⓵取得したCaseId
                selectCondition1.setCaseId(list1.get(i).getCaseId());
                // ⓵取得したPetitionUserId
                selectCondition1.setPetitionUserId(list1.get(i).getPetitionUserId());
                // 引数.立場フラグ(立場フラグ＝1（申立人）)
                selectCondition1.setPositionFlg(Constants.POSITIONFLAG_PETITION);
                // 引数.申立て番号
                selectCondition1.setCid(position.getCid());
                // 引数.件名
                selectCondition1.setCaseTitle(position.getCaseTitle());
                // 引数.登録日付From
                selectCondition1.setPetitionDateStart(position.getPetitionDateStart());
                // 引数.登録日付To
                selectCondition1.setPetitionDateEnd(position.getPetitionDateEnd());
                // 引数.ステータス
                selectCondition1.setCaseStatus(position.getCaseStatus());

                // TODO API「検索用ケース詳細取得」
                // ReturnResult resultList1 = SearchDetailCase(selectCondition);
                SelectListInfoResult resultList1 = new SelectListInfoResult();
                // 戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                if (resultList1 != null && resultList1.getCid() != null) {
                    petitionsResults.add(resultList1);
                }
            }
        }
        return petitionsResults;
    }

    /**
     * 共通関数「相手方のケース取得」
     *
     * @param email    ユーザ情報.Email
     * @param position 検索サブ画面で入力の画面項目
     * @return SelectListInfoResult 一覧画面表示用の相手方のデータ
     */
    private List<SelectListInfoResult> getTraderFlg(String email, Position position) {

        // 相手方のケース内容取得List
        List<TestMos> list2 = new ArrayList<>();
        // 相手方のケース詳細内容取得List
        List<SelectListInfoResult> traderFlgResults = new ArrayList<>();
        // API「検索用ケース詳細取得」の検索条件の引数
        SelectCondition selectCondition2 = new SelectCondition();

        // ⓶相手方の取得処理を行う。
        list2 = getSelectListInfoMapper.testTraderFlgSearch(email);

        // 相手方取得有りの場合、下記処理を行う）
        if (list2.size() > 0) {
            for (int i = 0; i < list2.size(); i++) {

                // ⓶取得したCaseId
                selectCondition2.setCaseId(list2.get(i).getCaseId());
                // ⓶取得したPetitionUserId
                selectCondition2.setPetitionUserId(list2.get(i).getPetitionUserId());
                // 引数.立場フラグ(立場フラグ＝2（相手方）)
                selectCondition2.setPositionFlg(Constants.POSITIONFLAG_TRADER);
                // 引数.申立て番号
                selectCondition2.setCid(position.getCid());
                // 引数.件名
                selectCondition2.setCaseTitle(position.getCaseTitle());
                // 引数.登録日付From
                selectCondition2.setPetitionDateStart(position.getPetitionDateStart());
                // 引数.登録日付To
                selectCondition2.setPetitionDateEnd(position.getPetitionDateEnd());
                // 引数.ステータス
                selectCondition2.setCaseStatus(position.getCaseStatus());

                // TODO API「検索用ケース詳細取得」
                // ReturnResult resultList2 = SearchDetailCase(selectCondition);
                SelectListInfoResult resultList2 = new SelectListInfoResult();
                // 戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                if (resultList2 != null && resultList2.getCid() != null) {
                    traderFlgResults.add(resultList2);
                }
            }
        }
        return traderFlgResults;
    }

    /**
     * 共通関数「調停人のケース取得」
     *
     * @param email    ユーザ情報.Email
     * @param position 検索サブ画面で入力の画面項目
     * @return SelectListInfoResult 一覧画面表示用の調停人のデータ
     */
    private List<SelectListInfoResult> getMediator(String email, Position position) {

        // 調停人のケース内容取得List
        List<TestMos> list3 = new ArrayList<>();
        // 調停人のケース詳細内容取得List
        List<SelectListInfoResult> mediatorResults = new ArrayList<>();
        // API「検索用ケース詳細取得」の検索条件の引数
        SelectCondition selectCondition3 = new SelectCondition();

        // ⓷調停人の取得処理を行う。
        list3 = getSelectListInfoMapper.testMediatorSearch(email);

        // 調停人取得有りの場合、下記処理を行う）
        if (list3.size() > 0) {
            for (int i = 0; i < list3.size(); i++) {

                // ⓷取得したCaseId
                selectCondition3.setCaseId(list3.get(i).getCaseId());
                // ⓷取得したPetitionUserId
                selectCondition3.setPetitionUserId(list3.get(i).getPetitionUserId());
                // 引数.立場フラグ(立場フラグ＝3（調停人）)
                selectCondition3.setPositionFlg(Constants.POSITIONFLAG_MEDIATOR);
                // 引数.申立て番号
                selectCondition3.setCid(position.getCid());
                // 引数.件名
                selectCondition3.setCaseTitle(position.getCaseTitle());
                // 引数.登録日付From
                selectCondition3.setPetitionDateStart(position.getPetitionDateStart());
                // 引数.登録日付To
                selectCondition3.setPetitionDateEnd(position.getPetitionDateEnd());
                // 引数.ステータス
                selectCondition3.setCaseStatus(position.getCaseStatus());

                // TODO API「検索用ケース詳細取得」
                // ReturnResult resultList3 = SearchDetailCase(selectCondition);
                SelectListInfoResult resultList3 = new SelectListInfoResult();
                // 戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                if (resultList3 != null && resultList3.getCid() != null) {
                    mediatorResults.add(resultList3);
                }
            }
        }
        return mediatorResults;
    }
}
