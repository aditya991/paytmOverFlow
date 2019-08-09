package com.paytm.services;
/*
 * @author: aditya10.kumar
 * @created: 09/08/19
 */

import com.paytm.entity.Answer;

public interface AnswerService
{
    public void updateAnswerByAnswerIdService(int id);
    public void deleteAnswerByAnswerIdService(int id);
    void saveAnswerService(Answer ans);
}
