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
//@Rest(rootUrl="http://10.0.2.2:8080/olme/course/",converters = GsonHttpMessageConverter.class)
@Rest(rootUrl = UrlUtil.root,converters = GsonHttpMessageConverter.class )
public interface CourseApi extends RestClientErrorHandling {
    @Get("tcourse/getAllTcourse?isAppLogin={isAppLogin}")
    RestResult getAllTcourse(String isAppLogin);

    @Get("vedio/getTVedioByTcourse?courseId={courseId}&&isAppLogin={isAppLogin}")
    RestResult getTVedioByTcourse(Integer courseId, String isAppLogin);   //data: List<CourseInfo>

}
