package com.olme.api;

import com.olme.domain.CourseInfo;
import com.olme.domain.CourseType;
import com.olme.domain.VedioInfo;

import org.androidannotations.annotations.rest.Get;
import org.androidannotations.annotations.rest.Rest;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.util.List;

/**
 * Created by wzy on 2014/8/16.
 */
@Rest(rootUrl="http://10.0.2.2:8080/olme/course/",converters = GsonHttpMessageConverter.class)
public interface CourseApi {
    @Get("type")
    List<CourseType> getCourseTypes();

    @Get("findcourses/{courseTypeId}")
    List<CourseInfo> getCourses(Integer courseTypeId);

    @Get("vedio/{chapterId}")
    List<VedioInfo> getVedios(Integer chapterId);
}
