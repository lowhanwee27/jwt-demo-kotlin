package com.jwt.demo.security

import org.springframework.security.core.annotation.AuthenticationPrincipal
import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
annotation class CurrentUser