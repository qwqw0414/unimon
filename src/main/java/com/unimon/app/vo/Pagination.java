package com.unimon.app.vo;

import org.apache.ibatis.session.RowBounds;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Pagination {

	private int page = 1;
	private int numPerPage = 5;
	private int totalContents;
	private int pageBarSize = 5;
	
	public Pagination(int cPage , int totalContents ) {
		this.page = cPage;
		this.totalContents = totalContents;
	}
	
	public RowBounds getRowBounds() {
		return new RowBounds((this.page - 1) * this.numPerPage, this.numPerPage);
	}
	
	public String getPageBar(String btnClass) {
		
		if(btnClass == null || btnClass.length() == 0) {
			
		}

		String pageBar = "";
		
		final int totalPage = (int) Math.ceil((double) this.totalContents / this.numPerPage);
		
		final int pageStart = ((this.page - 1) / this.pageBarSize) * this.pageBarSize + 1;
		final int pageEnd = pageStart + this.pageBarSize - 1;
		
		int pageNo = pageStart;
		
		pageBar += "<ul class='pagination justify-content-center'>";
		
		// [이전] previous
		if (pageNo == 1) {
//			pageBar += "<li class='page-item disabled'>"
//					+ "<a class='page-link' href='#' tabindex='-1' aria-disabled='true'> &lt </a>"
//					+ "</li>";
		} else {
			pageBar += "<li class='page-item'>" 
					+ "<a class='page-link'> &lt </a>" + getVal(pageNo - 1)
					+ "</li>";
		}
		// [pageNo]
		while (!(pageNo > pageEnd || pageNo > totalPage)) {
			if (this.page == pageNo) {
				pageBar += "<li class='page-item active'>" 
						+ "<a class='page-link bg-dark'>" + pageNo + "</a>" + "</li>";
			} else {
				pageBar += "<li class='page-item'>" 
						+ "<a class='page-link'>" + pageNo + "</a>" + getVal(pageNo) 
						+ "</li>";
			}
			pageNo++;
		}

		// [다음] next
		if (pageNo > totalPage) {
//			pageBar += "<li class='page-item disabled'>"
//					+ "<a class='page-link' href='#' tabindex='-1' aria-disabled='true'> &gt </a>"
//					+ "</li>";
		} else {
			pageBar += "<li class='page-item'>" 
					+ "<a class='page-link'> &gt </a>" + getVal(pageNo)
					+ "</li>";
		}

		pageBar += "</ul>";
		
		return pageBar;
	}
	
	private String getVal(int page) {
		return "<input type='hidden' value='" + page + "'>";
	}
	
}
