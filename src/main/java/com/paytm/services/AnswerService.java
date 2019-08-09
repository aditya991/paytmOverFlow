package com.paytm.services;
/*
 * @author: aditya10.kumar
 * @created: 09/08/19
 */

import com.paytm.entity.Answer;
import com.paytm.entity.User;

import java.util.List;

public interface AnswerService
{
    public void updateAnswerByAnswerIdService(int id, String answer);
    public void deleteAnswerByAnswerIdService(int id);
    void saveAnswerService(Answer ans);
    List<Answer> findAllAnswerByUserService(User user);
}
