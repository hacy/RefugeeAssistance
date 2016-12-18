package com.crossover.hackhathon.config.security;

import java.util.Date;
import java.util.List;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import com.crossover.hackhathon.model.ADateable;
import com.google.common.collect.Lists;
/**
 * An interceptor which is used to control access to (crud) repositories.
 * 
 * @author mhacioglu
 */
@Component("repositorySecurityInterceptor")
public class RepositorySecurityInterceptor implements MethodInterceptor {

	@Autowired
	private ApplicationContext ctx;

	@Override
	@SuppressWarnings("rawtypes")
	public Object invoke(MethodInvocation method) throws Throwable {
		
		long startTime = System.currentTimeMillis();
		try {
			
			if (method.getMethod().getName().startsWith("findAll")) {
				Object result = method.proceed();
				Page page = null;
				if (result instanceof Page) {
					page = (Page) result;
					result = page.getContent();
				}
				if (result instanceof List) {
					List target = (List) result;
					List<Object> tmpList = Lists.newArrayList(target);
					target = Lists.newArrayList();
				
					if (page != null) {
						result = new PageImpl<>(target);																
					}
				}
				return result;
			} else if (method.getMethod().getName().equals("save")) {
				Object[] args = method.getArguments();
				if (args.length != 1) {
					System.out.println("Can not decide on saving permissions.");
				} else {
					Object fArg = args[0];
					if (args.length == 1) {
						Object entityObj = args[0];
						if (entityObj instanceof ADateable) {
							ADateable dateable = (ADateable) entityObj;
							if (dateable.getCreateDate() == null) {
								dateable.setCreateDate(new Date());
							}
							dateable.setUpdateDate(new Date());
						}
					}
				}
				Object result = method.proceed();
				return result;
			}  else {
				Object result = method.proceed();
				return result;
			}
		} finally {
			long endTime = System.currentTimeMillis();
			long executionTime = endTime - startTime;
		}
	}
}
