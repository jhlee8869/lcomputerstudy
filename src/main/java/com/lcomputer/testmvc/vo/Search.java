package com.lcomputer.testmvc.vo;

public class Search extends Pagination {
	
	private String searchName;
	private String searchType;
	private String searchCategory;
	/*
	int count;       // user테이블에 등록 된 총 user 수
	int page;           // 현재 패이지번호
	int pageNum;          // userCount / page = 화면에 나타 낼 user index번호
	int startPage;     //﻿ pagination의 시작(ex,1,6,11)
	int endPage;      // ﻿pagination의 끝(ex,5,10,15)
	int lastPage;     // (userCount/화면에 표시할 갯수), pagination 마지막 번호
	int prevPage;     // pagination의 이전 목록
	int nextPage;     // pagination의 다음 목록
	public static final int pageUnit=5;  // 한번에 불러 올 pagination 수
	public static final int perPage=10;   // 한번에 불러 올 userCount 수
	*/
	public Search() {
		
	}

	public void init2() {
		/*
		pageNum = (page-1)*perPage;
		startPage =((page-1)/pageUnit)*pageUnit+1;
		lastPage = (int)Math.ceil(count / (float)perPage);
		endPage = startPage+pageUnit-1;
		endPage = endPage < lastPage ? endPage : lastPage;
		prevPage=(startPage-pageUnit);
		nextPage=(startPage+pageUnit);
		*/
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getSearchCategory() {
		return searchCategory;
	}

	public void setSearchCategory(String searchCategory) {
		this.searchCategory = searchCategory;
	}

	public void init() {
		/*
		pageNum = (page-1)*perPage;
		startPage =((page-1)/pageUnit)*pageUnit+1;
		lastPage = (int)Math.ceil(count / (float)perPage);
		endPage = startPage+pageUnit-1;
		endPage = endPage < lastPage ? endPage : lastPage;
		prevPage=(startPage-pageUnit);
		nextPage=(startPage+pageUnit);
		*/
	}
}