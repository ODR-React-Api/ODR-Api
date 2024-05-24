package com.web.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.web.app.domain.AnswerLogin.PetitionDataUser;
import com.web.app.domain.AnswerLogin.PetitionsData;
import com.web.app.domain.AnswerLogin.RepliesData;
import com.web.app.domain.AnswerLogin.UpdRepliesDataParameter;
import com.web.app.domain.Entity.CaseFileRelations;
import com.web.app.domain.Entity.CaseReplies;
import com.web.app.domain.Entity.Files;
import com.web.app.domain.Entity.MasterPlatforms;
import com.web.app.domain.constants.Constants;
import com.web.app.mapper.GetPetitionsDataMapper;
import com.web.app.mapper.GetRepliesDataMapper;
import com.web.app.mapper.UpdRepliesDataMapper;
import com.web.app.service.AnswerLoginService;
import com.web.app.service.UtilService;

/**
    11_回答登録画面
 * 
 * @author DUC 信召艶
 * @since 2024/04/25     
 * @version 1.0
 */
@Service
public class AnswerLoginServiceImpl implements AnswerLoginService {

    @Autowired
    private GetPetitionsDataMapper getPetitionsDataMapper;
    @Autowired
    private UpdRepliesDataMapper updRepliesDataMapper;

    @Autowired
    private UtilService utilService;
    
    @Autowired
    private GetRepliesDataMapper getRepliesDataMapper;
    
    /**
     * 申立データ取得API
     *
     * @param caseId セッション情報の案件ID
     * @param plateFormId セッション情報のプラットフォームID
     * @return 申立データ取得結果
     */
    @Override
    public List<PetitionsData> getPetitionData(String caseId, String plateFormId) {
        return getPetitionsDataMapper.getPetitionsData(caseId, plateFormId);
    }

    /**
     * API_プラットフォ情報取得
     *
     * @param plateFormId 画面のプラットフォームID
     * @return プラットフォ情報
     */
    @Override
    public PetitionDataUser getPetitionDataUser(String plateFormId) {
        // すべてのプラットフォーム情報を取得
        MasterPlatforms masterPlatforms = utilService.GetMasterPlatforms(plateFormId);
        // データ抽出
        PetitionDataUser petitionDataUser = new PetitionDataUser();
        petitionDataUser.setCounterclaimLimitDays(masterPlatforms.getCounterclaimLimitDays());
        petitionDataUser.setMediationLimitDays(masterPlatforms.getMediationLimitDays());
        petitionDataUser.setNegotiationLimitDays(masterPlatforms.getNegotiationLimitDays());
        petitionDataUser.setPhaseNegotiation(masterPlatforms.getPhase_negotiation());
        petitionDataUser.setPhaseReply(masterPlatforms.getPhase_reply());
        petitionDataUser.setUseOther(masterPlatforms.getUseOther());
        petitionDataUser.setUseProductUrl(masterPlatforms.getUseProductUrl());
        petitionDataUser.setUseTraderName(masterPlatforms.getUseTraderName());
        petitionDataUser.setUserProductId(masterPlatforms.getUserProductId());
        return petitionDataUser;
    }


    /**
     * API_反訴・回答データ新規登録/更新
     * 下書きデータ存在しない場合、下記のAPIをコールし、画面入力したデータをDBへ新規登録を行う
     * ・「反訴・回答」は、レコード新規登録（insert）で行う
     * ・「案件-添付ファイルリレーション」は、添付ファイルの数に等しいレコード新規登録(insert)で行う
     * ・「添付ファイル」は、添付ファイルの数に等しいレコード新規登録(insert)で行う
     * 下書きデータ存在する場合、下記のAPIをコールし、画面入力したデータをDBへ更新を行う
     * ・「反訴・回答」は、レコード更新（update）で行う
     * ・「案件-添付ファイルリレーション」は、添付ファイルの添削によりファイルの増減が発生しますので、レコード物理削除(delete)＋新規登録(insert)で行う
     * ・「添付ファイル」は、添付ファイルの添削によりファイルの増減が発生しますので、レコード物理削除(delete)＋新規登録(insert)で行う
     * 
     * @param updRepliesDataParameter API_反訴・回答データ新規登録/更新の引数
     * @return int DBへ登録/更新の状況
     */
    @Transactional
    @Override
    public int UpdRepliesData(UpdRepliesDataParameter updRepliesDataParameter) {
        // セッションのプラットフォームID
        String platformId = updRepliesDataParameter.getPlatformId();
        // セッション情報のCaseId
        String caseId = updRepliesDataParameter.getCaseId();
        // 添付資料
        // ファイル名
        String fileName = updRepliesDataParameter.getFileName();
        // 拡張子
        String fileExtension = updRepliesDataParameter.getFileExtension();
        // 反訴の添付資料
        // ファイル名
        String fileNameCounterClaim = updRepliesDataParameter.getFileNameCounterClaim();
        // 拡張子
        String fileExtensionCounterClaim = updRepliesDataParameter.getFileExtensionCounterClaim();
        // 反訴・回答.id 初期値:xxxx
        String caseRepliesId = "xxxx";

        // 画面上の申立番号を利用してテーブル「反訴・回答」で検索し、検索できれば更新し、検索できなければ挿入する。
        // 「反訴・回答」取得
        int caseRepliesCount = updRepliesDataMapper.getCaseRepliesCount(caseId, platformId);
        // 下書きデータ存在の判定
        if (caseRepliesCount > 0) {
            // 下書きデータ存在する場合、下記のAPIをコールし、画面入力したデータをDBへ更新を行う
            // 「反訴・回答」更新の項目を設定
            CaseReplies caseReplies = getCaseReplies(updRepliesDataParameter, false);
            // 「反訴・回答」更新
            int updCaseRepliesNum = updRepliesDataMapper.updCaseReplies(caseReplies);
            if (updCaseRepliesNum == 0) {
                return 0;
            }

            // セッション情報の削除対象ファイルid
            String delFileId = updRepliesDataParameter.getDelFileId();
            // 削除対象ファイルidの判定
            if (delFileId != null) {
                // 画面の下書きの添付ファイル削除の場合
                // 「添付ファイル」論理削除
                int updFilesNum = updRepliesDataMapper.updFiles(updRepliesDataParameter);
                if (updFilesNum == 0) {
                    return 0;
                }

                // 「案件-添付ファイルリレーション」論理削除
                int updCaseFileRelationsNum = updRepliesDataMapper.updCaseFileRelations(updRepliesDataParameter);
                if (updCaseFileRelationsNum == 0) {
                    return 0;
                }
            }

            // 添付資料ファイル名の判定
            if (fileName != null) {
                // 新規登録を実施Flg
                boolean insExeFlg = true;

                // 画面の下書きの添付ファイル未削除の場合
                if (delFileId == null) {
                    // 添付ファイル存在の判定
                    int filesCount = updRepliesDataMapper.getFilesCount(caseId, platformId, fileName, fileExtension);
                    if (filesCount > 0) {
                        // 画面の下書きの既存の添付ファイルは新規登録しない
                        insExeFlg = false;
                    }
                }

                // 新規登録を実施の判定
                if (insExeFlg) {
                    // 画面の下書きの添付ファイルは新規登録
                    // 添付資料
                    // 「添付ファイル」の新規登録の項目を設定
                    Files filesReply = getFiles(updRepliesDataParameter, false);
                    // 「添付ファイル」新規登録
                    int insFilesReplyNum = updRepliesDataMapper.insFiles(filesReply);
                    if (insFilesReplyNum == 0) {
                        return 0;
                    }

                    // 「反訴・回答.id」取得
                    caseRepliesId = updRepliesDataMapper.getCaseRepliesId(caseId, platformId);

                    // 「案件-添付ファイルリレーション」新規登録の項目を設定
                    CaseFileRelations caseFileRelationsReply = getCaseFileRelations(updRepliesDataParameter, false);
                    // 案件種類ID 反訴への回答.id
                    caseFileRelationsReply.setRelatedId(caseRepliesId);
                    // ファイルID 添付ファイル.id
                    caseFileRelationsReply.setFileId(filesReply.getId());
                    // 「案件-添付ファイルリレーション」新規登録
                    int insCaseFileRelationsReplyNum = updRepliesDataMapper
                            .insCaseFileRelations(caseFileRelationsReply);
                    if (insCaseFileRelationsReplyNum == 0) {
                        return 0;
                    }
                }
            }

            // 「反訴・回答.HaveCounterClaim」 0:反訴無し,1:反訴あり
            // 反訴の添付資料ファイル名の判定
            if (updRepliesDataParameter.getHaveCounterClaim() == 1 && fileNameCounterClaim != null) {
                // 新規登録を実施Flg
                boolean insExeFlg = true;

                // 画面の下書きの添付ファイル未削除の場合
                if (delFileId == null) {
                    // 添付ファイル存在の判定
                    int filesCount = updRepliesDataMapper.getFilesCount(caseId, platformId, fileNameCounterClaim,
                            fileExtensionCounterClaim);
                    if (filesCount > 0) {
                        // 画面の下書きの既存の添付ファイルは新規登録しない
                        insExeFlg = false;
                    }
                }

                // 新規登録を実施の判定
                if (insExeFlg) {
                    // 画面の下書きの添付ファイルは新規登録
                    // 反訴の添付資料
                    // 「添付ファイル」の新規登録の項目を設定
                    Files filesCounterClaim = getFiles(updRepliesDataParameter, true);
                    // 「添付ファイル」新規登録
                    int insFilesCounterClaimNum = updRepliesDataMapper.insFiles(filesCounterClaim);
                    if (insFilesCounterClaimNum == 0) {
                        return 0;
                    }

                    // caseRepliesId=初期値の場合
                    if(caseRepliesId.equals("xxxx")){
                        // 「反訴・回答.id」取得
                        caseRepliesId = updRepliesDataMapper.getCaseRepliesId(caseId, platformId);
                    }

                    // 「案件-添付ファイルリレーション」新規登録の項目を設定
                    CaseFileRelations caseFileRelationsCounterClaim = getCaseFileRelations(updRepliesDataParameter,
                            true);
                    // 案件種類ID 反訴への回答.id
                    caseFileRelationsCounterClaim.setRelatedId(caseRepliesId);
                    // ファイルID 添付ファイル.id
                    caseFileRelationsCounterClaim.setFileId(filesCounterClaim.getId());
                    // 「案件-添付ファイルリレーション」新規登録
                    int insCaseFileRelationsCounterClaimNum = updRepliesDataMapper
                            .insCaseFileRelations(caseFileRelationsCounterClaim);
                    if (insCaseFileRelationsCounterClaimNum == 0) {
                        return 0;
                    }
                }
            }

            return 1;
        } else {
            // 下書きデータ存在しない場合、下記のAPIをコールし、画面入力したデータをDBへ新規登録を行う
            // 「反訴・回答」の新規登録の項目を設定
            CaseReplies caseReplies = getCaseReplies(updRepliesDataParameter, true);
            // 「反訴・回答」新規登録
            int insCaseRepliesNum = updRepliesDataMapper.insCaseReplies(caseReplies);
            if (insCaseRepliesNum == 0) {
                return 0;
            }

            // 添付資料ファイル名の判定
            if (fileName != null) {
                // 添付資料
                // 「添付ファイル」の新規登録の項目を設定
                Files filesReply = getFiles(updRepliesDataParameter, false);
                // 「添付ファイル」新規登録
                int insFilesReplyNum = updRepliesDataMapper.insFiles(filesReply);
                if (insFilesReplyNum == 0) {
                    return 0;
                }

                // 「案件-添付ファイルリレーション」新規登録の項目を設定
                CaseFileRelations caseFileRelationsReply = getCaseFileRelations(updRepliesDataParameter, false);
                // 案件種類ID 反訴への回答.id
                caseFileRelationsReply.setRelatedId(caseReplies.getId());
                // ファイルID 添付ファイル.id
                caseFileRelationsReply.setFileId(filesReply.getId());
                // 「案件-添付ファイルリレーション」新規登録
                int insCaseFileRelationsReplyNum = updRepliesDataMapper.insCaseFileRelations(caseFileRelationsReply);
                if (insCaseFileRelationsReplyNum == 0) {
                    return 0;
                }
            }

            // 「反訴・回答.HaveCounterClaim」 0:反訴無し,1:反訴あり
            // 反訴の添付資料ファイル名の判定
            if (updRepliesDataParameter.getHaveCounterClaim() == 1 && fileNameCounterClaim != null) {
                // 反訴の添付資料
                // 「添付ファイル」の新規登録の項目を設定
                Files filesCounterClaim = getFiles(updRepliesDataParameter, true);
                // 「添付ファイル」新規登録
                int insFilesCounterClaimNum = updRepliesDataMapper.insFiles(filesCounterClaim);
                if (insFilesCounterClaimNum == 0) {
                    return 0;
                }

                // 「案件-添付ファイルリレーション」新規登録の項目を設定
                CaseFileRelations caseFileRelationsCounterClaim = getCaseFileRelations(updRepliesDataParameter, true);
                // 案件種類ID 反訴への回答.id
                caseFileRelationsCounterClaim.setRelatedId(caseReplies.getId());
                // ファイルID 添付ファイル.id
                caseFileRelationsCounterClaim.setFileId(filesCounterClaim.getId());
                // 「案件-添付ファイルリレーション」新規登録
                int insCaseFileRelationsCounterClaimNum = updRepliesDataMapper
                        .insCaseFileRelations(caseFileRelationsCounterClaim);
                if (insCaseFileRelationsCounterClaimNum == 0) {
                    return 0;
                }
            }

            return 1;
        }
    }

    /**
     * 「反訴・回答」の項目を設定
     * 
     * @param updRepliesDataParameter API_反訴・回答データ新規登録/更新の引数
     * @param insFlg                  新規登録/更新Flg true:「反訴・回答」新規登録 false:「反訴・回答」更新
     * @return caseReplies 「反訴・回答」の項目
     */
    public CaseReplies getCaseReplies(UpdRepliesDataParameter updRepliesDataParameter, boolean insFlg) {
        CaseReplies caseReplies = new CaseReplies();
        if (insFlg) {
            // Guid取得を利用 自動生成GIUD
            caseReplies.setId(utilService.GetGuid());
            caseReplies.setStatus(0);
            caseReplies.setDeleteFlag(Constants.DELETE_FLAG_0);
        }
        caseReplies.setPlatformId(updRepliesDataParameter.getPlatformId());
        caseReplies.setCaseId(updRepliesDataParameter.getCaseId());
        caseReplies.setReplyType(updRepliesDataParameter.getReplyType());
        caseReplies.setReplyTypeOther(updRepliesDataParameter.getReplyTypeOther());
        caseReplies.setReplyContext(updRepliesDataParameter.getReplyContext());
        caseReplies.setHaveCounterClaim(updRepliesDataParameter.getHaveCounterClaim());
        caseReplies.setCounterClaimContext(updRepliesDataParameter.getCounterClaimContext());
        caseReplies.setTraderAgent1_UserEmail(updRepliesDataParameter.getTraderAgent1_UserEmail());
        caseReplies.setTraderAgent2_UserEmail(updRepliesDataParameter.getTraderAgent2_UserEmail());
        caseReplies.setTraderAgent3_UserEmail(updRepliesDataParameter.getTraderAgent3_UserEmail());
        caseReplies.setTraderAgent4_UserEmail(updRepliesDataParameter.getTraderAgent4_UserEmail());
        caseReplies.setTraderAgent5_UserEmail(updRepliesDataParameter.getTraderAgent5_UserEmail());
        caseReplies.setLastModifiedBy(updRepliesDataParameter.getLoginUser());
        return caseReplies;
    }

    /**
     * 「添付ファイル」の新規登録の項目を設定
     * 
     * @param updRepliesDataParameter API_反訴・回答データ新規登録/更新の引数
     * @param counterClaimFlg         反訴Flg true:反訴の添付資料 false:添付資料
     * @return files 「添付ファイル」の新規登録の項目
     */
    public Files getFiles(UpdRepliesDataParameter updRepliesDataParameter, boolean counterClaimFlg) {
        Files files = new Files();
        // Guid取得を利用 自動生成GIUD
        files.setId(utilService.GetGuid());
        files.setPlatformId(updRepliesDataParameter.getPlatformId());
        files.setCaseId(updRepliesDataParameter.getCaseId());
        files.setFileBlobStorageId(null);
        if (counterClaimFlg) {
            files.setFileName(updRepliesDataParameter.getFileNameCounterClaim());
            files.setFileExtension(updRepliesDataParameter.getFileExtensionCounterClaim());
        } else {
            files.setFileName(updRepliesDataParameter.getFileName());
            files.setFileExtension(updRepliesDataParameter.getFileExtension());
        }
        // TODO
        // ＜内部ロジック生成ファイルURL＞
        files.setFileUrl(null);
        // ＜内部ロジック生成ファイルサイ＞
        files.setFileSize(null);
        files.setRegisterUserId(updRepliesDataParameter.getLoginUser());
        files.setDeleteFlag(Constants.DELETE_FLAG_0);
        files.setLastModifiedBy(updRepliesDataParameter.getLoginUser());
        return files;
    }

    /**
     * 「案件-添付ファイルリレーション」新規登録の項目を設定
     * 
     * @param updRepliesDataParameter API_反訴・回答データ新規登録/更新の引数
     * @param counterClaimFlg         反訴Flg true:反訴の添付資料 false:添付資料
     * @return caseFileRelations 「案件-添付ファイルリレーション」新規登録の項目
     */
    public CaseFileRelations getCaseFileRelations(UpdRepliesDataParameter updRepliesDataParameter,
            boolean counterClaimFlg) {
        CaseFileRelations caseFileRelations = new CaseFileRelations();
        // Guid取得を利用 自動生成GIUD
        caseFileRelations.setId(utilService.GetGuid());
        caseFileRelations.setPlatformId(updRepliesDataParameter.getPlatformId());
        caseFileRelations.setCaseId(updRepliesDataParameter.getCaseId());
        if (counterClaimFlg) {
            caseFileRelations.setRelationType(Constants.CASE_REPLIES_COUNTERCLAIM);
        } else {
            caseFileRelations.setRelationType(Constants.CASE_REPLIES_REPLY);
        }
        caseFileRelations.setDeleteFlag(Constants.DELETE_FLAG_0);
        caseFileRelations.setLastModifiedBy(updRepliesDataParameter.getLoginUser());
        return caseFileRelations;
    }

    /**
     * 反訴・回答データ取得
     *
     * @param caseId セッション情報のcaseid
     * @param PlatformId セッション情報のプラットフォームID
     * @return getRepliesList
     */    
    @Override
    public List<RepliesData> getRepliesData(String caseId, String platformId) {
        List<RepliesData> getRepliesDataList = new ArrayList<RepliesData>();
        getRepliesDataList = getRepliesDataMapper.getReplies(caseId, platformId);
        return getRepliesDataList;
    }
}
