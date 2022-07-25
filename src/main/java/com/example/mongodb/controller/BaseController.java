package com.example.mongodb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.example.mongodb.enums.ErrorMessage;
import com.example.mongodb.enums.SuccessMessage;
import com.example.mongodb.exception.PCQException;
import com.example.mongodb.util.SearchCriteria;
import com.example.mongodb.vo.ManipulationResponse;
import com.example.mongodb.vo.PageableResponse;

/**
 * The Class BaseController.
 */
public class BaseController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String[].class, new StringArrayPropertyEditor("DoNotSeperate"));
	}

	/**
	 * Format page response.
	 *
	 * @param page the page
	 * @return the pageable response
	 */
	public PageableResponse formatPageResponse(Page<?> page) {
		return PageableResponse.builder().currentPage(page.getNumber() + 1).pageSize(page.getSize())
				.totalPages(page.getTotalPages()).totalElements(page.getTotalElements()).results(page.getContent())
				.build();
	}
	
	/**
	 * Format manipulation response.
	 *
	 * @param successMessage the success message
	 * @return the manipulation response
	 */
	public ManipulationResponse formatManipulationResponse(SuccessMessage successMessage) {
		return formatManipulationResponse(successMessage, null);
	}

	/**
	 * Format manipulation response.
	 *
	 * @param successMessage the success message
	 * @param result         the result
	 * @return the manipulation response
	 */
	public ManipulationResponse formatManipulationResponse(SuccessMessage successMessage, Object result) {
		return ManipulationResponse.builder().message(successMessage.getMsg()).code(successMessage.getCode())
				.results(result).build();
	}

	/**
	 * Format search criteria.
	 *
	 * @param filter the filter
	 * @return the list
	 */
	public List<SearchCriteria> formatSearchCriteria(String[] filter) {
		List<SearchCriteria> criterias = new ArrayList<>();
		if (null != filter) {
			Collection<SearchCriteria> collect = Arrays.asList(filter).parallelStream().map(this::validateFilterPattern)
					.collect(Collectors.toList());
			criterias.addAll(collect);
		}
		return criterias;
	}

	/**
	 * Validate filter pattern.
	 *
	 * @param filter the filter
	 * @return the search criteria
	 */
	private SearchCriteria validateFilterPattern(String filter) {
		final Pattern pattern = Pattern.compile("([\\w.]+?)(:|<|>|<=|>=|\\(\\))([\\w\\s,.:-]+?)\\|");
		Matcher m = pattern.matcher(filter + "|");
		if (m.find()) {
			return SearchCriteria.builder().key(m.group(1)).operator(m.group(2)).value(m.group(3)).build();
		} else {
			throw new PCQException(ErrorMessage.FILTER_FORMAT_INVALID, filter);
		}
	}



}
