package com.puxintech.tywl.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.puxintech.tywl.util.PageRequest;

/**
 * @author yanhai
 */
public class PageRequestMethodArgumentResolver implements HandlerMethodArgumentResolver {

	public static final PageRequest DEFAULT = new PageRequest(0, 10, null, true);

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		Integer page = null;
		Integer size = null;
		String sortBy = null;
		Boolean ascending = true;

		DefaultPageRequest def = parameter.getParameterAnnotation(DefaultPageRequest.class);

		if (def != null) {
			page = def.page();
			size = def.size();
			sortBy = StringUtils.hasText(def.sortBy()) ? def.sortBy() : null;
			ascending = def.ascending();
		}

		if (StringUtils.hasText(webRequest.getParameter("page"))) {
			try {
				page = Integer.valueOf(webRequest.getParameter("page"));
			} catch (NumberFormatException e) {
			}
		}

		if (StringUtils.hasText(webRequest.getParameter("size"))) {
			try {
				size = Integer.valueOf(webRequest.getParameter("size"));
			} catch (NumberFormatException e) {
			}
		}

		String sort = webRequest.getParameter("sort");
		if (StringUtils.hasText(sort)) {
			String[] ss = sort.split(",");
			if (ss.length > 0) {
				sortBy = ss[0];
			}
			if (ss.length > 1) {
				ascending = ss[1].equals(PageRequest.ASC);
			}
		}
		return new PageRequest(page == null ? DEFAULT.getPage() : page, size == null ? DEFAULT.getSize() : size, sortBy,
				ascending);
	}

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return PageRequest.class.isAssignableFrom(methodParameter.getParameterType());
	}

}
