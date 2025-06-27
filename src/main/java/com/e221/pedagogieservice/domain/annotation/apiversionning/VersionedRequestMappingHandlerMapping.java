package com.e221.pedagogieservice.domain.annotation.apiversionning;

import lombok.NoArgsConstructor;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;

@NoArgsConstructor
public class VersionedRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo info = this.createRequestMappingInfos(method);
        if (info != null) {
            RequestMappingInfo typeInfo = this.createRequestMappingInfos(handlerType);
            if (typeInfo != null) {
                info = typeInfo.combine(info);
            }

            E221ApiVersion apiVersion = AnnotationUtils.getAnnotation(method, E221ApiVersion.class);
            if (apiVersion == null) {
                apiVersion = AnnotationUtils.getAnnotation(handlerType, E221ApiVersion.class);
            }
            if (apiVersion != null) {
                String version = apiVersion.value().trim();

                String prefix = "/api/v" + version;
                if (apiVersion.open())
                    prefix = "/public"+ prefix;
                info = RequestMappingInfo.paths(prefix).options(getBuilderConfiguration()).build().combine(info);
            }
        }

        return info;
    }

    private RequestMappingInfo createRequestMappingInfos(AnnotatedElement element) {
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(element, RequestMapping.class);
        RequestCondition<?> condition = (element instanceof Class ?
                getCustomTypeCondition((Class<?>) element) : getCustomMethodCondition((Method) element));
        return (requestMapping != null ? createRequestMappingInfo(requestMapping, condition) : null);
    }
}
