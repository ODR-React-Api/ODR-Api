package com.web.app.service;

import java.util.ArrayList;
import com.web.app.domain.MediateUser;

public interface MediationService {
    MediateUser Mediationstatus(MediateUser mediateUser);

    MediateUser MediationEmail(MediateUser mediateUser);

    ArrayList<MediateUser> MediatorIntelligence(MediateUser mediateUser);
}
