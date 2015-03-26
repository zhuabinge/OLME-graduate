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


//@Rest(rootUrl="http://10.0.2.2:8080/olme/user/",converters = GsonHttpMessageConverter.class)
@Rest(rootUrl= UrlUtil.root,converters = GsonHttpMessageConverter.class)
public interface UserApi extends RestClientErrorHandling {
    @Get("test/tuserLogin?userName={userName}&&userPw={userPw}")
    RestResult tuserLogin(String userName, String userPw);

    @Get("test/tuserRegister?userName={userName}&&userPw={userPw}")
    RestResult tuserRegister(String userName, String userPw);

    @Get("test/tuserUpdate?userId={userId}&&userSex={userSex}&&isAppLogin={isAppLogin}")
    RestResult updateTUser(Integer userId, String userSex, String isAppLogin);
}
