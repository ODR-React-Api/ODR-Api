package com.web.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.app.domain.CaseRelations;
import com.web.app.domain.RelationsContent;
import com.web.app.domain.Users;
import com.web.app.mapper.CaseRelationsMapper;
import com.web.app.mapper.RelationsContentMapper;
import com.web.app.service.RelationsContentService;

@Service
public class RelationsContentServiceImpl implements RelationsContentService {

  @Autowired
  private RelationsContentMapper relationsContentMapper;

  @Autowired
  private CaseRelationsMapper caseRelationsMapper;

  @Override
  public RelationsContent selectRelationsContentData(String caseId) {

    CaseRelations caseRelations = caseRelationsMapper.RelationsListDataSearch(caseId);

    Users petitionUser = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getPetitionUserInfoEmail());

    RelationsContent relationsContent = new RelationsContent();

    relationsContent.setPetitionUserName(petitionUser.getFirstName() + "　" + petitionUser.getLastName());

    relationsContent.setPetitionUserkana(petitionUser.getFirstNamekana() + "　" + petitionUser.getLastNamekana());

    relationsContent.setPetitionUsercompanyName(petitionUser.getCompanyName());

    relationsContent.setPetitionUserEmail(caseRelations.getPetitionUserInfoEmail());

    if (caseRelations.getAgent1Email() != null) {
      Users users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent1Email());

      relationsContent.setAgent1Name(users.getFirstName()+"　" +users.getLastName());

      relationsContent.setAgent1kana(users.getFirstNamekana()+"　" +users.getLastNamekana());

      relationsContent.setAgent1Email(caseRelations.getAgent1Email());

    }else{
      relationsContent.setAgent1Flag(1);
    }

    if (caseRelations.getAgent2Email() != null) {
      Users users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent2Email());

      relationsContent.setAgent2Name(users.getFirstName()+"　" +users.getLastName());

      relationsContent.setAgent2kana(users.getFirstNamekana()+"　" +users.getLastNamekana());

      relationsContent.setAgent2Email(caseRelations.getAgent2Email());

    }else{
      relationsContent.setAgent2Flag(1);
    }

    if (caseRelations.getAgent3Email() != null) {
      Users users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent3Email());

      relationsContent.setAgent3Name(users.getFirstName()+"　" +users.getLastName());

      relationsContent.setAgent3kana(users.getFirstNamekana()+"　" +users.getLastNamekana());

      relationsContent.setAgent3Email(caseRelations.getAgent3Email());

    }else{
      relationsContent.setAgent3Flag(1);
    }

    if (caseRelations.getAgent4Email() != null) {
      Users users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent4Email());

      relationsContent.setAgent4Name(users.getFirstName()+"　" +users.getLastName());

      relationsContent.setAgent4kana(users.getFirstNamekana()+"　" +users.getLastNamekana());

      relationsContent.setAgent4Email(caseRelations.getAgent4Email());

    }else{
      relationsContent.setAgent4Flag(1);
    }

    if (caseRelations.getAgent5Email() != null) {
      Users users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getAgent5Email());

      relationsContent.setAgent5Name(users.getFirstName()+"　" +users.getLastName());

      relationsContent.setAgent5kana(users.getFirstNamekana()+"　" +users.getLastNamekana());

      relationsContent.setAgent5Email(caseRelations.getAgent5Email());

    }else{
      relationsContent.setAgent5Flag(1);
    }

    Users traderUser = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getTraderUserEmail());

    relationsContent.setTraderUserName(traderUser.getFirstName() + "　" + traderUser.getLastName());

    relationsContent.setTraderUserkana(traderUser.getFirstNamekana() + "　" + traderUser.getLastNamekana());

    relationsContent.setTraderUsercompanyName(traderUser.getCompanyName());

    relationsContent.setTraderUserEmail(caseRelations.getTraderUserEmail());

    if (caseRelations.getTraderAgent1UserEmail() != null) {
      Users users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getTraderAgent1UserEmail());

      relationsContent.setTrader1Name(users.getFirstName()+"　" +users.getLastName());

      relationsContent.setTrader1kana(users.getFirstNamekana()+"　" +users.getLastNamekana());

      relationsContent.setTrader1Email(caseRelations.getTraderAgent1UserEmail());

    }else{
      relationsContent.setTrader1Flag(1);
    }

    if (caseRelations.getTraderAgent2UserEmail() != null) {
      Users users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getTraderAgent2UserEmail());

      relationsContent.setTrader2Name(users.getFirstName()+"　" +users.getLastName());

      relationsContent.setTrader2kana(users.getFirstNamekana()+"　" +users.getLastNamekana());

      relationsContent.setTrader2Email(caseRelations.getTraderAgent2UserEmail());

    }else{
      relationsContent.setTrader2Flag(1);
    }

    if (caseRelations.getTraderAgent3UserEmail() != null) {
      Users users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getTraderAgent3UserEmail());

      relationsContent.setTrader3Name(users.getFirstName()+"　" +users.getLastName());

      relationsContent.setTrader3kana(users.getFirstNamekana()+"　" +users.getLastNamekana());

      relationsContent.setTrader3Email(caseRelations.getTraderAgent3UserEmail());

    }else{
      relationsContent.setTrader3Flag(1);
    }

    if (caseRelations.getTraderAgent4UserEmail() != null) {
      Users users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getTraderAgent4UserEmail());

      relationsContent.setTrader4Name(users.getFirstName()+"　" +users.getLastName());

      relationsContent.setTrader4kana(users.getFirstNamekana()+"　" +users.getLastNamekana());

      relationsContent.setTrader4Email(caseRelations.getTraderAgent4UserEmail());

    }else{
      relationsContent.setTrader4Flag(1);
    }

    if (caseRelations.getTraderAgent5UserEmail() != null) {
      Users users = relationsContentMapper.RelationsContentListDataSearch(caseRelations.getTraderAgent5UserEmail());

      relationsContent.setTrader5Name(users.getFirstName()+"　" +users.getLastName());

      relationsContent.setTrader5kana(users.getFirstNamekana()+"　" +users.getLastNamekana());

      relationsContent.setTrader5Email(caseRelations.getTraderAgent5UserEmail());

    }else{
      relationsContent.setTrader5Flag(1);
    }
    return relationsContent;
  }

}