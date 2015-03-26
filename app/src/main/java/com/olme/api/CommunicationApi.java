package com.olme.api;

import com.olme.domain.RestResult;
import com.olme.tool.UrlUtil;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.androidannotations.api.rest.RestClientErrorHandling;
import org.springframework.http.converter.json.GsonHttpMessageConverter;


/**
 * Created by wzy on 2014/8/16.
 */
//@Rest(rootUrl="http://10.0.2.2:8080/olme/communication/",converters = GsonHttpMessageConverter.class)

@Rest(rootUrl = UrlUtil.root, converters = GsonHttpMessageConverter.class)
public interface CommunicationApi extends RestClientErrorHandling {

    @Get("communication/addCommunication?userId={userId}&&questionTheme={questionTheme}&&comContent={comContent}&&isAppLogin={isAppLogin}")
    RestResult addCommunication(Integer userId, String questionTheme, String comContent, String isAppLogin);

    @Get("communication/getAllCommunication?questionId={questionId}&&type={type}&&isAppLogin={isAppLogin}")
    RestResult getAllCommunication(Integer questionId, Integer type, String isAppLogin);  //List<AllQuestion>

    @Get("communication/getUserCommunication?userId={userId}&&questionId={questionId}&&isAppLogin={isAppLogin}")
    RestResult getUserCommunication(Integer userId, Integer questionId, String isAppLogin);

    @Get("tcomment/findCommentByQuestionId?questionId={questionId}&&commentId={commentId}&&type={type}&&isAppLogin={isAppLogin}")
    RestResult findCommentByQuestionId(Integer questionId, Integer commentId, Integer type, String isAppLogin);   //List<AllAnswerResult>

    @Get("tcomment/addComment?userId={userId}&&questionId={questionId}&&comContent={comContent}&&isAppLogin={isAppLogin}")
    RestResult addComment(Integer userId, Integer questionId, String comContent, String isAppLogin);
}
