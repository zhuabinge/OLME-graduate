package com.olme.api;

import com.olme.domain.AllAnswer;
import com.olme.domain.AllQuestion;
import com.olme.domain.MyQuestion;
import com.olme.domain.QuestionPhoto;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.List;

/**
 * Created by wzy on 2014/8/16.
 */
@Rest(rootUrl="http://10.0.2.2:8080/olme/communication/",converters = GsonHttpMessageConverter.class)
public interface CommunicationApi {

    @Get("findmyquestion/{userId}/{page}")
    List<MyQuestion> getMyQuestions(Integer userId, Integer page);

    @Get("findallquestions/{page}")
    List<AllQuestion> getAllQuestions(Integer page);

    @Get("findallanswers/{comId}/{page}")
    List<AllAnswer> getAllAnswers(Integer comId, Integer page);

    @Get("picture/{comId}")
    List<QuestionPhoto> getQuestionPhotos(Integer comId);

    @Get("comment/add/{answerId}/{replyerId}/{comId}/{content}")
    Boolean addComment(Integer answerId, Integer replyerId, Integer comId, String content);
}
