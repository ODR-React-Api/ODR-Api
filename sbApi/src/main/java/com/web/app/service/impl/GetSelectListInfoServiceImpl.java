package com.web.app.service.impl;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.GetEmail;
import com.web.app.domain.Position;
import com.web.app.domain.ReturnResult;
import com.web.app.domain.SelectCondition;
import com.web.app.domain.TestMos;
import com.web.app.mapper.GetSelectListInfoMapper;
import com.web.app.service.GetSelectListInfoService;

@Service
public class GetSelectListInfoServiceImpl implements GetSelectListInfoService {

    @Autowired
    private GetSelectListInfoMapper getSelectListInfoMapper;

    //立場フラグ初期化「1（申立人）、2（相手方）、3（調停人）」
    Integer positionFlg = 0;
    Date petitionDateStart;
    Date petitionDateEnd;
    //API「検索用ケース詳細取得」の検索条件の引数
    SelectCondition selectCondition = new SelectCondition();
    //API「検索用ケース詳細取得」から戻ったデータの引数
    ReturnResult returnResult = new ReturnResult();

    @Override
    public List<ReturnResult> selectMosList(Position position) {
        //日期String转换为yyyyMMdd型的日期
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        try {
            petitionDateStart = formatter.parse(position.getPetitionDateStart());
            petitionDateEnd = formatter.parse(position.getPetitionDateEnd()); 
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //TBL「ユーザ情報」を取得する
        GetEmail email = getSelectListInfoMapper.testOdrUsersSearch(position.getSessionId());

        //TBL「ユーザ情報」より取得したユーザメールをキーにTBL「案件別個人情報リレーション」より申立人、相手方、調停人となるケース取得
        List<TestMos> list1 = new ArrayList<>();
        List<TestMos> list2 = new ArrayList<>();
        List<TestMos> list3 = new ArrayList<>();
        List<ReturnResult> PetitionsResults1 = new ArrayList<>();
        List<ReturnResult> PetitionsResults2 = new ArrayList<>();
        List<ReturnResult> PetitionsResults3 = new ArrayList<>();
        if (email != null) {
            //引数.立場申立人Flg=0　かつ　引数.立場相手方Flg=0　かつ　引数.立場調停人Flg=0 の場合、申立人、相手方、調停人の取得処理を全て行う。
            if (position.getPetitionsFlg1() == 0 && position.getTraderFlg2() == 0 && position.getMediatorFlg3() == 0 ) {
                //申立人の取得処理を行う。
                list1 = getSelectListInfoMapper.testPetitionsSearch(email.getEmail());
                //申立人取得有りの場合、下記処理を行う）
                if (list1.size() > 0) {
                    for( int i=0; i < list1.size(); i++ ){
                        positionFlg = 1;
                        selectCondition.setCaseId(list1.get(i).getCaseId());
                        selectCondition.setPetitionUserId(list1.get(i).getPetitionUserId());
                        selectCondition.setPositionFlg(positionFlg);
                        selectCondition.setCid(position.getCid());
                        selectCondition.setCaseTitle(position.getCaseTitle());
                        selectCondition.setPetitionDateStart(petitionDateStart);
                        selectCondition.setPetitionDateEnd(petitionDateEnd);
                        selectCondition.setCaseStatus(position.getCaseStatus());
                        System.out.println("selectCondition"+selectCondition);
                        //DOTO API「検索用ケース詳細取得」
                        ReturnResult resultList1 = new ReturnResult();
                        //戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                        if (resultList1 != null && resultList1.getCid() != null) {
                            PetitionsResults1.add(resultList1);   
                        }
                    }
                }
                //相手方の取得処理を行う。
                list2 = getSelectListInfoMapper.testTraderFlgSearch(email.getEmail());
                //相手方取得有りの場合、下記処理を行う）
                if (list2.size() > 0) {
                    for( int i=0; i<list2.size(); i++ ){
                        positionFlg = 2;
                        selectCondition.setCaseId(list2.get(i).getCaseId());
                        selectCondition.setPetitionUserId(list2.get(i).getPetitionUserId());
                        selectCondition.setPositionFlg(positionFlg);
                        selectCondition.setCid(position.getCid());
                        selectCondition.setCaseTitle(position.getCaseTitle());
                        selectCondition.setPetitionDateStart(petitionDateStart);
                        selectCondition.setPetitionDateEnd(petitionDateEnd);
                        selectCondition.setCaseStatus(position.getCaseStatus());
                        System.out.println("selectCondition"+selectCondition);
                        //DOTO API「検索用ケース詳細取得」
                        ReturnResult resultList2 = new ReturnResult();
                        //戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                        if (resultList2 !=null && resultList2.getCid() != null) {
                            PetitionsResults2.add(resultList2);                        
                        }
                    };      
                }
                //調停人の取得処理を行う。
                list3 = getSelectListInfoMapper.testMediatorSearch(email.getEmail());
                //調停人取得有りの場合、下記処理を行う）
                if (list3.size() > 0) {
                    for( int i=0; i<list3.size(); i++ ){
                        positionFlg = 3;
                        selectCondition.setCaseId(list3.get(i).getCaseId());
                        selectCondition.setPetitionUserId(list3.get(i).getPetitionUserId());
                        selectCondition.setPositionFlg(positionFlg);
                        selectCondition.setCid(position.getCid());
                        selectCondition.setCaseTitle(position.getCaseTitle());
                        selectCondition.setPetitionDateStart(petitionDateStart);
                        selectCondition.setPetitionDateEnd(petitionDateEnd);
                        selectCondition.setCaseStatus(position.getCaseStatus());
                        System.out.println("selectCondition"+selectCondition);
                        //DOTO API「検索用ケース詳細取得」
                        ReturnResult resultList3 = new ReturnResult();
                        //戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                        if (resultList3 !=null && resultList3.getCid() != null) {
                            PetitionsResults3.add(resultList3);                        
                        }
                    };      
                }
            }
            //以外の場合
            else{
                //引数.立場申立人Flg＝1の場合、申立人の取得処理を行う
                if (position.getPetitionsFlg1() == 1 ) {
                    list1 = getSelectListInfoMapper.testPetitionsSearch(email.getEmail());
                    //申立人取得有りの場合、下記処理を行う）
                    if (list1.size() > 0) {
                        for( int i=0; i<list1.size(); i++ ){
                            positionFlg = 1;
                            selectCondition.setCaseId(list1.get(i).getCaseId());
                            selectCondition.setPetitionUserId(list1.get(i).getPetitionUserId());
                            selectCondition.setPositionFlg(positionFlg);
                            selectCondition.setCid(position.getCid());
                            selectCondition.setCaseTitle(position.getCaseTitle());
                            selectCondition.setPetitionDateStart(petitionDateStart);
                            selectCondition.setPetitionDateEnd(petitionDateEnd);
                            selectCondition.setCaseStatus(position.getCaseStatus());
                            System.out.println("selectCondition"+selectCondition);
                            //DOTO API「検索用ケース詳細取得」
                            ReturnResult resultList1 = new ReturnResult();
                            //戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                            if (resultList1 != null && resultList1.getCid() != null) {
                                PetitionsResults1.add(resultList1);   
                            }
                        };
                    }
                } 
                //引数.立場相手方Flg＝1の場合、相手方の取得処理を行う
                if (position.getTraderFlg2() == 1 ) {
                    list2 = getSelectListInfoMapper.testTraderFlgSearch(email.getEmail()); 
                    //相手方取得有りの場合、下記処理を行う）
                    if (list2.size() > 0) {
                        for( int i=0; i<list2.size(); i++ ){
                            positionFlg = 2;
                            selectCondition.setCaseId(list2.get(i).getCaseId());
                            selectCondition.setPetitionUserId(list2.get(i).getPetitionUserId());
                            selectCondition.setPositionFlg(positionFlg);
                            selectCondition.setCid(position.getCid());
                            selectCondition.setCaseTitle(position.getCaseTitle());
                            selectCondition.setPetitionDateStart(petitionDateStart);
                            selectCondition.setPetitionDateEnd(petitionDateEnd);
                            selectCondition.setCaseStatus(position.getCaseStatus());
                            System.out.println("selectCondition"+selectCondition);
                            //DOTO API「検索用ケース詳細取得」
                            ReturnResult resultList2 = new ReturnResult();
                            //戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                            if (resultList2 !=null && resultList2.getCid() != null) {
                                PetitionsResults2.add(resultList2);                            
                            }
                        };      
                    } 
                } 
                //引数.立場調停人Flg＝1の場合、調停人の取得処理を行う
                if (position.getMediatorFlg3() == 1 ) {
                    list3 = getSelectListInfoMapper.testMediatorSearch(email.getEmail());
                    //調停人取得有りの場合、下記処理を行う）
                    if (list3.size() > 0) {
                        for( int i=0; i<list3.size(); i++ ){
                            positionFlg = 3;
                            selectCondition.setCaseId(list3.get(i).getCaseId());
                            selectCondition.setPetitionUserId(list3.get(i).getPetitionUserId());
                            selectCondition.setPositionFlg(positionFlg);
                            selectCondition.setCid(position.getCid());
                            selectCondition.setCaseTitle(position.getCaseTitle());
                            selectCondition.setPetitionDateStart(petitionDateStart);
                            selectCondition.setPetitionDateEnd(petitionDateEnd);
                            selectCondition.setCaseStatus(position.getCaseStatus());
                            System.out.println("selectCondition"+selectCondition);
                            //DOTO API「検索用ケース詳細取得」
                            ReturnResult resultList3 = new ReturnResult();
                            //戻ったデータで申立人２次元配列（もしくはリスト）を設定する。
                            if (resultList3 !=null && resultList3.getCid() != null) {
                                PetitionsResults3.add(resultList3);                            
                            }
                        };      
                    }      
                }    
            }        
        }

        //申立人２次元配列（もしくはリスト）、相手方２次元配列（もしくはリスト）、調停人２次元配列（もしくはリスト）を結合する。
        List<ReturnResult> preResult = new ArrayList<>(PetitionsResults1);
        preResult.addAll(PetitionsResults2);
        preResult.addAll(PetitionsResults3);
        //・引数.メッセージ有無Flgによるデータ抽出結合	
        //a.引数.メッセージ有Flgが1の場合、未読メッセージ件数 > 0件のデータを抽出して、結合する。	
        //b.引数.メッセージ無Flgが1の場合、未読メッセージ件数 = 0件のデータを抽出して、結合する。	
        //c.上記以外の場合、両方のデータとも抽出して結合する
        for( int i = preResult.size() - 1; i >= 0; i--){
            if(position.getMessageFlag() == "1" ){
                if(preResult.get(i).getMsgCount() <= 0){
                    preResult.remove(i);
                }
            }
            else if (position.getMessageFlag() == "2") {
                if(preResult.get(i).getMsgCount() != 0){
                    preResult.remove(i);
                }   
            }
        }
        //・引数.要対応有無Flgによるデータ抽出結合		
        //a.引数.要対応有Flgが1の場合、要対応有無が1（要対応有り）のデータを抽出して、結合する。		
        //b.引数.要対応無FlgFlgが1の場合、要対応有無が0（要対応なし）のデータを抽出して、結合する。		
        //c.上記以外の場合、両方のデータとも抽出して結合する		
        for( int i = preResult.size() - 1; i >= 0; i--){
            if(position.getCorrespondenceFlag() == "1" ){
                if(preResult.get(i).getCorrespondence() != "1"){
                   preResult.remove(i);
                }
            }
            else if (position.getCorrespondenceFlag() == "2") {
                if(preResult.get(i).getCorrespondence() != "0"){
                   preResult.remove(i);
                }   
            }
        }
        // 要対応有無の降順　かつ　対応期日の昇順で結合後の２次元配列（もしくはリスト）をソートする
        if (preResult.size() > 0) {
            List<ReturnResult> finalResult = preResult.stream().sorted(Comparator.comparing(ReturnResult::getCorrespondence)
            .reversed().thenComparing(ReturnResult::getCorrespondDate)).collect(Collectors.toList()); 

            return(finalResult);   
        }else{
            return(preResult);
        }

        //使用addAll方法将这三个列表合并成一个新的列表
        // List<TestMos> mergedList = new ArrayList<>(list1);
        // mergedList.addAll(list2);
        // mergedList.addAll(list3);
        //返回合并的列表
        // return(mergedList);
        // return(preResult);
    }
}
